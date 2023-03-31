package com.movie2.controller;

import com.movie2.model.entity.Activity;
import com.movie2.model.entity.LeavingMessage;
import com.movie2.model.entity.User;
import com.movie2.model.entity.WorkerEvaluate;
import com.movie2.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api")
@CrossOrigin
@Slf4j
public class WorkerEvaluateController {
    @Autowired
    WorkerEvaluateService workerEvaluateService;//对客服评价
    @Autowired
    LeavingMessageService leavingMessageService;

    @Autowired
    UserService userService;

    @GetMapping("/we2")//我的评论
    public List<WorkerEvaluate> getMyAllWorkerEvaluate2(@RequestParam("uid") int uid,
                                                        @RequestParam("wid") int wid){
        log.info("WorkerEvaluateController----------->getMyAllWorkerEvaluate(@RequestParam(\"uid\") int uid)");
        List<Map<String,Object>> result = new ArrayList<>();
        WorkerEvaluate temp = new WorkerEvaluate();
        temp.setUid(uid);
        temp.setWid(wid);
        List<WorkerEvaluate> list = workerEvaluateService.selectWorkerEvaluatesByCondition(temp);

        return  list;
    }

    @GetMapping("/we")//我的评论
    public List<WorkerEvaluate> getMyAllWorkerEvaluate(@RequestParam("uid") int uid){
        log.info("WorkerEvaluateController----------->getMyAllWorkerEvaluate(@RequestParam(\"uid\") int uid)");
        List<Map<String,Object>> result = new ArrayList<>();
        WorkerEvaluate temp = new WorkerEvaluate();
        temp.setUid(uid);
        List<WorkerEvaluate> list = workerEvaluateService.selectWorkerEvaluatesByCondition(temp);
//        for (WorkerEvaluate workerEvaluate : list) {
//            Map<String,Object> map = new HashMap<>();
//            temp.setWid(workerEvaluate.getWid());
//            //通过用户id与workerid获得评论
//            List<WorkerEvaluate> other = workerEvaluateService.selectWorkerEvaluatesByCondition(temp);
//            map.put(""+workerEvaluate.getWid(),other);
//            result.add(map);
//        }
        return  list;
    }
    @GetMapping("/we3")//我的评论
    public List<WorkerEvaluate> getMyAllWorkerEvaluate3(@RequestParam("wid") int wid){
        log.info("WorkerEvaluateController----------->getMyAllWorkerEvaluate3(@RequestParam(\"wid\") int wid)");
        List<Map<String,Object>> result = new ArrayList<>();
        WorkerEvaluate temp = new WorkerEvaluate();
        temp.setWid(wid);
        List<WorkerEvaluate> list = workerEvaluateService.selectWorkerEvaluatesByCondition(temp);
        return  list;
    }
    @GetMapping("/lm")//所有评论
    public List<Map<String,Object>> getAllWorkerEvaluate(){
        log.info("WorkerEvaluateController----------->getAllWorkerEvaluate()");
        List<Map<String,Object>> result = new ArrayList<>();
        List<LeavingMessage> list = leavingMessageService.selectAllLeavingMessages();
        for (LeavingMessage leavingMessage : list) {
            Map<String,Object> map = new HashMap<>();
            map.put("id",leavingMessage.getId());

            map.put("leavingMessage",leavingMessage);
            User user =userService.selectByPrimaryKey(leavingMessage.getUid());
            map.put("user",user);
            result.add(map);
        }
        return  result;
    }
    @PostMapping("/lm")//回复留言
    public  Map<String,Object> AddLMessage(@RequestBody LeavingMessage leavingMessage){
        log.info("WorkerEvaluateController----------->AddLMessage()");
        Integer id=leavingMessage.getId();
        LeavingMessage temp = new LeavingMessage();
        temp = leavingMessageService.selectByPrimaryKey(id);
        temp.setUpdateTime(new Date());
        temp.setReply(leavingMessage.getReply());
        int update=leavingMessageService.updateByPrimaryKey(temp);
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        return map;
    }

    @PostMapping("/addEvaluate")//所有评论
    public Map<String,Object> addEvaluate(@RequestBody WorkerEvaluate evaluate){
        evaluate.setCreateTime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        int insert = workerEvaluateService.insert(evaluate);
        return map;
    }

}
