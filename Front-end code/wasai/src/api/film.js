import service from "../utils/request";

/* 3.2.4.1 查询轮播图列表 测试 2022-03-31 by chen_xy*/
export function ListAllPoster(){
    return service({
        url:'/api/poster',
        method:'get',
    })
}
/* 3.2.4.1 查询轮播图列表 测试 2022-03-31 by chen_xy*/
export function ListAllPoster2(){
    return service({
        url:'/api/poster/two',
        method:'get',
    })
}
/* 3.2.4.1 查询轮播图列表 实际接口  2022-03-31 by chen_xy*/
/* export function ListAllPoster(status){
    return service({
        url:'/api/poster?status'+status,
        method:'get',
    })
} */

/* 3.2.1.1 正在热播电影查询 测试  2022-03-31 by chen_xy*/
export function NowPlayingFilm(){
    return service({
        url:'/api/film/status',
        method:"get"
    })
}
/* 3.2.1.1 正在热播电影查询  实际  2022-03-31 by chen_xy*/
/* export function ListAllFilm(status){
    return service({
        url:'api/film?status'+status,
        method:"get"
    })
} */


/* 2.3.3 热门榜单查询 测试  2022-03-31 by chen_xy*/
export function ListHots(){
    return service({
        url:'/api/film/hot/limit',
        method:'get'
    })
}
/* 2.3.3 热门榜单查询 实际  2022-03-31 by chen_xy*/
/* export function ListHots(){
    return service({
        url:'/api/film/top/10',
        method:'get'
    })
} */

/* 3.2.1.1 查询电影列表 测试  2022-03-31 by chen_xy*/
export function ListAllFilm(){
    return service({
        url:'/api/film',
        method:"get"
    })
}

/* 查询筛选后的电影列表 传类型和地区 测试 2022-03-31 by chen_xy*/
/* export function FindFilmByRegionAndType(){
    return service({
        url:'/api/fillm/region/type',
        method:"get"
    })
} */
/* 查询筛选后的电影列表 传类型和地区 实际接口  2022-03-31 by chen_xy*/
export function FindFilmByRegionAndType(region,type){
    return service({
        url:'/api/film?region='+region+ '&type='+type,
        method:"get"
    })
} 


/* 2.4.2 查询电影详情 测试 2022-04-01 by chen_xy*/
export function FindFilmById(fid){
    return service({
        url: '/api/film/id?fid='+fid,
        method: 'get',
    })
}
/* 2.4.2 查询电影详情 实际 2022-04-01 by chen_xy*/
/* export function FindFilmById(id){
    return service({
        url:'/api/fillm/'+id,
        method:"get"
    })
}  */

/* 2.4.4 评分 实际传参（测试）2022-04-01 by chen_xy */
export function AddFilmEvaluate(data){
    return service({
        url:'/api/fe',
        method:"post",
        data:data
    })
}

/* 2.4.3 查询电影评论 测试 2022-04-01 by chen_xy*/
// export function FindFilmEvaluateByFid(){
//     return service({
//         url:'/api/fe/id',
//         method:"get",
//     })
// }
/* 2.4.3 查询电影评论 实际 2022-04-01 by chen_xy*/
export function FindFilmEvaluateByFid(fid){
    return service({
        url: '/api/fe?id='+fid,
        method:"get",
    })
} 

// * 3.2.2.1 查看排片列表
export function ListAllArrangementFilm(){
    return service({
        url:'/api/arrangement',
        method:"get",
    })
} 

/* 2.4.5 查询电影排片 测试 2022-04-01 by chen_xy*/
// export function FindArrangementFilmById(){
//     return service({
//         url:'/api/arrangement/film/fid',
//         method:"get",
//     })
// }
/* 2.4.5 查询电影排片 实际 2022-04-01 by chen_xy*/
export function FindArrangementFilmById(fid){
    return service({
        url:'/api/arrangement/film/fid?fid='+fid,
        method:"get",
    })
} 


/* 2.4.6 查询排片详情 测试 2022-04-01 by chen_xy*/
export function FindArrangementById(arrangementId){
    return service({
        url: '/api/arrangement/id?arrangementId='+arrangementId,
        method:"get",
    })
}
/* 2.4.6 查询排片详情 实际 2022-04-01 by chen_xy*/
/* export function FindArrangementById(id){
    return service({
        url:'api/arrangement/'+id,
        method:"get",
    })
} */

/* 2.4.7 查询排片座位情况 测试 2022-04-01 by chen_xy*/
// export function GetArrangementSeats(){
//     return service({
//         url:'/api/arrangement/getSeats/id',
//         method:"get",
//     })
// }
/* 2.4.7 查询排片座位情况 实际 2022-04-01 by chen_xy*/
export function GetArrangementSeats(id){
    return service({
        url:'/api/arrangement/getSeats?arrangementId='+id,
        method:"get",
    })
} 
//加入购物车
export function AddCart(data) {
    return service({
        url: '/api/cart/add',
        method: 'post',
        data:data
    })
}

export function AddFilm(data) {
    return service({
        url: '/api/film/add',
        method: 'post',
        data:data
    })
}
export function addposter(data){
    return service({
        url:'/api/poster',
        method:'post',
        data:data
    })
}

export function updatefilm(data) { 
    return service({
        url: '/api/film/arrangement',
        method:'post',
        data:data
    })
}

// export function deleteposterbyid(data) { 
//     return service({
//         url: '/api/poster/delete?id='+ id,
//         method:'get',
//     })
// }
export function deleteposterbyid(data) { 
    return service({
        url: '/api/poster/delete?id='+ id,
        method:'get',
    })
}
export function deletefilm(id) { 
    return service({
        url: '/api/film/delete?id='+ id,
        method:'get',
    })
}
// export function GetArrangementSeats(id){
//     return service({
//         url:'/api/arrangement/getSeats?arrangementId='+id,
//         method:"get",
//     })
// } 
export function AddArrangement(data) {
    return service({
        url: '/api/film/arrangement/add',
        method: 'post',
        data:data
    })
}
export function updatearrange(data) { 
    return service({
        url: '/api/arrangement/update',
        method:'post',
        data:data
    })
}
