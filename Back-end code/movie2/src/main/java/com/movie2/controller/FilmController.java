package com.movie2.controller;

import com.movie2.config.MyConstants;
import com.movie2.model.entity.*;
import com.movie2.service.ArrangementService;
import com.movie2.service.FilmService;
import com.movie2.service.PosterService;
import com.movie2.service.SeatService;
import com.movie2.util.MD5Util;
import com.sun.jmx.remote.security.FileLoginModule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;

// /api/poster  get
@RestController
//@RequestMapping("/api/film")
//@RequestMapping("")
@CrossOrigin
@Slf4j
public class FilmController {

    @Autowired
    SeatService seatService;
    @Autowired
    FilmService filmService;
    @Autowired
    ArrangementService arrangementService;
    //   /api/film/hot/limit
    @GetMapping("/api/film/hot/limit")
    public List<Film> getAllFilmsHot(){
        log.info("FilmController-> getAllFilmsHot() ");
        return filmService.getFilmsHot(5);
    }

    @GetMapping("/api/film")
            public List<Film> getAllFilms(@RequestParam(name = "region",required = false,defaultValue = "全部")String area,
                    @RequestParam(name = "type",required = false,defaultValue = "全部")String type){
                log.info("FilmController-> getAllFilms() ");
                List<Film> films=null;
                if("全部".equalsIgnoreCase(area)  && "全部".equalsIgnoreCase(type)){
                    films = filmService.selectAllFilms();
                }else if(!"全部".equalsIgnoreCase(area) && "全部".equalsIgnoreCase(type)) {
                    films = filmService.selectAllFilms(area,null);
                } else if("全部".equalsIgnoreCase(area) && !"全部".equalsIgnoreCase(type)) {
                    films = filmService.selectAllFilms(null,type);
                }else{
            films = filmService.selectAllFilms(area,type);
        }


        return films;
    }

    //查看排片列表
    @GetMapping("/api/arrangement")
    public List<Arrangement> getAllArrangementFilm(){
        log.info("获得所有的排片列表：FilmController---》getAllArrangementFilm");
        List<Arrangement> list = new ArrayList<>();
        list = arrangementService.selectAllArrangements();
        return list;
    }


//    @GetMapping("/film/detail")
//    public Film geFilmById(@RequestParam("fid") int fid){
//        log.info("FilmController->geFilmById(@RequestParam(fid) int fid) ");
//        return filmService.selectByPrimaryKey(fid);
//    }

    //获得场次信息
    @GetMapping("/api/arrangement/film/fid")
    public List<Arrangement> getArrangementById(@RequestParam("fid") int fid){
        log.info("FilmController->getArrangementById(@RequestParam(fid) int fid) ");
        System.out.println("fid:"+fid);
        Arrangement temp = new Arrangement();
        temp.setFid(fid);
        temp.setDate(new Date());
        return arrangementService.selectArrangementsByCondition(temp);
    }

    @GetMapping("/api/film/id")
    public Film getFilmById2(@RequestParam("fid") int fid){
        log.info("FilmController->getFilmById2(@RequestParam(fid) int fid) ");
        Film film = filmService.selectByPrimaryKey(fid);
        film.setCover(MyConstants.MY_URL+film.getCover());
        return film;
    }
    // 进入特定的场次，获得座位信息，电影信息等（通过排片Id获得）
    // /api/arrangement/id
    @GetMapping("/api/arrangement/id")
    public Map<String,Object> getArrangementAndSeatById(@RequestParam("arrangementId") int id){
        log.info("FilmController->getArrangementAndSeatById(@RequestParam(arrangementId) int fid) ");
        System.out.println("arrangementId:"+id);
        Map<String,Object> map = new HashMap<>();
        //获得排片信息
        Arrangement arrangement = arrangementService.selectByPrimaryKey(id);
        //通过fId获得电影信息
        Film film = filmService.selectByPrimaryKey(arrangement.getFid());
        map.put("film",film);
        map.put("arrangement",arrangement);
        return map;
    }
    //查询电影
    // /api/film/name
    @GetMapping("/api/film/name")
    public List<Film> getFilmsByName(@RequestParam("name") String name) throws UnsupportedEncodingException {
        log.info("FilmController->getFilmsByName(@RequestParam(name) String name) ");
        System.out.println(name);
        //name = new String(name.getBytes("iso-8859-1"),"utf-8");
        Film  f= new Film();
        f.setName(name);//模糊查询
        List<Film> films = filmService.selectFilmsByCondition(f);
        return films;
    }
    //api/arrangement/getSeats 获得已经定过座位信息
    @GetMapping("/api/arrangement/getSeats")
    public List<Seat> getSeats(@RequestParam("arrangementId") int id){
        log.info("FilmController->getSeats(@RequestParam(arrangementId) int fid) ");
        System.out.println("arrangementId:"+id);
        List<Seat> seats = new ArrayList<>();
        Seat seat = new Seat();
        seat.setAid(id);
        seats = seatService.selectSeatsByCondition(seat);
        return seats;
}
    @PostMapping("api/film/add")
    public Map<String,Object> addfilm(@RequestBody Film film){
        log.info("FilmController->addfilm(@RequestBody Film film)");
        film.setCreateTime(new Date());
        film.setUpdateTime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        int insert = filmService.insert(film);
        return map;
    }
    //修改电影
    @PostMapping("/api/arrangement")
    public Map<String,Object> modify(@RequestBody Film film) {
        log.info("FilmController---------------->modify()");
        //把原始的密码进行加密
        System.out.println(film);
        //修改
        filmService.updateByPrimaryKeySelective(film);//通过主键修改其他非空的字段
        Map<String,Object> map = new HashMap<>();
        map.put("msg","修改成功");
        map.put("success","success");
        return map;
    }
    //修改电影
    @PostMapping("/api/film/arrangement")
    public Map<String,Object> modify2(@RequestBody Film film) {
        log.info("FilmController---------------->modify()");
        System.out.println(film);
        //修改
        filmService.updateByPrimaryKeySelective(film);//通过主键修改其他非空的字段
        Map<String,Object> map = new HashMap<>();
        map.put("msg","修改成功");
        map.put("success","success");
        return map;
    }
    @GetMapping("/api/film/delete")
    public Map<String,Object> deleteByPrimaryKey(@RequestParam("id") int id){
        log.info("PosterController----------------> deleteByPrimaryKey(@RequestParam(\"id\") int id)");
        Map<String,Object> map = new HashMap<>();
        map.put("msg","删除成功");
        filmService.deleteByPrimaryKey(id);
        return  map;
    }

    @PostMapping("api/film/arrangement/add")
    public Map<String,Object> addfilmarrangement(@RequestBody Arrangement arrangement){
        log.info("FilmController->addfilm(@RequestBody Film film)");
//        arrangement.setCreateTime(new Date());
        arrangement.setUpdateTime(new Date());
        Map<String,Object> map = new HashMap<>();
        map.put("success","ok");
        int insert = arrangementService.insert(arrangement);
        return map;
    }
    @PostMapping("/api/arrangement/update")
    public Map<String,Object> modify3(@RequestBody Arrangement arrangement) {
        log.info("FilmController---------------->modify()");
        System.out.println(arrangement);
        //修改
        arrangementService.updateByPrimaryKeySelective(arrangement);//通过主键修改其他非空的字段
        Map<String,Object> map = new HashMap<>();
        map.put("msg","修改成功");
        map.put("success","success");
        return map;
    }
}
