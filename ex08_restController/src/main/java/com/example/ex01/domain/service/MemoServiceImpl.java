package com.example.ex01.domain.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.ex01.domain.dao.MemoDaoImpl;
import com.example.ex01.domain.dto.MemoDto;
import com.example.ex01.domain.mapper.MemoMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemoServiceImpl {
	
	@Autowired
	private MemoDaoImpl memoDaoImpl;

	@Autowired
	private MemoMapper memoMapper;
	
	public boolean memoRegistration(MemoDto memoDto) throws Exception {
		//Dao - 메모 Insert이후 true/false 반환
		int result = memoDaoImpl.insert(memoDto);
		return result>0;
	}
	
	public boolean memoRegistration_mapper(MemoDto memoDto) throws Exception {
		//Dao - 메모 Insert이후 true/false 반환
		int result = memoMapper.Insert(memoDto);
		return result>0;
	}
	//메모 수정 하기
	public boolean updateMemo(MemoDto memoDto) {
		int result = memoMapper.Update(memoDto);	
		return result>0;
	}
	
	//메모 삭제 하기
	public boolean removeMemo(int id) {
		int result = memoMapper.Delete(id);	
		return  result>0;
	}
	

	//특정 메모 가져오기
	public MemoDto getMemo(int id) {
		return memoMapper.SelectOne(id);
	}	
	
	//모든 메모 가져오기
	public List<MemoDto> getMemos(){
		return memoMapper.SelectAll();
	}
	

	public boolean memoAdd(MemoDto memoDto) throws Exception {
		//문제상황 만들기
		log.info("memoDto insert 전 " + memoDto);
		int result = memoDaoImpl.insertTx(memoDto);
		log.info("memoDto insert 후 " + memoDto);
		result = memoDaoImpl.insertTx(memoDto);
		return result>0;
	}
	
	@Transactional(rollbackFor = SQLException.class)
	public boolean memoAddTx(MemoDto memoDto) throws Exception {
		
		int result = memoDaoImpl.insert(memoDto);
		return result>0;
	}
	
	@Transactional(rollbackFor = SQLException.class)
	public List<MemoDto> getMemos(MemoDto memoDto) throws Exception {
		
		return memoDaoImpl.selectAll(memoDto);
	}

	@Transactional(rollbackFor = SQLException.class)
	public boolean memoUpdate(MemoDto memoDto) {
		return memoDaoImpl.Update(memoDto)>0;
	}

	@Transactional(rollbackFor = SQLException.class)
	public boolean memoUpdate_patch(MemoDto memoDto) {
		return memoDaoImpl.Update_patch(memoDto)>0;
	}

	@Transactional(rollbackFor = SQLException.class)
	public boolean memoDelete(int id) {
		return memoDaoImpl.Delete(id)>0;
	}
}




