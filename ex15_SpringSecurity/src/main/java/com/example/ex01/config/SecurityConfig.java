package com.example.ex01.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.example.ex01.config.auth.exception.CustomAccessDeniedHandler;
import com.example.ex01.config.auth.exception.CustomAuthenticationEntryPoint;
import com.example.ex01.config.auth.loginHandler.CustomAuthenticationFailureHandler;
import com.example.ex01.config.auth.loginHandler.CustomLoginSuccessHandler;
import com.example.ex01.config.auth.logout.CustomLogoutHandler;
import com.example.ex01.config.auth.logout.CustomLogoutSuccessHandler;
import com.example.ex01.domain.service.PrincipalDetailsServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig  extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private PrincipalDetailsServiceImpl principalDetailsServiceImpl;
	
	@Autowired
	private DataSource dataSource;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//CSRF TOKEN 비활성화
		//http.csrf().disable();
		
		//CSRF TOKEN을 쿠키로 전달(개발자도구(F12) 확인)
		//http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
		
		//권한체크
		http.authorizeRequests()
			.antMatchers("/","/join").permitAll()
			.antMatchers("/user").hasRole("USER")
			.antMatchers("/member").hasRole("MEMBER")
			.antMatchers("/admin").hasRole("ADMIN")
			.anyRequest().authenticated();
		
		//로그인
		http.formLogin()
			.loginPage("/login")
			.permitAll()
			.successHandler(new CustomLoginSuccessHandler())				//로그인 성공시 처리
			.failureHandler(new CustomAuthenticationFailureHandler());		//로그인 실패시 처리
		
		//로그아웃
		http.logout()
			.permitAll()
			.addLogoutHandler(new CustomLogoutHandler())
			.logoutSuccessHandler(new CustomLogoutSuccessHandler());
			
			
		
		//예외처리
		http.exceptionHandling()
			.authenticationEntryPoint(new CustomAuthenticationEntryPoint())	//미인증 사용자 예외처리
			.accessDeniedHandler(new CustomAccessDeniedHandler());			//권한 실패(부족)시 예외처리
		
		//REMEMBER_ME
		http.rememberMe()
			.key("rememberMeKey")
			.rememberMeParameter("remember-me")
			.alwaysRemember(false)
			.tokenValiditySeconds(60*60)
			.tokenRepository(tokenRepository());
			
	}


	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
//		auth.inMemoryAuthentication()
//			.withUser("user")
//			.password(passwordEncoder.encode("1234"))
//			.roles("USER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("member")
//			.password(passwordEncoder.encode("1234"))
//			.roles("MEMBER");
//		
//		auth.inMemoryAuthentication()
//			.withUser("admin")
//			.password(passwordEncoder.encode("1234"))
//			.roles("ADMIN");
		
		auth.userDetailsService(principalDetailsServiceImpl).passwordEncoder(passwordEncoder);
		
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}		
	
	@Bean
	public PersistentTokenRepository tokenRepository() {
		JdbcTokenRepositoryImpl repo = new JdbcTokenRepositoryImpl();
		repo.setDataSource(dataSource);
		return repo;
	}

	
		
	
}