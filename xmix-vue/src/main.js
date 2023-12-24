import Vue from 'vue'
import App from './App.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import router from './router'
import request from '@/utils/request';
import '@/styles/element-ui-reset.css'

Vue.config.productionTip = false

//引入element组件库
Vue.use(ElementUI,{size:"mini"});

Vue.prototype.request = request   //将request绑定到原型链上

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
