import service from '../utils/request'

//查询订单列表
export function AllOrder() {
    return service({
        url: '/api/order/all',
        method: 'get',
    })
}
export function AllOrderex() {
    return service({
        url: '/api/orderex/all',
        method: 'get',
    })
}export function updateorderex(data) { 
    return service({
        url: '/api/orderex/update',
        method:'post',
        data:data
    })
}
export function addorderex(data) { 
    return service({
        url: '/api/orderex/add',
        method:'post',
        data:data
    })
}
