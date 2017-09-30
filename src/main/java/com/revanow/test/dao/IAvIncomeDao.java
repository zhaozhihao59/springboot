package com.revanow.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.revanow.test.entity.AvIncome;
@Repository("avIncomeDaoImpl")
public interface IAvIncomeDao{

	/**
	 * 查询所有视频的收入
	 */
	List<AvIncome> listAvIncomeAll();


	/**
	 * 根据ID查询
	 *@param id 主键
	 *@return 视频的收入
	 */
	AvIncome getAvIncomeById(@Param("id") Integer id);

	/**
	 * 新增
	 *@param item 视频的收入
	 */
	void add(AvIncome item);

	/**
	 * 批量新增
	 *@param List 
	 */
	void batchInsert(List<AvIncome> arrayList);

	/**
	 * 修改
	 *@param item 视频的收入
	 */
	void update(AvIncome item);

	/**
	 * 删除
	 *@param item 视频的收入
	 */
	void delByIds(String[] ids);

}
