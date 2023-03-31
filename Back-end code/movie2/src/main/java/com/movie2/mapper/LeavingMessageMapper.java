package com.movie2.mapper;

import com.movie2.model.entity.LeavingMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.Map;
import java.util.List;

/**
 * 
 * LeavingMessageMapper数据库操作接口类
 * 
 **/
@Mapper
public interface LeavingMessageMapper{


	/**
	 * 
	 * 查询（根据主键ID查询）
	 * 
	 **/
	LeavingMessage  selectByPrimaryKey(@Param("id") Integer id);
	/**
	 * 
	 * 查询（获得数据表中的数量）
	 * 
	 **/
	public int selectLeavingMessageCount();

	/**
	 * 
	 * 查询（根据其他条件查询需要的结果）
	 * 
	 **/
	public int selectLeavingMessagesCountByCondition(Map<String, Object> map);

	/**
	 * 
	 * 查询（通过Map条件进行分页查询）
	 * 
	 **/
	public List<LeavingMessage> selectLeavingMessagesPerPageByCondition(Map<String, Object> map);

	/**
	 * 
	 * 查询（通过Map条件，获得所有符合的信息）
	 * 
	 **/
	public List<LeavingMessage> selectLeavingMessagesCondition(Map<String, Object> map);

	/**
	 * 
	 * 查询全部信息
	 * 
	 **/
	public List<LeavingMessage>  selectAllLeavingMessages();

	/**
	 * 
	 * 查询（根据实体类的条件查询）
	 * 
	 **/
	public List<LeavingMessage> selectLeavingMessagesByCondition(LeavingMessage record);

	/**
	 * 
	 * 删除（根据主键ID删除）
	 * 
	 **/
	public int deleteByPrimaryKey(@Param("id") Integer id);

	/**
	 * 
	 * 删除（条件删除）
	 * 
	 **/
	public int deleteByCondition(LeavingMessage record);

	/**
	 * 
	 * 添加
	 * 
	 **/
	public int insert(LeavingMessage record);

	/**
	 * 
	 * 添加 （匹配有值的字段）
	 * 
	 **/
	public int insertSelective(LeavingMessage record);

	/**
	 * 
	 * 修改 （匹配有值的字段）
	 * 
	 **/
	public int updateByPrimaryKeySelective(LeavingMessage record);

	/**
	 * 
	 * 修改（根据主键ID修改）
	 * 
	 **/
	public int updateByPrimaryKey(LeavingMessage record);

}