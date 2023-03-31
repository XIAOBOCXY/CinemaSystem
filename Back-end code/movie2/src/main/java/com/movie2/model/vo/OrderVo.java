package com.movie2.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderVo implements Serializable {
    private int[] ids;
    private Integer uid;
    private String phone;
    private Integer aid;
    private BigDecimal price;
    private Integer status;
}
