package com.revanow.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.revanow.auth.entity.User;
import com.revanow.auth.entity.RoleDepart;
import com.revanow.auth.dto.UserCondition;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
@Repository("userDaoImpl")
public interface IUserDao{

	/**
	 * 查询所有
	 */
	List<User> listUserAll();

	/**
	 * 查询区间数据
	 * @param bounds RowBounds对象
	 * @param condition 查询条件类
	 */
	List<User> listUserByPage(RowBounds bounds,@Param("condition") UserCondition condition);

	/**
	 * 查询总数
	 * @param condition 查询条件类
	 * @return 总条数
	 */
	int listUserByPageCount(@Param("condition") UserCondition condition);

	/**
	 * 根据ID查询用户
	 * @param id 主键
	 * @return 用户
	 */
	User getUserById(@Param("id") Long id);
	
	/**
	 * 根据账号查询用户
	 * @param account
	 * @return
	 */
	User getUserByAccount(@Param("account") String account);

	/**
	 * 新增
	 * @param item 用户
	 */
	void add(User item);

	/**
	 * 修改
	 * @param item 用户
	 */
	void update(User item);
	
	/**
	 * 修改状态
	 * @param item
	 */
	void updateState(User item);
	
	/**
	 * 修改密码
	 * @param item
	 */
	void updatePassword(User item);

	/**
	 * 根据ID集合批量删除
	 * @param ids ID集合
	 */
	void delByIds(String[] ids);

	void updateDepartByUserId(@Param("id") String id, @Param("departId")String departId,@Param("updateBy") String updateBy);
	/**
	 * 更新主管
	 * @param item
	 */
	void updateManager(User item);
	/**
	 * 得到部门的人数
	 * @param id
	 * @return
	 */
	int getUserCountByDepartId(@Param("id") String id);
	/**
	 * 部门移动
	 * @param ids
	 */
	void updateDepartByIds(String[] ids);

	void updateDepartByItem(User item);

}