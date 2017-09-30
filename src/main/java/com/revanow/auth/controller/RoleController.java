package com.revanow.auth.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.revanow.auth.entity.Right;
import com.revanow.auth.entity.Role;
import com.revanow.auth.entity.User;
import com.revanow.auth.form.RoleForm;
import com.revanow.auth.service.IRightService;
import com.revanow.auth.service.IRoleService;
import com.revanow.auth.service.IUserService;
import com.revanow.base.constant.SessionConstant;
import com.revanow.base.constant.SystemConstant;
import com.revanow.base.controller.BaseController;



/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Controller("roleController")
@Scope("prototype")
@RequestMapping("/admin/auth/role")
public class RoleController extends BaseController {

	@Resource(name = "rightServiceImpl")
	private IRightService rightService;
	@Resource(name = "roleServiceImpl")
	private IRoleService roleService;
	@Resource(name = "userServiceImpl")
	private IUserService userService;

	/**
	 * 角色权限首页
	 * @return
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public ModelAndView index(HttpSession session) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/auth/role_list");
		Map<String,Object> data = view.getModel();
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		//admin账号才有权限设置角色类型
		if(currentUser.getAccount().equals("admin")){
			data.put("haveRigth", true);
		}else{
			data.put("haveRigth", false);
		}
		return view;
	}
	
	/**
	 * 分页加载角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/listRoleByPage.htm", method = RequestMethod.POST)
	public void listRoleByPage(@ModelAttribute RoleForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		try{
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			model.getCondition().setDepartId(currentUser.getDepartId());
//			if(null != model.getItem().getNewDepartId() && model.getItem().getNewDepartId() != 1){
//				model.getCondition().setDepartId(model.getItem().getNewDepartId());
//			}
			
			roleService.listRoleByPage(model.getPageResult(), model.getCondition());
			JSONObject root = toPageJson(model.getPageResult(), null);
			ajaxPageResult(root,response);
		}catch(Exception ex){
			String msg = "查询角色时发生异常："+ex.getMessage();
			logger.error(msg,ex);
			ajaxJSON(Status.error, "查询失败");
		}
	}

	/**
	 * 添加角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/edit.htm", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute RoleForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/auth/role_edit");
		List<Right> rightList = rightService.listRightByParentId(0L, 1,currentUser.getDepartId());
		Map<String,Object> data = view.getModel();
		data.put("rights", rightList);
		
		//admin账号才有权限设置角色类型
		if(currentUser.getAccount().equals("admin")){
			data.put("haveRight", true);
		}else{
			data.put("haveRight", false);
		}
		if(null != model.getItem().getId()){
			Role item = roleService.getRoleById(model.getItem().getId());
			data.put("item", item);
		}
		
		return view;
	}
	
	/**
	 * 保存角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/saveRole.htm", method = RequestMethod.POST)
	public ModelAndView saveRole(@ModelAttribute("saveRoleVO") RoleForm model,BindingResult bindingResult,HttpServletRequest request,HttpSession session){
		try{
			//1.检查角色代码是否重复
			boolean checkRepeatResult = roleService.checkCodeRepeat(model.getItem());
			if(checkRepeatResult){
				return ajaxJSON(Status.error,"角色代码已经存在",true,request);
			}
			//2.保存角色
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			model.getItem().setCreateBy(currentUser.getAccount());
			model.getItem().setUpdateBy(currentUser.getAccount());
			if(null == model.getItem().getNewDepartId() || 1 == model.getItem().getNewDepartId()){
				model.getItem().setNewDepartId(currentUser.getDepartId());
			}
			roleService.saveRole(model.getItem());
			
			return ajaxJSON(Status.success, "保存成功");
		}catch(Exception ex){
			String msg = "保存角色时发生异常:"+ex.getMessage();
			logger.error(msg,ex);
			
			return ajaxJSON(Status.error, "保存失败，请稍后重试",true,request);
		}
	}
	
	
	/**
	 * 删除角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/del.htm", method = RequestMethod.POST)
	public ModelAndView del(@ModelAttribute RoleForm model,HttpServletRequest request,HttpServletResponse response){
		try{
			//1.删除角色
			roleService.delByIds(StringUtils.split(model.getSelIds(),","));
			return ajaxJSON(Status.success, "删除成功");
		}catch(Exception ex){
			String msg = "删除角色时发生异常:"+ex.getMessage();
			logger.error(msg,ex);
			return ajaxJSON(Status.error, "删除失败，请稍后重试");
		}
	}
	
	/**
	 * 设置角色类型
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/setSysRole.htm", method = RequestMethod.POST)
	public ModelAndView setSysRole(@ModelAttribute("saveRoleVO")  RoleForm model,BindingResult bindingResult,HttpServletRequest request){
		try{
			roleService.setSysRole(StringUtils.split(model.getSelIds(),","),model.getType());
			return ajaxJSON(Status.success, "设置角色类型成功");
		}catch(Exception ex){
			String msg = "设置角色类型时发生异常:"+ex.getMessage();
			logger.error(msg,ex);
			return ajaxJSON(Status.error, "设置角色类型失败，请稍后重试");
		}
	}
}
