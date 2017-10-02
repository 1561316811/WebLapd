package com.cyl.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.sql.DBService;

public class WorkStatusService {
	
	private static ArrayList<WorkStatus> list = new ArrayList<WorkStatus>();
	private static WorkStatusService instance = null;
	
	/**
	 * 初始化时先获取数据
	 * 这样只需要连接一次数据库
	 */
	private WorkStatusService(){
		
		String sql = "select * from workStatus order by number";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while(rs.next()){
				WorkStatus c = new WorkStatus(rs.getInt("number"), rs.getString("workStatus"));
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
	public static WorkStatusService getInstance(){
		if(instance == null){
			synchronized (WorkStatusService.class) {
				if(instance == null){
					instance = new WorkStatusService();
				}
			}
		}
		return instance;
	}

	/**
	 * 载入支付方式数据到数据库中
	 * @param pp 支付方式信息
	 * @return 载入成功返回true
	 */
	public boolean load(WorkStatus pp){
		
		if(! list.contains(pp)){
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into paypath (number, payPath) values (?, ?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setInt(1, list.size() + 1);
			preStmt.setString(2, pp.getWorkStatus());
			preStmt.executeUpdate();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			DBService.close(preStmt);
			DBService.close(conn);
		}
		//更新list中该对象的数据
		list.add(pp);
		
		return true;
	}
	
	/**
	 * 得到指定范围内PayPath对象list
	 * @param start 开始的下标，下标从 1 开始
	 * @param num 从start开始往后的元素个数
	 * @return 返回该List数据对象
	 */
	public List<WorkStatus> getLimitData(int start, int num){
		
		start --;
		return list.subList(start, start + num);
	}
	
	/**
	 * PayPath类对象的个数
	 * @return PayPath类对象的个数
	 */
	public int getNum(){
		return list.size();
	}
	
	/**
	 * 删除该对象
	 * @param pp 该对象
	 */
	public void del(WorkStatus pp){
		
		String sql = "delete from workstatus where name = '"+ pp.getWorkStatus() +"'";
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
		
		//PayPath equals() hashCode()  方法得到重写 
		//内存中移除
		list.remove(pp);
		
	}
	
	/**
	 * 修改指定PayPath对象中的信息
	 * @param pp 指定的对象
	 * @param ppn 修改之后的对象
	 */
	public void modify(WorkStatus pp, WorkStatus ppn){
		
		String sql = "update workstatus set workstatus ='"+ ppn.getWorkStatus() +"' where workstatus = '"+ pp.getWorkStatus() + "'";
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
		list.set(list.indexOf(pp), ppn);
		
	}
	/**
	 * 根据序号来获取员工工作状态途径的对象
	 * <li>insert into workstatus values(1,'非工作');
      <li>insert into workstatus values(2,'工作中');
      <li>insert into workstatus values(3,'待接收');
      <li>insert into workstatus values(4,'服务中');
      <li>insert into workstatus values(5,'空闲');
     
	 * @param index 支付序号,从1开始
	 * @return
	 */
	public WorkStatus getWorkStatusByNum(int index){
		return list.get(index - 1);
	}
	
}
