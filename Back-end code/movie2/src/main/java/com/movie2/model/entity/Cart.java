package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.15 购物车表（t_cart）
 * 
 **/
@SuppressWarnings("serial")
public class Cart implements Serializable {

	/**主键**/
	private Integer id;

	/**用户主键（关联t_user表）**/
	private Integer uid;

	/**排片id（关联
t_arrangement）**/
	private Integer aid;

	/**状态**/
	private Integer status;

	/**处理结果**/
	private String phone;

	/**座位**/
	private String seats;

	/**金额**/
	private java.math.BigDecimal price;

	/****/
	private Date createTime;

	/****/
	private Date updateTime;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setUid(Integer uid){
		this.uid = uid;
	}

	public Integer getUid(){
		return this.uid;
	}

	public void setAid(Integer aid){
		this.aid = aid;
	}

	public Integer getAid(){
		return this.aid;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setSeats(String seats){
		this.seats = seats;
	}

	public String getSeats(){
		return this.seats;
	}

	public void setPrice(java.math.BigDecimal price){
		this.price = price;
	}

	public java.math.BigDecimal getPrice(){
		return this.price;
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
		return "Cart [id=" + id + ", uid=" + uid + ", aid=" + aid + ", status=" + status + ", phone=" + phone + ", seats=" + seats + ", price=" + price + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
