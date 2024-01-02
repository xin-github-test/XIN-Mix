import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/test',
    name: 'test',
    component: () => import('@/views/manager/test.vue')
  },
  //   登录页面以及404页面（全局页面）
  {
    path: '/login',
    name: 'Login',
    component: () => import('@/views/common/Login.vue')
  },
  {
    path: '/404',
    name: 'NotFound',
    component: () => import('@/views/common/NotFound.vue')
  },

  //主页页面
  {
    //TODO 完善1：进入主页的时候可以写一个加载界面，等待其它页面加载完成之后 redirect到需要展示的页面
    path: '/',
    name: 'Home',
    component: () => import('@/views/common/Home.vue'),
  },
//     角色管理页面
  {
    path: '/RoleManagement',
    name: 'RoleManagement',
    component: () => import('@/views/manager/RoleManagement.vue')
  },
//     个人博客界面
  {
    path: '/PersonalBlog',
    name: 'PersonalBlog',
    component: () => import('@/views/PersonalBlog/PersonalBlog.vue'),
    children: [
      {
        path: '/Blog',
        name: 'Blog',
        meta:{path:'Blog'},
        component: () => import('@/views/PersonalBlog/Blog.vue')
      },
      {
        path: '/StreamPage',
        name: 'StreamPage',
        meta:{path:'StreamPage'},
        component: () => import('@/views/PersonalBlog/StreamPage.vue')
      },
      {
        path: '/CvPage',
        name: 'CvPage',
        component: () => import('@/views/PersonalBlog/CvPage.vue')
      }
    ]
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

//设置全局前置路由守卫，当用户访问不存在的页面时，路由到404页面
router.beforeEach((to, from, next)=>{
    //TODO 此处先随便写写，后期需要修改,最好是除了登陆页面之外，进入其它的页面需要进行身份的验证，最后需要加上jwt的token验证
    let routPaths = ['/test','/','/login','/404','/RoleManagement','/Blog','/StreamPage','/CvPage']
    let imgs = ['jpg','jpeg','webp','pdf']  //获取图片资源直接放行
  //一旦身份验证不过关的话直接转到404页面
    if(routPaths.includes(to.path) || imgs.includes(to.path.split('.')[1])){
      //指定的路径直接放行
      next()
    }else{
      //next('/404')会再次触发前置守卫，因此需要在前面加上对 '/404'地址的判断，防止无限递归导致栈溢出
      next('/404')
    }
})

export default router
