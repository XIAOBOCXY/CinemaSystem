package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.9 活动参与表（t_registration）
 * 
 **/
@SuppressWarnings("serial")
public class Registration implements Serializable {

	/**主键**/
	private Integer id;

	/**活动主键（关联t_activity表）**/
	private Integer aid;

	/**用户主键（关联t_user表）**/
	private Integer uid;

	/**添加时间（格式2022-01-14\n11:37:03）**/
	private Date createTime;

	/****/
	private Date updateTime;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setAid(Integer aid){
		this.aid = aid;
	}

	public Integer getAid(){
		return this.aid;
	}

	public void setUid(Integer uid){
		this.uid = uid;
	}

	public Integer getUid(){
		return this.uid;
	}

	public void setCreateTime(Date createTime){
		this.createTime = createTime;
	}

	public Date getCreateTime(){
		return this.createTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime = updateTime;
	}

	public Date getUpdateTime(){
		return this.updateTime;
	}

	@Override
	public String toString() {
		return "Registration [id=" + id + ", aid=" + aid + ", uid=" + uid + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
