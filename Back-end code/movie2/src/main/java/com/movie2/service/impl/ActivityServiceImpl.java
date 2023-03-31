package com.movie2.service.impl;

import com.movie2.model.entity.Activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.ActivityMapper;
import com.movie2.service.ActivityService;


/**
 * 
 * ActivityService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class ActivityServiceImpl implements ActivityService{

	@Autowired
	ActivityMapper activityMapper;
	

	//查询（根据主键ID查询）
	public Activity  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Activity信息:selectByPrimaryKey() ");
		return activityMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectActivityCount(){
		log.info("获得Activity数量信息:selectActivityCount() ");
		return activityMapper.selectActivityCount();
	}

	//查询全部信息
	public List<Activity>  selectAllActivitys ( ){
		log.info("获得全部Activity信息:selectAllActivitys() ");
		return activityMapper.selectAllActivitys ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectActivitysCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Activity信息:selectActivitysCountByCondition() ");
		return activityMapper.selectActivitysCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Activity> selectActivitysCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Activity信息:selectActivitysCountByCondition() ");
		return activityMapper.selectActivitysCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Activity> selectActivitysPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Activity信息:selectActivitysPerPageByCondition() ");
		return activityMapper.selectActivitysPerPageByCondition(map);
	}

	//条件查询
	public List<Activity>  selectActivitysByCondition (Activity record){
		log.info("通过传入的参数条件Activity信息进行查询:selectActivitysByCondition () ");
		return activityMapper.selectActivitysByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Activity record ){
		log.info("根据传入的Activity信息，条件删除:deleteByCondition() ");
		return activityMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Activity信息:deleteByPrimaryKey() ");
		return activityMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Activity record ){
		log.info("新增Activity信息:insert() ");
		return activityMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Activity record ){
		log.info("新增（匹配有值的字段）Activity信息:insertSelective() ");
		return activityMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Activity record ){
		log.info("修改（匹配有值的字段）Activity信息:updateByPrimaryKeySelective() ");
		return activityMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Activity record ){
		log.info("修改Activity信息:updateByPrimaryKey() ");
		return activityMapper.updateByPrimaryKey(record);
	}

}