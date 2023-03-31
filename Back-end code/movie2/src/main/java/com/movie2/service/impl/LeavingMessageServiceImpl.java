package com.movie2.service.impl;

import com.movie2.model.entity.LeavingMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.LeavingMessageMapper;
import com.movie2.service.LeavingMessageService;


/**
 * 
 * LeavingMessageService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class LeavingMessageServiceImpl implements LeavingMessageService{

	@Autowired
	LeavingMessageMapper leavingMessageMapper;

	//查询（根据主键ID查询）
	public LeavingMessage  selectByPrimaryKey (Integer id){
		log.info("通过主键查询LeavingMessage信息:selectByPrimaryKey() ");
		return leavingMessageMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectLeavingMessageCount(){
		log.info("获得LeavingMessage数量信息:selectLeavingMessageCount() ");
		return leavingMessageMapper.selectLeavingMessageCount();
	}

	//查询全部信息
	public List<LeavingMessage>  selectAllLeavingMessages ( ){
		log.info("获得全部LeavingMessage信息:selectAllLeavingMessages() ");
		return leavingMessageMapper.selectAllLeavingMessages ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectLeavingMessagesCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的LeavingMessage信息:selectLeavingMessagesCountByCondition() ");
		return leavingMessageMapper.selectLeavingMessagesCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<LeavingMessage> selectLeavingMessagesCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的LeavingMessage信息:selectLeavingMessagesCountByCondition() ");
		return leavingMessageMapper.selectLeavingMessagesCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<LeavingMessage> selectLeavingMessagesPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询LeavingMessage信息:selectLeavingMessagesPerPageByCondition() ");
		return leavingMessageMapper.selectLeavingMessagesPerPageByCondition(map);
	}

	//条件查询
	public List<LeavingMessage>  selectLeavingMessagesByCondition (LeavingMessage record){
		log.info("通过传入的参数条件LeavingMessage信息进行查询:selectLeavingMessagesByCondition () ");
		return leavingMessageMapper.selectLeavingMessagesByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( LeavingMessage record ){
		log.info("根据传入的LeavingMessage信息，条件删除:deleteByCondition() ");
		return leavingMessageMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除LeavingMessage信息:deleteByPrimaryKey() ");
		return leavingMessageMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( LeavingMessage record ){
		log.info("新增LeavingMessage信息:insert() ");
		return leavingMessageMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( LeavingMessage record ){
		log.info("新增（匹配有值的字段）LeavingMessage信息:insertSelective() ");
		return leavingMessageMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( LeavingMessage record ){
		log.info("修改（匹配有值的字段）LeavingMessage信息:updateByPrimaryKeySelective() ");
		return leavingMessageMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( LeavingMessage record ){
		log.info("修改LeavingMessage信息:updateByPrimaryKey() ");
		return leavingMessageMapper.updateByPrimaryKey(record);
	}

}