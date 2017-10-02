package com.cyl.admin.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cyl.basic.PayPath;
import com.cyl.basic.PayPathService;
import com.cyl.basic.RoomCatagory;
import com.cyl.sql.DBService;

public class RoomService {

	private static ArrayList<Room> list = new ArrayList<Room>();
	private static RoomService instance = null;

	/**
	 * 初始化时先获取数据 这样只需要连接一次数据库
	 */
	private RoomService() {

		String sql = "select * from room order by floor";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				Room c = new Room(rs.getString("idRoom"), rs.getString("catagory"), rs.getInt("floor"),
						rs.getInt("size"), rs.getString("remark"), rs.getDate("addTime"));
				list.add(c);
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
	public static RoomService getInstance() {
		if (instance == null) {
			synchronized (PayPathService.class) {
				if (instance == null) {
					instance = new RoomService();
				}
			}
		}
		return instance;
	}

	/**
	 * 载入Room类新对象 Notice ：该载入不会对该对象进行任何操作,请注意对象数据的完整性
	 * 
	 * @param r
	 *            Room对象
	 * @return 载入成功返回true
	 */
	public boolean load(Room r) {

		if (list.contains(r)) {
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			// `idRoom` VARCHAR(10) NOT NULL COMMENT '房间编号',
			// `catagory` VARCHAR(45) NULL COMMENT '房间名字',
			// `floor` INT NULL COMMENT '房间楼层',
			// `size` INT NULL COMMENT '房间可容纳客人的人数',
			// `remark` VARCHAR(255) NULL COMMENT '房间描述',
			// PRIMARY KEY (`idRoom`))
			String sql = "insert into room (idRoom, catagory, floor, size, remark, addTime) values (?, ?, ?, ?, ?, ?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, r.getIdRoom());
			preStmt.setString(2, r.getCategory());
			preStmt.setInt(3, r.getFloor());
			preStmt.setInt(4, r.getSize());
			preStmt.setString(5, r.getRemark());
			preStmt.setDate(6, r.getAddTime());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(preStmt);
			DBService.close(conn);
		}
		list.add(r);
		return true;
	}

	/**
	 * 得到指定范围内Room对象list
	 * @param start 开始的下标，下标从 1 开始
	 * @param num 从start开始往后的元素个数
	 * @return 返回该List数据对象
	 */
	public List<Room> getLimitData(int start, int num) {

		start--;
		return list.subList(start, start + num);

	}

	/**
	 * 得到所有idRoom的序号
	 * @return List类对象的
	 */
	public List<String> getAllId() {
		List<String> l = new ArrayList<String>();

		for (Room r : list) {
			l.add(r.getIdRoom());
		}
		return l;
	}

	/**
	 * Room类对象的个数
	 * @return Room类对象的个数
	 */
	public int getNum() {
		return list.size();
	}

	/**
	 * 删除该对象
	 * @param r 该对象
	 */
	public boolean del(Room r) {

		boolean isSuss = true;

		String sql = "delete from room where idRoom = '" + r.getIdRoom() + "'";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("删除数据失败");
			isSuss = false;
		} finally {
			DBService.close(stmt);
			DBService.close(conn);
		}
		list.remove(r);
		return isSuss;
	}

	public void modify(Room r, Room rn) {

		// `idRoom` VARCHAR(10) NOT NULL COMMENT '房间编号',
		// `catagory` VARCHAR(45) NULL COMMENT '房间名字',
		// `floor` INT NULL COMMENT '房间楼层',
		// `size` INT NULL COMMENT '房间可容纳客人的人数',
		// `remark` VARCHAR(255) NULL COMMENT '房间描述',
		// PRIMARY KEY (`idRoom`))

		String sql = "update room set idRoom ='" + r.getIdRoom() + "'" + "catagory =  '" + r.getCategory() + "'"
				+ "floor = " + r.getFloor() + "size = " + r.getSize() + "remark = '" + r.getRemark() + "'"
				+ "where idRoom = '" + r.getIdRoom() + "'";
		// System.out.println(sql);
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
		list.set(list.indexOf(r), rn);
	}
	
}