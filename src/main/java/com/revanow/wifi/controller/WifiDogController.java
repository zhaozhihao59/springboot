package com.revanow.wifi.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller("wifiDogController")
@Scope("prototype")
@RequestMapping("/wifi")
@EnableAutoConfiguration
public class WifiDogController {
	public static void main(String[] args) {
		SpringApplication.run(WifiDogController.class);
	}
	private Log logger = LogFactory.getLog(WifiDogController.class);
	@RequestMapping(value = "/login")
	public ModelAndView login(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/wifi/openApp");
		return view;
	}
	@RequestMapping(value = "/loginIn")
	public ModelAndView loginIn(){
		logger.info("loginIn start");
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/wifi/login");
		logger.info("loginIn end");
		return view;
	}
	@RequestMapping(value = "/auth")
	public void auth(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		logger.info("auth start");
		response.getWriter().write("Auth: 1");
		logger.info("auth end");
	}
	@RequestMapping(value = "/portal")
	public ModelAndView portal(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		logger.info("portal start");
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/wifi/success");
		logger.info("portal end");
		return view;
	}
	@RequestMapping(value = "/ping")
	public void ping(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		logger.info("ping start");
		response.getWriter().write("Pong");
		logger.info("ping end");
	}
	@RequestMapping(value = "/msg")
	public void message(HttpServletRequest request,HttpServletResponse response,HttpSession session) throws IOException{
		logger.info("msg start");
		response.getWriter().write("error message");
		logger.info("msg end");
	}
}
