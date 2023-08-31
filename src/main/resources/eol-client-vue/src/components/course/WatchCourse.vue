<!-- 文件路径：src\components\course\WatchCourse.vue -->

<template>
    <div style="padding-left:30px; padding-right:30px">

        <!-- 课程标题展示 -->
        <div>
            <h1>{{ courseInfo.title }}</h1>
        </div>

        <!-- 视频居中显示，并且大小要适中 -->
        <div style="margin-top: 30px; margin-bottom: 30px;padding-left: 150px;padding-right:150px; text-align: center;">
            <video ref="videoPlayerRef" :src="courseInfo.videoUrl" class="video-js vjs-big-play-centered vjs-fluid" controls preload="auto" width="640" height="264" data-setup="{}"></video>
        </div> 

    </div>
</template>

<script>
// js 使用
import Videojs from 'video.js'
import 'video.js/dist/video-js.css'
import {getById} from '@/api/course.js'

export default {
    name: 'WatchCourse',
    components: {
    },
    data() {
        return {
            courseInfo: {
            },
            videoPlayerOption: {
                controls: true, //确定播放器是否具有用户可以与之交互的控件。没有控件，启动视频播放的唯一方法是使用autoplay属性或通过Player API。 
                poster: '',//封面
                autoplay: false, //自动播放属性,
                muted: false, // 静音播放
                preload: 'auto', //建议浏览器是否应在<video>加载元素后立即开始下载视频数据。
                fluid: true
            },

        }
    },
    methods: {
        getCourseInfo(){
            getById(this.$route.params.courseId).then(res => {
                this.courseInfo = res.data
                console.log(this.listen)
            })
        }
    },
    
    mounted() {
        this.$nextTick(() => {
            Videojs(
                this.$refs.videoPlayerRef,
                this.videoPlayerOption,
                function onPlayerReady() {
                    console.log('onPlayerReady', this);
                    this.on('suspend', function () {
                        //延迟下载
                        console.log("延迟下载")
                    });
                });
        })
        this.getCourseInfo()



    }
}
</script>

<style scoped>
/* 限制video-js的大小比例为720p */
.video-js {
    width: 100%;
    height: 100%;
    max-width: 1280px;
    max-height: 720px;
    /* 底部外边距50px */
    margin-bottom: 50px;
    /* 添加10px的圆角 */
    border-radius: 10px;
    /* 添加微弱的阴影 */
    box-shadow: 0 0 5px rgba(0, 0, 0, 0.2);
    overflow: hidden;
    

}

/* 课程标题居中并且字体美化 */
h1 {
    text-align: center;
    font-family: "Microsoft YaHei";
    font-size: 30px;
    font-weight: 700;
    color: #333;
    margin: 0;
    padding: 0;
    
}

</style>