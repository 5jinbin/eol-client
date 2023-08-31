<!-- 文件路径：src\components\word\WordStudy.vue -->


<template>
  <div>
    <div class="parent-component">
      <WordBanner :pronunciation="pron" :spelling="word"></WordBanner>
    </div>

    <div class="box-card-parent">
      <el-card class="box-card" body-style="  padding-bottom: 0; margin-bottom: 0;">
        <div v-for="item, index in definition.split('\n')" :key="index" :class="{'text item':true,'show':isShowProp}">
          {{ item }}
        </div>
      </el-card>
    </div>

    <div class="remember">
      <el-button :disabled="isShowProp" v-if="flag_m" type="warning" @click="miss" plain>遗忘</el-button>
      <el-button v-if="(flag_m===false ||  flag_r===false)" type="info" @click="callback" plain>继续({{ seconds }})</el-button>
      <el-button :disabled="isShowProp" v-if="flag_r" type="success" @click="remember" plain>认识</el-button>
      <el-button :disabled="isShowProp" type="info" @click="jump" plain>跳过</el-button>
    </div>

    <el-progress :percentage="percentage" :show-text="false">
      <template v-slot:content>
        <el-tooltip :content="`${percentage}%`">
          <div style="width: 100%; text-align: center;">{{ percentage }}%</div>
        </el-tooltip>
      </template>
    </el-progress>


    <el-button class="setting" icon="el-icon-s-tools" @click="dialogVisible = true"  circle></el-button>


    <!-- 单词书设置 -->
    <el-dialog title="设置" :visible.sync="dialogVisible" width="30%">
      <el-form label-width="100px" style="margint">
        <el-form-item label="单词书种类">
          <el-select v-model="bookType" placeholder="请选择单词书种类">
            <el-option label="英语四级" value="0"></el-option>
            <el-option label="英语六级" value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="每日单词任务">
          <el-slider v-model="wordCount" :marks="marks" :max="50" tooltip-content="50"></el-slider>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="handleSubmit">确定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import WordBanner from '@/components/WordBanner.vue';
import { getUserWordConfig,updateUserWordConfig } from '@/api/word-config';
import { addWordStudyRecord } from '@/api/word';
import { getUserId } from '@/utils/auth';
import { getTask } from '@/api/word';



export default {
    components: {
      WordBanner
    },
    data() {
    return {
      seconds:0, // 倒计时
      flag_m : true, // 遗忘按钮是否可用
      flag_r : true, // 认识按钮是否可用
      isShowProp: true, // 是否显示单词释义
      callback: null, // 倒计时结束后的回调函数
      
      // 单词书设置
      dialogVisible: false,
      bookType: '0',
      wordCount: 0,
      marks: {
        10: '10',
        20: '20',
        30: '30',
        40: '40',
        50: ''
      },


      book:[],
      total: 0,
    }
  },
  computed: {
    percentage() {
      return parseInt(this.book.length > 0 ? Math.floor(Math.round((this.total - this.book.length) / (this.total ) * 100)):100);
    },
    pron() {
      return this.book.length > 0 ? this.book[0].usPron:''
    },
    word() {
      return this.book.length > 0 ? this.book[0].word:'OK ! ! !'
    },
    definition() {
      return this.book.length >0 ? this.book[0].definition:'Success is not final, failure is not fatal: It is the courage to continue that counts. \nThe greatest glory in living lies not in never falling, but in rising every time we fall.\nThe only way to do great work is to love what you do. If you haven\'t found it yet, keep looking. Don\'t settle. '
    }

  },
  methods: {
    handleSubmit() {
      // 用户单词配置提交设置的操作
      console.log(this.bookType, this.wordCount);
      this.dialogVisible = false;
      updateUserWordConfig(
        {userId:getUserId(),
          defaultDifficulty:this.bookType,
          dailyTaskCount:this.wordCount
        }).then(res => { 
          console.log(res)
          location.reload()
        }
      )
    },
    beginTimer(seconds,callback){
      this.seconds = seconds
      this.isShowProp = true
      let timer = setInterval(()=>{
        if(this.seconds > 0){
          this.seconds--
        }else{
          this.callback()
        }
      }, 1000)

      this.callback = ()=>{
        clearInterval(timer)

        this.isShowProp = false
        this.flag_m = true
        this.flag_r = true
        this.seconds = 0
        callback()
      }
    },
    miss(){
      // 遗忘
      console.log('遗忘')
      this.flag_m = false
      this.beginTimer(5,()=>{
        this.book.push(this.book.shift())
      })
    },
    remember() {
      // 认识
      console.log('认识')
      this.flag_r = false
      this.beginTimer(3,()=>{
        if(this.book.length > 0 && this.book[0].id != undefined)
          addWordStudyRecord({
            userId:getUserId(),
            wordId:this.book[0].id,
        })
        this.book.shift()
      })
    },
    jump() {
      // 下一个单词
      console.log('下一个单词')
      this.book.push(this.book.shift())
    },

  },
  watch: {
    book: {
      handler: function (val) {
        console.log('book',val)
        if(val.length === 0){
          this.isShowProp = true
          this.$message({
            message: '今日任务已完成',
            type: 'success'
          });
        }
      },
      deep: true
    }
  },

  mounted() {
    console.log('学习界面加载')
    // userWordConfig数据加载
    getUserWordConfig(getUserId()).then(res => {
      if(res.data === null || res.data === undefined || res.data.length === 0) return
      this.bookType = res.data.defaultDifficulty.toString()
      this.wordCount = res.data.dailyTaskCount
    })
    // book数据加载
    getTask(getUserId(getUserId())).then(res => {
      console.log('book',res)
      this.book = res.data
      this.total = res.data.length
      if(this.book.length > 0){
        this.isShowProp = false;
      }
    })

    console.log(this.book)
  }

};
</script>

<style scoped>
.show{
  filter: none !important;
}

.setting{
  position: absolute;
top: 10px;
left: 10px;
}

.el-progress {
  width: 90%;
  margin: 0 auto;
  margin-top: 50px;

}

.box-card-parent {
  margin-bottom: 30px;
  min-height: 150px;
}

.el-card.box-card {
  padding-bottom: 0;
  margin-bottom: 0;
  width: 90%;
  height: 132px;
  text-align: center;
  /* 水平居中 */
  margin: 0 auto;
}

div.text.item {
  filter: blur(3px) opacity(0.5);
}

.remember {
  width: 90%;
  margin: 0 auto
}

.remember>.el-button {
  width: 33.3%;
  margin-left: 0;
  border-radius: 0;
  border-left: none;
  border-right: none;
}

.parent-component {
  width: 100%;
  height: 300px;
}

.word {
  width: 400px;
  min-height: 300px;
  /* margin: 0 auto; */
  background: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  border-radius: 4px;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}

.clearfix:after {
  clear: both
}

.box-card {
  width: 300px;
  margin-top: 30px;
  margin-left: 20px;
}
</style>