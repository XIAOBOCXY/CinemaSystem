package com.movie2.service;

import com.movie2.model.entity.Admin;

import java.util.List;
import java.util.Map;


/**
 * 
 * AdminService业务逻辑操作接口类
 * 
 **/

public interface AdminService{


	//查询（根据主键ID查询）
	public Admin selectByPrimaryKey (Integer id );
	//查询全部信息
	public List<Admin>  selectAllAdmins ( );
	//条件查询信息
	public List<Admin>  selectAdminsByCondition (Admin record);
	//查询（获得数据表中的数量）
	public int selectAdminCount();
	//查询（根据 Map条件查询需要的结果）

	public int selectAdminsCountByCondition(Map<String,Object> map);

	//查询（通过Map条件，获得所有符合的信息）

	public List<Admin> selectAdminsCondition(Map<String,Object> map);
	//查询（通过Map条件进行分页查询）

	public List<Admin> selectAdminsPerPageByCondition(Map<String,Object> map);

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id );
	//删除（条件删除）
	public int deleteByCondition( Admin record );
	//添加
	public int insert( Admin record );
	//添加 （匹配有值的字段）
	public int insertSelective( Admin record );
	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Admin record );
	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Admin record );

}