package com.movie2.controller;

import com.movie2.config.MyConstants;
import com.movie2.model.entity.Admin;
import com.movie2.model.entity.User;
import com.movie2.model.vo.EmailVo;
import com.movie2.model.vo.LoginVo;
import com.movie2.service.AdminService;
import com.movie2.service.UserService;
import com.movie2.util.JwtTokenUtil;
import com.movie2.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.util.*;

@RestController
@CrossOrigin
@RequestMapping("/api/admin")
@Slf4j
public class AdminController {

    @Autowired
    UserService userService;
    @Autowired
    AdminService adminService;
//    //验证用户名是否存在
//    @PostMapping("/isExist")
//    public Map<String,String> isExist(@RequestBody EmailVo vo){
//        log.info("UserController---------------->isExist(EmailVo vo)");
//        Map<String,String> map = new HashMap<>();
//        User user = new User();
//        user.setUsername(vo.getEmail());//只放置登录名
//        List<User> users = userService.selectUsersByCondition(user);
//        if(users==null || users.size()==0){
//            //没有找到，就可以注册
//            map.put("msg","用户名不存在，可以注册");
//            map.put("success","success");
//        }else{
//            map.put("msg","用户名存在，不可以注册");
//            map.put("success","fail");
//        }
//        return  map;
//
//    }

//
//    @Value("${cxy.poster.upload}")
//    String upload;
//    //文件上传要分开处理
//    @PostMapping("/upload")
//    public Map<String,String> uploadFile(MultipartFile file) throws IOException {
//        log.info("UserController---------------->uploadFile()");
//        File path = new File(upload);
//        Map<String,String> map = new HashMap<>();
//
//        if(!path.exists()){
//            path.mkdirs();//文件夹不存在，创建文件夹
//        }
//        String originalFilename = file.getOriginalFilename();//获得原始的文件名 "a.jpg"
//        int index=originalFilename.lastIndexOf(".");
//        String newName = originalFilename.substring(0,index)+"_"+ UUID.randomUUID().toString()+originalFilename.substring(index);
//        //保存文件
//        try {
//            file.transferTo(new File(upload+newName));//不出现异常，就是文件上传成功
//            map.put("fileName","/images123/"+newName );//返回新的文件名（新增的）
//        } catch (IOException e) {
//            map.put("fileName","error");
//            e.printStackTrace();//文件上传失败
//            throw e;
//        }
//        return map;
//    }
//    //修改个人信息
//    @PostMapping("/modify")
//    public Map<String,Object> modify(@RequestBody User user) {
//        log.info("UserController---------------->modify()");
//        //查询（通过Id查询当前用户）
//        User temp = userService.selectByPrimaryKey(user.getId());
//        //把原始的密码进行加密
//        String newPass = MD5Util.md5To32String(user.getPassword(),temp.getSlat(),temp.getSlatIndex());
//        user.setPassword(newPass);//加密后的密码
//        System.out.println(user);
//        //修改
//        userService.updateByPrimaryKeySelective(user);//通过主键修改其他非空的字段
//        Map<String,Object> map = new HashMap<>();
//        map.put("token","ok");
//        return map;
//    }
    //登出系统
    @GetMapping("/logout")
    public Map<String,Object> logout(@RequestParam("id") int id){
        log.info("UserController---------------->logout()");
        Admin result = adminService.selectByPrimaryKey(id);//也可以不查
        List<String> list = new ArrayList<>();
        String token=JwtTokenUtil.createToken(result.getUsername(),list,30*1000);
        Map<String,Object> map = new HashMap<>();
        map.put("token",token);
        return map;
    }
    //注册功能
    @PostMapping("/login")
    public Map<String,Object> login(@RequestBody LoginVo vo) {
        log.info("AdminController---------------->login(@RequestBody LoginVo vo)");
        Map<String, Object> map = new HashMap<>();
        Admin admin = new Admin();
        admin.setUsername(vo.getUsername());
        List<Admin> admins = adminService.selectAdminsByCondition(admin);

        if (admins == null || admins.size() == 0) {
            map.put("msg", "用户名或密码错误");
            map.put("success", "fail");
        } else {
            Admin result = admins.get(0);
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

//    //注册功能
//    @GetMapping("/id")
//    public User getUserId(@RequestParam("id") int id){
//        log.info("UserController---------------->getUserId(@RequestBody LoginVo vo)");
//        User user = userService.selectByPrimaryKey(id);
//        user.setAvatar(MyConstants.MY_URL+user.getAvatar());
//        return  user;
//    }
    //查询所有用户列表
    @GetMapping("/all")
    public List<Admin> selectAllAdmins(){
        log.info("AdminController -> selectAllAdmins()");
        return adminService.selectAllAdmins();
    };
    //注册功能
    @GetMapping("/id")
    public Admin getUserId(@RequestParam("id") int id){
        log.info("Adminontroller---------------->getUserId(@RequestBody LoginVo vo)");
        Admin admin = adminService.selectByPrimaryKey(id);
        admin.setAvatar(MyConstants.MY_URL+admin.getAvatar());
        return  admin;
    }
}

