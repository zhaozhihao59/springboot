package com.revanow.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.revanow.auth.entity.Role;
import com.revanow.auth.dto.RoleCondition;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Repository("roleDaoImpl")
public interface IRoleDao{
	/**
	 * 查询所有角色
	 */
	List<Role> listRoleAll();

	/**
	 * 获取角色总数
	 * @param condition
	 * @return
	 */
	int listRoleByPageCount(@Param("condition") RoleCondition condition);

	/**
	 * 根据条件查询角色
	 * @param condition
	 * @return
	 */
	List<Role> listRoleByPage(RowBounds bounds,@Param("condition") RoleCondition condition);

	/**
	 * 获取角色
	 * @param id
	 * @return
	 */
	Role getRoleById(@Param("id") Long id);
	
	/**
	 * 根据角色代码获取数量
	 * @param item
	 * @return
	 */
	Integer getCountByCode(Role item);

	/**
	 * 新增角色
	 * @param saveRoleVO
	 */
	void add(Role item);

	/**
	 * 更新
	 * @param saveRoleVO
	 */
	void update(Role item);

	/**
	 * 删除角色
	 * @param id
	 */
	void delByIds(String[] ids);

}