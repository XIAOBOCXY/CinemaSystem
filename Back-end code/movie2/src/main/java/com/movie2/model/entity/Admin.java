package com.movie2.model.entity;

import java.io.Serializable;



/**
 * 
 * 管理员信息表
 * 
 **/
@SuppressWarnings("serial")
public class Admin implements Serializable {

	/**主键**/
	private Integer id;

	/**用户名**/
	private String username;

	/**密码**/
	private String password;

	/**头像**/
	private String avatar;



	public void setId(Integer id){
		this.id = id;
	}

	public Integer getId(){
		return this.id;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return this.username;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return this.password;
	}

	public void setAvatar(String avatar){
		this.avatar = avatar;
	}

	public String getAvatar(){
		return this.avatar;
	}

	@Override
	public String toString() {
		return "Admin [id=" + id + ", username=" + username + ", password=" + password + ", avatar=" + avatar+ "]";
	}
}
