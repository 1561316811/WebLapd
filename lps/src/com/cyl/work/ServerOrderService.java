package com.cyl.work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cyl.sql.DBService;
import com.cyl.user.User;
import com.cyl.util.OrderStatus;
import com.cyl.util.SimpleDate;

public class ServerOrderService {

	private static int number = 1;

	public static boolean load(ServerOrder item) {

		if (DBService.isValueExist("select * from serverorder where idOrder = '" + item.getIdOrder() + "'")) {
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
					+ "values (?, ?, ?, ?, ?,?, now())";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, item.getIdUser());
			preStmt.setInt(2, item.getId());
			preStmt.setString(3, item.getIdRoom());
			preStmt.setString(4, item.getIdOrder());
			preStmt.setString(5, item.getStatus());
			preStmt.setString(6, item.getClockCatagory());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(preStmt);
			DBService.close(conn);
		}
		return true;
	}

	public static String createId(int id, String idRoom) {
		String orderId = null;

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");// 设置日期格式

		orderId = id + idRoom + df.format(new Date()) + (number++);

		return orderId;
	}

	public static List<ServerOrder> getLimitData(int start, int num) {

		List<ServerOrder> listC = new ArrayList<ServerOrder>();

		String sql = "select * from serverorder where realPay > 0 limit " + start + ", " + num;

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

	public static int getNum() {

		String sql = "select count(*) from serverorder";

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

	public static void del(ServerOrder item) {

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

	}

	public static List<ServerOrder> getByIdUser(String idUser) {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();

		String sql = "select * from serverorder where idUser = '" + idUser + "'";

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
	 * 对于修改的部分，目前还没有确定
	 * 
	 * @param itemo
	 * @param itemn
	 * @throws SQLException
	 */
	public static void modify(WorkRank itemo, WorkRank itemn) throws SQLException {
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
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

		String sql = "update serverorder set id = ? , rankNum = ? , spotNum = ?  where id = '" + itemo.getId() + "'";

		preStmt = DBService.getPreStmt(conn, sql);
		preStmt.setInt(1, itemn.getId());
		preStmt.setInt(2, itemn.getRankNum());
		preStmt.setInt(3, itemn.getSpotNum());
		preStmt.executeUpdate();
		conn.setAutoCommit(true);

		DBService.close(preStmt);
		DBService.close(conn);

	}

	public static List<ServerOrder> getOrderByIdUser1(User user) {

		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select idRoom, clockcatagory, addTime, idOrder from serverorder where idUser = '"
				+ user.getIdUser() + "' and status = '" + OrderStatus.getStatus("os1") + "' and addTime >= '"
				+ SimpleDate.getTodayDateDay() + "'";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Date d = rs.getTimestamp("addTime");
				ServerOrder c = new ServerOrder();
				c.setIdRoom(rs.getString("idRoom"));
				c.setClockCatagory(rs.getString("clockcatagory"));
				c.setAddTime(rs.getTimestamp("addTime"));
				c.setIdOrder(rs.getString("idOrder"));
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

	public static List<ServerOrder> getOrderByIdUser2(User user) {

		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select idRoom, clockcatagory, addTime, idOrder,status,pay from serverorder where idUser = '"
				+ user.getIdUser() + "' and status = '" + OrderStatus.getStatus("os2") + "' and addTime >= '"
				+ SimpleDate.getTodayDateDay() + "'";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Date d = rs.getTimestamp("addTime");
				ServerOrder c = new ServerOrder();
				c.setIdRoom(rs.getString("idRoom"));
				c.setClockCatagory(rs.getString("clockcatagory"));
				c.setAddTime(rs.getTimestamp("addTime"));
				c.setIdOrder(rs.getString("idOrder"));
				c.setStatus(rs.getString("status"));
				c.setPay(rs.getInt("pay"));
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

	public static List<ServerOrder> getOrderByIdUser3(User user) {

		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select idRoom, clockcatagory, addTime, idOrder,status,pay from serverorder where idUser = '"
				+ user.getIdUser() + "' and status = '" + OrderStatus.getStatus("os3") + "' and addTime >= '"
				+ SimpleDate.getTodayDateDay() + "'";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Date d = rs.getTimestamp("addTime");
				ServerOrder c = new ServerOrder();
				c.setIdRoom(rs.getString("idRoom"));
				c.setClockCatagory(rs.getString("clockcatagory"));
				c.setAddTime(rs.getTimestamp("addTime"));
				c.setIdOrder(rs.getString("idOrder"));
				c.setStatus(rs.getString("status"));
				c.setPay(rs.getInt("pay"));
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

	public static ServerOrder getOrderByIdOrder(String idOrder) {

		ServerOrder c = null;

		String sql = "select * from serverorder where idOrder = '" + idOrder + "'";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				c = new ServerOrder(rs.getString("idUser"), rs.getInt("id"), rs.getString("idRoom"),
						rs.getString("idOrder"), rs.getString("status"), rs.getTimestamp("startTime"),
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
	
	

	public static List<ServerOrder> getTodayOrder() {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getTodayDateDay() + "'";

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
	
	public static List<ServerOrder> getTodayOrderByStatus(String status){
		
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getTodayDateDay() + "' and status = '" + OrderStatus.getStatus(status) +"'";

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
	
	public static List<ServerOrder> getTodayOrderInvalid() {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getTodayDateDay() + "' and status = '" + OrderStatus.getStatus("os5") +"'";

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
	
	public static List<ServerOrder> getTodayOrderPaying() {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getTodayDateDay() + "' and status = '" + OrderStatus.getStatus("os4") +"'";

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
	
	public static List<ServerOrder> getTodayOrderFinish() {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getTodayDateDay() + "' and status = '" + OrderStatus.getStatus("os4") +"'";

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
	
	public static List<ServerOrder> getTodayOrderService() {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getTodayDateDay() + "' and status = '" + OrderStatus.getStatus("os2") +"'";

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
	
	public static List<ServerOrder> getTodayOrderGet() {
		List<ServerOrder> listC = new ArrayList<ServerOrder>();
		String sql = "select * from serverorder where  " + "addTime >= '" + SimpleDate.getTodayDateDay() + "' and status = '" + OrderStatus.getStatus("os1") +"'";

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

	public static void setStartTimeByIdOrderByUser(String idOrder) {

		String sqlCheck = "select startTime from serverorder where idOrder = '" + idOrder + "'";
		String sql = "update serverorder set startTime = now() where idOrder = '" + idOrder + "'";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sqlCheck);
		try {
			rs.next();
			if (rs.getString(1) == null) {
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBService.close(stmt);
					DBService.close(conn);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			DBService.close(rs);
		} finally {
			DBService.close(stmt);
			DBService.close(conn);
		}
	}

	public static void setEndTimeByIdOrderByUser(String idOrder) {

		String sqlCheck = "select endTime from serverorder where idOrder = '" + idOrder + "'";
		String sql = "update serverorder set endTime = now() where idOrder = '" + idOrder + "'";

		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sqlCheck);

		try {
			rs.next();
			if (rs.getString(1) == null) {
				try {
					stmt.executeUpdate(sql);
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					DBService.close(stmt);
					DBService.close(conn);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
	}

	public static void modifyStatusToReceive(String idOrder) {

//		String sql = "update serverorder set status = '" + OrderStatus.getStatus("os2") + "' where idOrder = '" + idOrder
//				+ "'";
		
		String sql = "update serverorder set status = '" + OrderStatus.getStatus("os2") + "' where idOrder = '" + idOrder
				+ "'";
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
	
	public static void modifyStatus(String idOrder, String idStatus){
		String sql = "update serverorder set status= '" + OrderStatus.getStatus(idStatus) + "' where idOrder = '" + idOrder
				+ "'";

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

	public static void modifyStatusToPay(String idOrder) {

		String sql = "update serverorder set status= '" + OrderStatus.getStatus("os3") + "' where idOrder = '" + idOrder
				+ "'";
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

	public static void modifyPay(String idOrder, int pay) {

		String sql = "update serverorder set pay= " + pay + " where idOrder = '" + idOrder + "'";

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
	
	public static void modifyRelPay(String idOrder, int pay) {

		String sql = "update serverorder set realpay= " + pay + " where idOrder = '" + idOrder + "'";

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

	public static void modifyPayTime(String idOrder , Timestamp time) {

		String sql = "update serverorder set payTime= '" + time + "' where idOrder = '" + idOrder + "'";

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
	
	public static void modifyPayPath(String idOrder, String payPath) {

		String sql = "update serverorder set payPath= '" + payPath + "' where idOrder = '" + idOrder + "'";

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
	
	public static void modifyPledgeName(String idOrder, String pledgeName) {

		String sql = "update serverorder set pledgeName= " + pledgeName + " where idOrder = '" + idOrder + "'";

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
	
	public static void modifyPledgePrice(String idOrder, int pledgePrice) {

		String sql = "update serverorder set pledgePrice= " + pledgePrice + " where idOrder = '" + idOrder + "'";

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
}
