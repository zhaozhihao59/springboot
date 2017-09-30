package com.revanow.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.revanow.auth.dto.DepartCondition;
import com.revanow.auth.entity.Depart;

/**
 * 部门dao
 * @creator     zhangqiang
 * @create-time 2016年2月15日   下午1:48:10
 * @email zhangqiang@tocersoft.com
 * @company www.tocersoft.com
 * @version 1.0
 */
@Repository("departDaoImpl")
public interface IDepartDao {
	
	/**
	 * 查询全部部门
	 * @param value
	 * @return
	 */
	List<Depart> listDepartAll();

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Depart> listDepartByPage(RowBounds bounds,@Param("condition") DepartCondition condition);
	
	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listDepartByPageCount(@Param("condition") DepartCondition condition);

	/**
	 * 根据上级ID查询部门列表
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<Depart> listDepartByPid(@Param("parentId") String parentId);
	
	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	Depart getDepartById(@Param("id") String id);
	
	/**
	 * 根据父级ID查询部门数量
	 * @param parentId
	 * @return
	 */
	int getCountByParentId(@Param("parentId") String parentId);
	
	/**
	 * 获取同级别最大排序
	 * 
	 * @param parentId
	 * @return
	 */
	Integer getMaxSort(@Param("parentId") String parentId);

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
	 * 根据NAME查询
	 * @param Name 名称
	 * @return 部门表
	 */
	Depart getDepartByName(@Param("name") String name);
 
}

