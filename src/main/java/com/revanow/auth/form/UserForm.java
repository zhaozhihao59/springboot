package com.revanow.auth.form;

import com.revanow.auth.dto.UserCondition;
import com.revanow.auth.entity.User;
import com.revanow.base.form.BaseForm;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public class UserForm extends BaseForm<User>{

	private static final long serialVersionUID = -3145191762721347753L;

	private String selIds;

	private User item = new User();

	private UserCondition condition = new UserCondition();
	/** 新部门ID*/
	private String newDepartId;
	/** 旧部门ID*/
	private String oldDepartId;
	public String getSelIds(){
		return selIds;
	}
	public void setSelIds(String selIds){
		this.selIds = selIds;
	}

	public User getItem(){
		return item;
	}
	public void setItem(User item){
		this.item = item;
	}

	public UserCondition getCondition(){
		return condition;
	}

	public void setCondition(UserCondition condition){
		this.condition = condition;
	}
	public String getNewDepartId() {
		return newDepartId;
	}
	public void setNewDepartId(String newDepartId) {
		this.newDepartId = newDepartId;
	}
	public String getOldDepartId() {
		return oldDepartId;
	}
	public void setOldDepartId(String oldDepartId) {
		this.oldDepartId = oldDepartId;
	}

}

