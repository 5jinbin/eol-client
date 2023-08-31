<!-- 文件路径：src\views\page\Word.vue -->


<template>
  <div class='d1'>
    <router-view></router-view>
  </div>
</template>

<script>

export default {
  name: 'WordPage',
};
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