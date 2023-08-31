<!-- 文件路径：src\components\MyDialog.vue -->


<template>
    <el-dialog :title="word.word" :visible.sync="dialogVisible" width="400px" top="25vb" :z-index="1" center>
        <div class="word-info">
            <div class="pronunciation">
                <div>
                    <span v-if="word.ukPron">UK: </span>
                    <el-button type="text" v-if="word.ukPronPath" @click="playAudio('uk')">
                        <i class="el-icon-volume-high"></i>{{ word.ukPron }}
                    </el-button>
                    <audio ref="ukAudio" :src="word.ukPronPath"></audio>
                </div>


                <div>
                    <span v-if="word.usPron" style="margin-le;">US: </span>
                    <el-button type="text" v-if="word.usPronPath" @click="playAudio('us')">
                        <i class="el-icon-volume-high"></i> {{ word.usPron }}
                    </el-button>
                    <audio ref="usAudio" :src="word.usPronPath"></audio>
                </div>

            </div>

            <hr>

            <div class="word-definition">
                <h3 class="definition-title">单词解释</h3>
                <p class="definition-content">{{ word.definition }}</p>
            </div>

            <hr>


            <div class="word-sentence">
                <h3 class="sentence-title">例句</h3>
                <p class="sentence-content">{{ word.exampleSentence }}</p>
                <p class="sentence-translation">{{ word.exampleTranslation }}</p>
            </div>
        </div>
    </el-dialog>
</template>
  
<script>
export default {
    props: {
        word: {
            type: Object,
            required: true,
        },
    },
    data() {
        return {
            dialogVisible: false,
        };
    },
    methods: {
        playAudio(type) {
            if (type === "uk") {
                this.$refs.ukAudio.play();
            } else if (type === "us") {
                this.$refs.usAudio.play();
            }
        },
    },
};
</script>
  
<style scoped>
.pronunciation {
    display: flex;
    justify-content: space-between;
    margin-bottom: 10px;
}

.pron-type {
    font-size: 16px;
    margin-right: 5px;
}

.definition-title {
    font-size: 18px;
    margin-bottom: 10px;
}

.definition-content {
    font-size: 14px;
    white-space: pre-wrap;
}

.sentence-title {
    font-size: 18px;
    margin-top: 20px;
    margin-bottom: 10px;
}

.sentence-content {
    font-size: 16px;
    color: #666;
    margin-bottom: 5px;
}

.sentence-translation {
    font-size: 16px;
    color: #333;
    margin-bottom: 10px;
}
</style>
  