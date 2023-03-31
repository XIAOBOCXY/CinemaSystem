package com.movie2.model.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.annotation.sql.DataSourceDefinitions;
import java.io.Serializable;
import java.util.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order implements Serializable {

	/**主键**/
	private Integer id;

	/**用户主键（关联t_user表）**/
	private Integer uid;

	/**电话号**/
	private String phone;

	/**排片id（关联t_arrangement）**/
	private Integer aid;

	/**座位**/
	private String seats;

	/**金额**/
	private java.math.BigDecimal price;

	/**状态 0-等待支付；1-支付失败；2-支付成
功；3-已被撤销**/
	private Integer status;

	/**支付时间**/
	private Date payAt;

	/**添加时间（格式2022-01-14 11:37:03）**/
	private Date createTime;

	/**更新时间**/
	private Date updateTime;


}
