<!-- 文件路径：src\components\auth\Login.vue -->

<template  >
    <el-form :model="form" :rules="rules" ref="loginForm" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-tooltip class="item" effect="dark"  content="欢迎来到英语学习平台！您可以注册新用户来正常使用，也可以直接使用用户名：test" placement="right">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-tooltip>
      </el-form-item>
      <el-form-item label="密码" prop="password">
          <el-tooltip class="item" effect="dark" enterable="false" content="测试用户的密码为 test" placement="right">
            <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
          </el-tooltip>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="login">确认登录</el-button>
        <el-button @click="resetForm">重置表单</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script>
  export default {
    name: 'MyLogin',
    data() {
      return {
        form: {
          username: '',
          password: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ]
        }
      }
    },
    methods: {
      login() {
        this.$refs.loginForm.validate(valid => {
          if (valid) {
            this.$emit('login', this.form)
          }
        })
      },
      resetForm() {
        this.$refs.loginForm.resetFields()
      }
    }
  }
  </script>

