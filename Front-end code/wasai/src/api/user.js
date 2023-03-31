import service from "../utils/request";

/* 2.1.1 登录 2022-03-30 by chen_xy*/
export function Login(data){
    return service({
        url:'api/user/login',
        method:'post',
        data:data
    })
}
//退出系统
export function LogoutUser(uid) {
    return service({
        url: '/api/user/logout?uid='+uid,
        method: 'get',
    })
}
/* 2.1.2 注册 2022-03-30 by chen_xy */
export function Register(data){
    return service({
        url:'api/user/register',
        method:'post',
        data:data
    })
}
//验证用户名是否存在
export function IsExistUserName(data) {
    return service({
        url: '/api/user/isExist',
        method: 'post',
        data:data
    })
}
//发送邮箱验证码
export function SendEmailCode(data) {
    return service({
        url: '/api/common/email',
        method: 'post',
        data:data
    })
}
//真正传参
export function ModifySelf(data) { //id自动
    return service({
        url: '/api/user/modify',
        method: 'post',
				data:data
    })
}
/* 2.2.3.1 查询用户信息 测试 */
export function UserInfo(){
    return service({
        url:'/api/user/id?id='+localStorage.getItem("uid"),
        method:'get',
    })
}
/* 2.2.3.1 查询用户信息 测试 */
export function UserInfo2(id){
    return service({
        url:'/api/user/id?id='+id,
        method:'get',
    })
}
export function UserInfo3(id){
    return service({
        url:'/api/user/a/id?id='+id,
        method:'get',
    })
}
/* 2.2.3.1 查询用户信息 真正的传参接口 */
/* export function UserInfo(id){
    return service({
        url:'/api/user/' + id,
        method:'get',
    })
} */


/* 2.3.4 头部模糊查询 测试 2022-03-31 by chen_xy*/
// export function SearchFilm(){
//     return service({
//         url:'/api/film/name',
//         method:'get',
//     })
// }
/* 2.3.4 头部模糊查询 实际 2022-03-31 by chen_xy*/
export function SearchFilm(name){
    return service({
        url:'/api/film/name?name='+name,
        method:'get',
    })
} 
//查询电影列表
export function SearchFilmDetail() {
    return service({
        url: '/api/film/id',
        method: 'get',
    })
}

export function CartInfo() {
    return service({
        url: '/api/cart',
        method: 'get',
    })
}
/* 3.3.3.1 查询用户列表 测试*/
export function ListAllUser(){
    return service({
        url:'/api/user/all',
        method:'get',
    })
}


export function deleteuser(id) { 
    return service({
        url: '/api/user/delete?id='+ id,
        method:'get',
    })
}

export function updateuser(data) { 
    return service({
        url: '/api/user/modify',
        method:'post',
        data:data
    })
}

export function upload(data) { 
    return service({
        url: '/api/user/upload',
        method:'post',
        data:data
    })
}
