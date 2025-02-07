package com.example.ex01.config.auth.exception;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		System.out.println("CustomAuthenticationEntryPoint's commence()..." + authException);
		request.setAttribute("message", "인증이 필요한 페이지 입니다.");
		request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
	}

}
