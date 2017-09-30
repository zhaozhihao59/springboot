package com.revanow.auth.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.revanow.auth.entity.Depart;
import com.revanow.auth.entity.User;
import com.revanow.auth.form.DepartForm;
import com.revanow.auth.service.IDepartService;
import com.revanow.auth.service.IUserService;
import com.revanow.base.constant.SessionConstant;
import com.revanow.base.controller.BaseController;

/**
 * 部门控制器
 * @creator     zhangqiang
 * @create-time 2016年2月13日   上午9:42:13
 * @email zhangqiang@tocersoft.com
 * @company www.tocersoft.com
 * @version 1.0
 */
@Controller("departController")
@Scope("prototype")
@RequestMapping(value = "/admin/auth/depart")
public class DepartController extends BaseController {

	@Resource(name = "departServiceImpl")
	private IDepartService departService;
	@Resource(name = "userServiceImpl")
	private IUserService userService;
	
	/**
	 * 显示部门树，默认展开第一级
	 * @return
	 */
	@RequestMapping(value = "/list_depart.htm", method = RequestMethod.POST)
	public ModelAndView listDepartFirstTree(DepartForm model,HttpSession session){
		User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
		JSONArray root = new JSONArray();
		List<Depart> list=new ArrayList<Depart>();
		// 初次加载
		if(StringUtils.isBlank(model.getPid())){
			// 构造“全部”项
			Depart depart = departService.getDepartById("1");
			JSONObject topJson = makeJsonObject(depart);
			topJson.put("isParent", true);
			topJson.put("open", true);
			JSONArray children = new JSONArray();
			if(1 == currentUser.getDepartId()){
				list = departService.listDepartByPid(currentUser.getDepartId().toString());
				
				for (int i = 0; i < list.size(); i++){
					JSONObject json = makeJsonObject(list.get(i));
					children.add(json);
				}
			}else{
				children.add(makeJsonObject(departService.getDepartById(currentUser.getDepartId().toString())));
//				list = departService.listDepartByPid(currentUser.getDepartId().toString());
//				for (int i = 0; i < list.size(); i++) {
//					JSONObject json = makeJsonObject(list.get(i));
//					children.add(json);
//				}
			}
			
			topJson.put("children", children);
			root.add(topJson);
			
		// 点击后加载
		}else{
			list = departService.listDepartByPid(model.getPid());
			for (int i = 0; i < list.size(); i++){
				JSONObject json = makeJsonObject(list.get(i));
				root.add(json);
			}
		}
		
		Map<String,String> data = new HashMap<String,String>();
		data.put("data", root.toJSONString());
		
		return ajaxJSON(Status.success, "加载成功",data);
	}
	
	/**
	 * 跳转到修改部门页面
	 */
	@RequestMapping(value = "/edit.htm", method = RequestMethod.GET)
	public ModelAndView edit(DepartForm model) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/auth/depart_add");
		
		Map<String,Object> data = view.getModel();
		data.put("item", model.getItem());
		
		if(null != model.getItem().getId()){
			// 获得部门信息
			Depart item = departService.getDepartById(model.getItem().getId().toString());
			data.put("item", item);
		}
		return view;
	}
	
	/**
	 * 新增类别
	 */
	@RequestMapping(value = "/doSaveDepart.htm", method = RequestMethod.POST)
	public ModelAndView doSaveDepart(DepartForm model,HttpServletRequest request,HttpSession session) throws Exception {
		try {
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			model.getItem().setCreateBy(currentUser.getAccount());
			model.getItem().setUpdateBy(currentUser.getAccount());

			Map<String,String> data = new HashMap<String,String>();
			
			if(null == model.getItem().getId()){
				model.getItem().setParentId(currentUser.getDepartId().toString());
				departService.add(model.getItem());
				data.put("itemId", model.getItem().getId().toString());
			}else{
				List<Depart> list = departService.listDepartByPid(currentUser.getDepartId().toString());
				Set<Long> departIdsSet = new HashSet<Long>();
				for (Depart item : list) {
					departIdsSet.add(item.getId());
				}
				if(!departIdsSet.contains(model.getItem().getId())){
					return ajaxJSON(Status.error, "无权限修改");
				}
				departService.update(model.getItem());
			}
			
			return ajaxJSON(Status.success,"保存成功",data);
		} catch (Exception e) {
			String msg = "保存部门节点时发生异常："+e.getMessage();
			logger.error(msg,e);
			return ajaxJSON(Status.error,"保存失败",true,request);
		}
	}
	


	/**
	 * 删除
	 * @return 
	 */
	@RequestMapping(value = "/del.htm", method = RequestMethod.POST)
	public ModelAndView del(DepartForm model){
		try {
			List<String> delIds = new ArrayList<String>();
			String[] ids = model.getSelIds().split(",");
			for(String id : ids){
				//判断部门下的人数
				int userCount = userService.getUserCountByDepartId(id);
				if(userCount > 0){
					continue;
				}
				delIds.add(id);
			}
			if(delIds.size()!=0){
				departService.delByIds(delIds.toArray(new String[delIds.size()]));
			}else{
				return ajaxJSON(Status.error,"请先将该部门下的人员转移到其他部门下再删除");
			}
			return ajaxJSON(Status.success,"删除成功");
		} catch (Exception e) {
			String msg = "删除部门时发生异常：" + e.getMessage();
			logger.error(msg,e);
			return ajaxJSON(Status.error, msg);
		}
	}
	
	/**
	 * 构造JSON对象
	 * @param depart
	 * @return
	 */
	private JSONObject makeJsonObject(Depart depart){
		JSONObject data = new JSONObject();
		data.put("id",depart.getId());
		data.put("name",depart.getName());
		
		//根据部门ID查询部门下的子部门数量
		int childCount = departService.getCountByParentId(depart.getId().toString());
		if(childCount > 0){
			data.put("isParent", true);
		}else{
			data.put("isParent", false);
		}
		
		data.put("parentId",depart.getParentId());
		
		return data;
	}

	/**
	 * 跳转到移动部门页面
	 */
	@RequestMapping(value = "/move.htm", method = RequestMethod.GET)
	public ModelAndView move(DepartForm model) {
		ModelAndView view = new ModelAndView();
		view.setViewName("/admin/auth/depart_move");
		Map<String,Object> data = view.getModel();
		data.put("selIds", model.getSelIds());
		data.put("moveType", model.getMoveType());
		data.put("oldDepartId", model.getOldDepartId());
		return view;
	}
	
	/**
	 * 执行移动部门
	 */
	@RequestMapping(value = "/doMove.htm", method = RequestMethod.POST)
	public ModelAndView doMove(DepartForm model,HttpServletRequest request,HttpSession session) throws Exception {
		try {
			User currentUser = (User) session.getAttribute(SessionConstant.CURRENT_USER);
			Long curDepartId = currentUser.getDepartId();
			if("1".equals(model.getNewDepartId())){
				model.setNewDepartId(curDepartId.toString());
			}
			departService.doMove(model.getSelIds(), model.getNewDepartId(), currentUser.getAccount());
			return ajaxJSON(Status.success,"移动成功");
		} catch (Exception e) {
			String msg = "移动部门节点时发生异常："+e.getMessage();
			logger.error(msg,e);
			return ajaxJSON(Status.error, msg);
		}
	}
}