package com.movie2.service.impl;

import com.movie2.model.entity.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Map;
import com.movie2.mapper.CartMapper;
import com.movie2.service.CartService;


/**
 * 
 * CartService业务逻辑操作接口类的实现类
 * 
 **/

@Service
@Transactional
@Slf4j
public class CartServiceImpl implements CartService{

	@Autowired
	CartMapper cartMapper;

	@Override
	public List<Cart> selectAllCartsByIds(int[] ids) {
		log.info("批量查询购物车项信息: CartServiceImpl------> selectAllCartsByIds(List<Integer> ids)  ");
		return cartMapper.selectAllCartsByIds(ids);
	}

	@Override
	public int updateStatusByIds(Map<String, Object> map) {
		log.info("批量修改购物车项的状态:updateStatusByIds() ");
		return cartMapper.updateStatusByIds(map);
	}

	//查询（根据主键ID查询）
	public Cart  selectByPrimaryKey (Integer id){
		log.info("通过主键查询Cart信息:selectByPrimaryKey() ");
		return cartMapper.selectByPrimaryKey(id);
	}

	//查询（获得数据表中的数量）
	public int selectCartCount(){
		log.info("获得Cart数量信息:selectCartCount() ");
		return cartMapper.selectCartCount();
	}

	//查询全部信息
	public List<Cart>  selectAllCarts ( ){
		log.info("获得全部Cart信息:selectAllCarts() ");
		return cartMapper.selectAllCarts ( );
	}

	//查询（根据其他条件查询需要的结果）

	public int selectCartsCountByCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Cart信息:selectCartsCountByCondition() ");
		return cartMapper.selectCartsCountByCondition(map);
	}

	//查询（通过Map条件，获得所有符合的信息）

	public List<Cart> selectCartsCondition(Map<String,Object> map){
		log.info("根据其他条件查询需要的Cart信息:selectCartsCountByCondition() ");
		return cartMapper.selectCartsCondition(map);
	}

	//查询（通过条件进行分页查询）

	public List<Cart> selectCartsPerPageByCondition(Map<String,Object> map){
		log.info("通过条件进行分页查询Cart信息:selectCartsPerPageByCondition() ");
		return cartMapper.selectCartsPerPageByCondition(map);
	}

	//条件查询
	public List<Cart>  selectCartsByCondition (Cart record){
		log.info("通过传入的参数条件Cart信息进行查询:selectCartsByCondition () ");
		return cartMapper.selectCartsByCondition(record);
	}

	//删除（条件删除）
	public int deleteByCondition( Cart record ){
		log.info("根据传入的Cart信息，条件删除:deleteByCondition() ");
		return cartMapper.deleteByCondition(record);
	}

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey (Integer id ){
		log.info("主键ID删除Cart信息:deleteByPrimaryKey() ");
		return cartMapper.deleteByPrimaryKey(id);
	}

	//添加
	public int insert( Cart record ){
		log.info("新增Cart信息:insert() ");
		return cartMapper.insert(record);
	}

	//添加 （匹配有值的字段）
	public int insertSelective( Cart record ){
		log.info("新增（匹配有值的字段）Cart信息:insertSelective() ");
		return cartMapper.insertSelective(record);
	}

	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective( Cart record ){
		log.info("修改（匹配有值的字段）Cart信息:updateByPrimaryKeySelective() ");
		return cartMapper.updateByPrimaryKeySelective(record);
	}

	//修改（根据主键ID修改）
	public int updateByPrimaryKey ( Cart record ){
		log.info("修改Cart信息:updateByPrimaryKey() ");
		return cartMapper.updateByPrimaryKey(record);
	}

}