package com.revanow.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.revanow.auth.entity.Right;
import com.revanow.auth.dto.RightCondition;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Repository("rightDaoImpl")
public interface IRightDao{

	/**
	 * 获取所有权限数据
	 * @return
	 */
	List<Right> listRightAll();
	
	/**
	 * 根据用户ID获取权限集合
	 */
	List<Right> listRightByUserId(@Param("userId") Long userId);

	/**
	 * 根据父级ID查询下级操作权限
	 * @param 	pid		父级ID
	 * @return
	 */
	List<Right> listRightByParentId(@Param("pid") Long pid,@Param("departId")Long departId);
	
	/**
	 * 根据角色ID获取权限数据
	 * @param roleId
	 * @return
	 */
	List<Right> listRightByRoleId(@Param("roleId") Long roleId);
		
	/**
	 * 根据ID查询
	 *@param id 主键
	 *@return 
	 */
	Right getRightById(@Param("id") Long id);

	/**
	 * 新增
	 *@param item 
	 */
	void add(Right item);

	/**
	 * 批量新增
	 *@param List 
	 */
	void batchInsert(List<Right> arrayList);

	/**
	 * 修改
	 *@param item 
	 */
	void update(Right item);

	/**
	 * 删除
	 *@param item 
	 */
	void delByIds(String[] ids);

	List<Right> listRightByDepartId(@Param("departId") Long departId);

}