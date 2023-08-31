import Vue from 'vue'
import VueRouter from 'vue-router'


// 页面
// 主页面
import Home from '../views/MyHome.vue'
    // Home的子页面
import NoticeDisplay from '../views/page/NoticeDisplay.vue'
import Word from '../views/page/Word.vue'
    // Word的子页面
import WordStudy from '../components/word/WordStudy.vue'
import WordList from '../components/word/WordList.vue'

import Listen from '../views/page/Listen.vue'
        // Listen的子页面
import SelectListen from '../components/listen/SelectListen.vue'
import WatchListen from '../components/listen/WatchListen.vue'
import Article from '../views/page/Article.vue'
        // Article的子页面
import SelectArticle from '../components/article/SelectArticle.vue'
import WatchArticle from '../components/article/WatchArticle.vue'
import Course from '../views/page/Course.vue'
// Course的子页面
import SelectCourse from '../components/course/SelectCourse.vue'
import WatchCourse from '../components/course/WatchCourse.vue'
import Profile from '../views/page/Profile.vue'

// 登录注册页面
import Auth from '../views/MyAuth.vue'

Vue.use(VueRouter)

const routers = [
    {
        path: '/',     // 主页面
        name: 'Home',
        component: Home,
        meta:{
            breadcrumb:'首页'
        },
        children: [
            {
                path: '',
                component: NoticeDisplay,
            },
            {
                path: '/word',
                component: Word,
                meta:{
                    breadcrumb:'单词记忆'
                },
                children: [
                    {
                        path: '',
                        name: '/defaultWord',
                        component: WordStudy,
                        alias: '/word/study',
                        meta:{
                            breadcrumb:'学习页面'
                        }
                    },
                    {
                        path: '/list',
                        name: '/wordList',
                        component: WordList,
                        alias: '/word/list',
                        meta:{
                            breadcrumb:'数据检索'
                        }
                    }   
                ]

            },
            {
                path: 'listen',
                name: 'Listen',
                component: Listen,
                meta:{
                    breadcrumb:'听力练习'
                },
                children: [
                    {
                        path: '',
                        name: '/defaultListen',
                        component: SelectListen,
                        alias: '/Listen/select',
                        meta:{
                            breadcrumb:'列表'
                        }
                    },
                    {
                        path: ':listenId',
                        name: 'WatchListen',
                        component: WatchListen,
                        meta:{
                            breadcrumb:'详情'
                        }

                    }
                ]
            },
            {
                path: 'article',
                name: 'Article',
                component: Article,
                meta:{
                    breadcrumb:'文章阅读'
                },
                children: [
                    {
                        path: '',
                        name: '/defaultArticle',
                        component: SelectArticle,
                        alias: '/article/select',
                        meta:{
                            breadcrumb:'列表'
                        }
                    },
                    {
                        path: ':articleId',
                        name: 'WatchArticle',
                        component: WatchArticle,
                        meta:{
                            breadcrumb:'详情'
                        }
                    }
                ]
            },
            {
                path: 'course',
                name: 'Course',
                component: Course,
                meta:{
                    breadcrumb:'在线课程'
                },
                children: [
                    {
                        path: '',
                        name: '/defaultCourse',
                        component: SelectCourse,
                        alias: '/course/select',
                        meta:{
                            breadcrumb:'列表'
                        }
                    },
                    {
                        path: ':courseId',
                        name: 'WatchCourse',
                        component: WatchCourse,
                        meta:{
                            breadcrumb:'详情'
                        }
                    }
                ]
            },
            {
                path: 'profile',
                name: 'Profile',
                component: Profile,
                meta:{
                    breadcrumb:'个人中心'
                }
            }
        ]
    },
    {
        path: '/auth', // 登录注册页面
        name: 'Auth',
        component: Auth,
        
    }
]

const router = new VueRouter({
    mode: 'hash',
    base: process.env.BASE_URL,
    routes: routers
})

export default router







