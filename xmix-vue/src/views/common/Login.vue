<template >
  <div class="wrapper">
    <div class="container container-show" >
<!--    登陆页面-->
      <div class="login-box" @click="resetError" :class="{'animate_login':isActive}">
        <div class="apple-btn login-apple">
          <li class="red-btn"></li>
          <li class="yellow-btn"></li>
          <li class="green-btn"></li>
        </div>
      <div class="title">XMIX-Login</div>
      <div class="input">
        <span class="errInfo" v-if="errorInfo === ''"></span>
        <span class="errInfo" v-else>{{ errorInfo }}</span>
        <input type="text"  id="login-user" v-model="dataForm.username" placeholder="Input your username">
      </div>
      <div class="input">
        <input type="password" id="login-password" v-model="dataForm.password" placeholder="Input your password">
      </div>
      <div class="btn login-btn" @click="login">
        <span>登录</span>
      </div>
      <div class="login-change change-box" :style="dataForm.username || dataForm.password?{'height': '0px'}:{}">
        <div class="change-btn" @click="swichSignOrLogin">
          <span>去注册</span>
        </div>
      </div>
    </div>
<!--    注册页面-->
      <div class="sign-box" :class="{'animate_sign':isActive}">
        <div class="apple-btn sign-apple">
          <li class="red-btn"></li>
          <li class="yellow-btn"></li>
          <li class="green-btn"></li>
        </div>
        <div class="title">XMIX-Sign</div>
        <div class="input">
          <span class="errInfo" v-show="errorInfo === ''"></span>
          <span class="errInfo" v-show="errorInfo != ''">{{errorInfo}}</span>
          <input type="text" id="sign-user" v-model="dataForm.username" placeholder="Have A Good Name?">
        </div>
        <div class="input">
          <input type="password" id="sign-password" v-model="dataForm.password" placeholder="Keep Secret">
        </div>
        <div class="btn sign-btn" @click="registerUser">
          <span>注册</span>
        </div>
        <div class="change-box sign-change" :style="dataForm.username || dataForm.password?{'height': '0px'}:{}">
          <div class="change-btn toLogin" @click="swichSignOrLogin">
            <span>去登陆</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
//TODO 后续需要在前端加上用户名和密码的校验，错误信息的显示框已经做好
export default {
  name: "Login",
  data() {
    return{
      //错误信息
      errorInfo:'',
      //表单数据
      dataForm:{
        username:'',
        password:''
      },
      //注册登陆的切换
      isActive: false,

    }
  },
  methods: {
    resetError(){
      this.errorInfo = ''
    },
    //登陆与注册之间的切换
    swichSignOrLogin(){
      this.resetError()
      this.isActive = !this.isActive
    },
    //登陆操作
    login(){
      //重置错误
      this.resetError()
      //axios登陆
      this.request.post("/manager/role/login",this.dataForm).then(
          data => {
            //登陆成功之后需要将返回的数据进行存储以及页面的跳转
            if(data.code === 200){
              //1.将数据存入localStorage中
              localStorage.setItem("roleInfo",JSON.stringify(data.roleInfo))
              this.$router.push("/")
            }
          },
          err => {
            console.log(err)
            this.$message.error("请求失败！")
          }
      )
    },
    //注册操作
    registerUser(){
      //重置错误信息
      this.resetError()
      //axios注册
      this.request.post("/manager/role/register",this.dataForm).then(
          data => {
            if(data.code === 200){
              //注册成功
              //1.将数据存入localStorage中，暂时就不设置过期时间
              localStorage.setItem("roleInfo",JSON.stringify(data.roleInfo))
              this.$router.push("/")
            }
          },
          err => {
            console.log(err)
            this.$message.error("注册失败！")
          }
      )
    },
  },
};
</script>
<style scoped>
/**-----------------------**/
.errInfo{
  position: relative;
  width: 300px;
  height: 21px;
  bottom: 5px;   /**5**/
  left: 14px;
  color: red;
  opacity: 0;
  display: block;
  transition: 0.4s;
}
.container:hover .errInfo{
  bottom: 15px;
  opacity: 0.9;
  transition: .4s;

}
/**-----------------------**/

  .wrapper{
    height: 100vh;
    background-image: linear-gradient(120deg, #487eb0, #fbc531);
    overflow: hidden;
  }
  .container{
    position: absolute;
    height: 350px;
    width: 600px;
    background-color: rgba(255, 255, 255, .9);  /*设置透明度，透明度为0.9*/
    top: 50%;
    left: 50%;
    transform: translate(-50%, -30%); /*设置元素的位置*/
    border-radius: 10px;              /*设置边框的弧度*/
    box-shadow: 0px 0px 10px rgba(17, 39, 59, 0.8);/*设置box的阴影*/
    border: 1px solid rgba(17, 39, 59, 1); /*设置边框*/
    overflow: hidden;         /*超过边框的不显示*/
    box-sizing: border-box;  /*盒子模型，不用手动计算contend的值*/
    opacity: 0.5;             /*设置不透明度，背景色和文字都会受到影响*/
    transition: 1s;           /*设置组件出现的过渡时间*/
  }
  /*设置样式*/
  .container-show {
    transform: translate(-50%,-50%) ;
    opacity: 1;
  }
    /*鼠标放在该标签上，title触发样式:字体变大  与title的样式进行联动*/
  .container:hover .title {
    font-size: 20px;
    /* transform: translate(0,-30%); */
  }
  /*鼠标放在该标签上，btn触发样式*/
  .container:hover .btn {
    height: 30px;
    width: 90px;
    transform: translate(0,-30%);
    font-size: 12px;
  }
  /*鼠标放在该标签上，change-box触发样式*/
  .container:hover .change-box {
    height: 200px;
  }
  /*鼠标放在该标签上，change-btn触发样式*/
  .container:hover .change-btn {
    display: block;       /*此元素将显示为块级元素，此元素前后会带有换行符*/
  }
  /*鼠标放在该标签上，input触发样式:输入框向上移动30%*/
  .container:hover input {
    transform: translate(0, -30%);
  }
  /*设置login-box的样式*/
  .login-box {
    position: absolute;
    height: 100%;
    width: 100%;
    background-color: rgba(17, 39, 59, 0.8);
    transition: .4s;
    z-index: 1;    /*设置元素的堆叠顺序，值越大顺序越靠前（当元素重叠时，值越大的会显示的越前面）*/
    transform-origin: 0 100%;   /*允许您改变被转换元素的位置，设置旋转的原点位置*/
  }
  /*设置title元素的样式*/
  .title {
    margin-top: 10px;
    position: relative;
    height: 40px;
    width: 100%;
    font-size: 30px;
    display: flex;      /*弹性容器flex布局，默认值为row,水平排列*/
    justify-content: center;    /*当弹性布局中的组件未占满容器整个空间时，该样式能让容器中的所有组件居中*/
    align-items: center;        /*将弹性 <div> 元素的所有项目的居中对齐：*/
    text-transform: uppercase;  /*控制文本中内容的大小写*/
    font-weight: 500;
    letter-spacing: 3px;        /*字母的间距*/
    transition: .4s;            /*整个元素出现的过渡时间*/
  }
  /*和上面一起联动，设置title的颜色和阴影*/
  .login-box .title {
    color: white;
    text-shadow: 0px 0px 7px rgba(255, 255, 255, .9);
  }
  /*设置class为input的元素的样式*/
  .input {
    width: 400px;
    height: 45px;
    position: relative;
    margin: 50px auto;
    /* border-radius: 45px;
    overflow: hidden; */
  }
  /*设置input框的样式*/
  input {
    position: relative;
    width: 100%;
    height: 100%;
    border: none;
    outline: none;  /*绘制于元素周围的一条线，位于边框边缘的外围，可起到突出元素的作用*/
    /* box-sizing: border-box; */
    padding-left: 15px;   /*内部元素左边距*/
    font-size: 16px;
    background-color: rgba(255, 255, 255, 0.4);
    border-radius: 45px;  /*边框弧度*/
    transition: .4s;      /*元素过渡时间*/
  }
  /*样式的设置*/
  .login-box input {
    caret-color: white;  /*规定 input、textareas 或任何可编辑元素中的光标（插入符号）的颜色*/
    color: rgba(255, 255, 255, 0.8);
  }
  /*和上面一起联动*/
  .login-box input:focus {
    box-shadow: 0 0 10px rgba(1,1,1, .8);
  }
  /*设置class为btn 的样式*/
  .btn {
    height: 50px;
    width: 160px;
    position: relative;
    margin: -10px auto;
    background-color: rgba(0, 0, 0, 0.1);
    border-radius: 20px;
    color: rgba(255, 255, 255, .4);
    display: flex;
    justify-content: center;
    align-items: center;
    cursor: pointer;
    transition: .4s;
  }
  /*设置样式，与上面一起联动*/
  .change-box {
    position: absolute;
    height: 0px;
    width: 100%;
    clip-path: polygon(100% 50%, 50% 100%, 100% 100%);
    bottom: 0px;
    transition: .4s;
  }
  /*设置样式，与container联动*/
  .change-btn {
    position: absolute;
    bottom: 30px;
    right: 40px;
    font-size: 20px;
    display: none;
    font-weight: 500;
  }
  /*设置动画，与上面联动*/
  .change-btn:hover {
    text-shadow: 0px 0px 3px rgba(200, 200, 200, .8);
    cursor: pointer;
  }
  /*登陆按钮的动画*/
  .login-btn:hover {
    color: white;
    background-color: #57606f;
    box-shadow: rgba(0, 0, 0, 0.1);
    text-shadow: 0px 0px 2px rgba(0, 0, 0, 0.1);
  }
  /*设置样式*/
  .login-change {
    background-color: rgba(255, 255, 255, .8);
  }

  /* Mac 按钮样式及特效 */
  .apple-btn {
    position: absolute;
    height: 15px;
    width: 65px;
    top: 3px;
  }
  .apple-btn li{
    list-style: none;   /*设置列表图案*/
    position: relative;
    height: 15px;
    width: 15px;
    border-radius: 15px;
    opacity: 0;   /*不透明级别*/
  }
  .login-apple li{
    left: 5px;
    float: left;
  }
  li:nth-child(2) {
    margin: 0px 2px
  }
  .red-btn {
    background-color: red;
    transition: .3s;
    transform: translate(0, -50%);
  }
  .yellow-btn {
    background-color: orange;
    /* transition-delay: .2s; */
    transition: .6s;
    transform: translate(0, -50%);
  }
  .green-btn {
    background-color: rgb(15, 136, 15);
    /* transition-delay: .3s; */
    transition: .9s;
    transform: translate(0, -50%);
  }
  .container:hover .red-btn {
    opacity: 1;
    transform: translate(0,0);
  }
  .container:hover .yellow-btn {
    opacity: 1;
    transform: translate(0,0);
  }
  .container:hover .green-btn {
    opacity: 1;
    transform: translate(0,0);
  }

/*  切换的效果*/
  /*未知*/
  .animate_login {
    transform: rotate(-90deg);
  }
  .animate_sign {
    transform: rotate(0deg) !important;  /*!important  设置样式优先级，此处为最高*/
  }

  /* 注册框样式 */
  .sign-box {
    position: absolute;
    height: 100%;
    width: 100%;
    background-color: rgba(255, 255, 255, .8);
    transform-origin: 0 100%;
    transform: rotate(90deg);
    transition: .4s;
    z-index: 1;
  }

  .sign-box  .title {
    text-shadow: 0 0 10px rgba(200, 200, 200, .8);
    font-weight: 500;
  }

  .sign-change {
    background-color: rgba(17, 39, 59, 0.8);
  }

  .toLogin {
    color: white;
  }

  .sign-box input {
    box-shadow: 0 0 3px black;
  }

  .sign-box .btn {
    color: #1e90ff;
    background-color: rgba(200, 200, 200, .1);
    transition:.5s;
  }

  .sign-box .btn:hover {
    color: white;
    background-color: #1e90ff;
  }
  .sign-apple li {
    right: 5px;
    float: right;
  }

  .sign-apple {
    right: 5px;
  }
</style>