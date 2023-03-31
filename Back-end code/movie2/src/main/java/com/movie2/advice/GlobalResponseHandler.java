package com.movie2.advice;



import com.movie2.annotation.DisableBaseResponse;
import com.movie2.model.support.ResponseResult;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 统一拦截Controller中所有方法的返回值
 * 封装后返回ResponseResult<T>
 * ResponseBodyAdvice https://www.jb51.net/article/226704.htm
 * 不做json的封装，还是要返回json
 */
@RestControllerAdvice(basePackages = "com.movie2.controller")//这里是确定扫描哪个基础包下的类的方法，
public class GlobalResponseHandler implements ResponseBodyAdvice<Object> {

    //首先判断是否需要进行加工
    @Override
    public boolean supports(MethodParameter methodParameter, Class c) {
        //在beforeBodyWrite()函数前执行，返回ture，执行函数，返回false，不执行函数
        //如果方法上带有DisableBaseResponse注解， 不处理,返回false
        return !methodParameter.hasMethodAnnotation(DisableBaseResponse.class);
    }

    //ResponseBodyAdvice这个通知的beforeBodyWrite() 方法 在@ResponseBody前执行
    @Override
    public ResponseResult<Object> beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        if (o == null) {
            return new ResponseResult<>();//如果对象时null，只返回状态码等信息
        }
        return new ResponseResult<>(o);//如果非空，就把数据一并加入到返回对象中返回，把返回的内容加入到构造函数中
    }
}