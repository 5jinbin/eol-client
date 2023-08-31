<!-- 文件路径：src\components\article\SelectArticle.vue -->

<template>
    <div class="article-list">
        <el-card class="select-container" shadow="hover">
            
            <el-input placeholder="输入关键词进行搜索" v-model="inputKey" class="input-with-select">
                <el-select  v-model="searchTyper" slot="prepend" placeholder="类别">
                    <el-option label="作者" :value="0"></el-option>
                    <el-option label="标题" :value="1"></el-option>
                </el-select>
                <el-button slot="append" icon="el-icon-search" @click="search" clearable></el-button>
            </el-input>


        </el-card>
        <!-- <el-table :data="currentPageData" stripe style="width: 100%;">
            <el-table-column type="index" width="200"></el-table-column>
            <el-table-column prop="author" label="作者" width="300"></el-table-column>
            <el-table-column prop="title" label="标题" width="600"></el-table-column>
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="open(scope.row.id)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>
        </el-table> -->

        <el-table :data="currentPageData" stripe style="width: 100%;">
            <el-table-column type="index" width="100"></el-table-column>
            <el-table-column prop="author" label="作者" width="200"></el-table-column>
            <el-table-column prop="title" label="标题"  ></el-table-column>
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="open(scope.row.id)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page.sync="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" :total="articleData.length"
            layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>




    </div>
</template>

<script>
import router from '@/router/index.js';
import { getArticleByCondition } from '@/api/article.js';



export default {

    data() {
        return {
            searchTyper: 0,
            inputKey: '',

            currentPage: 1,
            pageSize: 10,
            articleData: [],
        };
    },
    computed: {
        currentPageData() {
            const startIndex = (this.currentPage - 1) * this.pageSize;
            const endIndex = startIndex + this.pageSize;
            return this.articleData.slice(startIndex, endIndex);
        }
    },
    watch: {

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
        getListenData() {
            let params = {};
            if(this.inputKey !== '') {
                params = this.searchTyper === 0 ? {author: this.inputKey} : {title: this.inputKey}
            }
            // 根据条件获取文章列表
            getArticleByCondition(params).then(res => {
                this.articleData = res.data
                console.log(this.articleData)
            })
        },
        search() {
            this.getListenData()
        },
        open(id) {
            router.push({
                path: '/article/' + id
            })
        }
    },
    mounted() {
        this.getListenData()
    },
};
</script>

<style scoped>
/* 可以在这里添加样式 */
.el-input {
    width: 800px;
}

.article-list {
    margin: 20px
}

.select-container {
    width: 100%;
    text-align: center;
    margin-bottom: 20px;
}

.el-select {
    width: 110px;
}

.el-table {
    margin-bottom: 30px;
}

.el-pagination {
    /* float: inline-end; */
    text-align: right;
}
</style>