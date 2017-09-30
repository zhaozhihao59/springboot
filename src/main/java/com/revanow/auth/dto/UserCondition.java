package com.revanow.auth.dto;

import java.io.Serializable;

/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public class UserCondition implements Serializable {
	private static final long serialVersionUID = 7904053207325003853L;
	private String searchKey;
	/**  */
	private String createBy;
	/**  */
	private String updateBy;
	/**  */
	private String staffId;
	/**  */
	private String departId;
	/** 姓名 */
	private String name;
	/**  */
	private String sex;
	/** 手机号码 */
	private String mobile;
	/**  */
	private String tel;
	/** 电子邮箱 */
	private String email;
	/**  */
	private String contact;
	/** 会员帐号 */
	private String account;
	/** 登录密码 */
	private String password;
	/**  */
	private String nickname;
	/**  */
	private String bankAccount;
	/**  */
	private String supplierId;

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
	public String getStaffId(){
		return this.staffId;
	}

	/**  */
	public void setStaffId(String staffId){
		this.staffId = staffId;
	}

	/**  */
	public String getDepartId(){
		return this.departId;
	}

	/**  */
	public void setDepartId(String departId){
		this.departId = departId;
	}

	/** 姓名 */
	public String getName(){
		return this.name;
	}

	/** 姓名 */
	public void setName(String name){
		this.name = name;
	}

	/**  */
	public String getSex(){
		return this.sex;
	}

	/**  */
	public void setSex(String sex){
		this.sex = sex;
	}

	/** 手机号码 */
	public String getMobile(){
		return this.mobile;
	}

	/** 手机号码 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	/**  */
	public String getTel(){
		return this.tel;
	}

	/**  */
	public void setTel(String tel){
		this.tel = tel;
	}

	/** 电子邮箱 */
	public String getEmail(){
		return this.email;
	}

	/** 电子邮箱 */
	public void setEmail(String email){
		this.email = email;
	}

	/**  */
	public String getContact(){
		return this.contact;
	}

	/**  */
	public void setContact(String contact){
		this.contact = contact;
	}

	/** 会员帐号 */
	public String getAccount(){
		return this.account;
	}

	/** 会员帐号 */
	public void setAccount(String account){
		this.account = account;
	}

	/** 登录密码 */
	public String getPassword(){
		return this.password;
	}

	/** 登录密码 */
	public void setPassword(String password){
		this.password = password;
	}

	/**  */
	public String getNickname(){
		return this.nickname;
	}

	/**  */
	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	/**  */
	public String getBankAccount(){
		return this.bankAccount;
	}

	/**  */
	public void setBankAccount(String bankAccount){
		this.bankAccount = bankAccount;
	}

	/**  */
	public String getSupplierId(){
		return this.supplierId;
	}

	/**  */
	public void setSupplierId(String supplierId){
		this.supplierId = supplierId;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

}