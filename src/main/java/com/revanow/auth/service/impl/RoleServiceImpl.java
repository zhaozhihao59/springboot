package com.revanow.auth.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.revanow.auth.dao.IRoleDao;
import com.revanow.auth.dao.IRoleDepartDao;
import com.revanow.auth.dao.IRoleRightDao;
import com.revanow.auth.dao.IUserDao;
import com.revanow.auth.dao.IUserRoleDao;
import com.revanow.auth.dto.RoleCondition;
import com.revanow.auth.entity.Role;
import com.revanow.auth.entity.RoleDepart;
import com.revanow.auth.entity.RoleRight;
import com.revanow.auth.entity.User;
import com.revanow.auth.service.IRoleService;
import com.revanow.base.constant.SystemConstant;
import com.revanow.base.page.PageResult;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Service("roleServiceImpl")
@Transactional(value = "transactionManager")
public class RoleServiceImpl implements IRoleService{
	@Resource(name = "roleDaoImpl")
	private IRoleDao roleDao;
	@Resource(name = "userDaoImpl")
	private IUserDao userDao;
	@Resource(name = "userRoleDaoImpl")
	private IUserRoleDao userRoleDao;
	@Resource(name = "roleRightDaoImpl")
	private IRoleRightDao roleRightDao;
	@Resource(name = "roleDepartDaoImpl")
	private IRoleDepartDao roleDepartDao;
	
	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> listRoleAll(){
		return roleDao.listRoleAll();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void listRoleByPage(PageResult pageResult,RoleCondition condition) {
		//1.查询总数
		int count = roleDao.listRoleByPageCount(condition);
		pageResult.setRows(count);
		
		//2.查询区间数据
		RowBounds bounds = new RowBounds(pageResult.getCurrentPageIndex(), pageResult.getPageSize());
		List<Role> roleList = roleDao.listRoleByPage(bounds,condition);
		
		for(Role curRole : roleList){
			StringBuffer users = new StringBuffer();
			List<User> userList = userRoleDao.listUserByRoleId(curRole.getId(),SystemConstant.SEARCH_ROLE_USER_COUNT);
			for(int j=0; j < userList.size();j++){
				if(j > 0){
					users.append(",");
				}
				User user = userList.get(j);
				users.append(user.getName());
			}
			curRole.setUserNames(users.toString());
		}
		
		pageResult.setResult(roleList);
	}
	
	@Override
	public Role getRoleById(Long id) {
		return roleDao.getRoleById(id);
	}

	@Override
	public void saveRole(Role item) {
		if(null == item.getId()){
			//新增
			roleDao.add(item);
		}else{
			Role oldItem = roleDao.getRoleById(item.getId());
			oldItem.setName(item.getName());
			oldItem.setCode(item.getCode());
			oldItem.setRemark(item.getRemark());
			//修改
			roleDao.update(oldItem);
			//清空角色权限
			roleRightDao.delByRoleId(item.getId());
		}
		
		//保存角色权限
		String[] rightIds = item.getRightIds().split(",");
		for(String rightId: rightIds){
			RoleRight roleRight = new RoleRight();
			roleRight.setRightId(NumberUtils.toLong(rightId));
			roleRight.setRoleId(item.getId());
			roleRightDao.add(roleRight);
		}
		RoleDepart departItem = new RoleDepart();
		departItem.setDepartId(item.getNewDepartId());
		departItem.setRoleId(item.getId());
		//删除旧部门
		roleDepartDao.delByRoleItem(item);
		roleDepartDao.add(departItem);
	}

	@Override
	public void delByIds(String[] ids) {
		for(String id : ids){
			//1.删除角色权限
			roleRightDao.delByRoleId(NumberUtils.toLong(id));
			//2.删除角色
			roleDao.delByIds(new String[]{id});
		}
		
	}

	@Override
	public boolean checkCodeRepeat(Role item) {
		int count = roleDao.getCountByCode(item);
		return count > 0;
	}

	/**
	 * 设置角色类型
	 * @param ids
	 * @param type
	 */
	@Override
	public void setSysRole(String[] ids, Integer type) {
		for(String id : ids){
			Role item = roleDao.getRoleById(NumberUtils.toLong(id));
			item.setRoleType(type);
			roleDao.update(item);
		}
	}


}