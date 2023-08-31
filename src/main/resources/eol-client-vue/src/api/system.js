// @/api/system.js

import request from '@/utils/request'

// 封装login请求
export function login(username, password) {
    return request({
        url: '/sys/login',
        method: 'post',
        data:{
            username,
            password
        }
    })
}


// 封装register请求
export function register(username, password) {
    return request({
        url: '/sys/register',
        method: 'post',
        data:{
            username,
            password
        }
    })
}
