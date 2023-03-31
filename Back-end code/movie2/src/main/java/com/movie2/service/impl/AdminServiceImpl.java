package com.movie2.service.impl;

import com.movie2.mapper.AdminMapper;
import com.movie2.model.entity.Admin;
import com.movie2.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 
 * AdminService业务逻辑操作接口类的实现类
 * 
 **/
@Service
@Transactional
@Slf4j
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminMapper adminMapper;

	//查询（根据主键ID查询）
	public Admin selectByPrimaryKey (Integer id){
		log.info("通过主键查询Admin信息:selectByPrimaryKey() ");
		return adminMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectAdminCount(){
		log.info("获得Admin数量信息:selectAdminCount() ");
		return adminMapper.selectAdminCount();
	}

	//查询全部信息
	public List<Admin>  selectAllAdmins ( ){
		log.info("获得全部Admin信息:selectAllAdmins() ");
		return adminMapper.selectAllAdmins ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectAdminsCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Admin信息:selectAdminsCountByCondition() ");
		return adminMapper.selectAdminsCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Admin> selectAdminsCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Admin信息:selectAdminsCountByCondition() ");
		return adminMapper.selectAdminsCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Admin> selectAdminsPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Admin信息:selectAdminsPerPageByCondition() ");
		return adminMapper.selectAdminsPerPageByCondition(map);
	}

	//条件查询
	public List<Admin>  selectAdminsByCondition (Admin record){
		log.info("通过传入的参数条件Admin信息进行查询:selectAdminsByCondition () ");
		return adminMapper.selectAdminsByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Admin record ){
		log.info("根据传入的Admin信息，条件删除:deleteByCondition() ");
		return adminMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Admin信息:deleteByPrimaryKey() ");
		return adminMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Admin record ){
		log.info("新增Admin信息:insert() ");
		return adminMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Admin record ){
		log.info("新增（匹配有值的字段）Admin信息:insertSelective() ");
		return adminMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Admin record ){
		log.info("修改（匹配有值的字段）Admin信息:updateByPrimaryKeySelective() ");
		return adminMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Admin record ){
		log.info("修改Admin信息:updateByPrimaryKey() ");
		return adminMapper.updateByPrimaryKey(record);
	}

}