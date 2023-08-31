// @/api/user.js

import request from '@/utils/request'

export function addUser(user) {
  return request({
    url: '/user/add',
    method: 'post',
    data: user
  })
}

export function deleteUser(id) {
  return request({
    url: `/user/delete/${id}`,
    method: 'delete'
  })
}

export function updateUser(user) {
  return request({
    url: '/user/update',
    method: 'put',
    data: user
  })
}

export function getUserById(id) {
  return request({
    url: `/user/${id}`,
    method: 'get'
  })
}

export function getUserListByPage(pageNum, pageSize) {
  return request({
    url: '/user/list',
    method: 'get',
    params: {
      pageNum,
      pageSize
    }
  })
}
