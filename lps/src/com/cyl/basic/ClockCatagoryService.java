package com.cyl.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.sql.DBService;

public class ClockCatagoryService {
	
	private static ArrayList<ClockCatagory> list = new ArrayList<ClockCatagory>();
	private static ClockCatagoryService instance = null;
	
	/**
	 * 初始化时先获取数据
	 * 这样只需要连接一次数据库
	 */
	private ClockCatagoryService(){
		
		String sql = "select * from clockcatagory order by number";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while(rs.next()){
				ClockCatagory c = new ClockCatagory(rs.getInt("number"), rs.getString("name"));
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
	 * @return 该实例
	 */
	public static ClockCatagoryService getInstance(){
		if(instance == null){
			synchronized (PayPathService.class) {
				if(instance == null){
					instance = new ClockCatagoryService();
				}
			}
		}
		return instance;
	}

public  boolean load(ClockCatagory cg){
		
		if(!list.contains(cg)){
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into clockcatagory (number, name) values (?, ?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setInt(1, list.size() + 1);
			preStmt.setString(2, cg.getName());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBService.close(preStmt);
			DBService.close(conn);
		}
		//更新listz中该对象的数据
		list.add(cg);
		return true;
	}

	public  List<ClockCatagory> getLimitData(int start, int num){
		
		start --;
		return list.subList(start, start + num);
		
	}
	
	public int getNum(){
		
		return list.size();
	}
	
	
	public  void del(ClockCatagory cg){
		
		String sql = "delete from clockcatagory where name = '"+ cg.getName() +"'";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBService.close(stmt);
			DBService.close(conn);
		}
		
		list.remove(cg);
		
	}
	
	public  void modify(ClockCatagory cg, ClockCatagory cgn){
		
		String sql = "update clockcatagory set name='"+ cgn.getName() +"' where name = '"+ cg.getName() + "'";
//		System.out.println(sql);
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DBService.close(stmt);
			DBService.close(conn);
		}
		list.set(list.indexOf(cg), cgn);
	}
}
