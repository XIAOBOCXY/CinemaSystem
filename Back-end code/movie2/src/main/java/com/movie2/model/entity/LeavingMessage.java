package com.movie2.model.entity;
import java.io.Serializable;
import java.util.*;


/**
 * 
 * 4.2.6 影院留言表（t_leaving_message）
 * 
 **/
@SuppressWarnings("serial")
public class LeavingMessage implements Serializable {

	/**主键**/
	private Integer id;

	/**用户主键（关联t_user表）**/
	private Integer uid;

	/**留言内容**/
	private String content;

	/**回复内容**/
	private String reply;

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

	public void setUid(Integer uid){
		this.uid = uid;
	}

	public Integer getUid(){
		return this.uid;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return this.content;
	}

	public void setReply(String reply){
		this.reply = reply;
	}

	public String getReply(){
		return this.reply;
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
		return "LeavingMessage [id=" + id + ", uid=" + uid + ", content=" + content + ", reply=" + reply + ", createTime=" + createTime + ", updateTime=" + updateTime+ "]";
	}
}
