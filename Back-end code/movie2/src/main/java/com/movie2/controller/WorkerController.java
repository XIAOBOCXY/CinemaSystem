package com.movie2.controller;

import com.movie2.config.MyConstants;
import com.movie2.model.entity.*;
import com.movie2.model.vo.LoginVo;
import com.movie2.service.ActivityService;
import com.movie2.service.ArrangementService;
import com.movie2.service.CartService;
import com.movie2.service.WorkerService;
import com.movie2.util.JwtTokenUtil;
import com.movie2.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/worker")
@CrossOrigin
@Slf4j
public class WorkerController {
    @Autowired
    WorkerService workerService;//客服
    @Autowired
    ArrangementService arrangementService;//排片
    @Autowired
    ActivityService activityService;//活动
    @GetMapping("")
    public List<Worker> getAllActivity(){
        log.info("获得所有的活动信息：ActivityController---》getAllActivity");
        List<Worker> list = new ArrayList<>();
        list = workerService.selectAllWorkers();
        return list;
    }
    @GetMapping("/")
    public List<Worker> selectAllWorkers(){
        log.info("WorkerController -> selectAllWorkers()");
        return workerService.selectAllWorkers();
    };

    //注册功能
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginVo vo) {
        log.info("WorkerController---------------->login(@RequestBody LoginVo vo)");
        Map<String, Object> map = new HashMap<>();
        Worker worker = new Worker();
        worker.setUsername(vo.getUsername());
        List<Worker> workers = workerService.selectWorkersByCondition(worker);

        if (workers == null || workers.size() == 0) {
            map.put("msg", "用户名或密码错误");
            map.put("success", "fail");
        } else {
            Worker result = workers.get(0);
            //用户是否已经禁用，

            //加密密码，判断密码是否匹配
            String newPass = vo.getPassword();
            if (newPass.equalsIgnoreCase(result.getPassword())) {
                //登录成功

                map.put("msg", "登录成功");
                map.put("success", "success");
                List<String> list = new ArrayList<>();
                list.add("user");
                String token = JwtTokenUtil.createToken(result.getUsername(), list, JwtTokenUtil.EXPIRATION_TIME);
                map.put("token", token);
                map.put("user", result);
            } else {
                //密码加密后，与数据库中存储的不一致
                map.put("msg", "用户名或密码错误");
                map.put("success", "fail");
            }

        }

        return map;
    }
    //登出系统
    @GetMapping("/logout")
    public Map<String,Object> logout(@RequestParam("id") int id){
        log.info("UserController---------------->logout()");
        Worker result = workerService.selectByPrimaryKey(id);//也可以不查
        List<String> list = new ArrayList<>();
        String token=JwtTokenUtil.createToken(result.getUsername(),list,30*1000);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return map;
    }


    //注册功能
    @GetMapping("/id")
    public Worker getWorkerId(@RequestParam("id") int id){
        log.info("WorkerController---------------->getWorkerId(@RequestBody LoginVo vo)");
        Worker worker= workerService.selectByPrimaryKey(id);
        worker.setAvatar(MyConstants.MY_URL+worker.getAvatar());
        return  worker;
    }
    @GetMapping("/a/id")
    public Worker getWorkerId2(@RequestParam("id") int id){
        log.info("WorkerController---------------->getWorkerId(@RequestBody LoginVo vo)");
        Worker worker= workerService.selectByPrimaryKey(id);
        worker.setAvatar(worker.getAvatar().replace("MyConstants.MY_URL",""));
        return  worker;
    }
    @PostMapping("/modify")
    public Map<String,Object> modify(@RequestBody Worker worker) {
        log.info("UserController---------------->modify()");

        //修改
        workerService.updateByPrimaryKeySelective(worker);//通过主键修改其他非空的字段
        Map<String,Object> map = new HashMap<>();
        map.put("msg","修改成功");
        map.put("success","success");
        return map;
    }
    @GetMapping("/delete")
    public Map<String,Object> deleteByPrimaryKey(@RequestParam("id") int id){
        log.info("PosterController----------------> deleteByPrimaryKey(@RequestParam(\"id\") int id)");
        Map<String,Object> map = new HashMap<>();
        map.put("msg","删除成功");
        workerService.deleteByPrimaryKey(id);
        return  map;
    }
    @PostMapping("/add")
    public Map<String,Object> addworker(@RequestBody Worker worker){
        log.info("FilmController->addfilm(@RequestBody Film film)");
//        arrangement.setCreateTime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        int insert = workerService.insert(worker);
        return map;
    }

}
