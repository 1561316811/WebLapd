package com.cyl.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import com.cyl.basic.PayPathService;
import com.cyl.basic.WorkStatusService;
import com.cyl.sql.DBService;

/**
 * 对于用户的业务类，情况比较特殊， 不能当其将工具类的使用 ， 此单例模式初始化时并不会将其数据库的用户信息加载到内存中。
 * 对于用户的登录，登录完成后进行系统上班签到，上班签到完成，系统将其信息载入内存 用户下班需要进行下班签到，下班签到系统会将其信息从内存中删除。
 * 该类同样采取的单例模式。 我们用户注销的时候我们将其清除。
 * 
 * @author 0001
 *
 */
public class UserService {
	Set s ;
	/**
	 * 该队列用于用户的登入信息保存
	 */
	private static LinkedList<User> listLogIn = new LinkedList<User>();
	/**
	 * 用户签到信息表
	 */
	private static LinkedList<User> listSignIn = new LinkedList<User>();
	private static UserService instance = null;

	private UserService() {

		/*
		 * String sql = "select * from user"; Connection conn =
		 * DBService.getConn(); Statement stmt = DBService.getStmt(conn);
		 * ResultSet rs = DBService.getRs(stmt, sql); try { while (rs.next()) {
		 * User u = new User(rs.getString("idUser"), rs.getString("password"),
		 * rs.getInt("id"), rs.getDate("birthday"), rs.getString("telPhone"),
		 * rs.getString("email"), rs.getString("address"),
		 * rs.getString("imagePath"), rs.getString("question"),
		 * rs.getString("answer"), rs.getDate("addTime"),
		 * rs.getString("workStatus")); list.add(u); } } catch (SQLException e)
		 * { e.printStackTrace(); } finally { DBService.close(rs);
		 * DBService.close(stmt); DBService.close(conn); }
		 */

	}

	/**
	 * 获取实例
	 * 
	 * @return 该实例
	 */
	public static UserService getInstance() {
		if (instance == null) {
			synchronized (PayPathService.class) {
				if (instance == null) {
					instance = new UserService();
				}
			}
		}
		return instance;
	}

	/**
	 * 检查登入账号和密码是否符合 如果账户密码符合，则抛出 UserInformationErrorException
	 * 
	 * @param idUser
	 *            用户名
	 * @param password
	 *            密码
	 * @throws UserInformationErrorException
	 */
	public boolean checkLogIn(String idUser, String password) {
		// 不存在则抛出 UserInformationErrorException 异常
		User ut = getUserFromSql(idUser); // 获取用户名的列表
//System.out.println(ut.getIdUser() + " || " + ut.getPassword());
		if (ut != null) {
			if (ut.getPassword().equals(password)) {
				return true;
			}
		} else {
			throw new UserInformationErrorException("用户名或密码错误");
		}
		return false;
	}

	/**
	 * 用户登入，将其数据载入列表中
	 * 
	 * @param user
	 * @return 是否登录成功，成功返回true，失败返回false
	 */
	public boolean logIn(User user) {
		boolean isSuc = false;
		isSuc = checkLogIn(user.getIdUser(), user.getPassword());
		if (isSuc) // 信息验证属实，载入登入列表
			if(!listLogIn.contains(user))  //不包含那么添加
				listLogIn.add(user);
		return isSuc;
	}

	/**
	 * 用户注销，用户信息将从队列中删除
	 * 
	 * @param user
	 *            用户信息
	 * @return 成功返回true
	 */
	public boolean logOut(User user) {
		return listLogIn.remove(user);
	}

	/**
	 * 员工上班签到，
	 *  对于传入参数user首先检查是否再登入列表中，
	 *   如果再那么加入签到表中。
	 * 更新数据库中员工的状态信息
	 * 
	 * @param user
	 *            签到用户
	 * @return 是否签到成功
	 */
	public boolean signIn(User user) {

		if (listLogIn.contains(user)) {
			User u = new User(user.getIdUser());
			
			u.setWorkStatus(
					WorkStatusService.getInstance()
					.getWorkStatusByNum(2).getWorkStatus());
			updateBasic(user);
			
			user.setWorkStatus(u.getWorkStatus());
			if(! listSignIn.contains(user))
				listSignIn.add(user);
			
			return true;
		} else {
			return false;
		}

	}

	/**
	 * 该方法先从队列中查找，如果不存在，再从数据库中查找
	 * 
	 * @param idUser
	 * @return 存在则返回 true ，否则返回false
	 */
	public boolean isUserExist(String idUser) {

		for (User user : listLogIn) { // 首先内存队列检测
			if (user.getIdUser().equals(idUser))
				return true;
		}

		if (getUserFromSql(idUser) != null) // 检查数据库中是否存在
			return true;
		return false;

	}

	/**
	 * 根据用户名从数据库中获取用户信息 不更新到内存中
	 * 
	 * @param idUser
	 *            用户名
	 * @return 返回一个用户对象
	 */
	private User getUserFromSql(String idUser) {
		User u = null;

		String sql = "select * from user where idUser = '" + idUser + "'";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				u = new User(rs.getString("idUser"), rs.getString("password"), rs.getInt("id"), rs.getDate("birthday"),
						rs.getString("telPhone"), rs.getString("email"), rs.getString("address"),
						rs.getString("imagePath"), rs.getString("question"), rs.getString("answer"),
						rs.getDate("addTime"), rs.getString("workStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}

		return u;
	}

	/**
	 * 根据工作牌号从数据库中获取用户信息 不更新到内存中
	 * 
	 * @param id
	 *            工作牌号
	 * @return 返回一个用户对象
	 */
	private User getUserFromSql(int id) {
		User u = null;

		String sql = "select * from user where id = '" + id + "'";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				u = new User(rs.getString("idUser"), rs.getString("password"), rs.getInt("id"), rs.getDate("birthday"),
						rs.getString("telPhone"), rs.getString("email"), rs.getString("address"),
						rs.getString("imagePath"), rs.getString("question"), rs.getString("answer"),
						rs.getDate("addTime"), rs.getString("workStatus"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
		return u;
	}

	/**
	 * 添加用户到数据库中
	 * 
	 * @param user
	 *            用户信息
	 */
	public void add(User user) {

		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			// `idUser` VARCHAR(45) NOT NULL COMMENT '员工编号',
			// `password` VARCHAR(45) NOT NULL COMMENT '员工密码',
			// `id` INT NULL COMMENT '员工的工作id',
			// `birthdate` DATE NULL COMMENT '出生日期',
			// `telNumber` VARCHAR(15) NULL COMMENT '电话号码',
			// `email` VARCHAR(20) NULL COMMENT '电子邮件',
			// `address` VARCHAR(200) NULL COMMENT '地址',
			// `imagePath` VARCHAR(255) NULL COMMENT '头像图片路径',
			// `question` VARCHAR(20) NOT NULL COMMENT '密保问题',
			// `answer` VARCHAR(100) NULL COMMENT '密保答案',
			// `workStatus` VARCHAR(20) NULL,
			// `addTime` DATETIME NULL,
			String sql = "insert into user(idUser, password, email, question, answer, addTime) values (?, ?, ?, ?, ?, ?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, user.getIdUser());
			preStmt.setString(2, user.getPassword());
			preStmt.setString(3, user.getEmail());
			preStmt.setString(4, user.getQuestion());
			preStmt.setString(5, user.getAnswer());
			preStmt.setDate(6, user.getAddTime());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(preStmt);
			DBService.close(conn);
		}

	}
	
	/**
	 * 根据用户名获取用户对象
	 * 首先从listSingIn列表中获取
	 * 再从listLogIn列表中获取
	 * 最后从sql中获取
	 * @param idUser
	 * @return
	 */
	public User getUserByIdUser(String idUser){
		User u = new User(idUser);
		
		for (User user : listSignIn) {
			if(user.getIdUser().equals(idUser)){
				return user;
			}
		}
		
		for (User user : listLogIn) {
			if(user.getIdUser().equals(idUser)){
				return user;
			}
		}
		
		return getUserFromSql(idUser);
	}

	/**
	 * 根据用户工作ID获取该用户的资料 
	 * （NOTICE）注意该用户必须添加到了内存之中
	 * 
	 * @param id
	 *            用户名
	 * @return 返回该用户对象
	 */
	public User getUserByID(int id) {

		for (User user : listLogIn) {
			if (user.getId() == id)
				return user;
		}
		return null;
	}

	private String idUserTemp = null;

	/**
	 * 此方法辅助user表中更新数据
	 * 
	 * @param idOrder
	 *            该元祖的唯一标识id
	 * @param param
	 *            该元祖某一列需要修改的名称
	 * @param value
	 *            修改之后的值
	 * @return
	 */
	private String getUpdateSql(String param, String value) {
		return "update user set " + param + " = '" + value + "' where idUser = '" + idUserTemp + "'";
	}

	/**
	 * 更新用户名
	 * 
	 * @param idUser
	 *            user中只是包含用户民的账号
	 * @return
	 */
	public boolean updateIdUser(String idUser) {

		boolean updateSuss = true;
		String sql = null;

		// `idUser` VARCHAR(45) NOT NULL COMMENT '员工账户',
		if (idUser != null) {
			sql = getUpdateSql("idUser", idUser);

			Connection conn = DBService.getConn();
			Statement stmt = DBService.getStmt(conn);
			try {
				stmt.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
				updateSuss = false;
			} finally {
				DBService.close(stmt);
				DBService.close(conn);
			}
		}
		return updateSuss;
	}

	/**
	 * 更新User表中用户数据，
	 * 用户名为空，更新失败
	 * @param user
	 *            需要更新的用户信息
	 * @throws SQLException
	 */
	public boolean updateBasic(User user) {

		if(user.getIdUser() == null)  //用户名为空，更新失败
			return false;
		
		boolean updateSuc = true;
		List<String> listSql = new ArrayList<String>();
		idUserTemp = user.getIdUser();

		// `password` VARCHAR(45) NOT NULL COMMENT '员工密码',
		if (user.getPassword() != null)
			listSql.add(getUpdateSql("password", user.getPassword()));

		// `id` INT NULL COMMENT '员工的工作id',
		if (user.getId() != null)
			listSql.add(getUpdateSql("id", user.getId() + ""));

		// `birthday` DATE NULL COMMENT '出生日期',
		if (user.getBirthday() != null)
			listSql.add(getUpdateSql("birthday", user.getBirthday() + ""));

		// `telNumber` VARCHAR(15) NULL COMMENT '电话号码',
		if (user.getTelPhone() != null)
			listSql.add(getUpdateSql("telPhone", user.getTelPhone() + ""));

		// `email` VARCHAR(20) NULL COMMENT '电子邮件',
		if (user.getEmail() != null)
			listSql.add(getUpdateSql("email", user.getEmail() + ""));

		// `address` VARCHAR(200) NULL COMMENT '地址',
		if (user.getAddress() != null)
			listSql.add(getUpdateSql("address", user.getAddress() + ""));

		// `imagePath` VARCHAR(255) NULL COMMENT '头像图片路径',
		if (user.getImagePath() != null)
			listSql.add(getUpdateSql("imagePath", user.getImagePath() + ""));

		// `question` VARCHAR(20) NOT NULL COMMENT '密保问题',
		if (user.getQuestion() != null)
			listSql.add(getUpdateSql("question", user.getQuestion() + ""));

		// `answer` VARCHAR(100) NULL COMMENT '密保答案',
		if (user.getAnswer() != null)
			listSql.add(getUpdateSql("answer", user.getAnswer() + ""));

		// `workStatus` VARCHAR(20) NULL,
		if (user.getWorkStatus() != null)
			listSql.add(getUpdateSql("workStatus", user.getWorkStatus() + ""));

		// `addTime` DATETIME NULL,
		if (user.getAddTime() != null)
			listSql.add(getUpdateSql("addTime", user.getAddTime() + ""));

		if (!listSql.isEmpty()) {

			Connection conn = DBService.getConn();
			Statement stmt = DBService.getStmt(conn);
			try {
				conn.setAutoCommit(false);
				for (String sql : listSql) {
					stmt.addBatch(sql);
				}
				stmt.executeBatch();
				conn.commit();
			} catch (SQLException e) {
				e.printStackTrace();
				updateSuc = false;
			} finally {
				DBService.close(stmt);
				DBService.close(conn);
			}
			
		}
		return updateSuc;
	}

	/**
	 * 获取在工作状态中的员工指定区间的员工列表
	 * @param start 从1开始的
	 * @param num 个数
	 * @return
	 */
	public List<User> getLimitData(int start, int num) {

		start--;
		return listSignIn.subList(start, start + num);

	}

	/**
	 * 获取所有正在工作员工的所有的id
	 * 
	 * @return 返回员工的id 列表
	 * 
	 */
	public List<Integer> getUserSignInId() {
		List<Integer> listId = new ArrayList<Integer>();
		for (User u : listSignIn) {
			listId.add(u.getId());
		}
		return listId;
	}

	/**
	 * 获取正在工作的员工的数量
	 * @return
	 */
	public int getNum() {
		return listSignIn.size();
	}

	/**
	 * 删除指定员工的资料
	 * 这属于系统级别的操作，管理员才具有的权限
	 * @param idUser 员工的账户
	 */
	public void del(String idUser) {

		String sql = "delete from user where idUser = '" + idUser + "'";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(stmt);
			DBService.close(conn);
		}
	}
	
	/**
	 * 该分类针对已经签到上班的人，即在listSignIn表中
	 * @return 返回再该状态下的员工的列表
	 */
	public List<User> classifyUserByStatus(int workStatus){
		
		String ws = WorkStatusService.getInstance()
				.getWorkStatusByNum(workStatus).getWorkStatus();
		List<User> listStatusUser = new ArrayList<User>();
		if(ws != null){
			for (User user : listSignIn) {
				if(user.getWorkStatus().equals(ws))
					listStatusUser.add(user);
			}
		}
		return listStatusUser;
	}

}
