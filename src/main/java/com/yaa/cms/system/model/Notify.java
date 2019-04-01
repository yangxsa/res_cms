package com.yaa.cms.system.model;

import java.io.Serializable;
import java.util.Date;


/**
 * Generator code
 */
public class Notify implements Serializable {

	private static final long serialVersionUID = 1L;

	//主键
	private Integer id;
	//标题
	private String title;
	//内容
	private String content;
	//状态
	private String status;
	//备注
	private String remarks;
	//创建者
	private String createBy;
	//创建时间
	private Date createTime;

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}
}
