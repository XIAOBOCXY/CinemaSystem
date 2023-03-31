package com.movie2;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan//加入servlet的扫描
@SpringBootApplication
@MapperScan("com.movie2.mapper")
@ServletComponentScan
public class Movie2Application {

    public static void main(String[] args) {
        SpringApplication.run(Movie2Application.class, args);
    }

}
