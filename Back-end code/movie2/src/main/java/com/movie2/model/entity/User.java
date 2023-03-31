package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.1 用户表
 * 
 **/
@SuppressWarnings("serial")
public class User implements Serializable {

	/**'主键'**/
	private Integer id;

	/**'用户名'**/
	private String username;

	/**'密码'**/
	private String password;

	/**'盐'**/
	private String slat;

	/**'索引'**/
	private Integer slatIndex;

	/****/
	private String avatar;

	/**'昵称'**/
	private String nickname;

	/**'邮箱'**/
	private String email;

	/**生日（格式2022-01-02）
**/
	private Date birthday;

	/**'性别（男生；女生）'**/
	private String gender;

	/**'个人简介'**/
	private String info;

	/**'创建时间（格式2022-01-14'**/
	private Date createTime;

	/**'更新时间（格式2022-01-14'**/
	private Date updateTime;

	/**'错误次数'**/
	private Integer errorNum;

	/**'状态'**/
	private Integer status;



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

	public void setSlat(String slat){
		this.slat = slat;
	}

	public String getSlat(){
		return this.slat;
	}

	public void setSlatIndex(Integer slatIndex){
		this.slatIndex = slatIndex;
	}

	public Integer getSlatIndex(){
		return this.slatIndex;
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

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return this.email;
	}

	public void setBirthday(Date birthday){
		this.birthday = birthday;
	}

	public Date getBirthday(){
		return this.birthday;
	}

	public void setGender(String gender){
		this.gender = gender;
	}

	public String getGender(){
		return this.gender;
	}

	public void setInfo(String info){
		this.info = info;
	}

	public String getInfo(){
		return this.info;
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

	public void setErrorNum(Integer errorNum){
		this.errorNum = errorNum;
	}

	public Integer getErrorNum(){
		return this.errorNum;
	}

	public void setStatus(Integer status){
		this.status = status;
	}

	public Integer getStatus(){
		return this.status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", slat=" + slat + ", slatIndex=" + slatIndex + ", avatar=" + avatar + ", nickname=" + nickname + ", email=" + email + ", birthday=" + birthday + ", gender=" + gender + ", info=" + info + ", createTime=" + createTime + ", updateTime=" + updateTime + ", errorNum=" + errorNum + ", status=" + status+ "]";
	}
}
