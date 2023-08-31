<!-- 文件路径：src\components\TopRight.vue -->


<template>
  <div class='topRight'>
    Welcome, {{ username}}
    <router-link to="/profile">个人中心</router-link>
    <router-link to="/auth">退出</router-link>
  </div>
</template>

<script>
import { getUserInfo} from '@/utils/auth'

export default {
  name: 'TopRight',
  data() {
    return {
      username: JSON.parse(getUserInfo()).username
    }
  }
};
</script>

<style scoped>
    .topRight{
        float: right;
    }

    a {
      margin-left:5px;
  text-decoration: none;
}
</style>