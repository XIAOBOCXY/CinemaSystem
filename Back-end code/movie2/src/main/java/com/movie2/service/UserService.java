package com.movie2.service;

import com.movie2.model.entity.User;
import java.util.List;
import java.util.Map;

/**
 * 
 * UserService业务逻辑操作接口类
 * 
 **/

public interface UserService{


	//查询（根据主键ID查询）
	public User  selectByPrimaryKey(Integer id);
	//查询全部信息
	public List<User>  selectAllUsers();
	//条件查询信息
	public List<User>  selectUsersByCondition(User record);
	//查询（获得数据表中的数量）
	public int selectUserCount();
	//查询（根据 Map条件查询需要的结果）

	public int selectUsersCountByCondition(Map<String, Object> map);

	//查询（通过Map条件，获得所有符合的信息）

	public List<User> selectUsersCondition(Map<String, Object> map);
	//查询（通过Map条件进行分页查询）

	public List<User> selectUsersPerPageByCondition(Map<String, Object> map);

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey(Integer id);
	//删除（条件删除）
	public int deleteByCondition(User record);
	//添加
	public int insert(User record);
	//添加 （匹配有值的字段）
	public int insertSelective(User record);
	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective(User record);
	//修改（根据主键ID修改）
	public int updateByPrimaryKey(User record);

}