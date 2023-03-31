package com.movie2.controller;

import com.movie2.model.entity.*;
import com.movie2.service.ActivityService;
import com.movie2.service.ArrangementService;
import com.movie2.service.CartService;
import com.movie2.service.FilmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/activity")
@CrossOrigin
@Slf4j
public class ActivityController {
    @Autowired
    CartService cartService;//购物车
    @Autowired
    ArrangementService arrangementService;//排片
    @Autowired
    ActivityService activityService;//活动
    @GetMapping("")
    public List<Activity> getAllActivity(){
        log.info("获得所有的活动信息：ActivityController---》getAllActivity");
        List<Activity> list = new ArrayList<>();
        list = activityService.selectAllActivitys();
        return list;
    }
    @PostMapping("/add")
    public Map<String,Object> addactivity(@RequestBody Activity activity){
        log.info("FilmController->addfilm(@RequestBody Film film)");
//        arrangement.setCreateTime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        int insert = activityService.insert(activity);
        return map;
    }

    @GetMapping("/delete")
    public Map<String,Object> deleteByPrimaryKey(@RequestParam("id") int id){
        log.info("PosterController----------------> deleteByPrimaryKey(@RequestParam(\"id\") int id)");
        Map<String,Object> map = new HashMap<>();
        map.put("msg","删除成功");
        activityService.deleteByPrimaryKey(id);
        return  map;
    }
}
