package com.example.ex01.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class CustomContextRefreshedListener implements ApplicationListener <ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		System.out.println("CustomContextRefreshedListener's onApplicationEvent().." + event.getSource());
	}

}
