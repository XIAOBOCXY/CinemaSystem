package com.movie2.controller;
import com.movie2.model.entity.*;
import com.movie2.model.vo.OrderVo;
import com.movie2.service.*;
import com.movie2.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/orderex")
@CrossOrigin
@Slf4j
public class OrderExceptionController {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderExceptionService orderExceptionService;
    @Autowired
    FilmService filmService;
    //查询所有订单列表
    @GetMapping("/all")
    public List<Map<String,Object> >selectAllOrderExceptions(){
        log.info("PosterController -> selectAllOrder()");
        List< Map<String,Object> > result = new ArrayList<>();
        List<OrderException> orderExceptions = orderExceptionService.selectAllOrderExceptions();
        for (OrderException orderException : orderExceptions) {
            Integer oid=orderException.getOid();
            Order order = orderService.selectByPrimaryKey(oid);
            Integer aid=order.getAid();
            Film film=filmService.selectByPrimaryKey(aid);
            Map<String,Object> map = new HashMap<>();
            map.put("orderex",orderException);
            map.put("film",film);
            result.add(map);
        }
        return result;
    };
    @PostMapping("/update")
        public Map<String,Object> modify4(@RequestBody OrderException orderException) {
            log.info("FilmController---------------->modify()");
            System.out.println(orderException);
        orderException.setCreateAt(new Date());
        orderException.setEndAt(new Date());
            //修改
            orderExceptionService.updateByPrimaryKeySelective(orderException);//通过主键修改其他非空的字段
            Map<String,Object> map = new HashMap<>();
        map.put("msg","修改成功");
        map.put("success","success");
        return map;
    }
    @PostMapping("/add")
    public Map<String,Object> addorderex(@RequestBody OrderException orderException){
        log.info("FilmController->addfilm(@RequestBody Film film)");
        orderException.setCreateAt(new Date());
        orderException.setEndAt(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        int insert = orderExceptionService.insert(orderException);
        return map;
    }

}
