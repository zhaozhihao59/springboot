package com.revanow.test.entity;

import  java.io.Serializable;
import java.util.Date;
public class AvIncome implements Serializable {
	private static final long serialVersionUID = 1L;

	/** 自增ID */
	private Integer id;
	/** 视频ID */
	private Integer avId;
	/** 用户MID */
	private Integer mid;
	/** 分区ID */
	private Integer tagId;
	/** 是否原创0-非原创1-原创 */
	private Integer isOriginal;
	/** 投稿时间 */
	private Date uploadTime;
	/** 播放量 */
	private Integer playCount;
	/**  */
	private Integer totalIncome;
	/** 新增的收入 */
	private Integer income;
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

	/** 视频ID */
	public Integer getAvId(){
		return this.avId;
	}

	/** 视频ID */
	public void setAvId(Integer avId){
		this.avId = avId;
	}

	/** 用户MID */
	public Integer getMid(){
		return this.mid;
	}

	/** 用户MID */
	public void setMid(Integer mid){
		this.mid = mid;
	}

	/** 分区ID */
	public Integer getTagId(){
		return this.tagId;
	}

	/** 分区ID */
	public void setTagId(Integer tagId){
		this.tagId = tagId;
	}

	/** 是否原创0-非原创1-原创 */
	public Integer getIsOriginal(){
		return this.isOriginal;
	}

	/** 是否原创0-非原创1-原创 */
	public void setIsOriginal(Integer isOriginal){
		this.isOriginal = isOriginal;
	}

	/** 投稿时间 */
	public Date getUploadTime(){
		return this.uploadTime;
	}

	/** 投稿时间 */
	public void setUploadTime(Date uploadTime){
		this.uploadTime = uploadTime;
	}

	/** 播放量 */
	public Integer getPlayCount(){
		return this.playCount;
	}

	/** 播放量 */
	public void setPlayCount(Integer playCount){
		this.playCount = playCount;
	}

	/**  */
	public Integer getTotalIncome(){
		return this.totalIncome;
	}

	/**  */
	public void setTotalIncome(Integer totalIncome){
		this.totalIncome = totalIncome;
	}

	/** 新增的收入 */
	public Integer getIncome(){
		return this.income;
	}

	/** 新增的收入 */
	public void setIncome(Integer income){
		this.income = income;
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