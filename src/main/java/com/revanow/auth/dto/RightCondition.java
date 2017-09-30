package com.revanow.auth.dto;

import java.io.Serializable;

/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public class RightCondition implements Serializable {
	private static final long serialVersionUID = 7904053207325003853L;
	private String searchKey;
	/**  */
	private String createBy;
	/**  */
	private String updateBy;
	/**  */
	private String name;
	/**  */
	private String tip;
	/**  */
	private String url;
	/**  */
	private String pcode;
	/** 图标名 */
	private String iconPath;

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
	public String getTip(){
		return this.tip;
	}

	/**  */
	public void setTip(String tip){
		this.tip = tip;
	}

	/**  */
	public String getUrl(){
		return this.url;
	}

	/**  */
	public void setUrl(String url){
		this.url = url;
	}

	/**  */
	public String getPcode(){
		return this.pcode;
	}

	/**  */
	public void setPcode(String pcode){
		this.pcode = pcode;
	}

	/** 图标名 */
	public String getIconPath(){
		return this.iconPath;
	}

	/** 图标名 */
	public void setIconPath(String iconPath){
		this.iconPath = iconPath;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

}