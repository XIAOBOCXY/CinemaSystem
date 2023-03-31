package com.movie2.service;

import com.movie2.model.entity.FilmEvaluate;
import java.util.List;
import java.util.Map;

/**
 * 
 * FilmEvaluateService业务逻辑操作接口类
 * 
 **/

public interface FilmEvaluateService{

	//获得评论与用户信息
	List<FilmEvaluate>  selectAllFilmEvaluatesAndUser(Integer fid);

	//查询（根据主键ID查询）
	public FilmEvaluate  selectByPrimaryKey(Integer id);
	//查询全部信息
	public List<FilmEvaluate>  selectAllFilmEvaluates();
	//条件查询信息
	public List<FilmEvaluate>  selectFilmEvaluatesByCondition(FilmEvaluate record);
	//查询（获得数据表中的数量）
	public int selectFilmEvaluateCount();
	//查询（根据 Map条件查询需要的结果）

	public int selectFilmEvaluatesCountByCondition(Map<String, Object> map);

	//查询（通过Map条件，获得所有符合的信息）

	public List<FilmEvaluate> selectFilmEvaluatesCondition(Map<String, Object> map);
	//查询（通过Map条件进行分页查询）

	public List<FilmEvaluate> selectFilmEvaluatesPerPageByCondition(Map<String, Object> map);

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey(Integer id);
	//删除（条件删除）
	public int deleteByCondition(FilmEvaluate record);
	//添加
	public int insert(FilmEvaluate record);
	//添加 （匹配有值的字段）
	public int insertSelective(FilmEvaluate record);
	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective(FilmEvaluate record);
	//修改（根据主键ID修改）
	public int updateByPrimaryKey(FilmEvaluate record);

}