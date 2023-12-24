<template>
  <div class="wrapper-bg">
    <div class="container">
      <Siderbar/>
<!--      router-view-->
      <router-view></router-view>
    </div>
  </div>
</template>

<script>
import pdf from 'vue-pdf'
import Blog from '@/views/PersonalBlog/Blog.vue'
import Siderbar from "@/components/Siderbar.vue";
export default {
  name: "PersonalBlog",

  components:{
    pdf,Blog,Siderbar
  },
  data(){
    return{
    }
  },
  methods:{
  }
}
</script>

<style scoped>
/*这段CSS选择了整个文档的根元素（html），并定义一些全局变量，但是在vue中需要重新写(需要用this进行绑定)*/
/*:root {
  --body-font: "Inter", sans-serif;
  --theme-bg: #1f1d2b;
  --body-color: #808191;
  --button-bg: #353340;
  --border-color: rgb(128 129 145 / 24%);
  --video-bg: #252936;
  --delay: 0s;
}*/
* {
  outline: none;
  box-sizing: border-box;
}
.wrapper-bg{
  -webkit-font-smoothing: antialiased;
  font-family: "Inter", sans-serif;
  color: #808191;
  background-image: url("@/assets/personalBg2.webp"),url("@/assets/personalBlogBg1.webp");
  background-position: center;   /*背景图像的位置*/
  background-size: cover;       /*规定背景图像的尺寸*/
  background-repeat: no-repeat;  /*背景图像是否从重复（比如按照x或y轴重复）*/
  background-blend-mode: color-dodge; /*俩张背景图像的混合模式*/
  background-color: rgba(18, 21, 39, 0.86); /*设置背景色，图像会覆盖背景色*/
  display: flex;    /*一种布局 flex*/
  justify-content: center;  /*应用于上面的flex布局:定义项目在主轴上的对齐方式*/
  align-items: center;  /*应用于上面的flex布局:定义项目在竖直上的对齐方式*/
  flex-direction: column; /*决定flex布局的主轴方向，此处为列*/
  width: 100%;
  height: 100vh;
  overflow: hidden;
}
.wrapper-bg:before {
   position: absolute; /*元素相对于最近祖先元素进行定位*/
   left: 0;
   top: 0;    /*上面俩行，代表将元素的左上角定位在父元素的左上角*/
   width: 100%;
   height: 100%;  /*设置为和父元素同样的宽高*/
   background: linear-gradient(
       163deg,
       rgba(31, 29, 43, 1) 21%,
       rgba(31, 29, 43, 0.3113620448179272) 64%
   );   /*颜色渐变填充*/
   opacity: 0.4;  /*透明度为0.4*/
   content: "XMIX";  /*必选项：整段CSS意味着给背景图片设置了一个半透明渐变的遮罩*/
 }
.container {
  background-color: #1f1d2b;
  /*max-width: 1240px;*/
  max-width: 90%;
  max-height: 900px;  /*设置元素的最大宽高*/
  height: 95vh;       /*将元素的高度设置为视口高度的95%*/
  display: flex;
  overflow: hidden;
  width: 100%;
  border-radius: 20px;
  font-size: 15px;
  font-weight: 500;
  box-shadow: 10px 10px 50px rgba(0, 0, 0, 1);  /*设置阴影*/
  position: relative;   /*设置为相对定位*/
}

/*估计是js控制的*/
.show .main-header,
.show .main-blogs,
.show .small-header,
.show .videos {
  display: none;
}
</style>
