package com.movie2.service.impl;

import com.movie2.model.entity.WorkerEvaluate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.WorkerEvaluateMapper;
import com.movie2.service.WorkerEvaluateService;


/**
 * 
 * WorkerEvaluateService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class WorkerEvaluateServiceImpl implements WorkerEvaluateService{

	@Autowired
	WorkerEvaluateMapper workerEvaluateMapper;
	

	//查询（根据主键ID查询）
	public WorkerEvaluate  selectByPrimaryKey (Integer id){
		log.info("通过主键查询WorkerEvaluate信息:selectByPrimaryKey() ");
		return workerEvaluateMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectWorkerEvaluateCount(){
		log.info("获得WorkerEvaluate数量信息:selectWorkerEvaluateCount() ");
		return workerEvaluateMapper.selectWorkerEvaluateCount();
	}

	//查询全部信息
	public List<WorkerEvaluate>  selectAllWorkerEvaluates ( ){
		log.info("获得全部WorkerEvaluate信息:selectAllWorkerEvaluates() ");
		return workerEvaluateMapper.selectAllWorkerEvaluates ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectWorkerEvaluatesCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的WorkerEvaluate信息:selectWorkerEvaluatesCountByCondition() ");
		return workerEvaluateMapper.selectWorkerEvaluatesCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<WorkerEvaluate> selectWorkerEvaluatesCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的WorkerEvaluate信息:selectWorkerEvaluatesCountByCondition() ");
		return workerEvaluateMapper.selectWorkerEvaluatesCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<WorkerEvaluate> selectWorkerEvaluatesPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询WorkerEvaluate信息:selectWorkerEvaluatesPerPageByCondition() ");
		return workerEvaluateMapper.selectWorkerEvaluatesPerPageByCondition(map);
	}

	//条件查询
	public List<WorkerEvaluate>  selectWorkerEvaluatesByCondition (WorkerEvaluate record){
		log.info("通过传入的参数条件WorkerEvaluate信息进行查询:selectWorkerEvaluatesByCondition () ");
		return workerEvaluateMapper.selectWorkerEvaluatesByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( WorkerEvaluate record ){
		log.info("根据传入的WorkerEvaluate信息，条件删除:deleteByCondition() ");
		return workerEvaluateMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除WorkerEvaluate信息:deleteByPrimaryKey() ");
		return workerEvaluateMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( WorkerEvaluate record ){
		log.info("新增WorkerEvaluate信息:insert() ");
		return workerEvaluateMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( WorkerEvaluate record ){
		log.info("新增（匹配有值的字段）WorkerEvaluate信息:insertSelective() ");
		return workerEvaluateMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( WorkerEvaluate record ){
		log.info("修改（匹配有值的字段）WorkerEvaluate信息:updateByPrimaryKeySelective() ");
		return workerEvaluateMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( WorkerEvaluate record ){
		log.info("修改WorkerEvaluate信息:updateByPrimaryKey() ");
		return workerEvaluateMapper.updateByPrimaryKey(record);
	}

}