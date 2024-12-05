package com.example.ex01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.ex01.interceptor.MemoInterceptor;
import com.example.ex01.listener.CustomContextRefreshedListener;
import com.example.ex01.listener.MemoAddEventListener;
import com.example.ex01.listener.RequestHandledEventListener;

@Configuration
@EnableWebMvc
@ComponentScans({
	@ComponentScan("com.example.ex01.controller"),
	@ComponentScan("com.example.ex01.restController"),
})
public class WebMvcConfig implements WebMvcConfigurer{
	
	
	//MULTIPARTCONFIG
	@Bean
	public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(1024*1024*10*2); 		// 20MB	//전체 업로드 허용 사이즈
        multipartResolver.setMaxUploadSizePerFile(1024*1024*10*2); 	// 20MB	//파일 1개당 허용가능한 업로드 사이즈
        multipartResolver.setMaxInMemorySize(1024*1024*10*2); 		//   //캐시 공간
		return multipartResolver;
	}
	
	
	//VIEW RESOLVER
	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();	
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	//STATIC RESOURCE
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}


	//INTERCEPTOR ADDED
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new MemoInterceptor()).addPathPatterns("/memo/*");
	}

	//LISTENER
	@Bean
	public RequestHandledEventListener requestHandledEventListener() {
		return new RequestHandledEventListener();
	}
	
	@Bean
	public CustomContextRefreshedListener customContextRefreshedListener() {
		return new CustomContextRefreshedListener();
	}
	
	@Bean
	public MemoAddEventListener memoAddEventListener() {
		return new MemoAddEventListener();
	}
	

}





