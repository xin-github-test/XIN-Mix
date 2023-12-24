<template>
    <div style="background-color:black;color: white">hello</div>
</template>

<script>
export default {
  name: 'test'
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
.sidebar {
  width: 220px;
  height: 100%;
  padding: 30px;
  display: flex;
  flex-direction: column;
  flex-shrink: 0;  /*属性固定在相同的容器中，项目相对于其余弹性项目的收缩量,值越小宽度越大*/
  transition-duration: 0.2s; /*过渡的时间*/
  overflow-y: auto;   /*设置x轴内容溢出的内容的处理方式：此处为，若内容溢出则提供滚轮*/
  overflow-x: hidden;  /*设置x轴内容溢出的内容的处理方式：此处为隐藏*/
}
/*通过Js控制是否显示*/
.sidebar .logo{
  display: none;
  width: 30px;
  height: 30px;
  background-color: #22b07d;
  flex-shrink: 0;
  color: #fff;
  align-items: center;
  border-radius: 50%;
}
.logo-expand {
  text-decoration: none;  /*去除下划线*/
  color: #fff;
  font-size: 19px;
  font-weight: 600;
  line-height: 34px;
  position: sticky;   /*将元素的定位类型设置为sticky，该元素在滚动到一定位置时会固定在屏幕上*/
  top: 0;             /*设置上面滚动的距离，和sticky一起使用：此处表示该元素滚动到距离顶部0px时会被固定住*/
}
.logo-expand:before{
  content: "";
  position: absolute;
  top: -30px;
  left: 0;    /*以上俩行用于设置元素的左上角的位置*/
  background: #1f1d2b;
  width: 200px;
  height: 70px;
  z-index: -1;  /*在当前元素logo-expand的下方*/
}
.sidebar-link:hover,
.sidebar-link .is-active{
  color: #fff;
  font-weight: 600;
}
.sidebar-link:hover:nth-child(2n + 1) svg,
.sidebar-link .is-active:nth-child(2n + 1) svg{
  background: #ff7551;
}
.sidebar-link:hover:nth-child(2n) svg,
.sidebar-link .is-active:nth-child(2n) svg{
  background: #32a7e2;
}
.sidebar-link:hover:nth-child(2n+3) svg,
.sidebar-link .is-active:nth-child(2n+3) svg{
  background: #6c5ecf;
}
@keyframes bottom {   /*定义动画*/
  0% {
    transform: translateY(100px);   /*初始状态*/
    opacity: 0;
  }

  100% {
    opacity: 1;
    transform: none;      /*结束状态*/
  }
}
.side-menu {
  display: flex;
  flex-direction: column;
}
.side-menu a{
  display: flex;
  align-items: center;
  text-decoration: none;
  color: #808191;
}
.side-menu a:not(:first-child) {
  margin-top: 26px;
}
.side-menu svg {
  width: 30px;
  padding: 8px;
  border-radius: 10px;
  background-color: #353340;
  flex-shrink: 0;   /*元素不会因为容器大小而变化尺寸*/
  margin-right: 16px;
}
.side-menu svg:hover {
  color: #fff;
}
.side-title {
  font-size: 12px;
  letter-spacing: 0.07em;
  margin-bottom: 24px;
}
.side-wrapper {
  border-bottom: 1px solid rgb(128 129 145 / 24%);
  padding: 36px 0;
  width: 145px;
}
.side-wrapper + .side-wrapper {
  border-bottom: none;
}
.wrapper {
  display: flex;
  flex-direction: column;
  flex-grow: 1;
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
.main-blog + .main-blog .author-img {
  border-color: rgb(255 255 255 / 75%);
  margin-top: 14px;
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
.main-blog__time {
  background: rgba(21, 13, 13, 0.44);
  color: #fff;
  padding: 3px 8px;
  font-size: 12px;
  border-radius: 6px;
  position: absolute;
  right: 20px;
  bottom: 20px;
}
.author-img {
  width: 52px;
  height: 52px;
  border: 1px solid rgb(255 255 255 / 75%);
  padding: 4px;
  border-radius: 50%;
  object-fit: cover;
}
.author-img__wrapper {
  position: relative;
  flex-shrink: 0;
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
  font-size: 15px;
  color: black;
  font-weight: 600;
  margin-bottom: 8px;
}
.author-info {
  font-size: 13px;
  font-weight: 400;
  color: black;
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

.video-by.offline:before {
  background-color: #ff7551;
}
.video-time {
  position: absolute;
  background: rgba(21, 13, 13, 0.44);
  color: rgb(255 255 255 / 85%);
  padding: 3px 8px;
  font-size: 12px;
  border-radius: 6px;
  top: 10px;
  z-index: 1;
  right: 8px;
}
.video:hover img{
  transform: scale(1.6);  /*放大1.6倍*/
  transform-origin: center;  /*从中心开始*/
}
.video:hover .video-time {
  display: none;
}
.video:hover .video-author {
  bottom: -65px;
  transform: scale(0.6);
  right: -3px;
  z-index: 10;
}
.video:hover .video-by {
  opacity: 0;
}
.video-author {
  position: absolute;
  right: 10px;
  transition: 0.4s;
  bottom: -25px;
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
.stream-area {
  display: none;
}
@media screen and (max-width: 940px) {
  .stream-area{
    flex-direction: column;
  }
}
@media screen and (max-width: 940px) {
  .stream-area .video-stream {
    width: 100%;
  }
}
@media screen and (max-width: 940px) {
  .stream-area .chat-stream {
    margin-left: 0;
    margin-top: 30px;
  }
}
@media screen and (max-width: 940px) {
  .stream-area .video-js.vjs-fluid {
    min-height: 250px;
  }
}

@media screen and (max-width: 940px) {
  .stream-area .msg__content {
    max-width: 100%;
  }
}
.show .stream-area {
  display: flex;
}
.show .main-header,
.show .main-blogs,
.show .small-header,
.show .videos {
  display: none;
}
.video-stream {
  width: 65%;
  object-fit: cover;
  transition: 0.3s;
}
.video-stream:hover .video-js .vjs-big-play-button {
  opacity: 1;
}
.video-p {
  margin-right: 12px;
  object-fit: cover;
  flex-shrink: 0;
  border-radius: 50%;
  position: relative;
  top: 0;
  left: 0;
}
.video-p .author-img {
  border: 0;
}
.video-p-wrapper {
  display: flex;
  align-items: center;
}
.video-p-wrapper .author-img {
  border: 0;
}
.video-p-wrapper svg {
  width: 20px;
  padding: 4px;
}
@media screen and (max-width: 650px) {
  .video-p-wrapper{
    flex-direction: column;
  }
}
@media screen and (max-width: 650px) {
  .video-p-wrapper .button-wrapper {
    margin: 20px auto 0;
  }
}
@media screen and (max-width: 650px) {
  .video-p-wrapper .video-p-detail {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
}
@media screen and (max-width: 650px) {
  .video-p-wrapper .video-p {
    margin-right: 0
  }
}
.video-p-sub {
  font-size: 12px;
}
.video-p-title {
  font-size: 24px;
  color: #fff;
  line-height: 1.4em;
  margin: 16px 0 20px;
}
.video-p-subtitle {
  font-size: 14px;
  line-height: 1.5em;
  max-width: 60ch;
}
.video-p-subtitle + .video-p-subtitle{
  margin-top: 16px;
}
.video-p-name {
  margin-bottom: 8px;
  color: #fff;
  display: flex;
  align-items: center;
}
.video-p-name:after{
  content: "";
  width: 6px;
  height: 6px;
  background-color: #22b07d;
  border-radius: 50%;
  margin-left: 8px;
  display: inline-block;
}
.video-p-name.offline:after {
  background-color: #ff7551;
}
.video-content {
  width: 100%;
}
.button-wrapper {
  display: flex;
  align-items: center;
  margin-left: auto;
}
.like {
  display: flex;
  align-items: center;
  background-color: #353340;
  color: #fff;
  border: 0;
  font-family: "Inter", sans-serif;
  border-radius: 8px;
  padding: 10px 16px;
  font-size: 14px;
  cursor: pointer;
}
.like.red {
  background-color: #ea5f5f;
}
.like svg {
  width: 18px;
  flex-shrink: 0;
  margin-right: 10px;
  padding: 0;
}
.like + .like{
  margin-left: 16px;
}
.video-stats {
  margin-left: 30px;
}

.video-detail {
  display: flex;
  margin-top: 30px;
  width: 100%;
}
.chat-header {
  display: flex;
  align-items: center;
  padding: 20px 0;
  font-size: 16px;
  font-weight: 600;
  color: #fff;
  position: sticky;
  top: 0;
  background-color: #252836;
  left: 0;
  z-index: 1;
  border-bottom: 1px solid rgb(128 129 145 / 24%);
}
.chat-header svg {
  width: 15px;
  margin-right: 6px;
  flex-shrink: 0;
}
.chat-header span {
  margin-left: auto;
  color: #808191;
  font-size: 12px;
  display: flex;
  align-items: center;
}
.chat-stream {
  flex-grow: 1;
  margin-left: 30px;
}
.chat {
  background-color: #252836;
  border-radius: 20px;
  padding: 0 20px;
  max-height: 414px;
  overflow: auto;
}
.chat-footer {
  display: flex;
  align-items: center;
  position: sticky;
  bottom: 0;
  left: 0;
  width: calc(100% + 20px);
  padding-bottom: 12px;
  background-color: #252836;
}
.chat-footer input {
  width: 100%;
  border: 0;
  background-color: #2d303e;
  border-radius: 20px;
  font-size: 12px;
  color: #fff;
  margin-left: -10px;
  padding: 12px 40px;
  font-weight: 500;
  font-family: "Inter", sans-serif;
  background-image: url("@/assets/svgIcon2.svg");
  background-repeat: no-repeat;
  background-size: 24px;
  background-position: 8px;
}
.chat-footer input::placeholder{
  color: #6c6e78;
}
.chat-footer:before {
  content: "";
  position: absolute;
  background-image: url("@/assets/svgIcon3.svg");
  background-repeat: no-repeat;
  background-size: 14px;
  background-position: center;
  width: 18px;
  height: 18px;
  background-color: #6c5ecf;
  padding: 4px;
  border-radius: 50%;
  right: 16px;
}
/*下面的样式还没有看*/
.chat-vid__title {
  color: #fff;
  font-size: 18px;
}
.chat-vid__container {
  margin-top: 40px;
}
.chat-vid__wrapper {
  display: flex;
  align-items: center;
  margin-top: 26px;
}
.chat-vid__name {
  color: #fff;
  font-size: 14px;
  line-height: 1.3em;
  display: -webkit-box;
  -webkit-line-clamp: 2;
  overflow: hidden;
  -webkit-box-orient: vertical;
}
.chat-vid__img {
  width: 100px;
  height: 80px;
  border-radius: 10px;
  object-fit: cover;
  object-position: right;
  margin-right: 16px;
  transition: 0.3s;
}
.chat-vid__img:hover{
  transform: scale(1.02);
}
.chat-vid__content {
  max-width: 20ch;
}
.chat-vid__by,
.chat-vid__info {
  color: #808191;
  font-size: 13px;
}
.chat-vid__by {
  margin: 6px 0;
}
.chat-vid__button {
  background-color: #6c5ecf;
  border: 0;
  color: #fff;
  font-size: 13px;
  margin-top: 26px;
  display: flex;
  padding: 0 10px;
  align-items: center;
  justify-content: center;
  height: 40px;
  border-radius: 10px;
  cursor: pointer;
  transition: 0.3s;
}
.chat-vid__button:hover {
  background-color: #5847d0;
}
.message {
  display: flex;
  align-items: center;
  margin-top: 18px;
}
.message:last-child {
  margin-bottom: 18px;
}
.message-container .author-img__wrapper svg {
  width: 15px;
}
.msg__name {
  font-size: 13px;
}
.msg__content {
  line-height: 1.4em;
  max-width: 26ch;
  display: -webkit-box;
  overflow: hidden;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
}
.small-header {
  font-size: 24px;
  font-weight: 500;
  color: #fff;
  margin: 30px 0 20px;
}
/*player*/
.video-js .vjs-control-bar {
  display: flex;
  align-items: center;
}
.vjs-poster {
  background-size: 150%;
}
.video-js .vjs-control-bar {
  width: 100%;
  position: absolute;
  bottom: 14px;
  padding-left: 36px;
  left: 14px;
  width: calc(100% - 28px);
  right: 0;
  border-radius: 10px;
  height: 4em;
  background-color: #2b333f;
  background-color: rgba(43, 51, 63, 0.7);
}
@media screen and (max-width: 625px) {
  .video-js .vjs-control-bar{
    padding-left: 0;
  }
}
.video-js:hover .vjs-big-play-button {
  background-color: rgba(43, 51, 63, 0.5);
}
.video-js .vjs-big-play-button {
  transition: 0.3s;
  opacity: 0;
  border: 0;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
.video-js .vjs-big-play-button:hover {
  background-color: rgba(43, 51, 63, 0.7);
  border-color: transparent;
}
.vjs-play-control:after {
  content: "LIVE";
  position: absolute;
  left: -66px;
  top: 7px;
  background-color: #8941e3;
  height: 24px;
  font-family: "Inter", sans-serif;
  font-size: 10px;
  padding: 0 12px 0 26px;
  display: flex;
  font-weight: 700;
  letter-spacing: 0.03em;
  align-items: center;
  border-radius: 6px;
  background-image: url("@/assets/icon4.svg");
  background-repeat: no-repeat;
  background-size: 6px;
  background-position: 12px;
}
@media screen and (max-width: 625px) {
  .vjs-play-control:after{
    display: none;
  }
}
.vjs-menu-button-inline .vjs-menu {
  top: 4px;
}
.video-js .vjs-control:before,
.video-js .vjs-time-control {
  line-height: 40px;
}
.video-js .vjs-tech {
  object-fit: cover;
}
button.vjs-play-control.vjs-control.vjs-button {
  margin-left: 40px;
}
@media screen and (max-width: 625px) {
  button.vjs-play-control.vjs-control.vjs-button {
    margin-left: 0;
  }
}
.vjs-icon-fullscreen-enter:before,
.video-js .vjs-fullscreen-control:before {
  content: "";
  position: absolute;
  display: block;
  background-image: url("@/assets/icon5.svg");
  background-size: 11px;
  background-position: center;
  background-position-y: 14px;
  background-repeat: no-repeat;
  opacity: 0.6;
}
.vjs-playback-rate .vjs-playback-rate-value {
  font-size: 1.1em;
  line-height: 3.5em;
  opacity: 0.6;
  font-weight: 700;
  font-family: "Inter", sans-serif;
}
.video-js .vjs-playback-rate {
  width: 2.2em;
}
.video-js.vjs-fluid {
  border-radius: 20px;
  overflow: hidden;
  min-height: 414px;
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
::-webkit-scrollbar {
  width: 6px;
  border-radius: 10px;
}

::-webkit-scrollbar-thumb {
  background-color: rgb(21 20 26 / 63%);
  border-radius: 10px;
}
/*这段CSS是由js控制的*/
.sidebar .collapse{
  width: 90px;
  border-right: 1px solid rgb(128 129 145 / 24%);
}
/*只有当通过Js添加了collapse这个选择器的时候，才加上下面的样式*/
/*.logo-expand,
.side-title {
  display: none;
}
.logo {
  display: flex;
}
.side-wrapper {
  width: 30px;
}
.side-menu svg {
  margin-right: 30px;
}*/

</style>