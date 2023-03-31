import service from "../utils/request";

/* 4.3.1 查询活动列表 2022-04-03 by chen_xy*/
export function SearchActivities(){
    return service({
        url:'/api/activity',
        method:'get',
    })
}

//报名活动
export function AddRegistration(data) {
    return service({
        url: '/api/registration',
        method: 'post',
		data:data,
    })
}export function Addactivity(data) {
    return service({
        url: '/api/activity/add',
        method: 'post',
        data:data
    })
}export function deleteactivity(id) { 
    return service({
        url: '/api/activity/delete?id='+ id,
        method:'get',
    })
}