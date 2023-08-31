<!-- 文件路径：src\views\MyHome.vue -->


<template>
  <div id="app">
    <el-container>
      <el-aside width="200px" style="height:100vh">
        <MyLogo></MyLogo>
        <NavMenu></NavMenu>
      </el-aside>
      <el-container>
        <el-header>
          <MyBreadcrumb></MyBreadcrumb>
          <MySearch></MySearch>
          <TopRight></TopRight>
        </el-header>
        <el-main>
          <!-- 单词记忆 -->
          <!-- <Word v-if="false"></Word> -->
          <!-- 听力训练 -->
          <!-- <Listen v-if="false"></Listen> -->
          <!-- 文章阅读 -->
          <!-- <Article v-if="false"></Article> -->
          <!-- 在线课程 -->
          <!-- <Course v-if="true"></Course> -->
          <router-view></router-view>
          
        </el-main>
      </el-container>
    </el-container>
  </div>
</template>
  
<script>
import NavMenu from '@/components/NavMenu.vue';
import MyLogo from '@/components/MyLogo.vue';
import TopRight from '@/components/TopRight.vue';
import MyBreadcrumb from '@/components/MyBreadcrumb.vue';
import MySearch from '@/components/MySearch.vue';

export default {
  name: 'MyHome',
  components: {
    NavMenu,
    MyLogo,
    TopRight,
    MyBreadcrumb,
    MySearch,
  }
}

</script>
  
<style scoped>
/* 使class为.search的元素靠最右端 */
.el-header .search {
  margin-left: auto;
}

.el-header{
  background-color: #ffffff;
  color: #333;
  z-index: 2;
    /* 微弱的阴影 */
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
  /* 使子元素垂直居中 */
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.el-aside {
  background-color: #D3DCE6;
  color: #333;
  text-align: center;
  overflow: hidden;
  z-index: 999;

  /* line-height: 200px; */
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  /* line-height: 160px; */
  height: 800px;


  height: calc(100vh - 100px);
  overflow-y: scroll;

}

/* 自定义滚动条 */
.el-main::-webkit-scrollbar {
  /*滚动条整体样式*/
  -webkit-appearance: none;
  width: 10px;
  height: 10px;
}

.el-main::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  cursor: pointer;
  border-radius: 5px;
  background: rgba(0, 0, 0, .25);
  -webkit-transition: color .2s ease;
  transition: color .2s ease;
}

.el-main::-webkit-scrollbar-thumb:hover {
  background: #949A9D;
}

.el-main::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  background: rgba(0, 0, 0, .1);
  border-radius: 0;
}

::selection {
  background-color: #cce2ff;
  color: rgba(0, 0, 0, .87);
}

body>.el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}</style>