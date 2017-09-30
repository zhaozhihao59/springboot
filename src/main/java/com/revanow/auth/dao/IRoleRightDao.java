package com.revanow.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.revanow.auth.entity.Role;
import com.revanow.auth.entity.RoleRight;

/**
 * 角色菜单关系表
 * @creator     拓者码工
 * @create-time 2016/2/15 11:53:08
 * @email developer@tocersoft.com
 * @company www.tocersoft.com
 * @version 1.0
 */
@Repository("roleRightDaoImpl")
public interface IRoleRightDao {

	/**
	 * 根据用户ID获取用户角色
	 * @param userId
	 * @return
	 */
	List<Role> listRoleByUserId(@Param("userId") String userId);

	/**
	 * 新增
	 * @param item 角色菜单关系表
	 */
	void add(RoleRight item);

	/**
	 * 根据角色ID集合批量删除
	 * @param ids ID集合
	 */
	void delByRoleId(Long roleId);

}