package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.11 排片表（t_arrangement）
 * 
 **/
@SuppressWarnings("serial")
public class Arrangement implements Serializable {

	/**主键**/
	private Integer id;

	/**电影主键（关联t_film表）**/
	private Integer fid;

	/**电影名**/
	private String name;

	/**开放多少座位**/
	private Integer seatNumber;

	/**票房统计**/
	private Integer boxOffice;

	/**价格**/
	private java.math.BigDecimal price;

	/**放映类型 2D 3D**/
	private String type;

	/**电影放映日期（格式2022-01-
14）**/
	private Date date;

	/**电影开始时间（格式11:37:03**/
	private Date startTime;

	/**电影结束时间（格式11:37:03）**/
	private Date endTime;

	/**排片人**/
	private String founder;

	/**添加时间（格式2022-01-14\n11:37:03）**/
	private Date createTime;

	/**更新时间**/
	private Date updateTime;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setFid(Integer fid){
		this.fid = fid;
	}

	public Integer getFid(){
		return this.fid;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public void setSeatNumber(Integer seatNumber){
		this.seatNumber = seatNumber;
	}

	public Integer getSeatNumber(){
		return this.seatNumber;
	}

	public void setBoxOffice(Integer boxOffice){
		this.boxOffice = boxOffice;
	}

	public Integer getBoxOffice(){
		return this.boxOffice;
	}

	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}

	public java.math.BigDecimal getPrice(){
		return this.price;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return this.type;
	}

	public void setDate(Date date){
		this.date = date;
	}

	public Date getDate(){
		return this.date;
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

	public void setFounder(String founder){
		this.founder = founder;
	}

	public String getFounder(){
		return this.founder;
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
		return "Arrangement [id=" + id + ", fid=" + fid + ", name=" + name + ", seatNumber=" + seatNumber + ", boxOffice=" + boxOffice + ", price=" + price + ", type=" + type + ", date=" + date + ", startTime=" + startTime + ", endTime=" + endTime + ", founder=" + founder + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
