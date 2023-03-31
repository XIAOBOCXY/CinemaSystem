package com.movie2.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinitions;
import java.io.Serializable;
import java.util.*;



/**
 * 
 * 
 * 
 **/
@SuppressWarnings("serial")

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderException implements Serializable {

	/****/
	private Integer id;

	/****/
	private Integer oid;

	/****/
	private String reason;

	/****/
	private Integer status;

	/****/
	private String result;

	/****/
	private String reviewer;

	/****/
	private Date createAt;

	/****/
	private Date endAt;




}
