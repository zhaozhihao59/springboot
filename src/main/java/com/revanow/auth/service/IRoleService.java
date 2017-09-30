package com.revanow.auth.service;

import java.util.List;

import com.revanow.auth.dto.RoleCondition;
import com.revanow.auth.entity.Role;
import com.revanow.base.page.PageResult;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public interface IRoleService{

	/**
	 * 查询所有角色
	 * @return
	 */
	List<Role> listRoleAll();


	/**
	 * 获取角色
	 * @param id
	 * @return
	 */
	Role getRoleById(Long id);

	/**
	 * 保存角色
	 * @param saveRoleVO
	 */
	void saveRole(Role item);

	/**
	 * 删除角色
	 * @param id
	 */
	void delByIds(String[] ids);

	/**
	 * 分页查询角色
	 * @param pageResult
	 * @param condition
	 */
	void listRoleByPage(PageResult<Role> pageResult,RoleCondition condition);

	/**
	 * 检查角色代码是否重复
	 * @param item
	 * @return
	 */
	boolean checkCodeRepeat(Role item);
	
	/**
	 * 设置角色类型
	 * @param ids
	 * @param type
	 */
	void setSysRole(String[] ids,Integer type);

}