package com.cyl.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.sql.DBService;

public class OrderStatusService {
	
	private static ArrayList<OrderStatus> list = new ArrayList<OrderStatus>();
	private static OrderStatusService instance = null;
	
	/**
	 * 初始化时先获取数据
	 * 这样只需要连接一次数据库
	 */
	private OrderStatusService(){
		
		String sql = "select * from orderstatus order by number";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while(rs.next()){
				OrderStatus c = new OrderStatus(rs.getInt("number"), rs.getString("orderstatus"));
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
	public static OrderStatusService getInstance(){
		if(instance == null){
			synchronized (OrderStatusService.class) {
				if(instance == null){
					instance = new OrderStatusService();
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
	public boolean load(OrderStatus pp){
		
		if(! list.contains(pp)){
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into orderstatus (number, orderstatus) values (?, ?)";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setInt(1, list.size() + 1);
			preStmt.setString(2, pp.getOrderStatus());
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
	 * 得到指定范围内OrderStatus对象list
	 * @param start 开始的下标，下标从 1 开始
	 * @param num 从start开始往后的元素个数
	 * @return 返回该List数据对象
	 */
	public List<OrderStatus> getLimitData(int start, int num){
		
		start --;
		return list.subList(start, start + num);
	}
	
	/**
	 * OrderStatus类对象的个数
	 * @return OrderStatus类对象的个数
	 */
	public int getNum(){
		return list.size();
	}
	
	/**
	 * 删除该对象
	 * @param pp 该对象
	 */
	public void del(OrderStatus pp){
		
		String sql = "delete from orderstatus where orderstatus = '"+ pp.getOrderStatus() +"'";
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
		
		//OrderStatus equals() hashCode()  方法得到重写 
		//内存中移除
		list.remove(pp);
		
	}
	
	/**
	 * 修改指定OrderStatus对象中的信息
	 * @param pp 指定的对象
	 * @param ppn 修改之后的对象
	 */
	public void modify(OrderStatus pp, OrderStatus ppn){
		
		String sql = "update orderstatus set orderstatus ='"+ ppn.getOrderStatus() +"' where orderstatus = '"+ pp.getOrderStatus() + "'";
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
	 * <br/><br/><br/>
	 * 建立表格sql语句
	 *<ul>
	 *<li>insert into orderstatus values(1,'待确定'); </li>
     *<li>insert into orderstatus values(2,'服务中');</li>
     *<li>insert into orderstatus values(3,'待支付');</li>
     *<li>insert into orderstatus values(4,'支付完成');</li>
     *<li>insert into orderstatus values(5,'订单失效');</li>
     *</ul>
	 * @param index 支付序号,从1开始
	 * @return
	 */
	public OrderStatus getOrderStatusByNum(int index){

		return list.get(index - 1);
	}
	
	/**
	 * @param index
	 * @return
	 * @see #getOrderStatusByNum
	 */
	public String getOrderStatus(int index){
		return getOrderStatusByNum(index).getOrderStatus();
	}
}
