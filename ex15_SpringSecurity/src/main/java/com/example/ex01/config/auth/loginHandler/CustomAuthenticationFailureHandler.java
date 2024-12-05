package com.example.ex01.config.auth.loginHandler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		System.out.println("CustomAuthenticationFailureHandler's onAuthenticationFailure().." + exception);
		request.setAttribute("message", "인증실패.. ID/PW를 확인하세요");
		
		request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);

	}

}
