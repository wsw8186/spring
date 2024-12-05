package com.example.ex.DataSourceTests;

import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class DSTests {

	@Autowired
	private DataSource dataSource;
	
	@Test
	void test_1() throws Exception {
		assertNotNull(dataSource);
		
		Connection conn = dataSource.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
		pstmt.setString(1, "메모TEST");
		pstmt.setString(2, "example@example.com");
		pstmt.executeUpdate();
		
	}
	
	@Autowired
	private DataSource dataSource2;
	
	@Test
	void test_2() throws Exception {
		assertNotNull(dataSource2);
		
		Connection conn = dataSource2.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
		pstmt.setString(1, "메모TEST2");
		pstmt.setString(2, "example2@example.com");
		pstmt.executeUpdate();
		
	}
	
	@Autowired
	private DataSource dataSource3;
	
	@Test
	void test_3() throws Exception {
		assertNotNull(dataSource3);
		
		Connection conn = dataSource3.getConnection();
		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
		pstmt.setString(1, "메모TEST3");
		pstmt.setString(2, "example3@example.com");
		pstmt.executeUpdate();
		
	}

	

}



