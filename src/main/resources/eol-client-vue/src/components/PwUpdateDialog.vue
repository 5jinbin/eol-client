<!-- 文件路径：src\components\PwUpdateDialog.vue -->


<template>
    <el-dialog title="修改密码" :visible.sync="dialogVisible" :close-on-click-modal="false">
        <el-form :model="passwordForm" ref="passwordForm" label-width="100px" style="padding: 20px;">
            <el-form-item label="原密码" prop="oldPassword">
                <el-input type="password" v-model="passwordForm.oldPassword"></el-input>
            </el-form-item>
            <el-form-item label="新密码" prop="newPassword">
                <el-input type="password" v-model="passwordForm.newPassword" @input="handlePasswordInput"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="confirmPassword">
                <el-input type="password" v-model="passwordForm.confirmPassword" @input="handlePasswordInput"></el-input>
            </el-form-item>
            <el-form-item label="密码强度">
                <el-progress :percentage="passwordStrength" :status="passwordStrengthStatus"></el-progress>
            </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取消</el-button>
            <el-button type="primary" @click="handleSubmit" :disabled="!passwordFormValid">确定</el-button>
        </div>
    </el-dialog>
</template>
  
<script>
import { getUserId } from '@/utils/auth'
import { updateUser, getUserById } from '@/api/user'

export default {
    props: {
        updatePasswordCallback: {
            type: Function,
            required: true
        }
    },
    data() {
        return {
            dialogVisible: false,
            user: {},
            passwordForm: {
                oldPassword: '',
                newPassword: '',
                confirmPassword: ''
            },
            passwordStrength: 0,
            passwordStrengthStatus: 'warning'
        }
    },
    methods: {
        handlePasswordInput() {
            // 计算密码强度
            const password = this.passwordForm.newPassword;
            let score = 0;
            if (/[A-Z]/.test(password)) score += 1;
            if (/[a-z]/.test(password)) score += 1;
            if (/[0-9]/.test(password)) score += 1;
            if (/[~!@#$%^&*()_+`\-={}[\]:;"'<>,.?/|\\]/.test(password)) score += 1;
            this.passwordStrength = score * 25;

            // 设置密码强度状态
            if (score === 0) {
                this.passwordStrengthStatus = '';
            } else if (score <= 2) {
                this.passwordStrengthStatus = 'exception';
            } else if (score === 3) {
                this.passwordStrengthStatus = 'warning';
            } else {
                this.passwordStrengthStatus = 'success';
            }
        },
        handleSubmit() {
            this.$refs.passwordForm.validate(valid => {
                if (valid) {

                    // 提交修改密码请求
                    // 验证原密码
                    if (this.user.password === this.passwordForm.oldPassword) {
                        this.user.password = this.passwordForm.newPassword
                        updateUser(this.user).then(() => {
                            this.$message.success('修改成功')
                            this.updatePasswordCallback()
                            this.dialogVisible = false;
                        })

                    } else {
                        this.$message.error('原密码错误')
                        return false;
                    }

                } else {
                    console.log('验证失败');
                    return false;
                }
            });
        }
    },
    computed: {
        passwordFormValid() {
            return this.passwordForm.oldPassword && this.passwordForm.newPassword && this.passwordForm.confirmPassword && this.passwordForm.newPassword === this.passwordForm.confirmPassword;
        }
    },
    mounted() {
        getUserById(getUserId()).then(res => {
            this.user = res.data
        })
    }
}
</script>
  