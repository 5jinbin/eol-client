<!-- 文件路径：src\components\auth\Register.vue -->

<template>
    <el-form :model="form" :rules="rules" ref="registerForm" label-width="80px">
      <el-form-item label="用户名" prop="username">
        <el-tooltip class="item" effect="dark"  content="提示：请不要在陌生网站使用个人常用的用户名或密码，谨防信息泄露！" placement="right">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-tooltip>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <!-- 隐藏确认密码 -->
      <el-form-item v-show="false" label="确认密码" prop="confirmPassword">
        <el-input type="password" v-model="form.confirmPassword" placeholder="请再次输入密码"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="register">确认注册</el-button>
        <el-button @click="resetForm">重置表单</el-button>
      </el-form-item>
    </el-form>
  </template>
  
  <script>
  export default {
    name: 'MyRegister',
    data() {
      return {
        form: {
          username: '',
          password: '',
          confirmPassword: ''
        },
        rules: {
          username: [
            { required: true, message: '请输入用户名', trigger: 'blur' }
          ],
          password: [
            { required: true, message: '请输入密码', trigger: 'blur' }
          ],
          confirmPassword: [
            { required: true, message: '请再次输入密码', trigger: 'blur' },
            {
              validator: (rule, value, callback) => {
                if (value !== this.form.password) {
                  callback(new Error('两次输入的密码不一致'))
                } else {
                  callback()
                }
              },
              trigger: 'blur'
            }
          ]
        }
      }
    },
    watch:{
      // 隐藏确认密码的逻辑
      'form.password'(newValue){
        this.form.confirmPassword = newValue;
      }
    },
    methods: {
      register() {
        this.$refs.registerForm.validate(valid => {
          if (valid) {
            this.$emit('register', this.form)
          }
        })
      },
      resetForm() {
        this.$refs.registerForm.resetFields()
      }
    }
  }
  </script>
