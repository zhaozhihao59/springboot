package com.revanow.auth.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.revanow.auth.entity.Right;
import com.revanow.auth.entity.User;
import com.revanow.auth.service.IRightService;
import com.revanow.auth.service.IUserService;
import com.revanow.base.constant.SessionConstant;
import com.revanow.base.controller.BaseController;
import com.revanow.base.controller.BaseController.Status;
import com.revanow.base.util.EncryptUtil;
import com.revanow.base.util.HttpUtil;
import com.revanow.test.dao.IAvIncomeDao;
import com.revanow.test.dao.IUpIncomeDao;
import com.revanow.test.entity.AvIncome;
import com.revanow.test.entity.UpIncome;

/**
 * 后台登录
 * @creator     zhangqiang
 * @create-time Jun 19, 2015   11:32:24 AM
 * @version 1.0
 */
@Controller("adminLoginController")
@Scope("prototype")
@RequestMapping(value = "")
public class AdminLoginController extends BaseController{
	
	@Resource(name = "userServiceImpl")
	private IUserService userService;
	@Resource(name = "rightServiceImpl")
	private IRightService rightService;
	@Resource
	private IAvIncomeDao avIncomeDao;
	@Resource
	private IUpIncomeDao upIncomeDao;
	@Value("${tab.server}")
	private String url;
	
	
	
	@RequestMapping(value = "/api/loginAPI.htm")
	public ModelAndView loginAPI(@RequestParam(required = false,name = "test") String test,
			@RequestParam(required = false,name = "test1") String test1,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		System.out.println(test + "    " + test1);
		JSONObject root = new JSONObject();
//		User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		
		root.put("useName","aaaa");
		root.put("password","v");
		logger.info("登录API:"+root.toJSONString());
		return ajaxJSON(Status.success, root);
	}
	
	/**
	 * 后台首页
	 * @return
	 */
	@RequestMapping(value = "/admin/index.htm", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/index/index");
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("url", url);
		User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		//构造用户权限JSON数据
		String rightJsonStr = rightService.buildUserRightJSONStr(user,paramMap);
		Map<String,Object> data = modelAndView.getModel();
		data.put(SessionConstant.CURRENT_PRIVILEGE, rightJsonStr);
		
		return modelAndView;
	}
	/**
	 * 欢迎页
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/welcomBI.htm", method = RequestMethod.GET)
	public String welcomeBI(Long id,HttpSession session,HttpServletRequest request) throws Exception {
	 	Right right = rightService.getRightById(id);
	 	String paramurl = right.getUrl();
	 	paramurl = paramurl.substring(paramurl.indexOf("//") + 1);
	 	String compareUrl = url.substring(url.indexOf("//") + 1);
	 	if(paramurl != null && paramurl.indexOf(compareUrl) == 0){
	 		Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("username", "admin");
			paramMap.put("target_site", "BI");
			String ticket = HttpUtil.postData(url+ "/trusted", paramMap);
			
			paramurl = url + "/trusted/"+ ticket + right.getUrl().substring(url.length());
		}
		return "redirect:"+paramurl;
	}
	/**
	 * 欢迎页
	 * @return
	 */
	@RequestMapping(value = "/admin/welcome.htm", method = RequestMethod.GET)
	public String welcome() {
		return "/admin/workbench/welcome";
	}

	/**
	 * 跳转到登录页面
	 * @return
	 */
	@RequestMapping(value = {"/login.htm","/"}, method = RequestMethod.GET)
	public String toLogin(HttpServletRequest request,HttpSession session) {
		//判断用户
//		User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
//		AvIncome list = avIncomeDao.getAvIncomeById(1);
//		if(null != user){
//			return "redirect:/admin/index.htm";
//		}
		List<UpIncome> listAll = upIncomeDao.listUpIncomeAll();
	 	List<AvIncome> avList = avIncomeDao.listAvIncomeAll();
	 	
	 	for (AvIncome item : avList) {
			item.setMid(listAll.get(new Random().nextInt(listAll.size() - 1)).getMid());
			avIncomeDao.update(item);
		}
		
		return "/admin/login/login";
	}
	

	/**
	 * 跳转到错误页面
	 * @return
	 */
	@RequestMapping(value = "/errorPage.htm", method = RequestMethod.GET)
	public String errorPage(HttpServletRequest request,HttpSession session) {
		return "/admin/index/errorPage";
	}
	
	/**
	 * 登录
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/doLogin.htm", method = RequestMethod.POST)
	public ModelAndView doLogin(HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		try{
			String p1 = request.getParameter("p1");	//账号
			String p2 = request.getParameter("p2");	//密码
			
			//2.判断用户名和密码
			User user = userService.getUserByAccount(p1);
			if(null == user){
				return ajaxJSON(Status.error, "用户名不存在");
			}
			
			if(!org.apache.commons.lang3.StringUtils.equals(user.getPassword(), EncryptUtil.encryptionPw(p2))){
				return ajaxJSON(Status.error, "用户名或密码不正确");
			}
			
			if(null != user.getState() && user.getState().intValue() == 0){
				return ajaxJSON(Status.error, "账号已被禁用");
			}
			
//			session.setAttribute(SessionConstant.TICKET, HttpUtil.postData(url+ "/trusted", paramMap));
			//3.校验通过，保存信息
			request.getSession().setAttribute(SessionConstant.CURRENT_USER, user);
			return ajaxJSON(Status.success, "登录成功");
		}catch(Exception ex){
			String msg = "登录时发生异常："+ex.getMessage();
			logger.error(msg,ex);
			
			return ajaxJSON(Status.error, "登录失败");
		}
	}
	
	/**
	 * 登出
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value = "/admin/logout.htm", method = RequestMethod.GET)
	public String logout(HttpServletRequest request,HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute(SessionConstant.CURRENT_USER);
		session.invalidate();
		return "redirect:/login.htm";
	}
	
}
