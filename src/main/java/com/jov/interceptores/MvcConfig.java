package com.jov.interceptores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.jov.interceptores.interceptors.LoadingTimeInterceptors;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

	@Autowired
	@Qualifier("timeInterceptor")
	private HandlerInterceptor timeInterceptor; 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//.addPathPatterns - solo para que se ejecute en /bar
//		registry.addInterceptor(timeInterceptor).addPathPatterns("/app/bar"); 
		registry.addInterceptor(timeInterceptor).excludePathPatterns("/app/bar"); 
	}
	
	

	
}
