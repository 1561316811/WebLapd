package com.cyl.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.sql.DBService;

public class RoomCatagoryService {

	private static ArrayList<RoomCatagory> list = new ArrayList<RoomCatagory>();
	private static RoomCatagoryService instance = null;

	/**
	 * 初始化时先获取数据 这样只需要连接一次数据库
	 */
	private RoomCatagoryService() {

		String sql = "select * from roomcatagory order by number";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while (rs.next()) {
				RoomCatagory c = new RoomCatagory(rs.getInt("number"), rs.getString("name"));
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
	public static RoomCatagoryService getInstance() {
		if (instance == null) {
			synchronized (PayPathService.class) {
				if (instance == null) {
					instance = new RoomCatagoryService();
				}
			}
		}
		return instance;
	}

	/**
	 * 载入Room对象数据到数据库中
	 * 
	 * @param cg
	 *            room对象
	 * @return 载入成功返回true
	 */
	public boolean load(RoomCatagory cg) {

		if (! list.contains(cg)) {
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into roomcatagory (number, name) values (?, ?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setInt(1, list.size() + 1);
			preStmt.setString(2, cg.getName());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(preStmt);
			DBService.close(conn);
		}
		// 更新listz中该对象的数据
		list.add(cg);
		return true;
	}

	/**
	 * 得到指定范围内RoomCatagory对象list
	 * 
	 * @param start
	 *            开始的下标，下标从 1 开始
	 * @param num
	 *            从start开始往后的元素个数
	 * @return 返回该List数据对象
	 */
	public List<RoomCatagory> getLimitData(int start, int num) {

		start--;
		return list.subList(start, start + num);

	}

	/**
	 * RoomCatagory类对象的个数
	 * 
	 * @return RoomCatagory类对象的个数
	 */
	public int getNum() {

		return list.size();

	}

	public  void del(RoomCatagory cg) {

		String sql = "delete from roomcatagory where name = '" + cg.getName() + "'";
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

		list.remove(cg);
	}
	
	public void del(String name){
		del(new RoomCatagory(0, name));
	}

	public  void modify(RoomCatagory cg, RoomCatagory cgn) {

		String sql = "update roomcatagory set name='" + cgn.getName() + "' where name = '" + cg.getName() + "'";
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
		list.set(list.indexOf(cg), cgn);
	}
}
