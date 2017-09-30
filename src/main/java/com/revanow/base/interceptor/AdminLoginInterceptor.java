package com.revanow.base.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.revanow.auth.entity.User;
import com.revanow.base.constant.SessionConstant;
import com.revanow.base.constant.SystemConstant;

/**
 * 后台用户登录拦截器
 * @creator     zhangqiang
 * @create-time Jun 19, 2015   2:09:07 PM
 * @version 1.0
 */
public class AdminLoginInterceptor  extends HandlerInterceptorAdapter {
	
	@Value("${dev.mode}")
	private String devMode;

	@Override
	public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		//判断用户是否登录
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		if(null == user){
			//判断开发模式
			if(StringUtils.isNotBlank(devMode) && "true".equals(devMode)){
				user = new User();
				user.setId(SystemConstant.ADMIN_ID);
				user.setName("系统管理员");
				user.setAccount("admin");
				
				session.setAttribute(SessionConstant.CURRENT_USER, user);
				
				return super.preHandle(request, response, handler);
			}
			
			//判断请求方式
			String requestType = request.getHeader("X-Requested-With");
			if(StringUtils.isNotBlank(requestType) && StringUtils.equals("XMLHttpRequest", requestType)){
				//异步请求
				response.setContentType("text/json;charset=UTF-8");
				response.getWriter().write("{\"status\":\"error\",\"message\":\"登录超时，请重新登录\"}");
				return false;
			}
			
			response.sendRedirect(request.getContextPath() + "/login.htm");
			return false;
		}
		
		return super.preHandle(request, response, handler);
	}
	
}
