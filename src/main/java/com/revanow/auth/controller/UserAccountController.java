package com.revanow.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.revanow.auth.entity.Role;
import com.revanow.auth.entity.User;
import com.revanow.auth.form.UserForm;
import com.revanow.auth.service.IUserService;
import com.revanow.base.constant.SessionConstant;
import com.revanow.base.constant.SystemConstant;
import com.revanow.base.controller.BaseController;
import com.revanow.base.controller.BaseController.Status;
import com.revanow.base.util.EncryptUtil;

/**
 * 用户账号管理
 * @creator     zhangqiang
 * @create-time 2016年2月13日   上午9:42:21
 * @email zhangqiang@tocersoft.com
 * @company www.tocersoft.com
 * @version 1.0
 */
@Controller("userAccountController")
@Scope("prototype")
@RequestMapping(value = "/admin/user/account")
public class UserAccountController extends BaseController {

	@Resource(name="userServiceImpl")
	private IUserService userService;
	
	/**
	 * 查看详细页面
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/detail.htm", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/auth/account_update");
		
		User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		User current = userService.getUserDetailById(user.getId());
		
		// 将角色拼接成字符串 用于JSON 读取
		JSONArray roleArray = new JSONArray ();
		for(Role role:current.getRoleList()){
			JSONObject obj = new JSONObject();
			obj.put("id", role.getId());
			obj.put("name", role.getName());
			roleArray.add(obj);
		}
		
		//传递数据
		Map<String,Object> data = modelAndView.getModel();
		data.put("item", current);
		data.put("roleJSON", roleArray.toJSONString());
		
		return modelAndView;
	}
	
	/**
	 * 判断当前密码是否正确
	 */
	@RequestMapping(value = "/judgeCurrentPwd.htm", method = RequestMethod.GET)
	public ModelAndView judgeCurrentPwd(HttpServletRequest request,HttpSession session){
		try{
			String oldPwd = request.getParameter("oldPwd");
			User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			User current = userService.getUserDetailById(user.getId());
			boolean result = false;
			if(current.getPassword().equals(EncryptUtil.encryptionPw(oldPwd))){
				result = true;
			}
			
			Map<String,String> data = new HashMap<String,String>();
			data.put("result", String.valueOf(result));
			
			return ajaxJSON(Status.success, "校验成功", data);
		}catch(Exception ex){
			String msg = "判断当前密码是否正确时发生异常：" + ex.getMessage();
			logger.error(msg,ex);
			
			return ajaxJSON(Status.error,"校验失败");
		}
	}
	
	/**
	 * 保存当前用户
	 * @return
	 */
	@RequestMapping(value = "/saveCurrentUser.htm", method = RequestMethod.POST)
	public ModelAndView saveCurrentOperator(@ModelAttribute UserForm model,HttpServletRequest request,HttpSession session){
		try{
			User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			User current = userService.getUserDetailById(user.getId());
			User clientUser = model.getItem();
			current.setStaffId(clientUser.getStaffId());
			current.setBirthday(clientUser.getBirthday());
			current.setContact(clientUser.getContact());
			current.setEmail(clientUser.getEmail());
			current.setMobile(clientUser.getMobile());
			current.setName(clientUser.getName());
			current.setSex(clientUser.getSex());
			current.setTel(clientUser.getTel());
			//userService.updateUser(current);
			
			return ajaxJSON(Status.success,"保存成功");
		}catch(Exception ex){
			String msg = "保存当前用户信息时发生异常：" + ex.getMessage();
			logger.error(msg,ex);
			
			return ajaxJSON(Status.error,"保存失败，请稍后重试");
		}
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping(value = "/changeCurrentPwd.htm", method = RequestMethod.POST)
	public ModelAndView changeCurrentPwd(@ModelAttribute UserForm model,HttpServletRequest request,HttpSession session){
		try{
			User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			
			User current = userService.getUserDetailById(user.getId());
			
			if("reset".equals(model.getItem().getPassword())){
				current.setPassword(SystemConstant.DEFAULT_PASSWORD);
			}else{
				current.setPassword(model.getItem().getPassword());
			}
			current.setIsChangePwd(model.getItem().getIsChangePwd());
			userService.resetPwd(current);
			
			return ajaxJSON(Status.success,"修改成功");
		}catch(Exception ex){
			String msg = "修改密码时发生异常：" + ex.getMessage();
			logger.error(msg,ex);
			
			return ajaxJSON(Status.error,"修改失败，请稍后重试");
		}
	}
}	
