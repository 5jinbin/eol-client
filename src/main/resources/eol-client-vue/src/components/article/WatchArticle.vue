<!-- 文件路径：src\components\article\WatchArticle.vue -->

<template>
    <div style="padding-left:30px; padding-right:30px">
        <h1 style="font-size:30px; text-align:center">{{ article.title }}</h1>
        <hr>
        <div v-html="compiledMarkdown"></div>
    </div>
</template>

<script>

import { marked } from 'marked';
import { getArticleById } from '@/api/article'


export default {
    name: 'WatchArticle',
    components: {

    },
    data() {
        return {

            article: {
                author: '',
                content: '',
                favoriteCount: '',
                id: '',
                studyCount: '',
                summary: '',
                title: ''
            }
        }
    },
    computed: {
        compiledMarkdown: function () {
            return marked(this.article.content.replaceAll('\n', '\n\n'));
        }
    },
    methods: {
        getArticleById() {
            getArticleById(this.$route.params.articleId).then(res => {
                this.article = res.data
                
            })
        }
    },
    mounted() {
        this.getArticleById()
    }
};
</script>

<style scoped>
.audioPlayer {
    width: 100%;
}

</style>