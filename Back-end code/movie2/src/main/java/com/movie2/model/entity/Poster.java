package com.movie2.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Poster implements Serializable {
    /**主键**/
    private Integer id;

    /**电影id**/
    private Integer fid;

    /**标题**/
    private String title;

    /**图片id（关联t_film.id）**/
    private String url;

    /**状态1-上架；0-下架**/
    private Integer status;

    /**添加时间（格式2022-01-14 11:37:03）**/
    private Date createTime;

    /**更新时间**/
    private Date updateTime;
}
