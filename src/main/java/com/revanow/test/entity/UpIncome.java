package com.revanow.test.entity;

import  java.io.Serializable;
import java.util.Date;
public class UpIncome implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	/** 用户MID */
	private Integer mid;
	/** 补贴视频数 */
	private Integer avCount;
	/** 播放量 */
	private Integer playCount;
	/** 新增的收入 */
	private Integer income;
	/**  */
	private Integer totalIncome;
	/** 日期 */
	private Date date;
	/** 是否删除 */
	private Integer isDeleted;
	/** 添加时间 */
	private Date ctime;
	/** 修改时间 */
	private Date mtime;

	/** 自增ID */
	public Integer getId(){
		return this.id;
	}

	/** 自增ID */
	public void setId(Integer id){
		this.id = id;
	}

	/** 用户MID */
	public Integer getMid(){
		return this.mid;
	}

	/** 用户MID */
	public void setMid(Integer mid){
		this.mid = mid;
	}

	/** 补贴视频数 */
	public Integer getAvCount(){
		return this.avCount;
	}

	/** 补贴视频数 */
	public void setAvCount(Integer avCount){
		this.avCount = avCount;
	}

	/** 播放量 */
	public Integer getPlayCount(){
		return this.playCount;
	}

	/** 播放量 */
	public void setPlayCount(Integer playCount){
		this.playCount = playCount;
	}

	/** 新增的收入 */
	public Integer getIncome(){
		return this.income;
	}

	/** 新增的收入 */
	public void setIncome(Integer income){
		this.income = income;
	}

	/**  */
	public Integer getTotalIncome(){
		return this.totalIncome;
	}

	/**  */
	public void setTotalIncome(Integer totalIncome){
		this.totalIncome = totalIncome;
	}

	/** 日期 */
	public Date getDate(){
		return this.date;
	}

	/** 日期 */
	public void setDate(Date date){
		this.date = date;
	}

	/** 是否删除 */
	public Integer getIsDeleted(){
		return this.isDeleted;
	}

	/** 是否删除 */
	public void setIsDeleted(Integer isDeleted){
		this.isDeleted = isDeleted;
	}

	/** 添加时间 */
	public Date getCtime(){
		return this.ctime;
	}

	/** 添加时间 */
	public void setCtime(Date ctime){
		this.ctime = ctime;
	}

	/** 修改时间 */
	public Date getMtime(){
		return this.mtime;
	}

	/** 修改时间 */
	public void setMtime(Date mtime){
		this.mtime = mtime;
	}

}