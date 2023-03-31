package com.movie2.service.impl;

import com.movie2.model.entity.Order;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.OrderMapper;
import com.movie2.service.OrderService;


/**
 * 
 * OrderService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class OrderServiceImpl implements OrderService{

	@Autowired
	OrderMapper orderMapper;
	

	//查询（根据主键ID查询）
	public Order  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Order信息:selectByPrimaryKey() ");
		return orderMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectOrderCount(){
		log.info("获得Order数量信息:selectOrderCount() ");
		return orderMapper.selectOrderCount();
	}

	//查询全部信息
	public List<Order>  selectAllOrders ( ){
		log.info("获得全部Order信息:selectAllOrders() ");
		return orderMapper.selectAllOrders ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectOrdersCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Order信息:selectOrdersCountByCondition() ");
		return orderMapper.selectOrdersCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Order> selectOrdersCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Order信息:selectOrdersCountByCondition() ");
		return orderMapper.selectOrdersCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Order> selectOrdersPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Order信息:selectOrdersPerPageByCondition() ");
		return orderMapper.selectOrdersPerPageByCondition(map);
	}

	//条件查询
	public List<Order>  selectOrdersByCondition (Order record){
		log.info("通过传入的参数条件Order信息进行查询:selectOrdersByCondition () ");
		return orderMapper.selectOrdersByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Order record ){
		log.info("根据传入的Order信息，条件删除:deleteByCondition() ");
		return orderMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Order信息:deleteByPrimaryKey() ");
		return orderMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Order record ){
		log.info("新增Order信息:insert() ");
		return orderMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Order record ){
		log.info("新增（匹配有值的字段）Order信息:insertSelective() ");
		return orderMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Order record ){
		log.info("修改（匹配有值的字段）Order信息:updateByPrimaryKeySelective() ");
		return orderMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Order record ){
		log.info("修改Order信息:updateByPrimaryKey() ");
		return orderMapper.updateByPrimaryKey(record);
	}

}