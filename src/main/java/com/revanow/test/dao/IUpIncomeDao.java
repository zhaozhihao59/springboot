package com.revanow.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.revanow.test.entity.UpIncome;
@Repository("upIncomeDaoImpl")
public interface IUpIncomeDao{

	/**
	 * 查询所有UP主的收入
	 */
	List<UpIncome> listUpIncomeAll();


	/**
	 * 根据ID查询
	 *@param id 主键
	 *@return UP主的收入
	 */
	UpIncome getUpIncomeById(@Param("id") Integer id);

	/**
	 * 新增
	 *@param item UP主的收入
	 */
	void add(UpIncome item);

	/**
	 * 批量新增
	 *@param List 
	 */
	void batchInsert(List<UpIncome> arrayList);

	/**
	 * 修改
	 *@param item UP主的收入
	 */
	void update(UpIncome item);

	/**
	 * 删除
	 *@param item UP主的收入
	 */
	void delByIds(String[] ids);

}
