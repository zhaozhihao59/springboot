package com.revanow.auth.form;

import com.revanow.auth.dto.RoleCondition;
import com.revanow.auth.entity.Role;
import com.revanow.base.form.BaseForm;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public class RoleForm extends BaseForm<Role>{

	private static final long serialVersionUID = 4497073257779829832L;

	private String selIds;

	private Role item = new Role();

	private RoleCondition condition = new RoleCondition();
	/** 类型 1系统角色 2普通角色*/
	private Integer type;
	public String getSelIds(){
		return selIds;
	}
	public void setSelIds(String selIds){
		this.selIds = selIds;
	}

	public Role getItem(){
		return item;
	}
	public void setItem(Role item){
		this.item = item;
	}

	public RoleCondition getCondition(){
		return condition;
	}

	public void setCondition(RoleCondition condition){
		this.condition = condition;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}

}

