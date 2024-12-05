package com.example.ex01.config;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.handler.BeanNameUrlHandlerMapping;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.example.ex01.handler.CustomHandler;
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
	
	//HANDLER MAPPING
	
	//1) BEAN MAPPING(BeanNameUrlHandlerMapping : 요청 URL을 동일한 이름을 가진 Bean에 매핑)
	@Bean
	BeanNameUrlHandlerMapping beanNameUrlHandlerMapping() {
		return new BeanNameUrlHandlerMapping();
	}
	
	@Bean(name = "/custom_01")
	public CustomHandler customHandler() {
		return new CustomHandler();
	}

	//2) SimpleUrlHandlerMapping : 개발자가 직접 매핑정보를 설정 ,  정적자워원에 대한 매핑정보 설정이 기본값
	@Bean
	SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping handlerMapping = new SimpleUrlHandlerMapping();
		
		Map<String, Object> urlMap = new HashMap();
		urlMap.put("/custom_02", new CustomHandler());

		handlerMapping.setUrlMap(urlMap);
		return handlerMapping;
	}
	
	//3) RequestMappingHandlerMapping : Controller의 매핑되는 URL을 찾아내고 해당 URL의 데한 요청 처리
	//적절한 컨트롤러 및 메서드를 찾아 매핑(@RequestMapping,@GetMapping,@PostMapping...)
	
	@Bean
	RequestMappingHandlerMapping requestMappingHandlerMapping() throws NoSuchMethodException, SecurityException {
		RequestMappingHandlerMapping handlerMapping =  new RequestMappingHandlerMapping();
		
		//URL에 매핑될 메서드 찾기(Reflection이용)
		Method method = CustomHandler.class.getMethod("hello", null);
		
		//URL요청 매핑정보 구성(요청 Method, URL...)
		RequestMappingInfo mappingInfo = RequestMappingInfo
										 .paths("/custom_03")
										 .methods(RequestMethod.GET)
										 .build();
		
		//요청 매핑정보, 핸들러, 메서드 등록
		handlerMapping.registerMapping(mappingInfo, new CustomHandler(), method);
		
		return handlerMapping;
	}
}




