export function getToken() {
  return localStorage.getItem('token')
}

export function setToken(token) {
  return localStorage.setItem('token', token)
}

export function removeToken() {
  return localStorage.removeItem('token')
}

export function getUserInfo() {
  return localStorage.getItem('userInfo')
}

export function setUserInfo(userInfo) {
  return localStorage.setItem('userInfo', userInfo)
}


export function removeUserInfo() {
  return localStorage.removeItem('userInfo')
}

export function getUserId() {
  return parseInt(localStorage.getItem('userId'))
}

export function setUserId(userId) {
  return localStorage.setItem('userId', userId)
}


export function removeUserId() {
  return localStorage.removeItem('userId')
}