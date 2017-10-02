package com.cyl.work;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.sql.DBService;

public class WorkRankService {


	public static boolean load(WorkRank item){
			
			if(DBService.isValueExist("select * from workrank where id = '" + item.getId() +"'")){
				return false;
			}
			Connection conn = DBService.getConn();
			PreparedStatement preStmt = null;
			try {
				conn.setAutoCommit(false);
				String sql = "insert into workrank (id) values (?)";
				preStmt = DBService.getPreStmt(conn, sql);
				preStmt.setInt(1, item.getId());
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

		public static List<WorkRank> getLimitData(int start, int num){
			
			List<WorkRank> listC = new ArrayList<WorkRank>();
			
			String sql = "select * from workrank order by rankNum limit "+ start +", " + num;
			
			Connection conn = DBService.getConn();
			Statement stmt = DBService.getStmt(conn);
			ResultSet rs = DBService.getRs(stmt, sql);
			try {
				while(rs.next()){
					WorkRank c = new WorkRank(rs.getInt("id"), 
							rs.getInt("rankNum"),
							rs.getInt("spotNum"));
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
		
		public static List<WorkRank> getWorkRankList(){
			
			List<WorkRank> list = new ArrayList<WorkRank>();
			
			String sql = "select * from workrank";
			
			Connection conn = DBService.getConn();
			Statement stmt = DBService.getStmt(conn);
			ResultSet rs = DBService.getRs(stmt, sql);
			
			try {
				while(rs.next()){
					WorkRank wr = new WorkRank();
					wr.setId(rs.getInt("id"));
					wr.setRankNum(rs.getInt("rankNum"));
					wr.setSpotNum(rs.getInt("spotNum"));
					list.add(wr);
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
		
		public static int getNum(){
			
			String sql = "select count(*) from workrank";
			
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
		
		
		public static void del(WorkRank item){
			
			String sql = "delete from workrank where id = '"+ item.getId() +"'";
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
		
		//修改
		public static void modify(WorkRank itemo, WorkRank itemn) throws SQLException{
			Connection conn = DBService.getConn();
			PreparedStatement preStmt = null;
				conn.setAutoCommit(false);
				String sql = 
						"update user set id = ? , rankNum = ? , spotNum = ?  where id = '" + itemo.getId() + "'";
				
				preStmt = DBService.getPreStmt(conn, sql);
				preStmt.setInt(1, itemn.getId());
				preStmt.setInt(2, itemn.getRankNum());
				preStmt.setInt(3, itemn.getSpotNum());
				preStmt.executeUpdate();
				conn.setAutoCommit(true);
				
				DBService.close(preStmt);
				DBService.close(conn);
				
		}

}
