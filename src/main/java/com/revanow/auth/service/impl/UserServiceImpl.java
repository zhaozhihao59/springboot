package com.revanow.auth.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.revanow.auth.dao.IDepartDao;
import com.revanow.auth.dao.IRoleDao;
import com.revanow.auth.dao.IRoleDepartDao;
import com.revanow.auth.dao.IUserDao;
import com.revanow.auth.dao.IUserRoleDao;
import com.revanow.auth.dto.UserCondition;
import com.revanow.auth.entity.Role;
import com.revanow.auth.entity.User;
import com.revanow.auth.entity.UserRole;
import com.revanow.auth.service.IUserService;
import com.revanow.base.constant.SystemConstant;
import com.revanow.base.page.PageResult;
import com.revanow.base.util.EncryptUtil;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Service("userServiceImpl")
@Transactional(value = "transactionManager")
public class UserServiceImpl implements IUserService{
	
	@Resource(name = "userDaoImpl")
	private IUserDao userDao;
	@Resource(name = "roleDaoImpl")
	private IRoleDao roleDao;
	@Resource(name = "userRoleDaoImpl")
	private IUserRoleDao userRoleDao;
	@Resource(name = "departDaoImpl")
	private IDepartDao departDao;
	@Resource(name = "roleDepartDaoImpl")
	private IRoleDepartDao roleDepartDao;
	
	/**
	 * 查询所有用户
	 */
	public List<User> listUserAll(){
		return userDao.listUserAll();
	}
	
	@Override
	public void listUserByPage(PageResult<User> pageResult,UserCondition condition) {
		//获取总行数
		int count  = userDao.listUserByPageCount(condition);
		pageResult.setRows(count);
		
		//获取结果集
		RowBounds bounds = new RowBounds(pageResult.getCurrentPageIndex(), pageResult.getPageSize());
		List<User> list = userDao.listUserByPage(bounds,condition);
		
		//封装
		pageResult.setResult(list);
		
		for(int i=0;i<list.size();i++){
			User user = list.get(i);
			
			//查询用户角色
			List<Role> roleList = userRoleDao.listRoleByUserId(user.getId());
			if(!CollectionUtils.isEmpty(roleList)){
				List<String> roleNameList = new ArrayList<String>(roleList.size());
				for(Role curRole : roleList){
					roleNameList.add(curRole.getName());
				}
				user.setRoleNames(StringUtils.join(roleNameList,","));
			}
			
			
		}
	}
	
	
	@Override
	public User getUserDetailById(Long id) {
		User user = userDao.getUserById(id);
		if(null != user){
			//查询角色
			List<Role> roleList = userRoleDao.listRoleByUserId(user.getId());
			user.setRoleList(roleList);
			List<Long> roleIdList = new ArrayList<Long>(roleList.size());
			for(Role curRole : roleList){
				roleIdList.add(curRole.getId());
			}
			user.setRoleIds(StringUtils.join(roleIdList,","));
		}
		return user;
	}

	/**
	 * 根据用户名获取用户
	 * @param account
	 * @return
	 */
	public User getUserByAccount(String account){
		return userDao.getUserByAccount(account);
	}
	
	
	@Override
	public void saveUser(User item,Long departId) {
		if(null == item.getId()){
			item.setPassword(EncryptUtil.encryptionPw(SystemConstant.DEFAULT_PASSWORD));	//密码进行2次加密
			if(null == item.getDepartId() || 1 == item.getDepartId()){
				item.setDepartId(departId);	
			}
			
			//新增用户
			userDao.add(item);
		}else{
			User old = userDao.getUserById(item.getId());
			old.setBirthday(item.getBirthday());
			old.setContact(item.getContact());
			old.setEmail(item.getEmail());
			old.setMobile(item.getMobile());
			old.setName(item.getName());
			old.setSex(item.getSex());
			old.setTel(item.getTel());
			old.setStaffId(item.getStaffId());
			//更新用户
			userDao.update(old);
			
			//删除角色
			userRoleDao.delByUserId(old.getId());
			
		}
		
		//保存角色
		if(StringUtils.isNotBlank(item.getRoleIds())){
			String [] roleId = item.getRoleIds().split(",");
			for (int i = 0; i < roleId.length; i++) {
				if(StringUtils.isBlank(roleId[i])){
					continue;
				}
				
				UserRole userRole = new UserRole();
				userRole.setUserId(item.getId());
				userRole.setRoleId(NumberUtils.toLong(roleId[i]));
				
				userRoleDao.add(userRole);
			}
		}
		
	}

	@Override
	public void updateState(User item) {
		userDao.updateState(item);
	}
	
	@Override
	public void resetPwd(User item) {
		//密码加密
		item.setPassword(EncryptUtil.encryptionPw(item.getPassword()));
		userDao.updatePassword(item);
	}
	
	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	public void delByIds(String[] ids){
		for(String id : ids){
			//删除用户角色
			userRoleDao.delByUserId(NumberUtils.toLong(id));
		}
		//删除用户
		userDao.delByIds(ids);
	}




	/**
	 * 新增
	 *@param item 
	 */
	public User add(User item){
		userDao.add(item);
		return item;
	}
	/**
	 * 修改
	 *@param item 
	 */
	public User update(User item){
		userDao.update(item);
		return item;
	}

	/**
	 * 批量更换部门
	 * @param selIds 多用户ID
	 * @param oldDepartId 旧部门ID
	 * @param departId 部门ID
	 * @param updateBy 修改人
	 */
	@Override
	public void doMoveDepart(String selIds,String oldDepartId,String departId, String updateBy) {
		String[] ids = selIds.split(",");
		User item = new User();
		item.setUpdateBy(updateBy);
		item.setDepartId(NumberUtils.toLong(departId));
		item.setIds(ids);
		userDao.updateDepartByItem(item);
	}

	/**
	 * 设置主管
	 * @param item
	 */
	@Override
	public void updateManager(User item) {
		userDao.updateManager(item);
		
		if(item.getIsManager() == 1){
			UserRole userRole = new UserRole();
			userRole.setUserId(item.getId());
			userRole.setRoleId(SystemConstant.MANAGER_ID);
			userRoleDao.add(userRole);
		}else{
			userRoleDao.delByUserIdAndRoleId(item.getId(),SystemConstant.MANAGER_ID);
		}
		
	}

	@Override
	public int getUserCountByDepartId(String id) {
		return userDao.getUserCountByDepartId(id);
	}
}