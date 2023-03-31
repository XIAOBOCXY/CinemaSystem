package com.movie2.service.impl;

import com.movie2.model.entity.Registration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.RegistrationMapper;
import com.movie2.service.RegistrationService;


/**
 * 
 * RegistrationService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	RegistrationMapper registrationMapper;
	

	//查询（根据主键ID查询）
	public Registration  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Registration信息:selectByPrimaryKey() ");
		return registrationMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectRegistrationCount(){
		log.info("获得Registration数量信息:selectRegistrationCount() ");
		return registrationMapper.selectRegistrationCount();
	}

	//查询全部信息
	public List<Registration>  selectAllRegistrations ( ){
		log.info("获得全部Registration信息:selectAllRegistrations() ");
		return registrationMapper.selectAllRegistrations ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectRegistrationsCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Registration信息:selectRegistrationsCountByCondition() ");
		return registrationMapper.selectRegistrationsCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Registration> selectRegistrationsCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Registration信息:selectRegistrationsCountByCondition() ");
		return registrationMapper.selectRegistrationsCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Registration> selectRegistrationsPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Registration信息:selectRegistrationsPerPageByCondition() ");
		return registrationMapper.selectRegistrationsPerPageByCondition(map);
	}

	//条件查询
	public List<Registration>  selectRegistrationsByCondition (Registration record){
		log.info("通过传入的参数条件Registration信息进行查询:selectRegistrationsByCondition () ");
		return registrationMapper.selectRegistrationsByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Registration record ){
		log.info("根据传入的Registration信息，条件删除:deleteByCondition() ");
		return registrationMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Registration信息:deleteByPrimaryKey() ");
		return registrationMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Registration record ){
		log.info("新增Registration信息:insert() ");
		return registrationMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Registration record ){
		log.info("新增（匹配有值的字段）Registration信息:insertSelective() ");
		return registrationMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Registration record ){
		log.info("修改（匹配有值的字段）Registration信息:updateByPrimaryKeySelective() ");
		return registrationMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Registration record ){
		log.info("修改Registration信息:updateByPrimaryKey() ");
		return registrationMapper.updateByPrimaryKey(record);
	}

}