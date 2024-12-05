package com.example.ex01.config.auth.loginHandler;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		System.out.println("CustomLoginSuccessHandler's onAuthenticationSuccess() invoke..");
		
		Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
		authorities.forEach(role->{
			System.out.println("ROLE : " + role.getAuthority());
			
			try {
				if("ROLE_ADMIN".equals(role.getAuthority())) {
					response.sendRedirect(request.getContextPath()+"/admin");
					return;
				}else if("ROLE_MEMBER".equals(role.getAuthority())) {
					response.sendRedirect(request.getContextPath()+"/member");
					return;
				}else if("ROLE_USER".equals(role.getAuthority())) {
					response.sendRedirect(request.getContextPath()+"/user");
					return;
				}else {
					response.sendRedirect(request.getContextPath()+"/");
					return;
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			
			
			
		});
		

	}

}