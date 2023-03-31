package com.movie2.service.impl;

import com.movie2.model.entity.Arrangement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.ArrangementMapper;
import com.movie2.service.ArrangementService;


/**
 * 
 * ArrangementService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class ArrangementServiceImpl implements ArrangementService{

	@Autowired
	ArrangementMapper arrangementMapper;

	//查询（根据主键ID查询）
	public Arrangement  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Arrangement信息:selectByPrimaryKey() ");
		return arrangementMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectArrangementCount(){
		log.info("获得Arrangement数量信息:selectArrangementCount() ");
		return arrangementMapper.selectArrangementCount();
	}

	//查询全部信息
	public List<Arrangement>  selectAllArrangements ( ){
		log.info("获得全部Arrangement信息:selectAllArrangements() ");
		return arrangementMapper.selectAllArrangements ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectArrangementsCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Arrangement信息:selectArrangementsCountByCondition() ");
		return arrangementMapper.selectArrangementsCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Arrangement> selectArrangementsCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Arrangement信息:selectArrangementsCountByCondition() ");
		return arrangementMapper.selectArrangementsCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Arrangement> selectArrangementsPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Arrangement信息:selectArrangementsPerPageByCondition() ");
		return arrangementMapper.selectArrangementsPerPageByCondition(map);
	}

	//条件查询
	public List<Arrangement>  selectArrangementsByCondition (Arrangement record){
		log.info("通过传入的参数条件Arrangement信息进行查询:selectArrangementsByCondition () ");
		return arrangementMapper.selectArrangementsByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Arrangement record ){
		log.info("根据传入的Arrangement信息，条件删除:deleteByCondition() ");
		return arrangementMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Arrangement信息:deleteByPrimaryKey() ");
		return arrangementMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Arrangement record ){
		log.info("新增Arrangement信息:insert() ");
		return arrangementMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Arrangement record ){
		log.info("新增（匹配有值的字段）Arrangement信息:insertSelective() ");
		return arrangementMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Arrangement record ){
		log.info("修改（匹配有值的字段）Arrangement信息:updateByPrimaryKeySelective() ");
		return arrangementMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Arrangement record ){
		log.info("修改Arrangement信息:updateByPrimaryKey() ");
		return arrangementMapper.updateByPrimaryKey(record);
	}

}