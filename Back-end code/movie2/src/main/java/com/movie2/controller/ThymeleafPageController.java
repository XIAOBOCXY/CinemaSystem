package com.movie2.controller;

import com.movie2.annotation.DisableBaseResponse;
import com.movie2.model.entity.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafPageController {
    //    @RequestMapping("/{page}")
//    @DisableBaseResponse
//    public String getPage(@PathVariable("page") String page){
//        return page;
//    }
    @GetMapping("/thy/deldemo")
    public String delete(@RequestParam("id")int id){
        System.out.println("id:"+id);
        //return "menu2";
        return "forward:/menu2";
    }

    @RequestMapping("/{page}")
    @DisableBaseResponse
    public String getPage(@PathVariable("page") String page, HttpServletRequest request){
        request.setAttribute("adminName","超超级管理员");
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(10,1,"137",1,"1,2,3", new BigDecimal(180),1,null,null,null));
        orderList.add(new Order(11,1,"138",1,"1,2,3", new BigDecimal(220),1,null,null,null));
        orderList.add(new Order(12,1,"131",1,"1,2,3", new BigDecimal(300),1,null,null,null));
        orderList.add(new Order(13,1,"132",1,"1,2,3", new BigDecimal(400),1,null,null,null));
        orderList.add(new Order(14,1,"133",1,"1,2,3", new BigDecimal(500),1,null,null,null));
        request.setAttribute("list",orderList);
        return page;
    }
}
