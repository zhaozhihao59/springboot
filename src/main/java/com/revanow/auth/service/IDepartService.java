package com.revanow.auth.service;

import java.util.List;

import com.revanow.auth.entity.Depart;

public interface IDepartService {
	
	/**
	 * 查询全部部门
	 * @param value
	 * @return
	 */
	List<Depart> listDepartAll();

	/**
	 * 根据上级ID查询部门列表
	 * @param pageResult 分页对象
	 * @param value 上级ID
	 */
	List<Depart> listDepartByPid(String pid);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	Depart getDepartById(String id);
	
	/**
	 * 根据父级ID查询部门数量
	 * @param parentId
	 * @return
	 */
	int getCountByParentId(String parentId);

	/**
	 * 新增
	 * @param item 部门表
	 */
	void add(Depart item);

	/**
	 * 修改
	 * @param item 部门表
	 */
	void update(Depart item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);
	
	/**
	 * 移动部门
	 * @param selIds 将移动的部门ID
	 * @param departId 目标部门ID
	 * @param updateBy 修改人
	 */
	void doMove(String selIds,String departId,String updateBy);
}

