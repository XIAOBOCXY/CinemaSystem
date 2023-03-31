package com.movie2.service.impl;

import com.movie2.model.entity.FilmEvaluate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.FilmEvaluateMapper;
import com.movie2.service.FilmEvaluateService;


/**
 * 
 * FilmEvaluateService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class FilmEvaluateServiceImpl implements FilmEvaluateService{

	@Autowired
	FilmEvaluateMapper filmEvaluateMapper;

	@Override
	public List<FilmEvaluate> selectAllFilmEvaluatesAndUser(Integer id) {
		log.info("selectAllFilmEvaluatesAndUser()");
		return filmEvaluateMapper.selectAllFilmEvaluatesAndUser(id);
	}

	//查询（根据主键ID查询）
	public FilmEvaluate  selectByPrimaryKey (Integer id){
		log.info("通过主键查询FilmEvaluate信息:selectByPrimaryKey() ");
		return filmEvaluateMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectFilmEvaluateCount(){
		log.info("获得FilmEvaluate数量信息:selectFilmEvaluateCount() ");
		return filmEvaluateMapper.selectFilmEvaluateCount();
	}

	//查询全部信息
	public List<FilmEvaluate>  selectAllFilmEvaluates ( ){
		log.info("获得全部FilmEvaluate信息:selectAllFilmEvaluates() ");
		return filmEvaluateMapper.selectAllFilmEvaluates ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectFilmEvaluatesCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的FilmEvaluate信息:selectFilmEvaluatesCountByCondition() ");
		return filmEvaluateMapper.selectFilmEvaluatesCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<FilmEvaluate> selectFilmEvaluatesCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的FilmEvaluate信息:selectFilmEvaluatesCountByCondition() ");
		return filmEvaluateMapper.selectFilmEvaluatesCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<FilmEvaluate> selectFilmEvaluatesPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询FilmEvaluate信息:selectFilmEvaluatesPerPageByCondition() ");
		return filmEvaluateMapper.selectFilmEvaluatesPerPageByCondition(map);
	}

	//条件查询
	public List<FilmEvaluate>  selectFilmEvaluatesByCondition (FilmEvaluate record){
		log.info("通过传入的参数条件FilmEvaluate信息进行查询:selectFilmEvaluatesByCondition () ");
		return filmEvaluateMapper.selectFilmEvaluatesByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( FilmEvaluate record ){
		log.info("根据传入的FilmEvaluate信息，条件删除:deleteByCondition() ");
		return filmEvaluateMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除FilmEvaluate信息:deleteByPrimaryKey() ");
		return filmEvaluateMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( FilmEvaluate record ){
		log.info("新增FilmEvaluate信息:insert() ");
		return filmEvaluateMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( FilmEvaluate record ){
		log.info("新增（匹配有值的字段）FilmEvaluate信息:insertSelective() ");
		return filmEvaluateMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( FilmEvaluate record ){
		log.info("修改（匹配有值的字段）FilmEvaluate信息:updateByPrimaryKeySelective() ");
		return filmEvaluateMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( FilmEvaluate record ){
		log.info("修改FilmEvaluate信息:updateByPrimaryKey() ");
		return filmEvaluateMapper.updateByPrimaryKey(record);
	}

}