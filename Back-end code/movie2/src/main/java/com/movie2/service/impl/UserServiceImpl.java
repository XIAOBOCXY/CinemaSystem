package com.movie2.service.impl;

import com.movie2.config.MyConstants;
import com.movie2.model.entity.Poster;
import com.movie2.model.entity.User;
import com.movie2.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.UserMapper;
import com.movie2.service.UserService;


/**
 * 
 * UserService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService{

	@Autowired
	UserMapper userMapper;

	//查询（根据主键ID查询）
	public User  selectByPrimaryKey (Integer id){
		log.info("通过主键查询User信息:selectByPrimaryKey() ");
		return userMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectUserCount(){
		log.info("获得User数量信息:selectUserCount() ");
		return userMapper.selectUserCount();
	}

	//查询全部信息
	public List<User>  selectAllUsers ( ){
		log.info("获得全部User信息:selectAllUsers() ");
		return userMapper.selectAllUsers ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectUsersCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的User信息:selectUsersCountByCondition() ");
		return userMapper.selectUsersCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<User> selectUsersCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的User信息:selectUsersCountByCondition() ");
		return userMapper.selectUsersCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<User> selectUsersPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询User信息:selectUsersPerPageByCondition() ");
		return userMapper.selectUsersPerPageByCondition(map);
	}

	//条件查询
	public List<User>  selectUsersByCondition (User record){
		log.info("通过传入的参数条件User信息进行查询:selectUsersByCondition () ");
		return userMapper.selectUsersByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( User record ){
		log.info("根据传入的User信息，条件删除:deleteByCondition() ");
		return userMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除User信息:deleteByPrimaryKey() ");
		return userMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( User record ){
		log.info("新增User信息:insert() ");
		//加密
		String salt = RandomStringUtils.randomAlphabetic(10);
		int index =(int)(Math.random()* MD5Util.TOTAL);
		record.setSlat(salt);
		record.setSlatIndex(index);
		String newPass = MD5Util.md5To32String(record.getPassword(),salt,index);
		record.setPassword(newPass);
		record.setStatus(0);
		record.setErrorNum(0);
		return userMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( User record ){
		log.info("新增（匹配有值的字段）User信息:insertSelective() ");
		return userMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( User record ){
		log.info("修改（匹配有值的字段）User信息:updateByPrimaryKeySelective() ");
		return userMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( User record ){
		log.info("修改User信息:updateByPrimaryKey() ");
		return userMapper.updateByPrimaryKey(record);
	}

}