package com.mvc.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mvc.web.connection.ConnectionProvider;
import com.mvc.web.connection.JdbcUtil;
import com.mvc.web.entity.content.Notice;
import com.mvc.web.entity.user.Login;
import com.mvc.web.entity.user.Register;
import com.mvc.web.entity.user.User;

public class UserDAO {
	
	private static UserDAO instance=new UserDAO(); //처음에 생성한 하나의 dao
	
	public static UserDAO getInstance() {
		return instance;
		
	}

	//Login
	public User LoginCheck(Login lg) {
		User ur=new User();
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		String sql = "select userID, userPass, userName, userEmail, userRank "
				    + "from user "
				    + "where flag='Y' "
				    + "and userID=? "
				    + "and userPass = SHA2(?,256); ";
		try {
			
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, lg.getId()); // login 객체 내 id 값을 입력
			psmt.setString(2, lg.getPass()); // login 객체 내 pass 값 입력
			rs = psmt.executeQuery();
			
			if (rs.next()) { //조회된 값이 있다면 id pass 일치
					ur.setId(lg.getId());
					ur.setName(rs.getString("userName"));
					ur.setRank(rs.getString("userRank"));
					ur.setEmail(rs.getString("userEmail"));
					ur.setNumber(1);
				}
			else {
				ur.setNumber(0); // 조회된 값이 없을때 id혹은 pass 불일치
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return ur;
	}

	//id check
	public int idchekc(String id3) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		int result=0;
		String sql="select userID from user where userID =?";
		try {
			con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, id3);
			rs = psmt.executeQuery();

			if (rs.next()) {
				result=1;
			}else {
				result=0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return result;
	}

	//사용자 추가
	public int signUp(Register rt) {
		Connection con=null;
		PreparedStatement psmt=null;
		ResultSet rs= null;
		int result=0;
		String  sql= "insert into user(userID,userPass,userName,userEmail)"
			     	+"values(?,SHA2(?,256),?,?)";
		try {
		    con = ConnectionProvider.getConnection();
			psmt = con.prepareStatement(sql);
			psmt.setString(1, rt.getId());
			psmt.setString(2, rt.getPass());
			psmt.setString(3, rt.getName());
			psmt.setString(4, rt.getEmail());
			
			System.out.println("psmt aaaaa: "+psmt);
			
			result=psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
			JdbcUtil.close(psmt);
			JdbcUtil.close(rs);
		}
		return result;
	}
}
