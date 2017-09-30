package com.revanow.auth.entity;

import  java.io.Serializable;

import java.util.Date;
/**
 * 权限部门中间表
 * @creator 赵志豪
 * @create-time 2016-11-09 14:30:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public class RoleDepart implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 主键 */
	private Long id;
	/** 部门ID */
	private Long departId;
	/** 权限ID */
	private Long roleId;

	/** 主键 */
	public Long getId(){
		return this.id;
	}

	/** 主键 */
	public void setId(Long id){
		this.id = id;
	}

	/** 部门ID */
	public Long getDepartId(){
		return this.departId;
	}

	/** 部门ID */
	public void setDepartId(Long departId){
		this.departId = departId;
	}

	/** 权限ID */
	public Long getRoleId(){
		return this.roleId;
	}

	/** 权限ID */
	public void setRoleId(Long roleId){
		this.roleId = roleId;
	}

}