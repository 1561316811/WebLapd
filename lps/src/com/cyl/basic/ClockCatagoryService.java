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

public static boolean load(ClockCatagory cg){
		
		if(DBService.isValueExist("select * from clockcatagory where name = '" + cg.getName() +"'")){
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into clockcatagory (name) values (?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, cg.getName());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBService.close(preStmt);
			DBService.close(conn);
		}
		return true;
	}

	public static List<ClockCatagory> getLimitData(int start, int num){
		
		List<ClockCatagory> listC = new ArrayList<ClockCatagory>();
		
		String sql = "select * from clockcatagory limit "+ start +", " + num;
		
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while(rs.next()){
				ClockCatagory c = new ClockCatagory(rs.getString("name"));
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
	
	public static int getNum(){
		
		String sql = "select count(*) from clockcatagory";
		
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
	
	
	public static void del(ClockCatagory cg){
		
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
		
	}
	
	public static void modify(ClockCatagory cg, ClockCatagory cgn){
		
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
		
	}
}
