import Vue from 'vue'
import App from './App.vue'

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

// video依赖库
import Video from 'video.js'
import 'video.js/dist/video-js.css'

// Vue Router依赖库
import VueRouter from 'vue-router'

Vue.use(VueRouter)
import router from './router' 


Vue.use(ElementUI);
Vue.prototype.$video = Video

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
