package com.sys.pojo;

import java.io.Serializable;

/**
 * 图片管理表
 * @author Administrator
 * 表结构
 * create table t_photo
(
    id bigint auto_increment primary key,
    photoType varchar(2) not null,
    photoPath varchar(2000) not null,
    photoName varchar(500) not null,
    photoAccount varchar(1000) not null,
    isDisabled tinyint(1) default 0 null,
    createTime varchar(20) not null,
    lastUpdateTime varchar(20) null,
    createUser varchar(50) not null,
    lastUpdateUser varchar(50) null,
    constraint UNIQUE_ACCOUNT
        unique (photoName,photoType,createTime,lastUpdateTime,lastUpdateUser,isDisabled)
);
 */
public class Photo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String photoType;
	private String photoPath;
	private String photoName;
	private String photoAccount;
	private Boolean isDisabled;
	private String createTime;
	private String lastUpdateTime;
	private String createUser;
	private String lastUpdateUser;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhotoType() {
		return photoType;
	}
	public void setPhotoType(String photoType) {
		this.photoType = photoType;
	}
	public String getPhotoPath() {
		return photoPath;
	}
	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}
	public String getPhotoName() {
		return photoName;
	}
	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}
	public String getPhotoAccount() {
		return photoAccount;
	}
	public void setPhotoAccount(String photoAccount) {
		this.photoAccount = photoAccount;
	}
	public Boolean getIsDisabled() {
		return isDisabled;
	}
	public void setIsDisabled(Boolean isDisabled) {
		this.isDisabled = isDisabled;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getLastUpdateTime() {
		return lastUpdateTime;
	}
	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getLastUpdateUser() {
		return lastUpdateUser;
	}
	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Photo other = (Photo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
