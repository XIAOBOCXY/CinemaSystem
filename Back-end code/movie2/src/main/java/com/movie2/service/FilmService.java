package com.movie2.service;

import com.movie2.model.entity.Film;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 
 * FilmService业务逻辑操作接口类
 * 
 **/

public interface FilmService{

	//获得热门电影
	List<Film> getFilmsHot( int hot);
	//查询（根据主键ID查询）
	public Film  selectByPrimaryKey(Integer id);
	//查询全部信息
	public List<Film>  selectAllFilms();
	public List<Film>  selectAllFilms(String area,String type);
	//条件查询信息
	public List<Film>  selectFilmsByCondition(Film record);
	//查询（获得数据表中的数量）
	public int selectFilmCount();
	//查询（根据 Map条件查询需要的结果）

	public int selectFilmsCountByCondition(Map<String, Object> map);

	//查询（通过Map条件，获得所有符合的信息）

	public List<Film> selectFilmsCondition(Map<String, Object> map);
	//查询（通过Map条件进行分页查询）

	public List<Film> selectFilmsPerPageByCondition(Map<String, Object> map);

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey(Integer id);
	//删除（条件删除）
	public int deleteByCondition(Film record);
	//添加
	public int insert(Film record);
	//添加 （匹配有值的字段）
	public int insertSelective(Film record);
	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective(Film record);
	//修改（根据主键ID修改）
	public int updateByPrimaryKey(Film record);

}