package com.cheetah.istat.regioni.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.cheetah.istat.interceptor.IstatInterceptor;
import com.google.gson.Gson;

@Configuration
@EnableScheduling
public class IstatConfiguration implements WebMvcConfigurer{
	
	
	@Bean
	public IstatInterceptor istatInterceptor() {
		return new IstatInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(istatInterceptor());
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean
	public IstatProperties regioniProperties() {
		return new IstatProperties();
	}
	
	@Bean
	public Gson gson() {
		return new Gson();
	}
}
