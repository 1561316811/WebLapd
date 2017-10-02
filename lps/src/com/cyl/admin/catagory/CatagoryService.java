package com.cyl.admin.catagory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.sql.DBService;

public class CatagoryService {

public static boolean load(Catagory cg){
		
		if(DBService.isValueExist("select * from catagory where name = '" + cg.getName() +"'")){
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into catagory (name) values (?)";
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

	public static List<Catagory> getLimitData(int start, int num){
		
		List<Catagory> listC = new ArrayList<Catagory>();
		
		String sql = "select * from catagory limit "+ start +", " + num;
		
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while(rs.next()){
				Catagory c = new Catagory(rs.getString("name"));
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
		
		String sql = "select count(*) from catagory";
		
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
	
	
	public static void del(Catagory cg){
		
		String sql = "delete from catagory where name = '"+ cg.getName() +"'";
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
	
	public static void modify(Catagory cg, Catagory cgn){
		
		String sql = "update catagory set name='"+ cgn.getName() +"' where name = '"+ cg.getName() + "'";
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
