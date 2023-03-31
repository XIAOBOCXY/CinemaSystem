package com.movie2.service.impl;

import com.movie2.config.MyConstants;
import com.movie2.model.entity.Film;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.FilmMapper;
import com.movie2.service.FilmService;


/**
 * 
 * FilmService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class FilmServiceImpl implements FilmService{

	@Autowired
	FilmMapper filmMapper;


	@Override
	public List<Film> getFilmsHot(int hot) {
		log.info("获得热门电影Film信息:getFilmsHot(int hot) ");
		List<Film> filmsHot = filmMapper.getFilmsHot(hot);
		for (Film film : filmsHot) {
			film.setCover(MyConstants.MY_URL+film.getCover());
		}
		return filmsHot;
	}

	//查询（根据主键ID查询）
	public Film  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Film信息:selectByPrimaryKey() ");
		return filmMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectFilmCount(){
		log.info("获得Film数量信息:selectFilmCount() ");
		return filmMapper.selectFilmCount();
	}

	//查询全部信息
	public List<Film>  selectAllFilms ( ){
		log.info("获得全部Film信息:selectAllFilms() ");
		List<Film> films = filmMapper.selectAllFilms();
		for (Film film : films) {
			film.setCover(MyConstants.MY_URL+film.getCover());
		}
		return films;
	}

	//查询全部信息
	public List<Film>  selectAllFilms (String area,String type){
		log.info("获得全部Film信息:selectAllFilms(String area,String type) ");
		Map<String,Object> map = new HashMap<>();
		map.put("area",area);
		map.put("type",type);
		List<Film> films = filmMapper.selectFilmsCondition(map);
		for (Film film : films) {
			film.setCover(MyConstants.MY_URL+film.getCover());
		}
		return films;
	}

	//查询（根据其他条件查询需要的结果）

	public int selectFilmsCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Film信息:selectFilmsCountByCondition() ");
		return filmMapper.selectFilmsCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Film> selectFilmsCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Film信息:selectFilmsCountByCondition() ");
		return filmMapper.selectFilmsCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Film> selectFilmsPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Film信息:selectFilmsPerPageByCondition() ");
		return filmMapper.selectFilmsPerPageByCondition(map);
	}

	//条件查询
	public List<Film>  selectFilmsByCondition (Film record){
		log.info("通过传入的参数条件Film信息进行查询:selectFilmsByCondition () ");
		return filmMapper.selectFilmsByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Film record ){
		log.info("根据传入的Film信息，条件删除:deleteByCondition() ");
		return filmMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Film信息:deleteByPrimaryKey() ");
		return filmMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Film record ){
		log.info("新增Film信息:insert() ");
		return filmMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Film record ){
		log.info("新增（匹配有值的字段）Film信息:insertSelective() ");
		return filmMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Film record ){
		log.info("修改（匹配有值的字段）Film信息:updateByPrimaryKeySelective() ");
		return filmMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Film record ){
		log.info("修改Film信息:updateByPrimaryKey() ");
		return filmMapper.updateByPrimaryKey(record);
	}

}