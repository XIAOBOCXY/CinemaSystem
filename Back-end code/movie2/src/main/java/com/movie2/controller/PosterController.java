package com.movie2.controller;

import com.movie2.model.entity.Film;
import com.movie2.model.entity.Poster;
import com.movie2.service.PosterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

// /api/poster get
@RestController
@RequestMapping("/api/poster")
@CrossOrigin
@Slf4j
public class PosterController {

    @Autowired
    PosterService posterService;

    @GetMapping("")
    public List<Poster> getAllPoster(){
        log.info("PosterController -> getAllPoster()");
        return posterService.getAllPoster();
    };
    @GetMapping("/two")
    public List<Poster> getAllPoster2(){
        log.info("PosterController -> getAllPoster2()");
        return posterService.getAllPoster2();
    };

//    @Value("${cxy.poster.upload}")
//    String upload;//获得本地目录
//    @PostMapping("/upload")
//    public Map<String,String> uploadPoster(
//            @RequestParam(name="title",defaultValue = "电影标题")String title,
//            @RequestParam(name="fid",defaultValue = "1")int fid
//            , MultipartFile file){
//        log.info("PosterController-> uploadPoster() ");
//        Map<String,String> map = new HashMap<>();
//        //保存到文件中
//        File path = new File(upload);
//        if(!path.exists()){
//            path.mkdirs();//文件夹不存在，创建文件夹
//        }
//        //(2)文件重名，a.jpg,        a.jpg
//        //(解决办法)uuid；当前时间.......
//        //使用uuid，保留原来的文件名，文件的类型是一致，a.jpg   ==>   *.gif
//        String originalFilename = file.getOriginalFilename();//获得原始的文件名 "a.jpg"
//        int index=originalFilename.lastIndexOf(".");
//        //originalFilename.substring(0,index) 获得原始文件的名字，没有扩展名
//        //+originalFilename.substring(index) 获得文件扩展名
//        String newName = originalFilename.substring(0,index)+"_"+ UUID.randomUUID().toString()+originalFilename.substring(index);
//        //保存文件
//        try {
//            file.transferTo(new File(upload+newName));//不出现异常，就是文件上传成功
//            //对数据库进行操作
//            map.put("message","文件保存成功");
//            Poster poster = new Poster();
//            poster.setUrl("/images/"+newName);
//            poster.setFid(fid);
//            poster.setStatus(1);
//            poster.setTitle(title);
//            //保存到数据库中
//            int count = posterService.insertPoster(poster);
//            map.put("db","数据库保存成功");
//        } catch (IOException e) {
//            e.printStackTrace();//文件上传失败
//            map.put("message","文件保存失败");
//        }
//        return map;
//    }
    @Value("${cxy.poster.upload}")
    String upload;
    //文件上传要分开处理
    @PostMapping("/upload")
    public Map<String,String> uploadFile(MultipartFile file) throws IOException {
        log.info("PosterController---------------->uploadFile()");
        File path = new File(upload);
        Map<String,String> map = new HashMap<>();

        if(!path.exists()){
            path.mkdirs();//文件夹不存在，创建文件夹
        }
        String originalFilename = file.getOriginalFilename();//获得原始的文件名 "a.jpg"
        int index=originalFilename.lastIndexOf(".");
        String newName = originalFilename.substring(0,index)+"_"+ UUID.randomUUID().toString()+originalFilename.substring(index);
//        System.out.println("newName:"+newName);
//        System.out.println("upload:"+upload);
        //保存文件
        try {
            file.transferTo(new File(upload+newName));//不出现异常，就是文件上传成功
            map.put("PosterName","/images/"+newName );//返回新的文件名（新增的）
        } catch (IOException e) {
            map.put("PosterName","error");
            e.printStackTrace();//文件上传失败
            throw e;
        }
        return map;
    }
    @PostMapping("")
    public Map<String,Object> addposter(@RequestBody Poster poster){
        log.info("PosterController->addposter(@RequestBody Poster poster)");
        poster.setCreateTime(new Date());
        poster.setUpdateTime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        int insert = posterService.insertPoster(poster);
        return map;
    }
    @GetMapping("/delete")
    public Map<String,Object> deleteByPrimaryKey(@RequestParam("id") int id){
        log.info("PosterController----------------> deleteByPrimaryKey(@RequestParam(\"id\") int id)");
        Map<String,Object> map = new HashMap<>();
        map.put("msg","删除成功");
        posterService.deleteByPrimaryKey(id);
        return  map;
    }

}
