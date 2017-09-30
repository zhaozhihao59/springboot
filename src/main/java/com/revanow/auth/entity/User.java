package com.revanow.auth.entity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * 用户
 * @creator     拓者码工
 * @create-time 2016/2/15 10:51:59
 * @company www.tocersoft.com
 * @version 1.0
 */
public class User implements Serializable  {
	/**  */
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
	/** 编号 */
	private String staffId;
	/** 姓名 */
	private String name;
	/** 性别 1：男 2：女 */
	private Integer sex;
	/** 生日 */
	@DateTimeFormat(iso=ISO.DATE,pattern="yyyy-MM-dd")
	private Date birthday;
	/** 手机号码 */
	private String mobile;
	/** 电话 */
	private String tel;
	/** 电子邮箱 */
	private String email;
	/** 联系方式 */
	private String contact;
	/** 会员帐号 */
	private String account;
	/** 登录密码 */
	private String password;
	/** 状态 1：启用 0：禁用 */
	private Integer state;
	/** 在线状态 1：在线 0：离线 */
	private Integer onlineState;
	/** 是否修改过密码 1：已修改 0：未修改 */
	private Integer isChangePwd;
	/** 部门ID */
	private Long departId;
	/** 角色ID集合，用逗号分隔 */
	private String roleIds;
	/** 角色名称列表，用逗号分隔 */
	private String roleNames;
	/** 部门ID集合，用逗号分隔 */
	private String departIds;
	/** 部门名称集合，用逗号分隔 */
	private String departNames;
	/** 是否是主管*/
	private Integer isManager;
	
	private String[] ids;
	/** 所属角色 */
	private List<Role> roleList = new ArrayList<Role>();
	
	public User() {
		super();
	}

	/** 编号 */
	public String getStaffId(){
		return this.staffId;
	}

	/** 编号 */
	public void setStaffId(String staffId){
		this.staffId = staffId;
	}

	/** 姓名 */
	public String getName(){
		return this.name;
	}

	/** 姓名 */
	public void setName(String name){
		this.name = name;
	}

	/** 性别 1：男 2：女 */
	public Integer getSex(){
		return this.sex;
	}

	/** 性别 1：男 2：女 */
	public void setSex(Integer sex){
		this.sex = sex;
	}

	/** 生日 */
	public Date getBirthday(){
		return this.birthday;
	}

	/** 生日 */
	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	/** 手机号码 */
	public String getMobile(){
		return this.mobile;
	}

	/** 手机号码 */
	public void setMobile(String mobile){
		this.mobile = mobile;
	}

	/** 电话 */
	public String getTel(){
		return this.tel;
	}

	/** 电话 */
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

	/** 联系方式 */
	public String getContact(){
		return this.contact;
	}

	/** 联系方式 */
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

	/** 状态 1：启用 0：禁用 */
	public Integer getState(){
		return this.state;
	}

	/** 状态 1：启用 0：禁用 */
	public void setState(Integer state){
		this.state = state;
	}

	/** 在线状态 1：在线 0：离线 */
	public Integer getOnlineState(){
		return this.onlineState;
	}

	/** 在线状态 1：在线 0：离线 */
	public void setOnlineState(Integer onlineState){
		this.onlineState = onlineState;
	}

	/** 是否修改过密码 1：已修改 0：未修改 */
	public Integer getIsChangePwd(){
		return this.isChangePwd;
	}

	/** 是否修改过密码 1：已修改 0：未修改 */
	public void setIsChangePwd(Integer isChangePwd){
		this.isChangePwd = isChangePwd;
	}
	
	
	/** 角色名称列表，用逗号分隔 */
	public String getRoleNames() {
		return roleNames;
	}

	/** 角色名称列表，用逗号分隔 */
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	
	/** 角色ID集合，用逗号分隔 */
	public String getRoleIds() {
		return roleIds;
	}

	/** 角色ID集合，用逗号分隔 */
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	/** 部门ID集合，用逗号分隔 */
	public String getDepartIds() {
		return departIds;
	}

	/** 部门ID集合，用逗号分隔 */
	public void setDepartIds(String departIds) {
		this.departIds = departIds;
	}
	
	/** 部门名称集合，用逗号分隔 */
	public String getDepartNames() {
		return departNames;
	}

	/** 部门名称集合，用逗号分隔 */
	public void setDepartNames(String departNames) {
		this.departNames = departNames;
	}

	/** 所属角色 */
	public List<Role> getRoleList() {
		return roleList;
	}

	/** 所属角色 */
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}


	/** 是否是主管*/
	public Integer getIsManager() {
		return isManager;
	}

	/** 是否是主管*/
	public void setIsManager(Integer isManager) {
		this.isManager = isManager;
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

	public Long getDepartId() {
		return departId;
	}

	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	public String[] getIds() {
		return ids;
	}

	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	
	
}