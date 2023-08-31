import axios from 'axios'
import {
  Message
} from 'element-ui'
import {
  getToken
} from '@/utils/auth'
import router from '@/router'

// 创建 axios 实例
const service = axios.create({
  //   baseURL: process.env.VUE_APP_BASE_API, // api 的 base_url
  baseURL: "http://localhost:8081", // api 的 base_url
  timeout: 5000 // 请求超时时间
})

// request 拦截器
service.interceptors.request.use(
  config => {
    // 在请求发送之前做一些处理，例如添加 token
    config.headers['Authorization'] = getToken()
    return config
  },
  error => {
    // 请求错误时处理
    console.log(error) // for debug
    Promise.reject(error)
  }
)

// response 拦截器
service.interceptors.response.use(
  response => {
    // 对响应数据进行处理，例如获取状态码等
    const res = response.data

    // 状态码为886时，表示 token 已过期，需要重新登录,跳转到登录页面
    
    if (res.code === 886) {
      Message({
        message: '登录状态已过期，请重新登录',
        type: 'error',
        duration: 5 * 1000

      })

      // 跳转到登录页面
      router.push('/auth')
    }  else if (res.code !== 200) {
      Message({
        message: res.message,
        type: 'error',
        duration: 5 * 1000
      })
      return Promise.reject('error')
    } else {
      return res
    }
  },
  error => {
    // 对响应错误进行处理
    console.log('err' + error) // for debug
    Message({
      message: error.message,
      type: 'error',
      duration: 5 * 1000
    })
    return Promise.reject(error)
  }
)

export default service