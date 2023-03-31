package com.movie2.controller;

import com.movie2.model.vo.EmailVo;
import com.movie2.util.JwtTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/common")
@CrossOrigin
@Slf4j
public class CommonController {
    @PostMapping("/email")
    public Map<String,Object> sendEmail(@RequestBody EmailVo vo){
        log.info("发送邮件：CommonController    --------->  sendEmail(@RequestBody EmailVo vo)  ");
        System.out.println(vo);
        //创建一个token，用于保存验证码信息
        //(1)创建一个验证码，加密到token中，发送token到前端，前端保存。用户进行注册时，把token一并提交给服务端，
        // 服务端直接通过token获得验证码,进而验证验证码是否正确
        //(2)发送消息到rabbitMQ中，消费者端负责发送消息，
        List<String> list = new ArrayList<>();
        int code = 100000+(int)(Math.random()*900000);
        System.out.println("code:"+code);
        String token= JwtTokenUtil.createToken(code+"",list,3*60*1000);
        Map<String,Object> map = new HashMap<>();
        map.put("msg","信息已经发送,请查收邮件，查看验证码信息");
        map.put("code",token);
        return map;
    }
}
