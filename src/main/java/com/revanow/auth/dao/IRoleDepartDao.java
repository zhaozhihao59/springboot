package com.revanow.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.revanow.auth.entity.Role;
import com.revanow.auth.entity.RoleDepart;
/**
 * 权限部门中间表
 * @creator 赵志豪
 * @create-time 2016-11-09 14:30:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Repository("roleDepartDaoImpl")
public interface IRoleDepartDao{

	/**
	 * 查询所有权限部门中间表
	 */
	List<RoleDepart> listAuthRoleDepartAll();

	/**
	 * 根据ID查询
	 *@param id 主键
	 *@return 权限部门中间表
	 */
	RoleDepart getAuthRoleDepartById(@Param("id") Long id);

	/**
	 * 新增
	 *@param item 权限部门中间表
	 */
	void add(RoleDepart item);

	/**
	 * 批量新增
	 *@param List 
	 */
	void batchInsert(List<RoleDepart> arrayList);

	/**
	 * 修改
	 *@param item 权限部门中间表
	 */
	void update(RoleDepart item);

	/**
	 * 删除
	 *@param item 权限部门中间表
	 */
	void delByIds(String[] ids);
	
	void updateByOldRole(@Param("item") Role item);
	/**
	 * 通过部门ID得到
	 * @param departId
	 * @return
	 */
	List<Long> listRoleByDepartId(Long departId);

	void delByRoleItem(@Param("item") Role item);

}
