package com.revanow.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.revanow.auth.entity.Role;
import com.revanow.auth.entity.User;
import com.revanow.auth.entity.UserRole;

/**
 * 用户角色关系表
 * @creator     拓者码工
 * @create-time 2016/2/15 11:25:47
 * @email developer@tocersoft.com
 * @company www.tocersoft.com
 * @version 1.0
 */
@Repository("userRoleDaoImpl")
public interface IUserRoleDao {

	/**
	 * 根据用户ID查询所有角色
	 */
	List<Role> listRoleByUserId(@Param("userId") Long userId);
	
	/**
	 * 根据角色ID查询用户
	 * @param roleId 角色ID
	 * @param searchUserCount 查询用户数量
	 * @return
	 */
	List<User> listUserByRoleId(@Param("roleId") Long roleId,@Param("searchUserCount") Integer searchUserCount);

	/**
	 * 新增
	 * @param item 用户角色关系表
	 */
	void add(UserRole item);

	/**
	 * 根据用户ID批量删除数据
	 * @param ids ID集合
	 */
	void delByUserId(@Param("userId") Long userId);
	
	void delByUserIdAndRoleId(@Param("userId") Long id,@Param("roleId") long l);

	

}