package com.movie2.service;

import com.movie2.model.entity.Arrangement;
import java.util.List;
import java.util.Map;

/**
 * 
 * ArrangementService业务逻辑操作接口类
 * 
 **/

public interface ArrangementService{


	//查询（根据主键ID查询）
	public Arrangement  selectByPrimaryKey(Integer id);
	//查询全部信息
	public List<Arrangement>  selectAllArrangements();
	//条件查询信息
	public List<Arrangement>  selectArrangementsByCondition(Arrangement record);
	//查询（获得数据表中的数量）
	public int selectArrangementCount();
	//查询（根据 Map条件查询需要的结果）

	public int selectArrangementsCountByCondition(Map<String, Object> map);

	//查询（通过Map条件，获得所有符合的信息）

	public List<Arrangement> selectArrangementsCondition(Map<String, Object> map);
	//查询（通过Map条件进行分页查询）

	public List<Arrangement> selectArrangementsPerPageByCondition(Map<String, Object> map);

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey(Integer id);
	//删除（条件删除）
	public int deleteByCondition(Arrangement record);
	//添加
	public int insert(Arrangement record);
	//添加 （匹配有值的字段）
	public int insertSelective(Arrangement record);
	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective(Arrangement record);
	//修改（根据主键ID修改）
	public int updateByPrimaryKey(Arrangement record);

}