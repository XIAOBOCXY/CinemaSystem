package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.3 客服表（t_worker）
 * 
 **/
@SuppressWarnings("serial")
public class Worker implements Serializable {

	/**主键**/
	private Integer id;

	/**用户名**/
	private String username;

	/**密码**/
	private String password;

	/**头像（图片id，关联t_film.id）**/
	private String avatar;

	/**昵称**/
	private String nickname;

	/**电话**/
	private String phone;

	/**状态1-在职 0-离职**/
	private String entry;

	/**性别（男；女）**/
	private String gender;

	/**部门**/
	private String department;

	/**创建时间（格式2022-01-14\n11:37:03）**/
	private Date createTime;

	/**更新时间（格式2022-01-14\n11:37:03）**/
	private Date updateTime;



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

	public void setNickname(String nickname){
		this.nickname = nickname;
	}

	public String getNickname(){
		return this.nickname;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return this.phone;
	}

	public void setEntry(String entry){
		this.entry = entry;
	}

	public String getEntry(){
		return this.entry;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return this.gender;
	}

	public void setDepartment(String department){
		this.department = department;
	}

	public String getDepartment(){
		return this.department;
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
		return "Worker [id=" + id + ", username=" + username + ", password=" + password + ", avatar=" + avatar + ", nickname=" + nickname + ", phone=" + phone + ", entry=" + entry + ", gender=" + gender + ", department=" + department + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
