package com.revanow.auth.entity;


import java.io.Serializable;

/**
 * 用户角色关系表
 * @creator     拓者码工
 * @create-time 2016/2/15 11:25:47
 * @company www.tocersoft.com
 * @version 1.0
 */
public class UserRole implements Serializable {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 用户ID */
	private Long userId;
	/** 角色ID */
	private Long roleId;
	

	public UserRole() {
		super();
	}

	/** 用户ID */
	public Long getUserId(){
		return this.userId;
	}

	/** 用户ID */
	public void setUserId(Long userId){
		this.userId = userId;
	}

	/** 角色ID */
	public Long getRoleId(){
		return this.roleId;
	}

	/** 角色ID */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	
}