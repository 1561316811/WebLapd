package com.cyl.sql;
import java.sql.*;

public class DBService {
	
//	private static Connection conn = null;
	
	public static Connection getConn(){
		Connection conn = null;
		try{
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost/lapd"+
					"?characterEncoding=utf8&useSSL=true&user=root&password=tiger";
			conn = DriverManager.getConnection(url);
			
//						Class.forName("com.mysql.jdbc.Driver");
//						conn = DriverManager.getConnection("jdbc:mysql://localhost/lapd?user=root&password=tiger");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static Statement getStmt(Connection conn){
		Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static PreparedStatement getPreStmt(Connection conn, String sql){
		PreparedStatement preStmt = null;
		try {
			preStmt = conn.prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preStmt;
	}
	
	public static ResultSet getRs(Statement stmt , String sql){
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public static boolean isValueExist(String sql){
		boolean isExist = false;
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			isExist = rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBService.close(rs);
			DBService.close(stmt);
			DBService.close(conn);
		}
		return isExist;
	}
	
	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
