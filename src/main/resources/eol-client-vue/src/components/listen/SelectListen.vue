<!-- 文件路径：src\components\listen\SelectListen.vue -->


<template>
    <div class="listen-list">
        <el-card class="select-container" shadow="hover">
            
            <el-input placeholder="输入标题进行搜索" v-model="inputTitle" class="input-with-select">
                <el-select  v-model="listenType" slot="prepend" clearable placeholder="全部">
                    <el-option label="英语四级" :value="0"></el-option>
                    <el-option label="英语六级" :value="1"></el-option>
                </el-select>
                <el-button slot="append" icon="el-icon-search" @click="searchTitle" clearable></el-button>
            </el-input>


        </el-card>
        <!-- <el-table :data="currentPageData" stripe style="width: 100%;">

            <el-table-column type="index" width="200"></el-table-column>
            <el-table-column label="难度等级" width="300">
                <template slot-scope="scope">
                    {{ scope.row.difficulty === 0 ? '英语四级' : '英语六级' }}
                </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" width="600"></el-table-column>
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="open(scope.row.id)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>
        </el-table> -->

        <el-table :data="currentPageData" stripe style="width: 100%;">
            <el-table-column type="index" width="100"></el-table-column>
            <el-table-column label="难度等级" width="200" >
                <template slot-scope="scope">
                    {{ scope.row.difficulty === 0 ? '英语四级' : '英语六级' }}
                </template>
            </el-table-column>
            <el-table-column prop="title" label="标题" ></el-table-column>
            <el-table-column label="操作" width="100">
                <template slot-scope="scope">
                    <el-button @click="open(scope.row.id)" type="text" size="small">查看</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
            :current-page.sync="currentPage" :page-sizes="[10, 20, 30, 40]" :page-size="pageSize" :total="listenData.length"
            layout="total, sizes, prev, pager, next, jumper">
        </el-pagination>




    </div>
</template>

<script>
import { getListeningByCondition } from '@/api/listen.js';
import router from '@/router/index.js';



export default {

    data() {
        return {
            listenType: '',
            inputTitle: '',

            currentPage: 1,
            pageSize: 10,
            listenData: [],
        };
    },
    computed: {
        currentPageData() {
            const startIndex = (this.currentPage - 1) * this.pageSize;
            const endIndex = startIndex + this.pageSize;
            return this.listenData.slice(startIndex, endIndex);
        }
    },
    watch: {
        listenType() {
            this.getListenData()
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
        getListenData() {
            // 根据条件获取听力列表
            getListeningByCondition({
                title: this.inputTitle === '' ? null : this.inputTitle,
                difficulty: this.listenType === '' ? null : this.listenType,

            }).then(res => {
                this.listenData = res.data
                console.log(this.listenData)
            })
        },
        searchTitle() {
            this.getListenData()
        },
        open(id) {
            router.push({
                path: '/listen/' + id
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

.listen-list {
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