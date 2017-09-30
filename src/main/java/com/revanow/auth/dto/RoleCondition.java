package com.revanow.auth.dto;

import java.io.Serializable;

/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public class RoleCondition implements Serializable {
	private static final long serialVersionUID = 7904053207325003853L;
	private String searchKey;
	/**  */
	private String createBy;
	/**  */
	private String updateBy;
	/**  */
	private String name;
	/**  */
	private String remark;
	/** 编码*/
	private String code;
	/** 获取部门Id */
	private Long departId;
	/**  */
	public String getCreateBy(){
		return this.createBy;
	}

	/**  */
	public void setCreateBy(String createBy){
		this.createBy = createBy;
	}

	/**  */
	public String getUpdateBy(){
		return this.updateBy;
	}

	/**  */
	public void setUpdateBy(String updateBy){
		this.updateBy = updateBy;
	}

	/**  */
	public String getName(){
		return this.name;
	}

	/**  */
	public void setName(String name){
		this.name = name;
	}

	/**  */
	public String getRemark(){
		return this.remark;
	}

	/**  */
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

}