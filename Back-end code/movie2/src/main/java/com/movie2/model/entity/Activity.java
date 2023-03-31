package com.movie2.model.entity;
import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.8 活动表（t_activity）
 * 
 **/
@SuppressWarnings("serial")
public class Activity implements Serializable {

	/**主键**/
	private Integer id;

	/**内容**/
	private String content;

	/**参与人数**/
	private Integer number;

	/**开始时间（格式2022-01-02）**/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date startTime;

	/**结束时间（格式2022-01-02）**/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date endTime;

	/**添加时间（格式2022-01-14\n11:37:03）**/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**修改时间 (格式2022-01)**/
	@JSONField(format="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setNumber(Integer number){
		this.number = number;
	}

	public Integer getNumber(){
		return this.number;
	}

	public void setStartTime(Date startTime){
		this.startTime = startTime;
	}

	public Date getStartTime(){
		return this.startTime;
	}

	public void setEndTime(Date endTime){
		this.endTime = endTime;
	}

	public Date getEndTime(){
		return this.endTime;
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
		return "Activity [id=" + id + ", content=" + content + ", number=" + number + ", startTime=" + startTime + ", endTime=" + endTime + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
