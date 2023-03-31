package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.10 电影表（t_film）
 * 
 **/
@SuppressWarnings("serial")
public class Film implements Serializable {

	/**主键**/
	private Integer id;

	/**电影名**/
	private String name;

	/**上映时间（格式2022-01-
14）**/
	private Date releaseTime;

	/**类型**/
	private String type;

	/**状态 1-上架；0-下架**/
	private Integer status;

	/**地区**/
	private String region;

	/**热度**/
	private Integer hot;

	/**介绍**/
	private String introduction;

	/**图片id（关联t_film.id）**/
	private String cover;

	/**电影时长 单位：分钟**/
	private Integer duration;

	/**删除标志 1-已被删除 0-未
删除**/
	private String isDelete;

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

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setReleaseTime(Date releaseTime){
		this.releaseTime = releaseTime;
	}

	public Date getReleaseTime(){
		return this.releaseTime;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setRegion(String region){
		this.region = region;
	}

	public String getRegion(){
		return this.region;
	}

	public void setHot(Integer hot){
		this.hot = hot;
	}

	public Integer getHot(){
		return this.hot;
	}

	public void setIntroduction(String introduction){
		this.introduction = introduction;
	}

	public String getIntroduction(){
		return this.introduction;
	}

	public void setCover(String cover){
		this.cover = cover;
	}

	public String getCover(){
		return this.cover;
	}

	public void setDuration(Integer duration){
		this.duration = duration;
	}

	public Integer getDuration(){
		return this.duration;
	}

	public void setIsDelete(String isDelete){
		this.isDelete = isDelete;
	}

	public String getIsDelete(){
		return this.isDelete;
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
		return "Film [id=" + id + ", name=" + name + ", releaseTime=" + releaseTime + ", type=" + type + ", status=" + status + ", region=" + region + ", hot=" + hot + ", introduction=" + introduction + ", cover=" + cover + ", duration=" + duration + ", isDelete=" + isDelete + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
