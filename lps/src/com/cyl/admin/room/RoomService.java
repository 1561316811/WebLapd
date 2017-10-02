package com.cyl.admin.room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.cyl.admin.catagory.Catagory;
import com.cyl.sql.DBService;

public class RoomService {
	
	public static boolean loadRoom(Room room){
		
		if(DBService.isValueExist("select * from room where idRoom = '" + room.getIdRoom() +"'")){
			return false;
		}
		Connection conn = DBService.getConn();
		PreparedStatement preStmt = null;
		try {
			conn.setAutoCommit(false);
//			`idRoom` VARCHAR(10) NOT NULL COMMENT '房间编号',
//			  `catagory` VARCHAR(45) NULL COMMENT '房间名字',
//			  `floor` INT NULL COMMENT '房间楼层',
//			  `size` INT NULL COMMENT '房间可容纳客人的人数',
//			  `remark` VARCHAR(255) NULL COMMENT '房间描述',
//			  PRIMARY KEY (`idRoom`))
			String sql = "insert into room (idRoom, catagory, floor, size, remark, addTime) values (?, ?, ?, ?, ?, now())";
			preStmt = DBService.getPreStmt(conn, sql);
			preStmt.setString(1, room.getIdRoom());
			preStmt.setString(2, room.getCategory());
			preStmt.setInt(3, room.getFloor());
			preStmt.setInt(4, room.getSize());
			preStmt.setString(5, room.getRemark());
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
	
public static List<Room> getLimitData(int start, int num){
		
		List<Room> list = new ArrayList<Room>();
		
		String sql = "select * from room limit "+ start +", " + num;
		
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		ResultSet rs = DBService.getRs(stmt, sql);
		
//		`idRoom` VARCHAR(10) NOT NULL COMMENT '房间编号',
//		  `catagory` VARCHAR(45) NULL COMMENT '房间名字',
//		  `floor` INT NULL COMMENT '房间楼层',
//		  `size` INT NULL COMMENT '房间可容纳客人的人数',
//		  `remark` VARCHAR(255) NULL COMMENT '房间描述',
//		  PRIMARY KEY (`idRoom`))
		
		try {
			while(rs.next()){
				Room c = new Room(rs.getString("idRoom"), 
						rs.getString("catagory"), 
						rs.getInt("floor"),
						rs.getInt("size"),
						rs.getString("remark"), rs.getDate("addTime"));
				list.add(c);
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

public static List<Room> getAllId(){
	List<Room> list = new ArrayList<Room>();
	
	String sql = "select idRoom from room ";
	
	Connection conn = DBService.getConn();
	Statement stmt = DBService.getStmt(conn);
	ResultSet rs = DBService.getRs(stmt, sql);
	
//	`idRoom` VARCHAR(10) NOT NULL COMMENT '房间编号',
//	  `catagory` VARCHAR(45) NULL COMMENT '房间名字',
//	  `floor` INT NULL COMMENT '房间楼层',
//	  `size` INT NULL COMMENT '房间可容纳客人的人数',
//	  `remark` VARCHAR(255) NULL COMMENT '房间描述',
//	  PRIMARY KEY (`idRoom`))
	
	try {
		while(rs.next()){
			Room c = new Room(rs.getString("idRoom"));
			list.add(c);
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
		
		String sql = "select count(*) from room";
		
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
	
	
	public static boolean del(Room cg){
		
		boolean isSuss = true;
		
		String sql = "delete from room where idRoom = '"+ cg.getIdRoom() +"'";
		Connection conn = DBService.getConn();
		Statement stmt = DBService.getStmt(conn);
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			System.out.println("删除数据失败");
			isSuss = false;
		}finally {
			DBService.close(stmt);
			DBService.close(conn);
		}
		return isSuss;
	}
	
	public static void modify(Room r, Room rn){
		
//		`idRoom` VARCHAR(10) NOT NULL COMMENT '房间编号',
//		  `catagory` VARCHAR(45) NULL COMMENT '房间名字',
//		  `floor` INT NULL COMMENT '房间楼层',
//		  `size` INT NULL COMMENT '房间可容纳客人的人数',
//		  `remark` VARCHAR(255) NULL COMMENT '房间描述',
//		  PRIMARY KEY (`idRoom`))
		
		String sql = "update room set idRoom ='"+ r.getIdRoom() +"'"
				+ "catagory =  '"+ r.getCategory() +"'"
				+"floor = "+ r.getFloor() 
				+ "size = "+ r.getSize()
				+ "remark = '" + r.getRemark() + "'" 
				+ "where idRoom = '"+ r.getIdRoom() + "'";
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