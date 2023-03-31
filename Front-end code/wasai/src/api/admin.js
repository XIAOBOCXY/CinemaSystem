import service from "../utils/request";

/* 3.1.1 登录 2022-03-30 by chen_xy*/
export function Login(data){
    return service({
        url:'api/admin/login',
        method:'post',
        data:data
    })
}
//退出系统
export function LogoutAdmin(id) {
    return service({
        url: '/api/admin/logout?id='+id,
        method: 'get',
    })
}

// /* 2.1.2 注册 2022-03-30 by chen_xy */
// export function Register(data){
//     return service({
//         url:'api/user/register',
//         method:'post',
//         data:data
//     })
// }
// //验证用户名是否存在
// export function IsExistUserName(data) {
//     return service({
//         url: '/api/user/isExist',
//         method: 'post',
//         data:data
//     })
// }

export function Admininfo(){
    return service({
        url:'/api/admin/id?id='+localStorage.getItem("id"),
        method:'get',
    })
}

