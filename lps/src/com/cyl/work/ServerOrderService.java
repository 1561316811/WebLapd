package com.cyl.work;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.cyl.basic.OrderStatusService;
import com.cyl.basic.PayPathService;
import com.cyl.sql.DBService;
import com.cyl.user.User;
import com.cyl.util.SimpleDate;

/**
 * 通过getInstance()获取的实例已经将本天的订单导入内存，请注意，只是本天的。
 * 如果要查找其他天的，需要调用先获取实例，再调用getOrderByDate()方法
 * 
 * @author 0001
 *
 */
public class ServerOrderService {

	/**
	 * 今日订单列表
	 */
	private static ArrayList<ServerOrder> listToday = new ArrayList<ServerOrder>();
	private static ServerOrderService instance = null;

	/**
	 * 初始化时先获取数据 这样只需要连接一次数据库 获取的订单默认为当天的订单
	 */
	private ServerOrderService() {

		String sql = "select * from serverorder  where " + "addTime >= '" + SimpleDate.getTodayDateDay() + "'";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				ServerOrder c = new ServerOrder(rs.getString("idUser"), rs.getInt("id"), rs.getString("idRoom"),
						rs.getString("idOrder"), rs.getString("status"), rs.getTimestamp("addTime"),
						rs.getTimestamp("endTime"), rs.getInt("pay"), rs.getInt("realPay"), rs.getTimestamp("payTime"),
						rs.getString("orderRemark"), rs.getString("payPath"), rs.getString("pledgeName"),
						rs.getInt("pledgePrice"), rs.getTimestamp("addTime"), rs.getString("clockcatagory"));
				listToday.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}

	}

	/**
	 * 获取实例
	 * 
	 * @return 该实例
	 */
	public static ServerOrderService getInstance() {
		if (instance == null) {
			synchronized (PayPathService.class) {
				if (instance == null) {
					instance = new ServerOrderService();
				}
			}
		}
		return instance;
	}

	/**
	 * 简单载入数据库，同步listToday，不进行数据的加工
	 * <li>需要载入的数据为：idUser, id, idRoom, idOrder, status, clockcatagory, addTime
	 * 
	 * @param item
	 * @return
	 */
	public boolean load(ServerOrder item) {

		if (listToday.contains(item)) {
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;

		try {
			conn.setAutoCommit(false);
			// `idUser` VARCHAR(45) NOT NULL COMMENT '员工编号',
			// `id` INT NOT NULL COMMENT '员工工作编号',
			// `idRoom` VARCHAR(10) NOT NULL COMMENT '所在房间编号',
			// `idOrder` VARCHAR(45) NOT NULL COMMENT '订单编号',
			// `status` INT NOT NULL DEFAULT 0 COMMENT '订单状态',
			// `startTime` DATETIME NULL COMMENT '此服务单开始时间',
			// `endTime` DATETIME NULL COMMENT '此服务单结束时间',
			// `pay` INT NULL COMMENT '应付金额',
			// `realPay` INT NULL COMMENT '实付金额',
			// `payTime` DATETIME NULL,
			// `orderRemark` VARCHAR(255) NULL COMMENT '订单描述',
			// `payPath` VARCHAR(10) NULL COMMENT '支付途径',
			// `pledgeName` VARCHAR(20) NULL,
			// `pledgePrice` INT NULL,
			String sql = "insert into serverorder " + "(idUser, id, idRoom, idOrder, status, clockcatagory, addTime) "
					+ "values (?, ?, ?, ?, ?,?,?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, item.getIdUser());
			preStmt.setInt(2, item.getId());
			preStmt.setString(3, item.getIdRoom());
			preStmt.setString(4, item.getIdOrder());
			preStmt.setString(5, item.getStatus());
			preStmt.setString(6, item.getClockCatagory());
			preStmt.setTimestamp(7, item.getAddTime());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(preStmt);
			DBService.close(conn);
		}
		listToday.add(item);
		return true;
	}

	static int number = 1;
	static DecimalFormat df = new DecimalFormat("000");
	static DecimalFormat dfRoom = new DecimalFormat("0000");
	static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式

	/**
	 * 订单编号为：开头4位房间号码，3位员工号，日期14位，订单号3位 ，总共24位
	 * 
	 * @param id
	 *            员工工作id
	 * @param idRoom
	 *            房间所在的id
	 * @return 返回一个新创建id
	 */
	public String createId(int id, String idRoom) {
		String orderId = null;

		orderId = dfRoom.format(Integer.parseInt(idRoom)) + df.format(id) + sdf.format(new Date())
				+ df.format((number++));

		return orderId;
	}

	public List<ServerOrder> getLimitData(int start, int num) {

		start--;
		return listToday.subList(start, start + num);

	}

	public int getNum() {

		return listToday.size();

	}

	public void del(ServerOrder item) {

		String sql = "delete from serverorder where idOrder = '" + item.getIdOrder() + "'";
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
		listToday.remove(item);

	}

	/**
	 * 如果listN 为空，那么对listToday系统默认进行分类 不为空，那么对lisN进行按用户名提取列表
	 * 
	 * @param idUser
	 * @param listN
	 * @return
	 * @see #getUserOrderByStatus(User, int, List)
	 */

	public List<ServerOrder> getUserOrderByIdUser(String idUser, List<ServerOrder> listN) {

		List<ServerOrder> listL = null;
		if (listN == null) {
			listL = listToday;
		} else {
			listL = listN;
		}

		/**
		 * 使用LinkedList方便修改
		 */
		List<ServerOrder> listC = new LinkedList<ServerOrder>();
		for (ServerOrder so : listL) {
			// System.out.println(so);
			if (so.getIdUser().equals(idUser)) {
				listC.add(so);
			}
		}
		return listC;

	}

	/**
	 * 根据对应的User（根据订单的编号直接定位）和订单的类型获取相应的订单列表
	 * 
	 * {@link com.cyl.basic.OrderStatusService}
	 * @param user
	 *            使用者 ，如果user == null 成立，那么不按照user进行分类
	 * @param indexOrderStatus
	 * 对于该参数，可以参考：
	 * {@link OrderStatusService #getOrderStatus(int)}
	 *            如果参数为0那么将全部order，不对种类进行分类
	 * @param listN
	 * 	
	 *            如果list为null那么默认对本天的订单进行按要求分类， 否则对list进行按要求分类
	 * @return 返回一个新的list，没有对原来的list数据进行操作
	 */
	public List<ServerOrder> getUserOrderByStatus(User user, int indexOrderStatus, List<ServerOrder> listN) {
		List<ServerOrder> listT = null;
		List<ServerOrder> l = new ArrayList<ServerOrder>();

		if (listN == null) {
			// 传入参数listN 为空条件成立，对今日订单进行分类
			if (user == null) {
				// 传入参数user 为空条件成立，对今日订单按照订单的种类进行分类
				listT = listToday;
			} else {
				// 按用户需求进行
				// System.out.println(user.getIdUser());
				listT = getUserOrderByIdUser(user.getIdUser(), null);
				// System.out.println(listT.size());
			}
		} else {
			// 传入参数listN 为空条件成立，对今日订单进行分类
			if (user == null) {
				// 传入参数user 为空条件成立，对今日订单按照订单的种类进行分类
				listT = listN;
			} else {
				// 按用户需求进行
				listT = getUserOrderByIdUser(user.getIdUser(), listN);
			}

		}

		if (indexOrderStatus == 0)
			// 条件成立，那么对订单不进行分类过滤，直接返回listC
			return listT;
		// System.out.println("listT.size() : " + listT.size());
		for (ServerOrder so : listT) {
			// System.out.println(so.getStatus());
			// System.out.println(OrderStatusService.getInstance().getOrderStatus(indexOrderStatus));
			if (so.getStatus().equals(OrderStatusService.getInstance().getOrderStatus(indexOrderStatus))) {
				l.add(so);
			}
		}
		return l;
	}

	/**
	 * 以订单的创建时间为标准，对订单创建时间天数进行分类查找
	 * 
	 * @param year
	 *            年份
	 * @param month
	 *            月份从1开始
	 * @param day
	 *            一月中的某一天
	 * @return
	 */
	public List<ServerOrder> getOrderByDate(int year, int month, int day) {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getDate(year, month, day) + "' "
				+ "and " + "addTime < '" + SimpleDate.getDate(year, month, day + 1) + "' ";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				ServerOrder c = new ServerOrder(rs.getString("idUser"), rs.getInt("id"), rs.getString("idRoom"),
						rs.getString("idOrder"), rs.getString("status"), rs.getTimestamp("addTime"),
						rs.getTimestamp("endTime"), rs.getInt("pay"), rs.getInt("realPay"), rs.getTimestamp("payTime"),
						rs.getString("orderRemark"), rs.getString("payPath"), rs.getString("pledgeName"),
						rs.getInt("pledgePrice"), rs.getTimestamp("addTime"), rs.getString("clockcatagory"));
				listC.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
		return listC;
	}

	/**
	 * 获取今日订单的列表
	 * 
	 * @return
	 */
	public List<ServerOrder> getTodayOrder() {

		return listToday;

	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static Timestamp getCurTimestamp() {

		return new Timestamp(System.currentTimeMillis());

	}

	private String idTemp = null;

	/**
	 * 此方法辅助serverorder表中更新数据
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
		return "update serverorder set " + param + " = '" + value + "' where idOrder = '" + idTemp + "'";
	}

	/**
	 * 更新
	 * 
	 * @param so
	 */
	public void updateOrder(ServerOrder so) {
		List<String> listSql = new ArrayList<String>();
		idTemp = so.getIdOrder();
		// `idUser` VARCHAR(45) NOT NULL COMMENT '员工编号',
		if (so.getIdUser() != null) {
			// System.out.println("I come idUser");
			listSql.add(getUpdateSql("idUser", so.getIdOrder()));
		}
		// `id` INT NOT NULL COMMENT '员工工作编号',
		if (so.getId() != null)
			listSql.add(getUpdateSql("id", so.getId() + ""));
		// `idRoom` VARCHAR(10) NOT NULL COMMENT '所在房间编号',
		if (so.getIdRoom() != null)
			listSql.add(getUpdateSql("idRoom", so.getIdRoom() + ""));
		// `idOrder` VARCHAR(45) NOT NULL COMMENT '订单编号',
		// `status` varchar(20) NOT NULL COMMENT '订单状态',
		if (so.getStatus() != null)
			listSql.add(getUpdateSql("status", so.getStatus() + ""));
		// `addTime` DATETIME NULL COMMENT '开单时间',
		if (so.getAddTime() != null)
			listSql.add(getUpdateSql("addTime", so.getAddTime() + ""));
		// `startTime` DATETIME NULL COMMENT '此服务单开始时间',
		if (so.getStartTime() != null)
			listSql.add(getUpdateSql("startTime", so.getStartTime() + ""));
		// `endTime` DATETIME NULL COMMENT '此服务单结束时间',
		if (so.getEndTime() != null)
			listSql.add(getUpdateSql("endTime", so.getEndTime() + ""));
		// `pay` INT NULL DEFAULT 0 COMMENT '应付金额',
		if (so.getPay() != null)
			listSql.add(getUpdateSql("pay", so.getPay() + ""));
		// `realPay` INT DEFAULT 0 NULL COMMENT '实付金额',
		if (so.getRealPay() != null)
			listSql.add(getUpdateSql("realPay", so.getRealPay() + ""));
		// `payTime` DATETIME NULL,
		if (so.getPayTime() != null)
			listSql.add(getUpdateSql("payTime", so.getPayTime() + ""));
		// `orderRemark` VARCHAR(255) NULL COMMENT '订单描述',
		if (so.getOrderRemark() != null)
			listSql.add(getUpdateSql("orderRemark", so.getOrderRemark() + ""));
		// `payPath` VARCHAR(10) NULL COMMENT '支付途径',
		if (so.getPayPath() != null)
			listSql.add(getUpdateSql("payPath", so.getPayPath() + ""));
		// `pledgeName` VARCHAR(20) NULL COMMENT '所压物品',
		if (so.getPledgeName() != null)
			listSql.add(getUpdateSql("pledgeName", so.getPledgeName() + ""));
		// `pledgePrice` INT NULL DEFAULT 0 COMMENT '押金金额',
		if (so.getPledgePrice() != null)
			listSql.add(getUpdateSql("pledgePrice", so.getPledgeName() + ""));
		// `clockcatagory` VARCHAR(20) NULL,
		if (so.getClockCatagory() != null)
			listSql.add(getUpdateSql("clockcatagory", so.getClockCatagory() + ""));

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
		} finally {
			DBService.close(stmt);
			DBService.close(conn);
		}

		ServerOrder soo = getOrderByIdOrder(so.getIdOrder());

		// 利用反射机制更新
		Field[] fn = so.getClass().getDeclaredFields();
		Field[] fo = soo.getClass().getDeclaredFields();
		for (int i = 0; i < fn.length; i++) {
			try {
				fo[i].setAccessible(true);
				fn[i].setAccessible(true);
				if (fn[i].get(so) != null) {
						fo[i].set(soo, fn[i].get(so));
				}
				fo[i].setAccessible(false);
				fn[i].setAccessible(false);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * 先从listToday 中查找 如果不存在，那么从数据库中寻找
	 * 
	 * @param idOrder
	 * @return
	 */
	public ServerOrder getOrderByIdOrder(String idOrder) {

		/**
		 * 在内存中查找
		 */
		for (ServerOrder serverOrder : listToday) {
			if (serverOrder.getIdOrder().equals(idOrder)) {
				return serverOrder;
			}
		}
		// 对数据库中进行查找
		return getOrderByIdOrderFromSql(idOrder);
	}

	/**
	 * 根据idOrder在数据库中行查找
	 * 
	 * @param idOrder
	 *            idOrder
	 * @return ServerOrder 对象
	 */
	private ServerOrder getOrderByIdOrderFromSql(String idOrder) {

		ServerOrder c = null;
		String sql = "select * from serverorder where  " + "idOrder >= '" + idOrder + "' ";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				c = new ServerOrder(rs.getString("idUser"), rs.getInt("id"), rs.getString("idRoom"),
						rs.getString("idOrder"), rs.getString("status"), rs.getTimestamp("addTime"),
						rs.getTimestamp("endTime"), rs.getInt("pay"), rs.getInt("realPay"), rs.getTimestamp("payTime"),
						rs.getString("orderRemark"), rs.getString("payPath"), rs.getString("pledgeName"),
						rs.getInt("pledgePrice"), rs.getTimestamp("addTime"), rs.getString("clockcatagory"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
		return c;

	}

}
