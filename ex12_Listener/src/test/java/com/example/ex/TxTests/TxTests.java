package com.example.ex.TxTests;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.service.MemoServiceImpl;

@ExtendWith(SpringExtension.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
class TxTests {
	
	@Autowired
	MemoServiceImpl memoServiceImpl;
	
	@Test
	void test_1() throws Exception {
		memoServiceImpl.memoAdd(new MemoDto(100,"a","a"));
	}
	
	@Test
	void test_2() throws Exception {
		memoServiceImpl.memoAddTx(new MemoDto(100,"a","a"));
	}

}
