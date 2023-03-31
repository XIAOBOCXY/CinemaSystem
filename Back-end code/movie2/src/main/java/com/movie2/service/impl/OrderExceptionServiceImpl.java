package com.movie2.service.impl;

import com.movie2.mapper.OrderExceptionMapper;
import com.movie2.model.entity.OrderException;
import com.movie2.service.OrderExceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.OrderExceptionMapper;
import com.movie2.service.OrderExceptionService;


/**
 * 
 * OrderExceptionService业务逻辑操作接口类的实现类
 * 
 **/
@Service
@Transactional
@Slf4j
public class OrderExceptionServiceImpl implements OrderExceptionService {

	@Autowired
	OrderExceptionMapper orderExceptionMapper;

	//查询（根据主键ID查询）
	public OrderException selectByPrimaryKey (Integer id){
		log.info("通过主键查询OrderException信息:selectByPrimaryKey() ");
		return orderExceptionMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectOrderExceptionCount(){
		log.info("获得OrderException数量信息:selectOrderExceptionCount() ");
		return orderExceptionMapper.selectOrderExceptionCount();
	}

	//查询全部信息
	public List<OrderException>  selectAllOrderExceptions ( ){
		log.info("获得全部OrderException信息:selectAllOrderExceptions() ");
		return orderExceptionMapper.selectAllOrderExceptions ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectOrderExceptionsCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的OrderException信息:selectOrderExceptionsCountByCondition() ");
		return orderExceptionMapper.selectOrderExceptionsCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<OrderException> selectOrderExceptionsCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的OrderException信息:selectOrderExceptionsCountByCondition() ");
		return orderExceptionMapper.selectOrderExceptionsCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<OrderException> selectOrderExceptionsPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询OrderException信息:selectOrderExceptionsPerPageByCondition() ");
		return orderExceptionMapper.selectOrderExceptionsPerPageByCondition(map);
	}

	//条件查询
	public List<OrderException>  selectOrderExceptionsByCondition (OrderException record){
		log.info("通过传入的参数条件OrderException信息进行查询:selectOrderExceptionsByCondition () ");
		return orderExceptionMapper.selectOrderExceptionsByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( OrderException record ){
		log.info("根据传入的OrderException信息，条件删除:deleteByCondition() ");
		return orderExceptionMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除OrderException信息:deleteByPrimaryKey() ");
		return orderExceptionMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( OrderException record ){
		log.info("新增OrderException信息:insert() ");
		return orderExceptionMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( OrderException record ){
		log.info("新增（匹配有值的字段）OrderException信息:insertSelective() ");
		return orderExceptionMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( OrderException record ){
		log.info("修改（匹配有值的字段）OrderException信息:updateByPrimaryKeySelective() ");
		return orderExceptionMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( OrderException record ){
		log.info("修改OrderException信息:updateByPrimaryKey() ");
		return orderExceptionMapper.updateByPrimaryKey(record);
	}

}