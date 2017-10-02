package com.cyl.admin.paypath;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.basic.ClockCatagory;
import com.cyl.sql.DBService;

public class PayPathService {
	

public static boolean load(PayPath pp){
		
		if(DBService.isValueExist("select * from paypath where payPath = '" + pp.getPayPath() +"'")){
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into paypath (payPath) values (?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, pp.getPayPath());
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

	public static List<PayPath> getLimitData(int start, int num){
		
		List<PayPath> listC = new ArrayList<PayPath>();
		
		String sql = "select * from paypath limit "+ start +", " + num;
		
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while(rs.next()){
				PayPath c = new PayPath(rs.getString("payPath"));
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
		
		String sql = "select count(*) from paypath";
		
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
	
	
	public static void del(PayPath pp){
		
		String sql = "delete from paypath where name = '"+ pp.getPayPath() +"'";
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
	
	public static void modify(PayPath pp, PayPath ppn){
		
		String sql = "update paypath set payPath ='"+ ppn.getPayPath() +"' where payPath = '"+ pp.getPayPath() + "'";
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
