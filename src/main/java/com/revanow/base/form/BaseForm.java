package com.revanow.base.form;

import java.io.Serializable;

import com.revanow.base.page.PageResult;

public abstract class BaseForm<T> implements Serializable{
	private static final long serialVersionUID = -4244104275294035351L;

	
	/** token数据 */
	private String token;
	/** 选中ID集合 */
	private String selIds;
	/** 查询关键字 */
	private String searchKey;
	
	private PageResult<T> pageResult = new PageResult<T>();
	
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getSelIds() {
		return selIds;
	}

	public void setSelIds(String selIds) {
		this.selIds = selIds;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public PageResult<T> getPageResult() {
		return pageResult;
	}

	public void setPageResult(PageResult<T> pageResult) {
		this.pageResult = pageResult;
	}
}
