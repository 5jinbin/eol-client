<!-- 文件路径：src\components\MyBreadcrumb.vue -->


<template>
    <div class='breadcrumb'>
        <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item v-for="(item, index) in matched" :key="index" :to="item.path">{{ item.meta.breadcrumb }}</el-breadcrumb-item>

        </el-breadcrumb>
    </div>
</template>

<script>
export default {
    name: 'MyBreadcrumb',
    computed: {
    matched() {
      return this.$route.matched;
    }
  }
};
</script>

<style scoped></style>