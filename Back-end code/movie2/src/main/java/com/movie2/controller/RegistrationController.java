package com.movie2.controller;

import com.movie2.model.entity.Activity;
import com.movie2.model.entity.Registration;
import com.movie2.service.ActivityService;
import com.movie2.service.ArrangementService;
import com.movie2.service.CartService;
import com.movie2.service.RegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/registration")
@CrossOrigin
@Slf4j
public class RegistrationController {
    @Autowired
    RegistrationService registrationService;//报名

    @PostMapping("")//添加报名
    public Map<String,Object> addRegistration(@RequestBody  Registration registration){
        log.info("添加报名信息：RegistrationController---》addRegistration");
        Map<String,Object> map = new HashMap<>();
        int count = registrationService.insert(registration);
        map.put("succ","ok");
        return map;
    }

}
