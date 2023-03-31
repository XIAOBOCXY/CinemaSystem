package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")
public class Seat implements Serializable {

	/**主键**/
	private Integer id;

	/**排片id**/
	private Integer aid;

	/**已选座位号**/
	private Integer seat;

	/**创建时间**/
	private Date createTime;

	/**更新时间**/
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

	public void setSeat(Integer seat){
		this.seat = seat;
	}

	public Integer getSeat(){
		return this.seat;
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
		return "Seat [id=" + id + ", aid=" + aid + ", seat=" + seat + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
