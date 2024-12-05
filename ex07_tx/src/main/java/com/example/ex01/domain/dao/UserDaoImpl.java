package com.example.ex01.domain.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ex01.domain.dto.UserDto;

@Repository
public class UserDaoImpl {
	
	@Autowired
	private DataSource dataSource3;
	
	public int insert(UserDto UserDto) throws SQLException {
		Connection conn = dataSource3.getConnection();
	    PreparedStatement pstmt = conn.prepareStatement("INSERT INTO tbl_user (userid, password, rePassword, username, phone, zipcode, addr1, addr2, birthday) VALUES (?, ?, ?, ?, ?, ?, ?, ?, now())");

	    pstmt.setString(1, UserDto.getUserid());
	    pstmt.setString(2, UserDto.getPassword());
	    pstmt.setString(3, UserDto.getRePassword());
	    pstmt.setString(4, UserDto.getUsername());
	    pstmt.setString(5, UserDto.getPhone());
	    pstmt.setString(6, UserDto.getZipcode());
	    pstmt.setString(7, UserDto.getAddr1());
	    pstmt.setString(8, UserDto.getAddr2());
	   


	    int result = pstmt.executeUpdate();

	    pstmt.close();
	    conn.close();

	    return result;
	}
}
