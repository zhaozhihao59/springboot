package com.revanow.auth.entity;
import java.io.Serializable;
import java.util.Date;

/**
 * 部门表
 * @creator     拓者码工
 * @create-time 2016/2/15 13:31:20
 * @email developer@tocersoft.com
 * @company www.tocersoft.com
 * @version 1.0
 */
public class Depart implements Serializable {
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
	/** 菜单名 */
	private String name;
	/** 父级ID */
	private String parentId;
	/** 节点级别：1-一级节点，2-二级节点，3-三级节 */
	private Integer level;
	/** 部门备注 */
	private String note;
	/** 排序 */
	private Integer sort;
	

	public Depart() {
		super();
	}

	/** 菜单名 */
	public String getName(){
		return this.name;
	}

	/** 菜单名 */
	public void setName(String name){
		this.name = name;
	}

	/** 父级ID */
	public String getParentId(){
		return this.parentId;
	}

	/** 父级ID */
	public void setParentId(String parentId){
		this.parentId = parentId;
	}

	/** 节点级别：1-一级节点，2-二级节点，3-三级节 */
	public Integer getLevel(){
		return this.level;
	}

	/** 节点级别：1-一级节点，2-二级节点，3-三级节 */
	public void setLevel(Integer level){
		this.level = level;
	}

	/** 部门备注 */
	public String getNote(){
		return this.note;
	}

	/** 部门备注 */
	public void setNote(String note){
		this.note = note;
	}

	/** 排序 */
	public Integer getSort(){
		return this.sort;
	}

	/** 排序 */
	public void setSort(Integer sort){
		this.sort = sort;
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

	
}