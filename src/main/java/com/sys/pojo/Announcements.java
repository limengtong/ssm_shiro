package com.sys.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * 我的主页公告表
 * @author Administrator
 *
 */
public class Announcements implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String title;
	private Date createTime;
	/** 禁用/启用 */
	private Boolean isDisabled;
	/** 是否删除*/
	private Boolean isDeleted;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
