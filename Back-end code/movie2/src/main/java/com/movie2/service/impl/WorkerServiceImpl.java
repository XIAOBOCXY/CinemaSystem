package com.movie2.service.impl;

import com.movie2.model.entity.Worker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.WorkerMapper;
import com.movie2.service.WorkerService;


/**
 * 
 * WorkerService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class WorkerServiceImpl implements WorkerService{

	@Autowired
	WorkerMapper workerMapper;
	
	//查询（根据主键ID查询）
	public Worker  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Worker信息:selectByPrimaryKey() ");
		return workerMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectWorkerCount(){
		log.info("获得Worker数量信息:selectWorkerCount() ");
		return workerMapper.selectWorkerCount();
	}

	//查询全部信息
	public List<Worker>  selectAllWorkers ( ){
		log.info("获得全部Worker信息:selectAllWorkers() ");
		return workerMapper.selectAllWorkers ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectWorkersCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Worker信息:selectWorkersCountByCondition() ");
		return workerMapper.selectWorkersCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Worker> selectWorkersCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Worker信息:selectWorkersCountByCondition() ");
		return workerMapper.selectWorkersCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Worker> selectWorkersPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Worker信息:selectWorkersPerPageByCondition() ");
		return workerMapper.selectWorkersPerPageByCondition(map);
	}

	//条件查询
	public List<Worker>  selectWorkersByCondition (Worker record){
		log.info("通过传入的参数条件Worker信息进行查询:selectWorkersByCondition () ");
		return workerMapper.selectWorkersByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Worker record ){
		log.info("根据传入的Worker信息，条件删除:deleteByCondition() ");
		return workerMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Worker信息:deleteByPrimaryKey() ");
		return workerMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Worker record ){
		log.info("新增Worker信息:insert() ");
		return workerMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Worker record ){
		log.info("新增（匹配有值的字段）Worker信息:insertSelective() ");
		return workerMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Worker record ){
		log.info("修改（匹配有值的字段）Worker信息:updateByPrimaryKeySelective() ");
		return workerMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Worker record ){
		log.info("修改Worker信息:updateByPrimaryKey() ");
		return workerMapper.updateByPrimaryKey(record);
	}

}