package com.revanow.auth.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.revanow.auth.dto.UserCondition;
import com.revanow.auth.entity.User;
import com.revanow.base.page.PageResult;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public interface IUserService{
	/**
	 * 查询所有用户
	 */
	List<User> listUserAll();

	/**
	 * 分页查询用户
	 * @param pageResult
	 * @param condition
	 */
	void listUserByPage(PageResult<User> pageResult,UserCondition condition);

	/**
	 * 根据用户名获取用户
	 * @param account
	 * @return
	 */
	User getUserByAccount(String account);
	
	/**
	 * 根据ID查询用户详细，包含角色信息
	 * @param id
	 * @return
	 */
	User getUserDetailById(Long id);
	
	
	/**
	 * 保存用户
	 * @param item 用户对象
	 * @param departId 
	 */
	void saveUser(User item, Long departId);
	
	/**
	 * 修改用户状态
	 * @param item
	 */
	void updateState(User item);
	
	/**
	 * 重置密码
	 * @param item
	 */
	void resetPwd(User item);
	
	/**
	 * 删除用户
	 * @param id
	 */
	void delByIds(String[] ids);
	
	/**
	 * 批量更换部门
	 * @param selIds 多用户ID
	 * @param oldDepartId 旧部门ID
	 * @param departId 部门ID
	 * @param updateBy 修改人
	 */
	void doMoveDepart(String selIds,String oldDepartId,String departId,String updateBy);
	
	/**
	 * 设置主管
	 * @param item
	 */
	void updateManager(User item);

	int getUserCountByDepartId(String id);

}