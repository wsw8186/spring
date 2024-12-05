package com.example.ex01.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.ex01.domain.dto.UserDto;
import com.example.ex01.domain.mapper.UserMapper;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl {
	
	@Autowired
	private UserMapper	userMapper;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public void memberJoin(UserDto userDto) {
		userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
		userMapper.Insert(userDto);
	}
	
}
