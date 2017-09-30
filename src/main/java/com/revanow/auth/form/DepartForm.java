package com.revanow.auth.form;

import com.revanow.auth.dto.DepartCondition;
import com.revanow.auth.entity.Depart;
import com.revanow.base.form.BaseForm;

public class DepartForm extends BaseForm<Depart>{
	private static final long serialVersionUID = 2079969062131635061L;

	private Depart item = new Depart();
	private DepartCondition condition = new DepartCondition();
	private String pid;
	private String newDepartId;
	//移动类型 1 部门移动部门  2 用户更换部门
	private String moveType;
	//旧部门ID(用于用户移动部门)
	private String oldDepartId;

	public DepartForm() {
		super();
	}

	public Depart getItem() {
		return item;
	}

	public void setItem(Depart item) {
		this.item = item;
	}

	public DepartCondition getCondition() {
		return condition;
	}

	public void setCondition(DepartCondition condition) {
		this.condition = condition;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getNewDepartId() {
		return newDepartId;
	}

	public void setNewDepartId(String newDepartId) {
		this.newDepartId = newDepartId;
	}

	public String getMoveType() {
		return moveType;
	}

	public void setMoveType(String moveType) {
		this.moveType = moveType;
	}

	public String getOldDepartId() {
		return oldDepartId;
	}

	public void setOldDepartId(String oldDepartId) {
		this.oldDepartId = oldDepartId;
	}

}
