<!-- 文件路径：src\views\MyAuth.vue -->


<template>
    <div class="auth-container">
      <div class="auth-card">
        <div class="auth-header">
          <span class="auth-title">{{ isLogin ? '登录' : '注册' }}</span>
          <el-button type="text" @click="switchAuthMode">{{ isLogin ? '注册' : '登录' }}</el-button>
        </div>
        <div class="auth-body">
          <login v-if="isLogin" @login="handleLogin" />
          <register v-else @register="handleRegister" />
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import Login from '@/components/auth/Login.vue'
  import Register from '@/components/auth/Register.vue'
  import { login } from '@/api/system'
  import { setToken, setUserInfo , removeToken , removeUserInfo} from '@/utils/auth'
  import { register } from '@/api/system'
  
  export default {
    name: 'MyAuth',
    components: {
      Login,
      Register
    },
    data() {
      return {
        isLogin: true
      }
    },
    methods: {
      switchAuthMode() {
        this.isLogin = !this.isLogin
      },
      handleLogin({ username, password }) {
          // 处理登录逻辑
            login(username, password ).then(res => {
                // 存储 token 到本地存储, 保存登录状态
                setToken( res.data.token)
                setUserInfo(JSON.stringify(res.data.userInfo))
                localStorage.setItem('userId', res.data.userInfo.id)
                console.log('登录成功');
                // 跳转到首页
                this.$router.push('/')
            })
          

      },
      handleRegister({ username, password }) {
        // 处理注册逻辑
        console.log(username, password)
        register(username, password ).then(()=>{
            // 注册成功处理逻辑
            // console.log('注册成功');
            this.$message({
              message: '注册账号成功！(自动跳转到登录页面)',
              type: 'success'
            });
            this.isLogin = true;
        })
      }
    },
    mounted() { 
      // console.log(this.$route.query)
      removeToken()
      removeUserInfo()
    }
  }
  </script>
  
  <style>


.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vb;
  background-image: url(../assets/img/bg.jpg);
  background-size: cover;
  background-position: center;
}
  
  .auth-card {
    width: 400px;
    padding: 20px;
    border: 1px solid rgba(255, 255, 255, 0.45);
    border-radius: 12px;
    box-shadow: 0 0px 16px rgba(255, 163, 126, 0.25);
    /* 半透明背景色 */
    background: rgba(0, 0, 0, 0.5);
    /* 渐变背景色 */
    /* background: linear-gradient(rgba(227, 227, 227, 0.9), rgba(235, 255, 253, 0.8)); */


  }
  
  .auth-header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 20px;
  }
  
  .auth-title {
    font-size: 24px;
    font-weight: bold;
    color: #FFF;
  }
  
  .auth-body {
    display: flex;
    flex-direction: column;
    align-items: center;
  }

  .auth-body .el-form-item__label{
    color: #ffffff !important;
  }


  .auth-body .el-input__inner{
    background-color: rgba(0,0,0,0) !important;
    color: #ffffff !important;
    border-top: none;
    border-left: none;
    border-right: none;
    border-radius: 0;
  }

  </style>