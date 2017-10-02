package com.cyl.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.cyl.basic.PayPath;
import com.cyl.basic.PayPathService;
import com.cyl.sql.DBService;
import com.cyl.user.UserInformationErrorException;

public class AdminService {
	
	private static ArrayList<Admin> list = new ArrayList<Admin>();
	private static AdminService instance = null;
	
	/**
	 * 初始化时先获取数据
	 * 这样只需要连接一次数据库
	 */
	private AdminService(){
		
		String sql = "select * from admin ";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		try {
			while(rs.next()){
				Admin c = new Admin(rs.getString("idAdmin"), rs.getString("password"), rs.getDate("addTime"));
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
	public static AdminService getInstance(){
		if(instance == null){
			synchronized (PayPathService.class) {
				if(instance == null){
					instance = new AdminService();
				}
			}
		}
		return instance;
	}
	
	public void checkLogIn(String idUser, String password) {
		if (idUser != null && password != null) {
			for (Admin admin : list) {
				if(admin.getIdAdmin().equals(idUser) && admin.getPassword().equals(password));
					return ;
		}
			throw new AdminInformationErrorException("用户名或密码错误");
		} else {
			throw new AdminInformationErrorException("用户名或密码错误");
		}
	}

}
