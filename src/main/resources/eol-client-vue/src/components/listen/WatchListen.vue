<!-- 文件路径：src\components\listen\WatchListen.vue -->


<template>
    <div style="padding-left:30px; padding-right:30px">
        <h1 style="font-size:30px; text-align:center">{{ listen.title }}</h1>
        <hr>
        
        <audio class="audioPlayer" ref="audioPlayer" :src="listen.audioPath" controls>
        
        </audio>

        <div v-html="compiledMarkdown"></div>
    </div>
</template>

<script>
import { marked } from 'marked';
import { getListeningById } from '@/api/listen'


export default {
    name: 'WatchListen',
    components: {

    },
    data() {
        return {
            listen: {
                id: '',
                title: '',
                audioPath: '',
                content: '',
                difficulty: ''
            }

        }
    },
    computed: {
        compiledMarkdown: function () {
            return marked(this.listen.content.replaceAll('\n', '\n\n'));
        }
    },
    methods: {
        getListenById() {
            getListeningById(this.$route.params.listenId).then(res => {
                this.listen = res.data
                console.log(this.listen)
            })
        }
    },
    mounted() {
        this.getListenById()
    }
};
</script>

<style scoped>
.audioPlayer {
    width: 100%;
}

</style>