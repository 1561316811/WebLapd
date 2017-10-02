package com.cyl.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.sql.DBService;
import com.cyl.work.ServerOrder;
import com.cyl.work.ServerOrderService;

public class UserService {

	public static void checkLogIn(String idUser, String password) {
		if (idUser != null && password != null) {
			Connection conn = DBService.getConn();
			String sql = "select * from user where idUser = '" + idUser + "' and password = '" + password + "'";
			// System.out.println(sql);
			Statement stmt = DBService.getStmt(conn);
			ResultSet rs = DBService.getRs(stmt, sql);
			try {
				if (!rs.next()) {
					throw new UserInformationErrorException("用户名或密码错误");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBService.close(rs);
				DBService.close(stmt);
				DBService.close(conn);
			}
		} else {
			throw new UserInformationErrorException("用户名或密码错误");
		}
	}

	public static boolean isUserEixst(String idUser) {

		String sql = "select * from user where idUser = '" + idUser + "'";
		return DBService.isValueExist(sql);

	}

	public static void add(User user) {
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
			String sql = "insert into user(idUser, password, email, question, answer, addTime) values (?, ?, ?, ?, ?, now())";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, user.getIdUser());
			preStmt.setString(2, user.getPassword());
			preStmt.setString(3, user.getEmail());
			preStmt.setString(4, user.getQuestion());
			preStmt.setString(5, user.getAnswer());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(preStmt);
			DBService.close(conn);
		}
	}

	public static User getByID(String idUser) {
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
		User u = null;
		Connection conn = DBService.getConn();
		String sql = "select * from user where idUser = '" + idUser + "'";
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			if (!rs.next()) {
				throw new UserNotFoundException("用户名不存在");
			} else {
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

	public static void update(String idUser, User user) throws SQLException {
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
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
		String sql = "update user set id = ? , birthday = ? , telPhone = ? ,"
				+ " email = ? , address = ? where idUser = '" + idUser + "'";

		preStmt = DBService.getPreStmt(conn, sql);
		preStmt.setInt(1, user.getId());
		preStmt.setDate(2, user.getBirthday());
		preStmt.setString(3, user.getTelPhone());
		preStmt.setString(4, user.getEmail());
		preStmt.setString(5, user.getAddress());
		preStmt.executeUpdate();
		conn.setAutoCommit(true);

		DBService.close(preStmt);
		DBService.close(conn);
	}

	public static List<User> getLimitData(int start, int num) {

		List<User> list = new ArrayList<User>();

		String sql = "select * from user limit " + start + ", " + num;

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);

		// `idUser` VARCHAR(45) NOT NULL COMMENT '员工编号',
		// `password` VARCHAR(45) NOT NULL COMMENT '员工密码',
		// `id` INT NULL COMMENT '员工的工作id',
		// `birthday` DATE NULL COMMENT '出生日期',
		// `telPhone` VARCHAR(15) NULL COMMENT '电话号码',
		// `email` VARCHAR(20) NULL COMMENT '电子邮件',
		// `address` VARCHAR(200) NULL COMMENT '地址',
		// `imagePath` VARCHAR(255) NULL COMMENT '头像图片路径',
		// `question` VARCHAR(20) NOT NULL COMMENT '密保问题',
		// `answer` VARCHAR(100) NULL COMMENT '密保答案',
		// `workStatus` VARCHAR(20) NULL,
		// `addTime` DATETIME NULL,

		try {
			while (rs.next()) {
				User c = new User(rs.getString("idUser"), rs.getString("password"), rs.getInt("id"),
						rs.getDate("birthday"), rs.getString("telPhone"), rs.getString("email"),
						rs.getString("address"), rs.getString("imagePath"), rs.getString("question"),
						rs.getString("answer"), rs.getDate("addTime"), rs.getString("workStatus"));

				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}

		return list;
	}

	public static List<User> getUserId() {
		List<User> list = new ArrayList<User>();

		String sql = "select id from user ";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);

		// `idUser` VARCHAR(45) NOT NULL COMMENT '员工编号',
		// `password` VARCHAR(45) NOT NULL COMMENT '员工密码',
		// `id` INT NULL COMMENT '员工的工作id',
		// `birthday` DATE NULL COMMENT '出生日期',
		// `telPhone` VARCHAR(15) NULL COMMENT '电话号码',
		// `email` VARCHAR(20) NULL COMMENT '电子邮件',
		// `address` VARCHAR(200) NULL COMMENT '地址',
		// `imagePath` VARCHAR(255) NULL COMMENT '头像图片路径',
		// `question` VARCHAR(20) NOT NULL COMMENT '密保问题',
		// `answer` VARCHAR(100) NULL COMMENT '密保答案',
		// `workStatus` VARCHAR(20) NULL,
		// `addTime` DATETIME NULL,

		try {
			while (rs.next()) {
				User c = new User();
				c.setId(rs.getInt(1));
				list.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
		return list;
	}

	public static int getNum() {

		String sql = "select count(*) from user";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);

		int num = 0;

		try {
			rs.next();
			num = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
		return num;
	}

	public static void del(User cg) {

		String sql = "delete from user where idUser = '" + cg.getIdUser() + "'";
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

	public static String getUserNameById(int id) {
		String sql = "select idUser from user where id =  " + id;

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);

		String idUser = "";

		try {
			rs.next();
			idUser = rs.getString(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
		return idUser;
	}

	public static int getIdByWorkRank(String idUser) {
		String sql = "select id from workrank order by rankNum";
//System.out.println(sql);
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		
		int id = 0;
		try {
			while (rs.next()) {
				id = rs.getInt(1);
			
System.out.println( UserService.class.getName() +  " id :" + id );
				if(id == 0)  //如果id为0，那么直接跳过
					continue;
				List<ServerOrder> list = ServerOrderService.getOrderByIdUser2(new User(UserService.getUserNameById(id)));
				boolean isSuit = true;
				for (int i = 0; i < list.size(); i++) {
					if(list.get(i).getId() == id){
						isSuit = false;
						break;
					}
				}
				if(isSuit){
					break;
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}

		return id;
	}

	public static int getIdByUserName(String idUser) {

		String sql = "select id from user where idUser =  " + idUser;

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);

		int id = 0;

		try {
			rs.next();
			id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}

		return id;
	}

}
