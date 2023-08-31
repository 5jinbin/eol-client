<!-- 文件路径：src\views\page\Profile.vue -->


<template>
  <div class="d1">
    <div style="padding-left: 200px; padding-right: 200px; padding-top: 50px">
      <el-button @click="$refs.userInfoDialog.dialogVisible = true" class="edit" type="primary">修改个人信息</el-button>
      <el-button @click="$refs.pwUpdateDialog.dialogVisible = true" class="edit" type="primary">修改密码</el-button>
      <h1>个人信息</h1>
      <el-descriptions :column="4" direction="vertical" border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            用户名
          </template>
          {{ user.username }}</el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-mobile-phone"></i>
            手机号码 </template>{{ user.phone }}</el-descriptions-item>

        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-male"></i>
            性别 </template>{{user.gender === 'male' ? '男' : '女' }}</el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-date"></i>
            出生日期 </template>{{ formatDate(user.birthday) }}</el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-edit"></i>
            姓名 </template>{{ user.name }}</el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-message"></i>
            邮箱地址 </template>{{ user.email }}</el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-trophy"></i>
            学历 </template>{{ user.education }}</el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-upload2
  "></i>
            注册时间 </template>{{ formatDate(user.createTime) }}</el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-tickets"></i>
            自我介绍 </template>{{ user.introduction }}</el-descriptions-item>
      </el-descriptions>
    </div>

    <UserInfoDialog ref="userInfoDialog" :update-user-callback="getUserInfo"></UserInfoDialog>
    <PwUpdateDialog ref="pwUpdateDialog" :update-password-callback="updatePasswordCallback" ></PwUpdateDialog>
  </div>
</template>
  
<script>
import router from "@/router";
import { getUserById } from "@/api/user.js";
import { getUserId } from "@/utils/auth.js";
import UserInfoDialog from "@/components/UserInfoDialog.vue"
import PwUpdateDialog from "@/components/PwUpdateDialog.vue"

export default {
  name: "ProfilePage",
  components: {
    UserInfoDialog,
    PwUpdateDialog
  },
  data() {
    return {
      user: {},
    };
  },
  methods: {
    formatDate(date) {
      return date ? new Date(date).toLocaleDateString() : "";
    },
    getUserInfo() {
      getUserById(getUserId()).then((res) => {
        this.user = res.data;
        console.log(this.user)
      });
    },
    openEditDialog() {
      this.dialogVisible=true;
    },
    updatePasswordCallback() {
      router.push('/auth')
    }
  },
  mounted() {
    this.getUserInfo();
    console.log(this.user)
    window.test = this.$refs
  },
};
</script>
  
<style scoped>
.d1 {
  padding: 0;
  padding-top: 20px;
  background: #fff;
  width: 100%;
  min-height: calc(100vb - 120px);
}

.edit {
  float: right;
  margin-bottom: 10px;
  margin-left: 5px
}
</style>