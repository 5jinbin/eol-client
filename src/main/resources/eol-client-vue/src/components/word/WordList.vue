<!-- 文件路径：src\components\word\WordList.vue -->


<template>
    <div class="word-list">
        <el-card class="select-container" shadow="hover">
            <span style="font-weight:bold">种类</span>
            <el-select v-model="bookType" clearable placeholder="请选择单词种类">
                <!-- <el-option label="全部" :value="-1"></el-option> -->
                <el-option label="英语四级" :value="0"></el-option>
                <el-option label="英语六级" :value="1"></el-option>
            </el-select>
            <span style="font-weight:bold">状态</span>
            <el-select v-model="status" clearable placeholder="请选择单词状态">
                <!-- <el-option label="全部" :value="-1"></el-option> -->
                <el-option label="未背" :value="0"></el-option>
                <el-option label="已背" :value="1"></el-option>
            </el-select>

            <el-button @click="() => { bookType = status = '' }">重置</el-button>

        </el-card>

        <!-- <el-table :data="currentPageData" stripe style="width: 100%;">
            <el-table-column type="index" width="50">
            </el-table-column>
            <el-table-column prop="word" label="单词" width="160"></el-table-column>
            <el-table-column prop="ukPron" label="英式发音" width="160"></el-table-column>
            <el-table-column prop="usPron" label="美式发音" width="160"></el-table-column>
            <el-table-column prop="definition" label="释义" width="460"></el-table-column>
            <el-table-column label="难度等级" width="200">
                <template slot-scope="scope">
                    {{ scope.row.difficulty === 0 ? '英语四级' : '英语六级' }}
                </template>
            </el-table-column>
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="showWordDetail(scope.row)" type="text" size="small">详情</el-button>
                </template>
            </el-table-column>
        </el-table> -->

        <el-table :data="currentPageData" stripe style="width: 100%;">
            <el-table-column type="index" width="50">
            </el-table-column>
            <el-table-column prop="word" label="单词" ></el-table-column>
            <el-table-column prop="ukPron" label="英式发音" ></el-table-column>
            <el-table-column prop="usPron" label="美式发音" ></el-table-column>
            <el-table-column prop="definition" label="释义" ></el-table-column>
            <el-table-column label="难度等级">
                <template slot-scope="scope">
                    {{ scope.row.difficulty === 0 ? '英语四级' : '英语六级' }}
                </template>
            </el-table-column>
            <el-table-column label="操作" >
                <template slot-scope="scope">
                    <el-button @click="showWordDetail(scope.row)" type="text" size="small">详情</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page.sync="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" :total="wordData.length"
            layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>



        <MyDialog :word="dialogWord" ref="worList_wordDialog"></MyDialog>
    </div>
</template>

<script>
import { getWordsByCondition } from '@/api/word'
import { getUserId } from '@/utils/auth';
import MyDialog from '../MyDialog.vue';



export default {
    components: {
        MyDialog
    },
    data() {
        return {
            bookType: '',
            status: '',

            currentPage: 1,
            pageSize: 10,
            wordData: [{
            }],

            // 单词详情
            dialogWord: {
                word: '',
                ukPron: '',
                usPron: '',
                definition: '',
                difficulty: 0,
                status: 0,
                bookType: 0,
                createTime: '',
                updateTime: '',
            }
        };
    },
    computed: {
        currentPageData() {
            const startIndex = (this.currentPage - 1) * this.pageSize;
            const endIndex = startIndex + this.pageSize;
            return this.wordData.slice(startIndex, endIndex);
        }
    },
    watch: {
        bookType() {
            this.getWordData()
        },
        status() {
            this.getWordData()
        },
    },
    methods: {
        handleSizeChange(val) {
            this.pageSize = val
            console.log(`每页 ${val} 条`)
        },
        handleCurrentChange(val) {
            this.currentPage = val
            console.log(`当前页: ${val}`)
        },
        getWordData() {
            getWordsByCondition(
                getUserId(),
                this.bookType === '' ? -1 : this.bookType,
                this.status === '' ? -1 : this.status
            ).then(res => {
                this.wordData = res.data
            })

        },
        showWordDetail(word) {
            this.$refs.worList_wordDialog.dialogVisible = true
            this.dialogWord = word;
            console.log('word', word);
        }
    },
    mounted() {
        this.getWordData()
    },
};
</script>

<style scoped>
/* 可以在这里添加样式 */
.word-list {
    margin: 20px
}

.select-container {
    width: 100%;
    display: flex;
    align-items: center;
    /* float: right; */
    margin-bottom: 20px;
}

.el-select {
    width: 30%;
    margin-right: 20px;
    margin-left: 10px;
}

.el-table {
    margin-bottom: 30px;
}

.el-pagination {
    /* float: inline-end; */
    text-align: right;
}
</style>