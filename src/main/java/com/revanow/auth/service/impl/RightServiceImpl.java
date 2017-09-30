package com.revanow.auth.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.revanow.auth.dao.IRightDao;
import com.revanow.auth.dao.IRoleDao;
import com.revanow.auth.dao.IRoleRightDao;
import com.revanow.auth.entity.Right;
import com.revanow.auth.entity.RoleRight;
import com.revanow.auth.entity.User;
import com.revanow.auth.service.IRightService;
import com.revanow.base.constant.SystemConstant;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Service("rightServiceImpl")
@Transactional(value = "transactionManager")
public class RightServiceImpl implements IRightService{

	@Resource(name="rightDaoImpl")
	private IRightDao rightDao;
	@Resource(name="roleDaoImpl")
	private IRoleDao iroledao;
	@Resource(name = "roleRightDaoImpl")
	private IRoleRightDao roleRightDao;
	public List<Right> listRightAll() {
		return rightDao.listRightAll();
	}

	public List<Right> listRightByUserId(Long userId) {
		return rightDao.listRightByUserId(userId);
	}
	
	@Override
	public List<Right> listRightByRoleId(Long roleId) {
		return rightDao.listRightByRoleId(roleId);
	}
	
	@Override
	public List<Right> listRightByParentId(Long pid, Integer level,Long departId) {
		List<Right> rights = listRightByPidLoop(pid, level,departId);
		return rights;
	}
	public static void main(String[] args) {
		String a = "bca";
		System.out.println(a.substring("bc".length()));
	}
	public String buildUserRightJSONStr(User user,Map<String, String> paramMap){
		String userRightJsonStr = null;
		List<Right> rights = new ArrayList<Right>();
		/*
		 * admin 拥有最大权限数据
		 */
		if (user.getAccount().equals(SystemConstant.ADMIN_USER)) {
			rights = rightDao.listRightAll();
		} else {
			rights = rightDao.listRightByUserId(user.getId());
		}

		JSONArray root = new JSONArray();
		// 构建权限
		for (int i = 0; i < rights.size(); i++) {
			Right permission = rights.get(i);
			
			if (permission.getLocation() == 1) { // 1级
				if(permission.getHidden() != null && permission.getHidden() == 1){
					continue;
				}
				JSONObject first = new JSONObject();
				first.put("id", permission.getId());
				first.put("text", permission.getName());
				first.put("url", permission.getUrl());
				first.put("location", permission.getLocation());
				first.put("iconPath", permission.getIconPath());
				first.put("service", permission.getService());
				this.buildRights(first, permission, rights,paramMap);

				root.add(first);
			}

		}

		userRightJsonStr = root.toJSONString();
		
		return userRightJsonStr;
	}
	
	/**
	 * 构建权限
	 * @param paramMap 
	 * 
	 * @param first
	 * @param permission
	 */
	private void buildRights(JSONObject po, Right parent,List<Right> permissions, Map<String, String> paramMap) {
		JSONArray children = new JSONArray();
		po.put("children", children);
		for (int i = 0; i < permissions.size(); i++) {
			Right permission = permissions.get(i);
			if (null != permission.getParentId()
					&& permission.getParentId().longValue() == parent.getId().longValue()) {
				if(null != permission.getHidden() && permission.getHidden() == 1){
					continue;
				}
				JSONObject node = new JSONObject();
				node.put("id", permission.getId());
				node.put("pcode", permission.getPcode());
				node.put("text", permission.getName());
				String paramurl = permission.getUrl();
				String compareUrl = paramMap.get("url").substring(paramMap.get("url").indexOf("//")+1);
				if(paramurl != null){
					paramurl = paramurl.substring(paramurl.indexOf("//") + 1);
					if(paramurl.indexOf(compareUrl) == 0){
						permission.setUrl("admin/welcomBI.htm?id="+permission.getId());
					}
				}
				paramurl = permission.getUrl();
				//判断是否需要维护
				if((permission.getService() != null && permission.getService() == 1) ||( po.getInteger("service") != null && po.getInteger("service") == 1)){
					if(paramurl.indexOf("?")>=0){
						paramurl += "&serviceState=1";
					}else{
						paramurl += "?serviceState=1";
					}
				}
				node.put("url", paramurl);
				
				node.put("location", permission.getLocation());
				node.put("iconPath", permission.getIconPath());
				node.put("service", permission.getService());
				this.buildRights(node, permission, permissions,paramMap);
				children.add(node);

			}
		}
	}
	
	/**
	 * 递归得到数据权限树
	 * @param pid
	 * @param level
	 * @return
	 */
	private List<Right> listRightByPidLoop(Long pid, Integer level,Long departId){
		
		// 符合条件的权限结果集
		List<Right> rights = null;
		
		// 如果级数 > 0，说明至少1级才能查出结果
		if(level > 0){
			// 查询PID下的权限
			rights = rightDao.listRightByParentId(pid,departId);
			// 查过一级，级数-1
			level--;
			// 递归查询下级权限
			for(Right right : rights){
				// 逐一调当前的递归方法进行逐级下查
				List<Right> childRights = listRightByPidLoop(right.getId(), level,departId);
				// 将子权限查询后存入权限实体中
				right.setChildRights(childRights);
			}
		}
		return rights;
	}
	/**
	 * 根据ID查询
	 *@param id 主键
	 *@return 
	 */
	public Right getRightById(Long id){
		return rightDao.getRightById(id);
	}

	/**
	 * 新增
	 *@param item 
	 */
	public Right add(Right item){
		rightDao.add(item);
		RoleRight roleRight = new RoleRight();
		roleRight.setRoleId(1L);
		roleRight.setRightId(item.getId());
		roleRightDao.add(roleRight);
		return item;
	}

	/**
	 * 批量新增
	 *@param List 
	 */
	public void batchInsert(List<Right> arrayList){

		rightDao.batchInsert(arrayList);
	}

	/**
	 * 修改
	 *@param item 
	 */
	public Right update(Right item){
		rightDao.update(item);
		return item;
	}

	/**
	 * 删除
	 *@param item 
	 */
	public void delByIds(String[] ids){
		rightDao.delByIds(ids);
	}

	@Override
	public List<Right> listRightByDepartId(Long departId) {
		return rightDao.listRightByDepartId(departId);
	}

}