/* 将axios和新建的config.js文件引入进来 */
import request from "axios"
import config from "@/config"

/* 配置后台api地址，以及请求头token */
const token=localStorage.getItem('token');
const service =request.create({
    baseURL:config.API_URL,
    headers:{
        "Authorization":token,
        //"code": localStorage.getItem('code'),//验证码
    }
});
/* 封装axios response 相应参数，请求拦截 */
service.interceptors.response.use(
    response=>{
        const res=response.data;
        return res;
    },
    error=>{
        console.log('err'+error);
        return Promise.reject(error)
    }
)
export default service