package com.movie2.controller;

import com.movie2.model.entity.*;
import com.movie2.model.vo.EmailVo;
import com.movie2.service.ArrangementService;
import com.movie2.service.CartService;
import com.movie2.service.FilmService;
import com.movie2.service.OrderService;
import com.movie2.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
@CrossOrigin
@Slf4j
public class CartController {
    @Autowired
    CartService cartService;//购物车
    @Autowired
    ArrangementService arrangementService;//排片
    @Autowired
    FilmService filmService;//电影
    @Autowired
    OrderService orderService;//电影
    @GetMapping("")
    public List< Map<String,Object> > getMyCart(@RequestParam("uid") int uid){
        log.info("加入购物车：CartController    --------->  getMyCart(@RequestParam(\"uid\") int uid)");
        List<Map<String,Object> > result = new ArrayList<>();
        List<Cart> carts = new ArrayList<>();
        Cart temp = new Cart();
        temp.setUid(uid);
        temp.setStatus(0);//没有加入到订单中的购物车项
        //0，表示正常的购物车项，没有加入到订单中
        //2，表示已经加入到订单中，不用再显示了
        //3，表示异常的购物车项，
        carts = cartService.selectCartsByCondition(temp);//获得所有的购物车信息
        for (Cart cart : carts) {
            Map<String,Object> map = new HashMap<>();
            map.put("cart",cart);
            //查找排片信息
            Arrangement arrangement = arrangementService.selectByPrimaryKey(cart.getAid());
            map.put("arrangement",arrangement);
            //查找电影信息
            Film film = filmService.selectByPrimaryKey(arrangement.getFid());
            map.put("film",film);
            result.add(map);
        }

        return result;
    }

    @PostMapping("/add")
    public Map<String,Object> addCart(@RequestBody Cart cart){
        log.info("加入购物车：CartController    --------->  addCart(@RequestBody int[] seats) ");
        //是否批量处理
        //https://blog.csdn.net/chang100111/article/details/115664432
        //这里使用是一个字符串添加到数据库中，如果是批量添加，需要在购物车中加入orderId字段
        Map<String,Object> map =new HashMap<>();
        int count = cartService.insert(cart);
        if(count==1){
            map.put("success","ok");
        }
        return map;
    }
}
