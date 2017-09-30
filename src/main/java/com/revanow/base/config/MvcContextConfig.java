package com.revanow.base.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.revanow.base.interceptor.AdminLoginInterceptor;

@Configuration
@EnableAutoConfiguration
public class MvcContextConfig extends WebMvcConfigurationSupport {

	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AdminLoginInterceptor())
		.addPathPatterns("/admin/**/*.htm","/api/**/*.htm","/prod/**/*.htm")
		.excludePathPatterns("/api/auto/deploy/api.htm","/admin/login.htm");
		
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/").setCachePeriod(31556926);
		registry.addResourceHandler("/favicon.ico").addResourceLocations("/favicon.ico").setCachePeriod(31536000);
	}

	@Override
	protected void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

}
