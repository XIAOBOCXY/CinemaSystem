package com.movie2.service;

import com.movie2.model.entity.Poster;

import java.util.List;

public interface PosterService {
    //获得轮播图
    List<Poster> getAllPoster();
    List<Poster> getAllPoster2();

    //添加一个轮播图广告
    int insertPoster(Poster poster);
    //删除（根据主键ID删除）
    public int deleteByPrimaryKey(Integer id);

}
