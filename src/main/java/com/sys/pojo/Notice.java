package com.sys.pojo;

import java.io.Serializable;

/**
 * 公告管理表
 * @author Administrator
 * 表结构：
 * create table t_notice
(
    id           bigint auto_increment
        primary key,
    account      varchar(1000)         null,
    createTime   varchar(20)  null,
    isDeleted    tinyint(1) default 0 null,
    isDisabled   tinyint(1) default 0 null,
    constraint UNIQUE_ACCOUNT
        unique (account)
);
 */
public class Notice implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Long id;
	private String createTime;
	/** 公告内容*/
	private String account;
	/** 禁用/启用 */
	private Boolean isDisabled;
	/** 是否删除*/
	private Boolean isDeleted;
	
	public Notice() {
		super();
	}

	public Notice(String account) {
		super();
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
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
		Notice other = (Notice) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
