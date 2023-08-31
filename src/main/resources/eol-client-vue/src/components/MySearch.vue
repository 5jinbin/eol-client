<!-- 文件路径：src\components\MySearch.vue -->


<template>
  <div class='search'>
    <el-input v-model="input" placeholder="请输入单词(Enter搜索)" @keyup.enter.native="searchWordEnter"></el-input>
    <MyDialog :word="word" ref="wordDialog"></MyDialog>
  </div>
</template>

<script>
import MyDialog from "@/components/MyDialog"
import {getWordByName} from "@/api/word"


export default {
  components: {
    MyDialog
  },
  data() {
    return {
      input: '',
      word:{}
    }
  },
  methods: {
    searchWordEnter(){
      // 触发方法提示
      console.log('searchWordEnter')

      getWordByName(this.input).then(res => {
        this.word = res.data
        this.$refs.wordDialog.dialogVisible = true
      })
    }
  },
  mounted(){

  }
}
</script>

<style scoped>
  .search{
    width: 200px;
    float: right;
    padding-right: 60px;
  }
</style>