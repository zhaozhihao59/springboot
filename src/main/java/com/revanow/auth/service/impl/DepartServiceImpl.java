package com.revanow.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.revanow.auth.dao.IDepartDao;
import com.revanow.auth.dao.IUserDao;
import com.revanow.auth.entity.Depart;
import com.revanow.auth.service.IDepartService;

@Repository("departServiceImpl")
@Transactional(value = "transactionManager")
public class DepartServiceImpl implements IDepartService{

	@Resource(name = "departDaoImpl")
	private IDepartDao departDao;
	@Resource(name = "userDaoImpl")
	private IUserDao userDao;

	/**
	 * 根据上级ID查询部门列表
	 * @param pageResult 分页对象
	 * @param value 上级ID
	 */
	@Override
	public List<Depart> listDepartByPid(String pid) {
		return departDao.listDepartByPid(pid);
	}
	
	@Override
	public List<Depart> listDepartAll() {
		return departDao.listDepartAll();
	}

	/**
	 * 根据ID查询
	 * @param id 主键
	 * @return 部门表
	 */
	public Depart getDepartById(String id){
		return departDao.getDepartById(id);
	}
	
	/**
	 * 根据父级ID查询部门数量
	 * @param parentId
	 * @return
	 */
	public int getCountByParentId(String parentId){
		return departDao.getCountByParentId(parentId);
	}

	/**
	 * 新增
	 * @param item 部门表
	 */
	public void add(Depart item){
		if(StringUtils.isBlank(item.getParentId())){
			return;
		}
		
		//判断上级
		int level = 1;
		int sort = 1;
		if(!"0".equals(item.getParentId())){
			Depart parentDepart = departDao.getDepartById(item.getParentId());
			level = parentDepart.getLevel() + 1;
		}
		
		Integer maxSort = departDao.getMaxSort(item.getParentId());
		if(null == maxSort){
			maxSort = 0;
		}
		sort = maxSort + 1;
		
		item.setSort(sort);
		item.setLevel(level);
		
		departDao.add(item);
	}

	/**
	 * 修改
	 * @param item 部门表
	 */
	public void update(Depart item){
		departDao.update(item);
	}

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		/*
		List<String> delIds = new ArrayList<String>();
		for(String id : ids){
			//判断部门下的人数
			int userCount = userDepartDao.getUserCountByDepartId(id);
			if(userCount > 0){
				continue;
			}
			delIds.add(id);
		}
		departDao.delByIds(delIds.toArray(new String[delIds.size()]));
		*/
		departDao.delByIds(ids);
	}

	/**
	 * 移动部门
	 * @param selIds 将移动的部门ID
	 * @param departId 目标部门ID
	 * @param updateBy 修改人
	 */
	@Override
	public void doMove(String selIds, String departId, String updateBy) {
		//通过上级部门获得下级部门层级
		Depart targetDepart = departDao.getDepartById(departId);
		Integer level = targetDepart.getLevel()+1;
		//处理部门迁移
		String[] array = selIds.split(",");
		for (int i = 0; i < array.length; i++) {
			String id = array[i];
			Depart depart = departDao.getDepartById(id);
			depart.setParentId(departId);
			depart.setLevel(level);
			depart.setUpdateBy(updateBy);
			departDao.update(depart);
		}
	}
	 
}

