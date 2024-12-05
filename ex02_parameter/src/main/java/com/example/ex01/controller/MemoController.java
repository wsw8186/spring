package com.example.ex01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ex01.domain.dto.MemoDto;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/memo")
@Slf4j
public class MemoController {
	
	@GetMapping("/add")
	public void memo_get() {
		///메모추가 페이지 포워딩 핸들러
		log.info("GET /memo/add...");
	}
	
	@PostMapping("/add")
	public void memo_post(@ModelAttribute MemoDto memoDto) {
		log.info("POST /memo/post..." + memoDto);
		//메모추가 핸들러
		
	}

}
