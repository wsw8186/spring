package com.example.ex01.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.web.context.support.RequestHandledEvent;

public class RequestHandledEventListener implements ApplicationListener<RequestHandledEvent> {

	@Override
	public void onApplicationEvent(RequestHandledEvent event) {
		System.out.println("RequestHandledEventListener's onApplicationEvent()..."+ event.getSource()); 
		
	}
	
	
}
