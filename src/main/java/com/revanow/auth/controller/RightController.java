package com.revanow.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.revanow.auth.entity.Right;
import com.revanow.auth.entity.User;
import com.revanow.auth.form.RightForm;
import com.revanow.auth.service.IRightService;
import com.revanow.auth.service.IRoleService;
import com.revanow.base.constant.SessionConstant;
import com.revanow.base.controller.BaseController;



/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Controller("rightController")
@Scope("prototype")
@RequestMapping("/admin/auth/right")
public class RightController extends BaseController {

	@Resource(name = "rightServiceImpl")
	private IRightService rightService;
	@Resource(name = "roleServiceImpl")
	private IRoleService roleService;
	@Value("${tab.server}")
	private String url;
	/**
	 * 首页
	 * @return 
	 */
	@RequestMapping(value = "/index.htm", method = RequestMethod.GET)
	public String index(){
		return "/admin/auth/right_list";
	}
	/**
	 * 展示树状操作权限
	 * @return
	 */
	@RequestMapping(value = "/listRightByTree.htm", method = RequestMethod.POST)
	public ModelAndView listRightByTree(Long pid,HttpSession session){
		JSONArray root = new JSONArray();
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		List<Right> list = rightService.listRightByParentId(pid,3,currentUser.getDepartId());
		if(null != list && list.size() > 0){
			for (int i = 0; i < list.size(); i++){
				Right right = list.get(i);
				JSONObject json = makeRightTreeJsonForSet(right);
				
				List<Right> listChild = right.getChildRights();
				JSONArray arrayChild = new JSONArray();
				for(Right rightChild : listChild){
					JSONObject obj = makeRightTreeJsonForSet(rightChild);
					List<Right> listChildSub = rightChild.getChildRights();
					JSONArray arrayChildSub = new JSONArray();
					for(Right rightChildSub : listChildSub){
						JSONObject objSub = makeRightTreeJsonForSet(rightChildSub);
						arrayChildSub.add(objSub);
					}
					obj.put("children", arrayChildSub);
					arrayChild.add(obj);
				}
				json.put("children", arrayChild);
				root.add(json);
			}
		}
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("data", root.toJSONString());
		
		return ajaxJSON(Status.success, "加载成功",data);
	}
	

	/**
	 * 根据角色ID查询权限
	 * @param roleId
	 * @return
	 */
	@RequestMapping(value = "/listRightByRoleId.htm", method = RequestMethod.POST)
	public ModelAndView listRightByRoleId(String roleId,HttpSession session) {
		List<Right> rightList = new ArrayList<Right>();
		if(StringUtils.isNotBlank(roleId)){
			rightList = rightService.listRightByRoleId(NumberUtils.toLong(roleId));
		}
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		List<Right> rightAll = rightService.listRightByDepartId(currentUser.getDepartId());
		JSONArray result = new JSONArray();
		
		for (Right right : rightAll) {
			boolean checked = false;
			if(!CollectionUtils.isEmpty(rightList)){
				for (Right r : rightList) {
					if(right.getId().equals(r.getId())){
						checked = true;
					}
				}
			}
			result.add(makeRightTreeJsonObject(right,checked));
		}
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("data", result.toJSONString());
		
		return ajaxJSON(Status.success, "加载成功",data);
	}


	/**
	 * 为权限设置构建树形菜单结构
	 * @param right
	 * @return
	 */
	private JSONObject makeRightTreeJsonForSet(Right right) {
		JSONObject data = new JSONObject();
		data.put("id", right.getId());
		data.put("name", right.getName());
		data.put("tip", right.getTip());
		data.put("sort", right.getSort());
		data.put("funUrl", right.getUrl());
		data.put("location", right.getLocation());
		data.put("parentId", right.getParentId());
		data.put("iconPath", right.getIconPath());
		data.put("service", right.getService());
		data.put("hidden", right.getHidden());
		if(null != right.getLocation() && right.getLocation().intValue() != 3){
			data.put("isParent", true);
		}else{
			data.put("isParent", false);
		}
		if(null != right.getLocation() && right.getLocation().intValue() == 1){
			data.put("open", true);
		}else{
			data.put("open", false);
		}
		return data;
	}

	private JSONObject makeRightTreeJsonObject(Right right,Boolean checked) {
		JSONObject data = new JSONObject();
		data.put("id", right.getId());
		data.put("pid", right.getParentId());
		data.put("name", right.getName());
		data.put("checked", checked);
		data.put("open", true);
		data.put("sort", right.getSort());
		data.put("tip", right.getTip());
		data.put("url", right.getUrl());
		data.put("location", right.getLocation());
		data.put("parentId", right.getParentId());
		data.put("iconPath", right.getIconPath());
		data.put("service", right.getService());
		data.put("hidden", right.getHidden());
		return data;
	}
	
	/**
	 * 跳转到修改类别页面
	 */
	@RequestMapping(value = "/edit.htm", method = RequestMethod.GET)
	public ModelAndView edit(@ModelAttribute RightForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("/admin/auth/right_add");
		//传递数据
		Map<String,Object> data = modelAndView.getModel();
		if(null != model.getItem().getId()){
			// 获得类别的ID
			Long nodeId = model.getItem().getId();
			// 获得类别信息
			Right item = rightService.getRightById(nodeId);
			data.put("item", item);
		}else {
			model.getItem().setService(0);
			data.put("item", model.getItem());
		}
		return modelAndView;
	}
	
	/**
	 * 保存权限
	 */
	@RequestMapping(value = "/saveRight.htm", method = RequestMethod.POST)
	public ModelAndView saveRight(@ModelAttribute RightForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		try {
			if (StringUtils.isBlank(model.getItem().getName())) {
				return ajaxJSON(Status.error, "权限名称不能为空");
			}
			User curUser = (User)session.getAttribute(SessionConstant.CURRENT_USER);
			// 排序
			Integer sort = model.getItem().getSort() == null ? 0 : model.getItem().getSort() ;
			model.getItem().setSort(sort);
			if(null == model.getItem().getId()){
				model.getItem().setCreateBy(curUser.getAccount());
				model.getItem().setUpdateBy(curUser.getAccount());
				rightService.add(model.getItem());
			}else{
				model.getItem().setUpdateBy(curUser.getAccount());
				rightService.update(model.getItem()); 
				return ajaxJSON(Status.success,"保存成功");
			}
			Map<String,String> data = new HashMap<String,String>();
			data.put("itemId", model.getItem().getId().toString());
			return ajaxJSON(Status.success, "保存成功", data);
			
		} catch (Exception e) {
			String msg = "新增菜单时发生异常：" + e.getMessage();
			logger.error(msg,e);
			
			return ajaxJSON(Status.error,"保存失败");
		}
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value = "/del.htm", method = RequestMethod.POST)
	public ModelAndView del(@ModelAttribute RightForm model,HttpServletRequest request,HttpServletResponse response,HttpSession session) throws Exception {
		try{
			rightService.delByIds(model.getSelIds().split(","));
			return ajaxJSON(Status.success,"删除成功");
		}catch(Exception ex){
			String msg = "删除菜单时发生异常：" + ex.getMessage();
			logger.error(msg,ex);
			
			return ajaxJSON(Status.error,"删除失败");
		}
	}
}
