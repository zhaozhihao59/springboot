package com.revanow.auth.service;

import java.util.List;
import java.util.Map;

import com.revanow.auth.entity.Right;
import com.revanow.auth.entity.User;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public interface IRightService{

	/**
	 * 获取全部权限集合
	 * @return
	 */
	List<Right> listRightAll();
	
	/**
	 * 根据父级ID查询下级操作权限
	 * @param 	pid		父级ID
	 * @param departId 
	 * @param departId 
	 * @return
	 */
	List<Right> listRightByParentId(Long pid, Integer level, Long departId);

	/**
	 * 根据用户ID获取权限集合
	 * @param userId
	 * @return
	 */
	List<Right> listRightByUserId(Long userId);
	
	/**
	 * 根据角色ID得到权限
	 * @param roleId
	 * @return
	 */
	List<Right> listRightByRoleId(Long roleId);

	/**
	 * 构造用户权限JSON数据
	 * @param user
	 * @param paramMap 
	 * @return
	 */
	String buildUserRightJSONStr(User user, Map<String, String> paramMap);

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	Right getRightById(Long id);
	/**
	 * 新增
	 *@param item 
	 */
	Right add(Right item);

	/**
	 * 批量新增
	 *@param List 
	 */
	void batchInsert(List<Right> arrayList);

	/**
	 * 修改
	 *@param item 
	 */
	Right update(Right item);

	/**
	 * 删除
	 *@param item 
	 */
	void delByIds(String[] ids);
	/**
	 * 得到权限通过部门
	 * @param departId
	 * @return
	 */
	List<Right> listRightByDepartId(Long departId);

}