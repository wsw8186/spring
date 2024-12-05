package com.example.ex01.domain.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.ex01.domain.dto.MemoDto;

@Repository	
public class MemoDaoImpl {

//	@Autowired
//	private DataSource dataSource3;
//	
//	public int insert(MemoDto memoDto) throws SQLException {
//		//tbl_memo에 insert처리 후 증가 행 반환
//		Connection conn = dataSource3.getConnection();
//		PreparedStatement pstmt = conn.prepareStatement("insert into tbl_memo values(null,?,?,now())");
//		pstmt.setString(1, memoDto.getText());
//		pstmt.setString(2, memoDto.getWriter());
//		
//		int result = pstmt.executeUpdate();
//		
//		pstmt.close();
//		
//		return result;
//	}
	
	@Autowired
	private SqlSession sqlSession;
	private static String namespace = "com.example.ex01.domain.mapper.MemoMapper.";
	
	public int insert(MemoDto memoDto) throws SQLException{
		sqlSession.insert(namespace+"Insert",memoDto);
		return memoDto.getId();
	}
	public int insertTx(MemoDto memoDto) throws SQLException{
		sqlSession.insert(namespace+"InsertTx",memoDto);
		return memoDto.getId();
	}
	
	public List<MemoDto> selectAll() throws SQLException{
		return sqlSession.selectList(namespace+"SelectTx",null);	
	}
	
	public int Update(MemoDto memoDto) {
		return sqlSession.update(namespace+"UpdateTx",memoDto);
	}
	
	
	public int Update_patch(MemoDto memoDto) {
		return sqlSession.update(namespace+"UpdateTx_patch",memoDto);
	}
	
	public int Delete(int id) {
		return sqlSession.delete(namespace+"Delete",id);
	}
	
}







