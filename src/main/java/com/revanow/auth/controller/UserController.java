package com.revanow.auth.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.revanow.auth.entity.User;
import com.revanow.auth.form.UserForm;
import com.revanow.auth.service.IUserService;
import com.revanow.base.constant.SessionConstant;
import com.revanow.base.controller.BaseController;



/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Controller("userController")
@Scope("prototype")
@RequestMapping("/admin/auth/user")
public class UserController extends BaseController {

	@Resource(name = "userServiceImpl")
	private IUserService userService;
	
	/**
	 * 用户管理
	 * @return
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView index(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/auth/user_list");
		return view;
	}
	
	
	/**
	 * 分页查询用户
	 * @return
	 */
	@RequestMapping(value = "/listUserByPage.htm", method = RequestMethod.POST)
	public void listUserByPage(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try{
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			Long curDepartId = currentUser.getDepartId();
			if("1".equals(model.getCondition().getDepartId()) || null == model.getCondition().getDepartId()){
				model.getCondition().setDepartId(curDepartId.toString());
			}
			userService.listUserByPage(model.getPageResult(),model.getCondition());
			JSONObject root = toPageJson(model.getPageResult(), new String[]{"id","staffId","name","sex","birthday","mobile","tel","email","contact","account","password","state","onlineState","isChangePwd","roleNames","departNames","isManager","departId"});
			ajaxPageResult(root,response);
		}catch(Exception ex){
			String msg = "查询用户时发生异常："+ex.getMessage();
			logger.error(msg,ex);
		}
	}
	
	/**
	 * 跳转到新增或修改用户页面
	 * @return
	 */
	@RequestMapping(value = "/edit.htm", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		Map<String, Object> data=view.getModel();
		view.setViewName("/admin/auth/user_add");
		data.put("item",model.getItem());
		if(null != model.getItem().getId()){
			User item = userService.getUserDetailById(model.getItem().getId());
			data.put("item", item);
		}
		
		return view;
	}
	
	/**
	 * 保存用户
	 * @return
	 */
	@RequestMapping(value = "/saveUser.htm", method = RequestMethod.POST)
	public ModelAndView saveUser(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			model.getItem().setCreateBy(currentUser.getAccount());
			model.getItem().setUpdateBy(currentUser.getAccount());
			userService.saveUser(model.getItem(),model.getItem().getDepartId());
			return ajaxJSON(Status.success,"保存成功");
		} catch (Exception e) {
			String msg = "保存用户时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajaxJSON(Status.error,"保存失败");
		}
	}
	
	
	/**
	 * 修改用户状态
	 */
	@RequestMapping(value = "/updateState.htm", method = RequestMethod.POST)
	public ModelAndView updateState(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			model.getItem().setUpdateBy(currentUser.getAccount());
			
			userService.updateState(model.getItem());
			return ajaxJSON(Status.success,"修改成功");
		} catch (Exception e) {
			String msg = "修改用户状态时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajaxJSON(Status.error,"修改失败");
		}
	}
	
	
	/**
	 * 重置密码
	 * @return
	 */
	@RequestMapping(value = "/resetPwd.htm", method = RequestMethod.POST)
	public ModelAndView resetPwd(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			model.getItem().setUpdateBy(currentUser.getAccount());
			
			userService.resetPwd(model.getItem());
			return ajaxJSON(Status.success,"重置成功");
		} catch (Exception e) {
			String msg = "重置密码时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajaxJSON(Status.error,"重置失败");
		}
	}
	
	
	/**
	 * 删除用户
	 * @return 
	 */
	@RequestMapping(value = "/del.htm", method = RequestMethod.POST)
	public ModelAndView del(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try {
			userService.delByIds(model.getSelIds().split(","));
			return ajaxJSON(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "删除用户时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajaxJSON(Status.error, "删除失败，请稍后重试");
		}
	}
	

	/**
	 * 弹出选择角色页面
	 * @return
	 */
	@RequestMapping(value = "/choose_role.htm", method = RequestMethod.GET)
	public ModelAndView chooseRole(@ModelAttribute UserForm model,HttpServletRequest request,HttpServletResponse response){
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/auth/user_choose_role");
		Map<String, Object> data = view.getModel();
		data.put("item", model.getItem());
		return view;
	}
	
	/**
	 * 用户批量移动部门
	 */
	@RequestMapping(value = "/doMove.htm", method = RequestMethod.POST)
	public ModelAndView doMove(@ModelAttribute UserForm model,HttpServletRequest request,HttpSession session){
		try{
			User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			userService.doMoveDepart(model.getSelIds(),model.getOldDepartId(), model.getNewDepartId(), user.getAccount());
			return ajaxJSON(Status.success,"移动成功");
		}catch(Exception ex){
			String msg = "更换部门时发生异常：" + ex.getMessage();
			logger.error(msg,ex);
			return ajaxJSON(Status.error,"移动失败，请稍后重试");
		}
	}
	
	/**
	 * 设置主管
	 */
	@RequestMapping(value = "/updateManager.htm", method = RequestMethod.POST)
	public ModelAndView updateManager(@ModelAttribute UserForm model,HttpServletRequest request,HttpSession session){
		try{
			User user = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			model.getItem().setUpdateBy(user.getAccount());
			userService.updateManager(model.getItem());
			
			return ajaxJSON(Status.success,"设置主管成功");
		}catch(Exception ex){
			String msg = "设置主管时发生异常：" + ex.getMessage();
			logger.error(msg,ex);
			return ajaxJSON(Status.error,"设置主管失败，请稍后重试");
		}
	}
	
}
