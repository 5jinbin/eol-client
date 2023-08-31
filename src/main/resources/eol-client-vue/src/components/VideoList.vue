<!-- 文件路径：src\components\VideoList.vue -->


<template>
    <div style="padding:60px;padding-top: 40px;">
        <el-row :gutter="20">
            <el-col v-for="course in courseList" :key="course.id" :span="6" >

                <div   @click="open(course.id)" >

                    <el-card shadow="hover" :body-style="{ padding: '0px', 'padding-bottom': '10px' }" >
                        <img :src="course.coverImage" width="100%" >
                        <div class="text-align">
                            <span style="width:100%">{{ course.title }}</span>
                        </div>
                    </el-card>
                </div>
            </el-col>
        </el-row>




    </div>
</template>

<script>
import router from '@/router';

export default {
    props: {
        courseList: {
            type: Array,
            required: true,
        }
    },
    data() {
        return {
        };
    },
    methods:{
        open(id) {
            console.log(1)
            router.push({
                path: '/course/' + id
            })
        }
    },
    created() {
        console.log('############',this.localCourseList1);
    }
    

};
</script>

<style scoped>
.text-align {
    text-align: center;
}


.el-col {
    margin-bottom: 30px;
}

.el-row:last-child {
    margin-bottom: -30px;
}

/* .row-bg {
    padding: 10px 0;
    background-color: #f9fafc;
} */
</style>