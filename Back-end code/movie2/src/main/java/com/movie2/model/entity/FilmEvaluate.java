package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 电影评论表
 * 
 **/
@SuppressWarnings("serial")
public class FilmEvaluate implements Serializable {

	/**主键（主键索引），自增**/
	private Integer id;

	/**电影id**/
	private Integer fid;

	/**用户id**/
	private Integer uid;

	/**评分**/
	private java.math.BigDecimal star;

	/**内容**/
	private String comment;

	/**添加时间**/
	private Date createAt;

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

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

	public void setUid(Integer uid){
		this.uid = uid;
	}

	public Integer getUid(){
		return this.uid;
	}

	public void setStar(java.math.BigDecimal star){
		this.star = star;
	}

	public java.math.BigDecimal getStar(){
		return this.star;
	}

	public void setComment(String comment){
		this.comment = comment;
	}

	public String getComment(){
		return this.comment;
	}

	public void setCreateAt(Date createAt){
		this.createAt = createAt;
	}

	public Date getCreateAt(){
		return this.createAt;
	}

	@Override
	public String toString() {
		return "FilmEvaluate [id=" + id + ", fid=" + fid + ", uid=" + uid + ", star=" + star + ", comment=" + comment + ", createAt=" + createAt+ "]";
	}
}
