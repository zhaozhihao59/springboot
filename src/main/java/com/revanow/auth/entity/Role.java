package com.revanow.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色实体
 * 
 * @creator zhangqiang
 * @create-time Nov 7, 2011 4:04:12 PM
 */
public class Role implements Serializable  {
	private static final long serialVersionUID = 2790199600385605443L;
	
	/**  */
	private Long id;
	/**  */
	private String createBy;
	/**  */
	private Date createDate;
	/**  */
	private String updateBy;
	/**  */
	private Date updateDate;
	/**  */
	private Integer deleteFlag;
	/** 姓名 */
	private String name;
	/** 角色代码 */
	private String code;
	/** 角色类型 1：系统角色 2：普通角色 */
	private Integer roleType;
	/** 说明 */
	private String remark;
	/** 新部门Id */
	private Long newDepartId;
	/** 旧部门Id */
	private Long oldDepartId;
	/** 权限ID集合 */
	private String rightIds;
	/** 授权用户名称列表，用逗号分隔显示 */
	private String userNames;
	/** 关联的菜单 */
	private List<Right> rightList = new ArrayList<Right>();
	/** 关联的用户 */
	private List<User> userList = new ArrayList<User>();
	
	
	public Role() {
		super();
	}

	/** 姓名 */
	public String getName(){
		return this.name;
	}

	/** 姓名 */
	public void setName(String name){
		this.name = name;
	}

	/** 角色代码 */
	public String getCode(){
		return this.code;
	}

	/** 角色代码 */
	public void setCode(String code){
		this.code = code;
	}


	/** 说明 */
	public String getRemark(){
		return this.remark;
	}

	/** 说明 */
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	/** 权限ID集合 */
	public String getRightIds() {
		return rightIds;
	}

	/** 权限ID集合 */
	public void setRightIds(String rightIds) {
		this.rightIds = rightIds;
	}

	/** 授权用户名称列表，用逗号分隔显示 */
	public String getUserNames() {
		return userNames;
	}

	/** 授权用户名称列表，用逗号分隔显示 */
	public void setUserNames(String userNames) {
		this.userNames = userNames;
	}

	/** 关联的菜单 */
	public List<Right> getRightList() {
		return rightList;
	}

	/** 关联的菜单 */
	public void setRightList(List<Right> rightList) {
		this.rightList = rightList;
	}

	/** 关联的用户 */
	public List<User> getUserList() {
		return userList;
	}

	/** 关联的用户 */
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}
	/** 角色类型 1：系统角色 2：普通角色 */
	public Integer getRoleType() {
		return roleType;
	}
	/** 角色类型 1：系统角色 2：普通角色 */
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}

	public Long getNewDepartId() {
		return newDepartId;
	}

	public void setNewDepartId(Long newDepartId) {
		this.newDepartId = newDepartId;
	}

	public Long getOldDepartId() {
		return oldDepartId;
	}

	public void setOldDepartId(Long oldDepartId) {
		this.oldDepartId = oldDepartId;
	}
}
