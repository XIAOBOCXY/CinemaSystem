package com.movie2.service;

import com.movie2.model.entity.OrderException;

import java.util.List;
import java.util.Map;


/**
 * 
 * OrderExceptionService业务逻辑操作接口类
 * 
 **/

public interface OrderExceptionService{


	//查询（根据主键ID查询）
	public OrderException selectByPrimaryKey (Integer id );
	//查询全部信息
	public List<OrderException>  selectAllOrderExceptions ( );
	//条件查询信息
	public List<OrderException>  selectOrderExceptionsByCondition (OrderException record);
	//查询（获得数据表中的数量）
	public int selectOrderExceptionCount();
	//查询（根据 Map条件查询需要的结果）

	public int selectOrderExceptionsCountByCondition(Map<String,Object> map);

	//查询（通过Map条件，获得所有符合的信息）

	public List<OrderException> selectOrderExceptionsCondition(Map<String,Object> map);
	//查询（通过Map条件进行分页查询）

	public List<OrderException> selectOrderExceptionsPerPageByCondition(Map<String,Object> map);

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id );
	//删除（条件删除）
	public int deleteByCondition( OrderException record );
	//添加
	public int insert( OrderException record );
	//添加 （匹配有值的字段）
	public int insertSelective( OrderException record );
	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( OrderException record );
	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( OrderException record );

}