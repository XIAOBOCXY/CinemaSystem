package com.movie2.model.support;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.io.Serializable;
@Data
@AllArgsConstructor
public class ResponseResult<T> implements Serializable {
    //这是一个泛型类的写法，在我们这个项目中没有用到，因为返回都是JSON的数据。
    private Integer code;//返回的状况码
    private boolean success;//返回是否成功
    private String msg;//返回的消息
    private T data;//返回的数据

    public ResponseResult() {
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = null;
    }

    //不附带msg返回数据
    public ResponseResult(T data) {
        this.code = 200;
        this.success = true;
        this.msg = null;
        this.data = data;
    }

    //默认附带msg返回数据
    public ResponseResult(String msg, T data) {
        this.code = 200;
        this.success = true;
        this.msg = msg;
        this.data = data;
    }

    //默认的错误返回
    public ResponseResult(Integer code, String msg) {
        this.code = code;
        this.success = false;
        this.msg = msg;
        this.data = null;
    }

}