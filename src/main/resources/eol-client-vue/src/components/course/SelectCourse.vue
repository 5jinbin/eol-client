<!-- 文件路径：src\components\course\SelectCourse.vue -->


<template>
    <div>
        <div class="text-align">
            <el-input class="search-input" placeholder="搜索课程关键词" v-model="inputKey" clearable>
                <el-button @click="getCourseData" slot="append" icon="el-icon-search"></el-button>
            </el-input>
        </div>

        <VideoList :courseList="currentPageData"></VideoList>

        <div class="text-align">
            <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                :current-page.sync="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize"
                :total="tableData.length" layout="total, prev, pager, next, jumper">
            </el-pagination>
        </div>


    </div>
</template>

<script>
import VideoList from '../VideoList.vue'
import {getByTitle} from '@/api/course.js'

export default {
    name: 'SelectCourse',
    components: {
        VideoList
    },
    data() {
        return {
            currentPage: 1,
            pageSize: 8,
            inputKey: '',
            tableData: [],

        }
    },
    computed: {
        currentPageData() {
            const startIndex = (this.currentPage - 1) * this.pageSize;
            const endIndex = startIndex + this.pageSize;
            return this.tableData.slice(startIndex, endIndex);
        }
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
        getCourseData(){
            getByTitle(this.inputKey === '' ? null : this.inputKey)
            .then(res => {
                this.tableData = res.data
                console.log(this.tableData)
            })
        },

    },
    mounted(){
        this.getCourseData()
    }
}
</script>

<style scoped>

.text-align {
    text-align: center;
}

.el-row{
    margin-bottom: 30px;
}

.el-row:last-child{
    margin-bottom: 0;
}

.search-input {
    width: 400px;
    margin: 0 auto;
    margin-top: 10px;
}



.row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
}
</style>