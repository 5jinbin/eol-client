<!-- 文件路径：src\components\NavMenu.vue -->


<template>
  <div class='navMenu'>

    <el-row>
      <el-col :span="24" class="main">
        <el-menu 
        :default-active="this.getDefaultActive()" 
        class="el-menu-vertical-demo" 
        @open="handleOpen" 
        @close="handleClose" 
        :router="true"
        background-color="#545c64"
        text-color="#eee"
        active-text-color="#fff">
        <el-menu-item index="/">
            <i class="el-icon-school"></i>
            <span slot="title">公告信息</span>
          </el-menu-item>
          <el-submenu index="/word">
            <template slot="title">
              <!-- 推荐个ELEMENTUI中 适合单词书的icon -->
              <i class="el-icon-copy-document"></i>
              <span>单词记忆</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="/word" >学习界面</el-menu-item>
              <el-menu-item index="/word/list" >数据检索</el-menu-item>
            </el-menu-item-group>
          </el-submenu>
          <el-menu-item index="/listen" to="/listen">
            <i class="el-icon-headset"></i>
            <span slot="title">听力训练</span>
          </el-menu-item>
          <el-menu-item index="/article">
            <i class="el-icon-document"></i>
            <span slot="title">文章阅读</span>
          </el-menu-item>
          <el-menu-item index="/course">
            <i class="el-icon-data-analysis"></i>
            <span slot="title">在线课程</span>
          </el-menu-item>
          <el-menu-item index="/profile">
            <i class="el-icon-user"></i>
            <span slot="title">个人中心</span>
          </el-menu-item>
        </el-menu>
      </el-col>
    </el-row>
  </div>
</template>

<script>
export default {
  name: 'NavMenu',
  methods: {
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    getDefaultActive: function() {
      return this.$route.path
    }
  }
};
</script>

<style scoped>
.el-menu-item-group .el-menu-item:not(.is-active){
  margin-left: 35px;
}

.navMenu {
  width: calc(100% + 50px);
  margin-left: -50px;
  padding-top: 10px;
  background: #545c64
}

.el-menu-item.is-active {
  background: #27272776 !important; 
  /* 渐变背景色 */
  border-left:rgb(255, 255, 255) solid 55px;
  border-bottom:rgba(255, 255, 255, 0.1) solid 1px;
  border-top:rgba(255, 255, 255, 0.1) solid 1px;
  padding-right: 65px;
}

.el-menu-vertical-demo {
  height: calc(100vb - 70px);
  border-right-width: 0px;

}
</style>