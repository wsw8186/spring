package com.example.ex.MybatisTests;

import java.sql.Connection;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Slf4j
class mybatisTests {

	@Autowired
	SqlSessionFactory sqlSessionFactory;
	
	@Test
	void test_1() {
		System.out.println(sqlSessionFactory.toString());
		SqlSession sqlSession =  sqlSessionFactory.openSession();
		Connection conn =  sqlSession.getConnection();
		System.out.println(conn.toString());
		
	}
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	@Test
	void test_2() {
		System.out.println(sqlSessionTemplate);
		Connection conn =  sqlSessionTemplate.getConnection();
	}
	
	@Autowired
	MemoMapper memoMapper;
	@Test
	void test_3() {

		////INSERT UPDATE DELETE
		//memoMapper.Insert(new MemoDto(-1,"MYBATIS!!","ex@ex.com"));
		//memoMapper.Update(new MemoDto(16,"MYBATIS_UPDATE","ex@ex.com"));
		//memoMapper.Delete(16);

		////SELECT
//		MemoDto dto = memoMapper.SelectOne(14);
//		System.out.println(dto);

		////SELECT
//		List<MemoDto> list = memoMapper.SelectAll();
//		list.forEach(System.out::println);

		////SELECT
//		List< Map<String,Object> >listMap = memoMapper.SelectAllByResultMap();
//		listMap.forEach(map->{
//			
//			for(String key : map.keySet()) {
//				System.out.println(key + " : " + map.get(key));
//			}
//			
//		});
		
		//INSERT _ XML
		//int r= memoMapper.Insert_xml(new MemoDto(-1,"MYBATIS_xml_2!!","xml@xml.com"));
		memoMapper.Delete_xml(14);
		
	}
	

}





