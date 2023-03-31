package com.movie2.service.impl;

import com.movie2.model.entity.Seat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.SeatMapper;
import com.movie2.service.SeatService;


/**
 * 
 * SeatService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class SeatServiceImpl implements SeatService{

	@Autowired
	SeatMapper seatMapper;
	//批量添加
	public int insertList(List<Seat> list){
		log.info("批量添加:insertList(List<Seat> list ");
		return seatMapper.insertList(list);
	}

	//查询（根据主键ID查询）
	public Seat  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Seat信息:selectByPrimaryKey() ");
		return seatMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectSeatCount(){
		log.info("获得Seat数量信息:selectSeatCount() ");
		return seatMapper.selectSeatCount();
	}

	//查询全部信息
	public List<Seat>  selectAllSeats ( ){
		log.info("获得全部Seat信息:selectAllSeats() ");
		return seatMapper.selectAllSeats ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectSeatsCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Seat信息:selectSeatsCountByCondition() ");
		return seatMapper.selectSeatsCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Seat> selectSeatsCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Seat信息:selectSeatsCountByCondition() ");
		return seatMapper.selectSeatsCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Seat> selectSeatsPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Seat信息:selectSeatsPerPageByCondition() ");
		return seatMapper.selectSeatsPerPageByCondition(map);
	}

	//条件查询
	public List<Seat>  selectSeatsByCondition (Seat record){
		log.info("通过传入的参数条件Seat信息进行查询:selectSeatsByCondition () ");
		return seatMapper.selectSeatsByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Seat record ){
		log.info("根据传入的Seat信息，条件删除:deleteByCondition() ");
		return seatMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Seat信息:deleteByPrimaryKey() ");
		return seatMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Seat record ){
		log.info("新增Seat信息:insert() ");
		return seatMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Seat record ){
		log.info("新增（匹配有值的字段）Seat信息:insertSelective() ");
		return seatMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Seat record ){
		log.info("修改（匹配有值的字段）Seat信息:updateByPrimaryKeySelective() ");
		return seatMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Seat record ){
		log.info("修改Seat信息:updateByPrimaryKey() ");
		return seatMapper.updateByPrimaryKey(record);
	}

}