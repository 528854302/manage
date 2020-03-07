import request from '@/utils/request'
import { getToken } from '@/utils/auth'
export function login(data) {
  return request({
    url: '/oauth/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/oauth/info',
    method: 'get',
    params: { access_token: token }
  })
}

export function logout() {
  return request({
    url: '/oauth/out',
    method: 'get',
    params:{access_token:getToken()}
  })
}
