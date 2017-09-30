package com.revanow.auth.entity;

import java.io.Serializable;



/**
 * 角色菜单关系表
 * @creator     拓者码工
 * @create-time 2016/2/15 11:53:08
 * @company www.tocersoft.com
 * @version 1.0
 */
public class RoleRight implements Serializable {
	/**  */
	private static final long serialVersionUID = 1L;

	/** 角色ID */
	private Long roleId;
	/** 菜单ID */
	private Long rightId;
	

	public RoleRight() {
		super();
	}

	/** 角色ID */
	public Long getRoleId(){
		return this.roleId;
	}

	/** 角色ID */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

	/** 菜单ID */
	public Long getRightId(){
		return this.rightId;
	}

	/** 菜单ID */
	public void setRightId(Long rightId){
		this.rightId = rightId;
	}

	
}