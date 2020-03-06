import request from '@/utils/request'

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

export function logout(token  ) {
  return request({
    url: '/oauth/logout',
    method: 'post',
    params: { access_token: token }
  })
}
