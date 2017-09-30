package com.revanow.auth.form;

import com.revanow.auth.dto.RightCondition;
import com.revanow.auth.entity.Right;
import com.revanow.base.form.BaseForm;
/**
 * 
 * @creator 赵志豪
 * @create-time 2016-08-08 18:16:15
 * @email 490875647@qq.com
 * @version 1.0
 */
public class RightForm extends BaseForm<Right>{
	private static final long serialVersionUID = -8651568355248956816L;

	private String selIds;

	private Right item = new Right();

	private RightCondition condition = new RightCondition();
	/** 权限父级ID */
	private String pid;

	public String getSelIds(){
		return selIds;
	}
	public void setSelIds(String selIds){
		this.selIds = selIds;
	}

	public Right getItem(){
		return item;
	}
	public void setItem(Right item){
		this.item = item;
	}

	public RightCondition getCondition(){
		return condition;
	}

	public void setCondition(RightCondition condition){
		this.condition = condition;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}

}

