package com.revanow.base.error;

public enum DataErrorCode {

	NULL_OBJ("DATA001","对象不能为空");
	
	
	DataErrorCode(String val,String desc){
		this.setValue(val);;
		this.setDesc(desc);
	}
	
	private String value;
	private String desc;
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String toString(){
		return "[" + this.value + "]" + this.desc;
	}
}
