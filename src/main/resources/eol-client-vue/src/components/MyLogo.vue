<!-- 文件路径：src\components\MyLogo.vue -->


<template>
    <div class="myLogo">
        <h1 :style="dynamic">英语一站式学习平台</h1>
    </div>
</template>

<script>
export default {
    name:'MyLogo',
    data() {
      return {
        dynamic:{
          background: 'linear-gradient(45deg, rgb(127,255,127), rgb(127,127,255))',
          '-webkit-background-clip': 'text',
          '-webkit-text-fill-color': 'transparent'
        }
      }
    },
    watch:{
      dynamicBackground(val){
        console.log(val)
      }
    },
    mounted() {
      let angle = 45

      let l = {
        r:127,
        g:255,
        b:127,
      }

      let r = {
        r:127,
        g:127,
        b:255
      }

      function getRandom(i){
        // 随机加一减一不变
        let r = Math.floor(Math.random() * 15) - 7;
        i = i + r;
        return Math.min(255,Math.max(127,i));
      }

      window.myInterval = setInterval(()=>{

        angle = (angle+3) % 361

        for(let i in l){
          l[i] = getRandom(l[i])
        }
        for(let i in r){
          r[i] = getRandom(r[i])
        }

        // h1Element.style.background = `linear-gradient(900deg, rgb(128,128,244), rgb(128,255,128))`;


        this.dynamic.background = `linear-gradient(${angle}deg, rgb(${l.r},${l.g} ,${l.b}),rgb(${r.r},${r.g} ,${r.b}))`
      },50)
    },
    destroyed(){
      clearInterval(window.myInterval)
    }
};
</script>

<style scoped>

.myLogo {
  height: 60px;
  width: 200px;
  overflow: hidden;
  background-color: #545c64f2;
  /* color: #fff; */
}


.myLogo img {
  height: 40px;
  width: 40px;
  margin-right: 10px;
  float: left;
}

.myLogo h1 {
  font-size: 20px;
  line-height: 60px;
  margin: 0;
  -webkit-background-clip: text !important;
}
</style>