package com.movie2.model.vo;

import com.movie2.model.entity.FilmEvaluate;
import com.movie2.model.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
@Data
public class FilmEvaluateVo { // {
    private User user;
    private FilmEvaluate filmEvaluate;

}
