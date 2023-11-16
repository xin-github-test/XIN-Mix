import Vue from 'vue'
import App from './App.vue'
import router from './router'
import request from '@/utils/request';

Vue.config.productionTip = false

Vue.prototype.request = request   //将request绑定到原型链上

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
