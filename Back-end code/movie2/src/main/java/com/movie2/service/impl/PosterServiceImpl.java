package com.movie2.service.impl;

import com.movie2.config.MyConstants;
import com.movie2.mapper.PosterMapper;
import com.movie2.model.entity.Poster;
import com.movie2.service.PosterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class PosterServiceImpl implements PosterService {
    @Autowired
    PosterMapper posterMapper;
    @Override
    public List<Poster> getAllPoster() {
        log.info("PosterServiceImpl--------------------------------------->getAllPoster()");
        List<Poster> allPoster=posterMapper.getAllPoster();
        for (Poster poster : allPoster) {
            poster.setUrl(MyConstants.MY_URL+poster.getUrl());
        }
        return allPoster;
    }

    @Override
    public List<Poster> getAllPoster2() {
        log.info("PosterServiceImpl--------------------------------------->getAllPoster2()");
        List<Poster> allPoster=posterMapper.getAllPoster2();
        for (Poster poster : allPoster) {
            poster.setUrl(MyConstants.MY_URL+poster.getUrl());
        }
        return allPoster;
    }

    @Override
    public int insertPoster(Poster poster) {
        log.info("PosterServiceImpl--------------------------------------->insertPoster(Poster poster)");
        return posterMapper.insertPoster(poster);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        log.info("PosterServiceImpl--------------------------------------->deletePoster(Poster poster)");
        return posterMapper.deleteByPrimaryKey(id);
    }



}
