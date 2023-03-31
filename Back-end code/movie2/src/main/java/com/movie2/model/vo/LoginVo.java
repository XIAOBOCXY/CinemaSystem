package com.movie2.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
//注册的Vo
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVo implements Serializable {
    private String username;
    private String password;
    private String email;
    private String token;//带有验证码的token
    private String emailCode;//用户填写的验证码信息
}
