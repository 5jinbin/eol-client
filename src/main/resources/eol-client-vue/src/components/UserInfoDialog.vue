<!-- 文件路径：src\components\UserInfoDialog.vue -->


<template>
  <div>
    <el-dialog title="编辑用户信息" :visible.sync="dialogVisible" :close-on-click-modal="false">
      <el-form :model="user" ref="userForm" label-width="100px" style="padding: 20px;" :rules="rules">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="user.name"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-select v-model="user.gender" placeholder="请选择性别">
            <el-option label="男" value="male"></el-option>
            <el-option label="女" value="female"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="user.age"></el-input>
        </el-form-item>
        <el-form-item label="出生日期" prop="birthday">
          <el-date-picker v-model="user.birthday" value-format="yyyy-MM-dd" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item label="手机号码" prop="phone">
          <el-input v-model="user.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱地址" prop="email">
          <el-input v-model="user.email"></el-input>
        </el-form-item>
        <el-form-item label="学历" prop="education">
          <el-select v-model="user.education" placeholder="请选择学历">
            <el-option label="高中" value="高中"></el-option>
            <el-option label="大专" value="大专"></el-option>
            <el-option label="本科" value="本科"></el-option>
            <el-option label="硕士" value="硕士"></el-option>
            <el-option label="博士" value="博士"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="自我介绍" prop="introduction">
          <el-input type="textarea" :rows="4" v-model="user.introduction"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button @click="saveUserInfo" type="primary">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { getUserId } from '@/utils/auth'
import { updateUser, getUserById } from '@/api/user'

export default {
  props: {
    updateUserCallback: {
      type: Function,
      required: true
    }
  },
  data() {
    return {
      dialogVisible: false,
      user: {},
      rules: {
        name: [
          { required: true, message: '请输入姓名', trigger: 'blur' },
          { max: 10, message: '姓名不能超过10个字符', trigger: 'blur' }
        ],
        gender: [{ required: true, message: '请选择性别', trigger: 'change' }],
        age: [{ required: true, message: '请输入年龄', trigger: 'blur' }],
        birthday: [{ required: true, message: '请选择出生日期', trigger: 'change' }],
        phone: [
          { required: true, message: '请输入手机号码', trigger: 'blur' },
          { pattern: /^1[3456789]\d{9}$/, message: '手机号码格式不正确', trigger: 'blur' }
        ],
        email: [
          { required: true, message: '请输入邮箱地址', trigger: 'blur' },
          { type: 'email', message: '邮箱地址格式不正确', trigger: ['blur', 'change'] }
        ],
        education: [{ required: true, message: '请选择学历', trigger: 'change' }],
        introduction: [{ max: 300, message: '自我介绍不能超过300个字符', trigger: 'blur' }]
      }
    }
  },

  methods: {
    getUserInfo() {
      // 根据 userId 获取用户信息并初始化 user 对象
      // 此处需要调用后端接口，代码未提供
      getUserById(getUserId()).then(res => {
        this.user = res.data
      })
    },
    saveUserInfo() {
  this.$refs.userForm.validate(valid => {
    if (valid) {
      console.log(this.user.birthday)
      updateUser(this.user).then(() => {
        // 更新成功后调用回调方法并且关闭弹窗并提示
        this.updateUserCallback()
        this.dialogVisible = false
        this.$message.success('更新成功')
      })
    } else {
      // 验证不通过，不执行后续更新操作
      return false;
    }
  });
}

  },
  mounted() {
      this.getUserInfo()
  }
}

</script>


<style scoped>
.d1 {
position: relative;
padding: 0;
padding-top: 20px;
background: #fff;
width: 100%;
min-height: calc(100vb - 120px);
}
</style>

