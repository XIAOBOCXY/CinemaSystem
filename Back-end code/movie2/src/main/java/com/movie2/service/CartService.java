package com.movie2.service;

import com.movie2.model.entity.Cart;
import java.util.List;
import java.util.Map;

/**
 * 
 * CartService业务逻辑操作接口类
 * 
 **/

public interface CartService{

	//批量查询购物车项信息
	List<Cart>  selectAllCartsByIds(int[] ids);

	//批量修改购物车项的状态
	int updateStatusByIds(Map<String,Object> map);
	//查询（根据主键ID查询）
	public Cart  selectByPrimaryKey(Integer id);
	//查询全部信息
	public List<Cart>  selectAllCarts();
	//条件查询信息
	public List<Cart>  selectCartsByCondition(Cart record);
	//查询（获得数据表中的数量）
	public int selectCartCount();
	//查询（根据 Map条件查询需要的结果）

	public int selectCartsCountByCondition(Map<String, Object> map);

	//查询（通过Map条件，获得所有符合的信息）

	public List<Cart> selectCartsCondition(Map<String, Object> map);
	//查询（通过Map条件进行分页查询）

	public List<Cart> selectCartsPerPageByCondition(Map<String, Object> map);

	//删除（根据主键ID删除）
	public int deleteByPrimaryKey(Integer id);
	//删除（条件删除）
	public int deleteByCondition(Cart record);
	//添加
	public int insert(Cart record);
	//添加 （匹配有值的字段）
	public int insertSelective(Cart record);
	//修改 （匹配有值的字段）
	public int updateByPrimaryKeySelective(Cart record);
	//修改（根据主键ID修改）
	public int updateByPrimaryKey(Cart record);

}