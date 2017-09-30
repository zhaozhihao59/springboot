package com.revanow.base.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	/** 
     *  
     */  
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {  
        builder.sources(this.getClass());  
        
        return super.configure(builder);  
    } 
    
    public static void main(String[] args) {
		SpringApplication.run(Application.class);
	}
}
