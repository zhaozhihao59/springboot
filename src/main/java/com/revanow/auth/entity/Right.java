package com.revanow.auth.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

/**
 * 权限
 * 
 * @creator zhangqiang
 * @create-time May 25, 2011 9:21:37 AM
 */
public class Right implements Serializable {
	private static final long serialVersionUID = 1L;
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
	/** 是否维护中1-维护 0-未维护 */
	private Integer service;
	/** 是否隐藏 1-隐藏 0-不隐藏 */
	private Integer hidden;
	/** 名称 */
	private String name;
	/** 上级编号 */
	private Long parentId;
	/** 位置 1：主菜单 2：一级子菜单 3：二级子菜单 4：工具栏 */
	private Integer location;
	/** 提示信息 */
	private String tip;
	/** 地址 */
	private String url;
	/** 状态 1：正常 0：禁用 */
	private Integer state;
	/** 图标路径 */
	private String iconPath;
	/** 关联多个角色 */
	private List<Role> roles = new ArrayList<Role>();
	/** 权限码 */
	private String pcode;
	/** 排序码 */
	private Integer sort;
	/** 下级子权限 */
	private List<Right> childRights = new ArrayList<Right>();
	/** 权限字符串 */
	private String roleStr;
	
	public Right() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Integer getLocation() {
		return location;
	}

	public void setLocation(Integer location) {
		this.location = location;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public List<Right> getChildRights() {
		return childRights;
	}

	public void setChildRights(List<Right> childRights) {
		this.childRights = childRights;
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

	public Integer getService() {
		return service;
	}

	public void setService(Integer service) {
		this.service = service;
	}

	public Integer getHidden() {
		return hidden;
	}

	public void setHidden(Integer hidden) {
		this.hidden = hidden;
	}

	public String getRoleStr() {
		return roleStr;
	}

	public void setRoleStr(String roleStr) {
		this.roleStr = roleStr;
	}

}
