package com.cyl.admin;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cyl.sql.DBService;
import com.cyl.user.UserInformationErrorException;

public class AdminService {
	
	public static void checkLogIn(String idUser, String password) {
		if (idUser != null && password != null) {
			Connection conn = DBService.getConn();
			String sql = "select * from admin where idAdmin = '" + idUser + "' and password = '" + password +"'";
			Statement stmt = DBService.getStmt(conn);
			ResultSet rs = DBService.getRs(stmt, sql);
			try {
				if (!rs.next()) {
					throw new AdminInformationErrorException("用户名或密码错误");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				DBService.close(rs);
				DBService.close(stmt);
				DBService.close(conn);
			}
		} else {
			throw new AdminInformationErrorException("用户名或密码错误");
		}
	}


}
