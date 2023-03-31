import service from "../utils/request";
/* 3.5.1.1 查询员工列表 测试  */
export function ListAllWorker(){
    return service({
        url:'/api/worker',
        method:"get"
    })
}
/* 3.1.1 登录 2022-03-30 by chen_xy*/
export function Login(data){
    return service({
        url:'api/worker/login',
        method:'post',
        data:data
    })
}
//退出系统
export function LogoutWorker(id) {
    return service({
        url: '/api/worker/logout?id='+id,
        method: 'get',
    })
}
export function Workerinfo(){
    return service({
        url:'/api/worker/id?id='+localStorage.getItem("id"),
        method:'get',
    })
}
export function WorkerInfo3(id){
    return service({
        url:'/api/worker/id?id='+id,
        method:'get',
    })
}
export function WorkerInfo2(id){
    return service({
        url:'/api/worker/a/id?id='+id,
        method:'get',
    })
}
export function updateworker(data) { 
    return service({
        url: '/api/worker/modify',
        method:'post',
        data:data
    })
}
export function deleteworker(id) { 
    return service({
        url: '/api/worker/delete?id='+ id,
        method:'get',
    })
}
export function Addworker(data) {
    return service({
        url: '/api/worker/add',
        method: 'post',
        data:data
    })
}
