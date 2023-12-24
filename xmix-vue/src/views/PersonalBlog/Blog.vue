<template>
  <div class="wrapper">
        <div class="header">
          <div class="search-bar">
            <input type="text" placeholder="Search">
          </div>
          <div class="user-settings">
            <span class="home-style" @click="goHome">
              <i class="el-icon-house"></i>
              Home
              <i class="el-icon-right"></i>
            </span>
            <img class="user-img" src="https://images.unsplash.com/photo-1587918842454-870dbd18261a?ixlib=rb-1.2.1&ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&auto=format&fit=crop&w=943&q=80" alt="">
            <div class="user-name">Thomas</div>
            <svg viewBox="0 0 492 492" fill="currentColor">
              <path d="M484.13 124.99l-16.11-16.23a26.72 26.72 0 00-19.04-7.86c-7.2 0-13.96 2.79-19.03 7.86L246.1 292.6 62.06 108.55c-5.07-5.06-11.82-7.85-19.03-7.85s-13.97 2.79-19.04 7.85L7.87 124.68a26.94 26.94 0 000 38.06l219.14 219.93c5.06 5.06 11.81 8.63 19.08 8.63h.09c7.2 0 13.96-3.57 19.02-8.63l218.93-219.33A27.18 27.18 0 00492 144.1c0-7.2-2.8-14.06-7.87-19.12z"></path>
            </svg>
            <div class="notify">
              <div class="notification"></div>
              <svg viewBox="0 0 24 24" fill="currentColor">
                <path fill-rule="evenodd" clip-rule="evenodd" d="M18.707 8.796c0 1.256.332 1.997 1.063 2.85.553.628.73 1.435.73 2.31 0 .874-.287 1.704-.863 2.378a4.537 4.537 0 01-2.9 1.413c-1.571.134-3.143.247-4.736.247-1.595 0-3.166-.068-4.737-.247a4.532 4.532 0 01-2.9-1.413 3.616 3.616 0 01-.864-2.378c0-.875.178-1.682.73-2.31.754-.854 1.064-1.594 1.064-2.85V8.37c0-1.682.42-2.781 1.283-3.858C7.861 2.942 9.919 2 11.956 2h.09c2.08 0 4.204.987 5.466 2.625.82 1.054 1.195 2.108 1.195 3.745v.426zM9.074 20.061c0-.504.462-.734.89-.833.5-.106 3.545-.106 4.045 0 .428.099.89.33.89.833-.025.48-.306.904-.695 1.174a3.635 3.635 0 01-1.713.731 3.795 3.795 0 01-1.008 0 3.618 3.618 0 01-1.714-.732c-.39-.269-.67-.694-.695-1.173z" />
              </svg>
            </div>
          </div>
        </div>
        <div class="main-container">
          <div class="main-header anim" style="--delay: 0s">About Me</div>
          <div class="main-blogs">
            <div class="main-blog anim" style="--delay: .1s">
              <div class="main-blog__title">Just Do It! There're Nothing Can Beat You! </div>
            </div>
            <div class="main-blog anim" style="--delay: .2s">
              <div class="main-blog__title">About Me</div>
              <div class="main-blog__author tips">
                <div class="author-detail">
                  <div class="author-name">姓名: Tony Andrew</div>
                  <div class="author-gender">性别: 男</div>
                  <div class="author-hobby">爱好: 音乐</div>
                  <div class="author-character">性格: 开朗</div>
                  <div class="author-hometown">家乡: 江西</div>
                  <div class="author-major">专业: 计算机科学与技术</div>
                </div>
              </div>
            </div>
          </div>
          <!--          其它-->
          <div class="small-header anim" style="--delay: .3s">Others</div>
          <div class="videos">
            <div class="video anim" style="--delay: .4s">
              <div class="video-wrapper" @click="toCvPage">
                <img :src="require('/src/assets/SpringBg.jpg')" alt="">
              </div>
              <div class="video-by">CV</div>
              <div class="video-name">作者简历</div>
              <div class="video-view">54K views<span class="seperate video-seperate"></span>1 week ago</div>
            </div>
            <div class="video anim" style="--delay: .5s">
              <div class="video-wrapper">
                <img :src="require('/src/assets/SummerBg.jpg')" alt="">
              </div>
              <div class="video-by">Essay</div>
              <div class="video-name">短文分享</div>
              <div class="video-view">42K views<span class="seperate video-seperate"></span>1 week ago</div>
            </div>
            <div class="video anim" style="--delay: .6s">
              <div class="video-wrapper">
                <img :src="require('/src/assets/AutumnBg.jpg')" alt="">
              </div>
              <div class="video-by">Log</div>
              <div class="video-name">日志分享</div>
              <div class="video-view">64K views<span class="seperate video-seperate"></span>2 week ago</div>
            </div>
            <div class="video anim" style="--delay: .7s">
              <div class="video-wrapper">
                <img :src="require('/src/assets/WinterBg.jpg')" alt="">
              </div>
              <div class="video-by">Notes</div>
              <div class="video-name">留言</div>
              <div class="video-view">50K views<span class="seperate video-seperate"></span>1 week ago</div>
            </div>
          </div>

        </div>
      </div>
</template>

<script>
export default {
  name: "Blog",
  methods:{
    goHome(){
      this.$router.back()
    },
    toCvPage(){
      this.$router.push("/CvPage")
    }
  }
}
</script>

<style scoped>
* {
  outline: none;
  box-sizing: border-box;
}
.home-style{
  color: #ff7551;
  margin-right: 50px;
  border-radius: 5px;
}
.home-style:hover{
  cursor: pointer;
  color: white;
  font-weight: 700;
  background-color: #6c5ecf;
}
@media screen and (max-width: 735px) {
  .main-blogs {
    flex-wrap: wrap;
  }
  .main-blog,
  .main-blog + .main-blog {
    width: 100%;
  }
  .videos {
    grid-template-columns: 1fr;
  }
  .main-blog + .main-blog {
    margin-left: 0;
    margin-top: 20px;
    background-size: cover;
  }
}
@media screen and (max-width: 475px) {
  .main-blog__title {
    font-size: 20px;
  }
  .author-name {
    font-size: 14px;
  }
  .main-blog__author {
    flex-direction: column-reverse;
    align-items: flex-start;
  }
  .author-detail {
    margin-left: 0;
  }
  .main-blog .author-img {
    margin-top: 14px;
  }
  .main-container {
    padding: 0 20px 20px;
  }
  .header {
    padding: 20px;
  }
  .sidebar.collapse {
    width: 40px;
  }
  .sidebar {
    align-items: center;
  }
  body {
    padding: 0;
  }
  .container {
    height: 100vh;
    border-radius: 0;
    max-height: 100%;
  }
}
.wrapper {
  display: flex;
  flex-direction: column;
  flex-grow: 1;    /*所有弹性项目的占比（值越大占比越大）*/
}
.header {
  display: flex;
  align-items: center;  /*竖直方向上居中*/
  flex-shrink: 0; /*所有弹性项目的收缩占比（值越大占比越小）*/
  padding: 30px;
}
.search-bar {
  height: 34px;
  display: flex;
  width: 100%;
  max-width: 450px;
}
.search-bar input {
  width: 100%;
  height: 100%;
  border: none;
  background-color: #353340;
  border-radius: 8px;
  font-family: "Inter", sans-serif;
  font-size: 14px;
  font-weight: 500;
  padding: 0 40px 0 16px;
  box-shadow: 0 0 0 2px rgba(134, 140, 160, 0.02);
  background-image: url("@/assets/searchIcon.svg");
  background-size: 14px;  /*设置图像的大小*/
  background-repeat: no-repeat;
  background-position: 96%;  /*设置图像的起始位置*/
  color: #fff;
}
.user-settings{
  display: flex;
  align-items: center;
  padding-left: 20px;
  flex-shrink: 0;    /*元素不会因为容器大小而变化尺寸*/
  margin-left: auto;
}
.user-settings svg{
  width: 10px;
  flex-shrink: 0; /*元素不会因为容器大小而变化尺寸*/
}
@media screen and (max-width: 575px) {
  .user-settings svg{
    display: none;
  }
}
.user-settings .notify{
  position: relative;
}
.user-settings .notify svg{
  width: 20px;
  margin-left: 24px;
  flex-shrink: 0;  /*元素不会因为容器大小而变化尺寸*/
}
.user-settings .notify .notification{
  width: 8px;
  height: 8px;
  border-radius: 50%;
  background-color: #ec5252;
  position: absolute;
  right: 1px;
  border: 1px solid #1f1d2b;
  top: -2px;
}
@media screen and (max-width: 575px) {
  .user-settings .notify .notification{
    display: none;
  }
}
.user-img{
  width: 30px;
  height: 30px;
  flex-shrink: 0;
  object-fit: cover;
  border-radius: 50%;
}
.user-name{
  color: #fff;
  font-size: 14px;
  margin: 0 6px 0 12px;
}
@media screen and (max-width: 575px) {
  .user-name{
    display: none;
  }
}
.main-container {
  display: flex;
  flex-direction: column;
  padding: 0 30px 30px;
  flex-grow: 1;  /*设置.main-container的弹性增长因子为1。这意味着.main-container将尽可能地占据多余的空间*/
  overflow: auto;
}
.anim {
  animation: bottom 0.8s 0s both;
}
.main-header {
  font-size: 30px;
  color: #fff;
  font-weight: 700;
  padding-bottom: 20px;
  position: sticky;
  top: 0;
  left: 0;
  background: linear-gradient(  /*定义了一种渐变背景*/
      to bottom,
      #1f1d2b 0%,
      #1f1d2b 78%,
      rgb(31 29 43 / 0%) 100%
  );
  z-index: 11;
}
.main-blogs{
  display: flex;
  align-items: center;
}
.main-blog__author {
  display: flex;
  align-items: center;
  padding-bottom: 10px;
}
.main-blog__author.tips{
  flex-direction: column-reverse;
  align-items: flex-start;
}
.main-blog__title {
  font-size: 25px;
  max-width: 12ch;
  font-weight: 600;
  letter-spacing: 1px;
  color: #fff;
  margin-bottom: 30px;
}
.main-blog {
  background-image: url("@/assets/skate-removebg-preview.webp");
  background-size: 80%;  /*图像大小为原来的80%*/
  background-position-x: 150px;  /*图像往右偏移150px*/
  background-color: #31abbd;
  display: flex;
  flex-direction: column;
  width: 65%;
  padding: 30px;
  border-radius: 20px;
  align-self: stretch;
  overflow: hidden;
  position: relative;
  transition: background 0.3s;
  background-repeat: no-repeat;
}
.main-blog + .main-blog{
  margin-left: 20px;
  width: 35%;
  background-image: url("@/assets/img2.jpg");
  background-color: unset;  /*未设置背景颜色*/
  background-position-x: 0;
  background-size: 139%;
  filter: saturate(1.4);   /*饱和度滤镜*/
}
.main-blog + .main-blog .author-img__wrapper svg {
  border-color: #ffe6b2;
  color: #e7bb7d;
}
.main-blog + .main-blog .author-detail {
  margin-left: 0;
}
@media screen and (max-width: 905px){
  .main-blog,
  .main-blog + .main-blog {
    width: 50%;
    padding: 30px;
  }
}
@media screen and (max-width: 905px){
  .main-blog {
    background-size: cover;
    background-position-x: center;
    background-blend-mode: overlay; /*设置了背景图像的混合模式:当两个颜色混合时，它们的颜色值将相互影响，从而产生一种混合效果。*/
  }
}

.author-img__wrapper svg{
  width: 16px;
  padding: 2px;
  background-color: #fff;
  color: #0daabc;
  border-radius: 50%;
  border: 2px solid #0daabc;
  position: absolute;
  bottom: 5px;
  right: 0;
}
.author-name,
.author-gender,
.author-hobby,
.author-character,
.author-hometown,
.author-major{
  font-family: 楷体;
  font-size: 18px;
  color: black;
  font-weight: 700;
  margin-bottom: 8px;
}
.author-detail {
  margin-left: 16px;
}
.seperate {
  width: 3px;
  height: 3px;
  display: inline-block;   /*内联块，不会独占一行*/
  vertical-align: middle;  /*垂直居中*/
  border-radius: 50%;
  background-color: #fff;
  margin: 0 6px;
}
.seperate.video-seperate {
  background-color: rgb(128 129 145 / 24%);
}
.videos {
  display: grid;   /*定义网格布局*/
  width: 100%;
  grid-template-columns: repeat(4, 1fr);  /*适用于网格布局：创建4列，每列占用1个可用单位（平分）*/
  grid-column-gap: 20px;  /*每个网格之间的列宽*/
  grid-row-gap: 20px;   /*每个网格之间的行宽*/
}
@media screen and (max-width: 980px) {
  .videos{
    grid-template-columns: repeat(2, 1fr);
  }
}
.video {
  position: relative;
  background-color: #252936;
  border-radius: 20px;
  overflow: hidden;
  transition: 0.4s;
}
.video-wrapper {
  position: relative;
}
.video-name {
  color: #fff;
  font-size: 16px;
  line-height: 1.4em;   /*设置元素的行高：为当前字体大小的1.4倍*/
  padding: 12px 20px 0;
  overflow: hidden;
  background-color: #252936;
  z-index: 9;
  position: relative;
  display: -webkit-box; /*这行代码设置了元素的显示类型为弹性布局，这使得其子元素可以使用弹性布局的属性。-webkit-box是旧版本的WebKit浏览器（如早期的Android和iOS浏览器）对弹性布局的实现。*/
  -webkit-line-clamp: 2; /*这行代码设置了元素的最大行数为2。超出这个数量的文本行将被隐藏。这是一个非标准的CSS属性，只在WebKit和Blink引擎的浏览器中有效（如Chrome和Safari）。*/
  -webkit-box-orient: vertical; /*这行代码设置了弹性布局的方向为垂直。这是一个非标准的CSS属性，只在WebKit和Blink引擎的浏览器中有效（如Chrome和Safari）。*/
}
.video-view {
  font-size: 12px;
  padding: 12px 20px 20px;
  background-color: #252936;
  position: relative;
}
.video-by {
  transition: 0.3s;
  padding: 20px 20px 0;
  display: inline-flex;   /*内联flex布局*/
  position: relative;
}
.video:hover img{
  transform: scale(1.6);  /*放大1.6倍*/
  transform-origin: center;  /*从中心开始*/
}
.video:hover .video-by {
  opacity: 0;
}
.video-author svg{
  background-color: #0aa0f7;
  color: #fff;
  border-color: #252936;
}
.videos img {
  max-width: 100%;
  width: 100%;
  border-radius: 20px 20px 0 0;
  display: block;
  cursor: pointer;
  transition: 0.4s;
}

.small-header {
  font-size: 24px;
  font-weight: 500;
  color: #fff;
  margin: 30px 0 20px;
}
::-webkit-scrollbar {
  width: 6px;
  border-radius: 10px;
}
::-webkit-scrollbar-thumb {
  background-color: rgb(21 20 26 / 63%);
  border-radius: 10px;
}
</style>
