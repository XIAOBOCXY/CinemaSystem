package com.movie2.controller;

import com.movie2.annotation.DisableBaseResponse;
import com.movie2.model.entity.Order;
import com.movie2.model.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/thy")
@Controller
@Slf4j
public class ThymeleafController {
    @GetMapping("/login4")
    @DisableBaseResponse
    public String login4(@RequestParam("url") String url,
                         @RequestParam("id") String id,
                         @RequestParam("other") String other){
        System.out.println(url);
        System.out.println(id);
        System.out.println(other);
        //(1)重定向, 两次请求，request中数据是不存在，redirect:
        //return "redirect:/thy/login3";
        //(2)请求转发，默认return是请求转发，request中数据是存在
        //return "menu2";//转发到menu2.html
        //转发到login3 url中 forward:
        return "forward:/thy/login3";
    }

    @RequestMapping("/login3")
    @DisableBaseResponse
    public String login3(@RequestParam(name="userName",defaultValue = "123") String userName,
                         @RequestParam(name="password",defaultValue = "123") String password,
                         @RequestParam(name="verity",defaultValue = "123") String verity,
                         HttpServletRequest request){
        System.out.println(userName);
        System.out.println(password);
        System.out.println(verity);
        //登录成功后，从数据库中拿出的数据
        request.setAttribute("adminName","超超级管理员");
        List<Order> orderList = new ArrayList<>();
        orderList.add(new Order(10,1,"137",1,"1,2,3", new BigDecimal(180),1,null,null,null));
        orderList.add(new Order(11,1,"138",1,"1,2,3", new BigDecimal(220),1,null,null,null));
        orderList.add(new Order(12,1,"131",1,"1,2,3", new BigDecimal(300),1,null,null,null));
        orderList.add(new Order(13,1,"132",1,"1,2,3", new BigDecimal(400),1,null,null,null));
        orderList.add(new Order(14,1,"133",1,"1,2,3", new BigDecimal(500),1,null,null,null));
        request.setAttribute("list",orderList);
        return "index";

    }
    @PostMapping("/login2")
    public String login2(@ModelAttribute User user, HttpServletRequest request){
        log.info("ThymeleafController---------------------->  login2(@ModelAttribute User user, HttpServletRequest request)");
        if("zhangsan".equals(user.getUsername()) && "123456".equals(user.getPassword())){
            //生命周期越来越长
            request.setAttribute("msg","<font color='green'>登录成功</font>");//一次请求
            User loginUser = new User();
            loginUser.setUsername("zhangsan");
            loginUser.setNickname("张三丰");
            loginUser.setErrorNum(10);
            request.setAttribute("user123",loginUser);
            List<Order> orderList = new ArrayList<>();
            orderList.add(new Order(1,1,"137",1,"1,2,3", new BigDecimal(180),1,null,null,null));
            orderList.add(new Order(1,1,"138",1,"1,2,3", new BigDecimal(220),1,null,null,null));
            orderList.add(new Order(1,1,"131",1,"1,2,3", new BigDecimal(300),1,null,null,null));
            orderList.add(new Order(1,1,"132",1,"1,2,3", new BigDecimal(400),1,null,null,null));
            orderList.add(new Order(1,1,"133",1,"1,2,3", new BigDecimal(500),1,null,null,null));
            request.setAttribute("list",orderList);
            System.out.println(loginUser);
            //request.getSession().setAttribute("msg","登录成功");//一次会话，只要浏览器不关闭
            //request.getServletContext().setAttribute("msg","登录成功");//应用程序级，服务器不关，
            return "orderList";
        }else{
            request.setAttribute("msg","<font color='red'>登录失败</font>");
            return "login2";

        }

    }
}
