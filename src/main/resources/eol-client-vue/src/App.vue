<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>

<script>
// import MyHome from "@/views/MyHome"
// import MyAuth from "@/views/MyAuth"
import {getWordByName} from "@/api/word"


export default {
  name: 'App',
  components: { 
    // MyHome,
    // MyAuth
  },
  methods: {
    test(){
      window.getWordByName = getWordByName
    }
  },
  mounted() {

    this.test()
  },
}

</script>
