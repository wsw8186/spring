package com.example.ex01.domain.service;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ex01.domain.dao.UserDaoImpl;
import com.example.ex01.domain.dto.UserDto;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl {
	
	@Autowired
	private UserDaoImpl UserDaoImpl;
	
	public boolean userRegistration(UserDto userDto) throws SQLException {
		//Dao - 메모 Insert이후 true/false 반환
		return UserDaoImpl.insert(userDto) > 0;
	}
}
