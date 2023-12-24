<template>
  <div class="wrapper">
    <div ref="overlay-navigation" class="overlay-navigation" :class="overlayNavigationClass">
      <nav  role="navigation">
        <ul>
          <li ref="li0"><a ref="a0" @click="toRoleManagement" data-content="角色管理">Role Management</a></li>
          <li ref="li1"><a ref="a1" @click="toPersonalBlog" data-content="个人博客">PersonalBlog</a></li>
          <li ref="li2"><a ref="a2" href="#" data-content="技能">Skills</a></li>
          <li ref="li3"><a ref="a3" href="#" data-content="工作">Works</a></li>
          <li ref="li4"><a ref="a4" href="#" data-content="联系作者">Contact</a></li>
        </ul>
      </nav>
    </div>

    <section class="home">
      <a>
        <span class="HelloWords" @click="toLogin" v-if="!dataForm.nickname">[请登陆]</span>
        <el-dropdown v-else
                     size="medium"
                     placement="bottom"
                     @command="handleCommand">
            <span class="el-dropdown-link HelloWords">
              你好, {{dataForm.nickname}}<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-avatar class="avatar-home" shape="square" :size="50" :src="userCenter.roleVo.avatarUrl"></el-avatar>
          <el-dropdown-menu>
            <el-dropdown-item command="userCenter">个人中心</el-dropdown-item>
            <el-dropdown-item divided disabled command="updatePwd">修改密码</el-dropdown-item>
            <el-dropdown-item divided command="logOut">注销</el-dropdown-item>
          </el-dropdown-menu>
        </el-dropdown>
      </a>
      <div  @click="changeStyle" class="open-overlay" :style="openOverlayCss">
        <span class="bar-top" :class="barTopClass"></span>
        <span class="bar-middle" :class="barMiddleClass"></span>
        <span class="bar-bottom" :class="barBottomClass"></span>
      </div>
      <div class="myDiv">
        <ul>
          <li>作者:xxxx</li>
          <li>创建日期:xxxxxxx</li>
          <li>作者介绍:xxx.com</li>
          <li>项目介绍:xxxxx</li>
        </ul>
      </div>
    </section>

    <el-drawer
        v-if="userCenter.id"
        :visible.sync="personalCenterVisible"
        direction="ltr"
        :before-close="handleClose">
      <div slot="title" style="display: flex">
        <div class="left">
          <el-avatar class="avatar" shape="square" :size="50" :src="userCenter.roleVo.avatarUrl"></el-avatar>
          <span style="margin-left: 30px;color: #1ee2e7;width: 50px;height: 50px">管理员</span>
        </div>

      </div>
      <el-divider style="margin: 0px"></el-divider>
      <div class="displayInfo" style="width: 500px;height: 550px;">
        <span class="title">用户信息</span>
        <span class="info"><i class="el-icon-user"></i>用&nbsp;户&nbsp;名：<span style="display: inline;color: #fccdaf;font-size: 22px">{{ userCenter.roleVo.username }}</span></span>
        <span class="info"><i class="el-icon-s-flag"></i>备&nbsp;&nbsp;&nbsp;&nbsp;注：<span style="display: inline;color: #fccdaf;font-size: 22px">{{ userCenter.roleVo.nickname }}</span></span>
        <span class="info"><i class="el-icon-mobile-phone"></i>电&nbsp;&nbsp;&nbsp;&nbsp;话：<span style="display: inline;color: #fccdaf;font-size: 22px">{{ userCenter.telephone }}</span></span>
        <span class="info"><i class="el-icon-message"></i>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：<span style="display: inline;color: #fccdaf;font-size: 22px">{{ userCenter.email }}</span></span>
        <span class="info"><i class="el-icon-office-building"></i>居&nbsp;住&nbsp;地：<span style="display: inline;color: #fccdaf;font-size: 22px">{{ userCenter.address }}</span></span>
        <span class="info"><i class="el-icon-house"></i>联系地址：<span style="display: inline;color: #fccdaf;font-size: 22px">{{ userCenter.contactAddress }}</span></span>
      </div>
      <div class="bottom"
           style="display: flex"
      >
      <button class="ui-btn" style="margin-left: 32px"
              @click="editInfo"
      >
        <span>
          修&nbsp;改
        </span>
      </button>
      <button class="ui-btn" style="margin-left: 250px"
              @click="handleCommand('logOut')"
      >
        <span>
          注&nbsp;销
        </span>
      </button>
      </div>
    </el-drawer>

<!--    对话框:需要注意的问题，最开始注册的时候用户并没有填写这些信息-->
    <el-dialog
        v-if="dialogVisible"
        title="个人信息"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose">
      <el-card class="box-card"
               shadow="hover"
      >
        <div slot="header" class="clearfix">
          <el-upload
              class="avatar-uploader"
              :action="uploadUrl+this.dataForm.id"
              :show-file-list="false"
              :on-success="handleAvatarSuccess"
              :before-upload="beforeAvatarUpload">
            <img v-if="userCenter.roleVo.avatarUrl" :src="userCenter.roleVo.avatarUrl" class="avatar-img">
            <i v-else class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </div>
<!--        管理员的效果和渲染需要改-->
        <div class="displayInfo-dialog" style="width: 100%;height: 100%;">
          <span class="info-dialog"><i class="el-icon-user"></i>用&nbsp;户&nbsp;名：<span style="display: inline;color: black;font-size: 22px">{{ updateUserInfo.roleVo.username }}</span></span>
          <span class="info-dialog"><i class="el-icon-s-flag"></i>备&nbsp;&nbsp;&nbsp;&nbsp;注：<span style="display: inline;color: black;font-size: 22px">{{ updateUserInfo.roleVo.nickname }}</span></span>
          <span class="info-dialog"><i class="el-icon-mobile-phone"></i>电&nbsp;&nbsp;&nbsp;&nbsp;话：<input v-model="updateUserInfo.telephone" class="input-dialog"/></span>
          <span class="info-dialog"><i class="el-icon-message"></i>邮&nbsp;&nbsp;&nbsp;&nbsp;箱：<input v-model="updateUserInfo.email" class="input-dialog"/></span>
          <span class="info-dialog"><i class="el-icon-office-building"></i>居&nbsp;住&nbsp;地：
            <el-cascader
              v-model="updateUserInfo.address"
              :options="options"
              @change="handleChange"></el-cascader>
          </span>
          <span class="info-dialog"><i class="el-icon-house"></i>联系地址：<textarea v-model="updateUserInfo.contactAddress" class="textarea-dialog"></textarea></span>
        </div>
      </el-card>
      <span slot="footer" class="dialog-footer">
        <el-button @click="cancel">取 消</el-button>
        <el-button type="primary" @click="updateInfo()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
import locationJson from '@/assets/locationJson/location.json'
import {serverIp} from '/public/config'
import {serverPort} from '/public/config'
export default {
  name: "Home",
  data(){
    return{
      uploadUrl: 'http://'+serverIp+':'+serverPort+'/manager/file/upload/',
      //修改个人信息的对话框
      options: [],
      updateUserInfo: {
        address: []
      },
      //个人中心的数据
      userCenter:{
        roleVo:{
          avatarUrl:''
        }
      },
      //数据
      dataForm:{},
      //样式
      overlayNavigationClass:{
        'overlay-active':false,
        'overlay-slide-up':false,
        'overlay-slide-down':false
      },
      barTopClass:{
        'animate-out-top-bar':false,
        'animate-top-bar':false
      },
      barMiddleClass:{
        'animate-out-middle-bar':false,
        'animate-middle-bar':false
      },
      barBottomClass:{
        'animate-out-bottom-bar':false,
        'animate-bottom-bar':false
      },
      openOverlayCss:{},
      //个人中心的显示
      personalCenterVisible: false,
      //个人中心修改框的展示
      dialogVisible: false
    }
  },
  //钩子函数
  created() {
    //取出localStorage中保存的用户信息
    let res = localStorage.getItem("roleInfo");
    const userInfo = res ? JSON.parse(res):{}
    this.loadUserInfo(userInfo.id)
    this.dataForm = userInfo
    //读取本地json文件，生成城市位置
    const locations = locationJson
    this.listToTree(locations)
  },
  methods:{
    //userInfo的获取
    loadUserInfo(userInfoId){
      //获取userCenter的信息
      if(userInfoId){
        this.request.get("/manager/role/info/"+userInfoId).then(
            res => {
              if(res.code === 200){
                this.userCenter = res.data
              }
            }
        )
      }
    },
    //地址的选择
    handleChange(value) {
      console.log(value);
    },
    toRoleManagement(){
      this.$router.push('/RoleManagement')
    },
    toPersonalBlog(){
      this.$router.push('/Blog')
    },
    toLogin(){
      this.$router.push('/login')
    },
    //样式的改变
    changeStyle(){
      this.openOverlayCss = {'pointer-events':'none'}
      // overlay_navigation 是否有overlay-active class属性，有则去掉，无则添加
      this.overlayNavigationClass["overlay-active"] = !this.overlayNavigationClass["overlay-active"]
      if(this.overlayNavigationClass["overlay-active"]){

        this.barTopClass["animate-out-top-bar"] = false
        this.barTopClass["animate-top-bar"] = true

        this.barMiddleClass["animate-out-middle-bar"] = false
        this.barMiddleClass["animate-middle-bar"] = true

        this.barBottomClass["animate-out-bottom-bar"] = false
        this.barBottomClass["animate-bottom-bar"] = true

        Velocity(
            this.$refs["overlay-navigation"],
            'transition.slideLeftIn',
            {
              duration: 300,
              delay: 0,
              begin: () => {
                Velocity(
                    [Object.values(this.$refs)[1],Object.values(this.$refs)[3],Object.values(this.$refs)[5],Object.values(this.$refs)[7],Object.values(this.$refs)[9]],
                  'transition.perspectiveLeftIn',
                  {
                  stagger: 150,
                  delay: 0,
                  complete: () => {
                    Velocity(
                        [Object.values(this.$refs)[0],Object.values(this.$refs)[2],Object.values(this.$refs)[4],Object.values(this.$refs)[6],Object.values(this.$refs)[8]],
                        {
                      opacity: [1, 0],
                   },
                    {
                      delay: 10,
                      duration: 140
                    });
                    this.openOverlayCss = {'pointer-events':'auto'}
                  }
                })
              }
            }
        )
      }
      else{
        this.openOverlayCss = {'pointer-events':'none'}

        this.barTopClass["animate-top-bar"] = false
        this.barTopClass["animate-out-top-bar"] = true

        this.barMiddleClass["animate-middle-bar"] = false
        this.barMiddleClass["animate-out-middle-bar"] = true

        this.barBottomClass["animate-bottom-bar"] = false
        this.barBottomClass["animate-out-bottom-bar"] = true


        Velocity(
            [Object.values(this.$refs)[1],Object.values(this.$refs)[3],Object.values(this.$refs)[5],Object.values(this.$refs)[7],Object.values(this.$refs)[9]],
            'transition.perspectiveRightOut',
            {
              stagger: 150,
              delay: 0,
              complete: () => {
                Velocity(
                    this.$refs["overlay-navigation"],
                    'transition.fadeOut',
                    {
                      delay: 0,
                      duration: 300,
                      complete: () => {
                        Velocity(
                            [Object.values(this.$refs)[0],Object.values(this.$refs)[2],Object.values(this.$refs)[4],Object.values(this.$refs)[6],Object.values(this.$refs)[8]],
                            {
                              opacity: [0, 1],
                            },
                            {
                              delay: 0,
                              duration: 50
                            });
                        this.openOverlayCss = {'pointer-events':'auto'}
                      }
                });
              }
            }
                )

      }
    },
    //注销以及个人中心
    handleCommand(command){
      if(command === 'logOut'){
        //注销
        localStorage.removeItem("roleInfo")
        this.personalCenterVisible = false
        //重新刷新页面
        location.reload()
      }else if(command === 'updatePwd'){
        //TODO 修改密码 (使用steps步骤条)
      }else{
        //进入个人中心
        this.personalCenterVisible = true
      }
    },
    //个人中心关闭后的处理
    handleClose(done){
      this.updateUserInfo = {}
      done()
    },
    //修改个人中心的信息
    editInfo(){
      this.updateUserInfo = {...this.userCenter}
      //将 '北京市-市辖区-朝阳区-三里屯街道' 转成下面的格式
      //['北京市', '市辖区', '朝阳区', '三里屯街道']
      this.updateUserInfo.address = this.userCenter.address.split('-')
      this.personalCenterVisible = false
      this.dialogVisible = true
    },
    cancel(){
      this.updateUserInfo = {}
      this.dialogVisible = false
    },
    //修改信息
    updateInfo(){
      let info = {...this.updateUserInfo}
      info.address = info.address.join("-")
      this.request.post("/manager/role/info/",info).then(
          res => {
            if(res.code === 200){
              this.loadUserInfo(this.updateUserInfo.roleVo.id)
              this.$message.success("修改成功！")
              this.updateUserInfo = {}
              this.dialogVisible = false
            }
          }
      )
    },
    //对话框相关(头像)
    async handleAvatarSuccess(res, file) {
      if(res.code === 200){
        let newUser = {
          'id':this.userCenter.roleVo.id,
          'avatarUrl':res.data
        }
        //修改头像
        const code = await this.request.post("/manager/role/update",newUser).then(
            res => {
              if(res.code === 200){
                this.$message.success("头像上传成功！")
                return res.code
              }
            }
        )
        if(code === 200){
          this.userCenter.roleVo.avatarUrl = res.data
        }
      }
    },
    listToTree(listData){
      //TODO 整体的复杂度应该可以优化
      //将所有的parentid进行聚合去重(整体遍历1次)（1）
      let noDupDataList =new Set(listData.map(data => data.parentid))
      //整体排序1次（2）
      let noDupSortedDataList = Array.from(noDupDataList).sort()
      //按照parentid进行分类
      let dataObj = {}
      //对去重后的数据进行遍历1次（3）
      noDupSortedDataList.forEach(data => dataObj[data]=[])
      //对整体数据进行遍历第2次（4）
      listData.map(data => dataObj[data.parentid].push({"id":data.id,"value":data.name,"label":data.name,"children":[]}))
      //整合数据
      let keys = Object.keys(dataObj);
      //执行完之后，dataObj['0']就是需要的树状菜单(递归1次)
      this.getChildren(dataObj['0'],keys,dataObj)
      this.options = dataObj['0']

    },
    getChildren(nodeList,keys,dataObj){
        nodeList.map(data => {
        if(keys.includes(data.id)){
          data.children = dataObj[data.id]
          this.getChildren(dataObj[data.id],keys,dataObj)
        }else{
          delete data.children
        }
        return data
      })
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg';
      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!');
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!');
      }
      return isJPG && isLt2M;
    }
  }
}
</script>
<style scoped>
/*素材库按钮---------------------------*/
.ui-btn {
  --btn-default-bg: rgb(41 41 41);
  --btn-padding: 15px 20px;
  --btn-hover-bg: rgb(51, 51, 51);
  --btn-transition: .3s;
  --btn-letter-spacing: .1rem;
  --btn-animation-duration: 1.2s;
  --btn-shadow-color: rgba(0, 0, 0, 0.137);
  --btn-shadow: 0 2px 10px 0 var(--btn-shadow-color);
  --hover-btn-color: #FAC921;
  --default-btn-color: #fff;
  --font-size: 16px;
  /* 👆 this field should not be empty */
  --font-weight: 600;
  --font-family: Menlo,Roboto Mono,monospace;
  /* 👆 this field should not be empty */
}

/* button settings 👆 */

.ui-btn {
  box-sizing: border-box;
  padding: var(--btn-padding);
  border-radius: 20px;
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--default-btn-color);
  font: var(--font-weight) var(--font-size) var(--font-family);
  background: var(--btn-default-bg);
  border: none;
  cursor: pointer;
  transition: var(--btn-transition);
  overflow: hidden;
  box-shadow: var(--btn-shadow);
}

.ui-btn span {
  letter-spacing: var(--btn-letter-spacing);
  transition: var(--btn-transition);
  box-sizing: border-box;
  position: relative;
  background: inherit;
}

.ui-btn span::before {
  box-sizing: border-box;
  position: absolute;
  content: "";
  background: inherit;
}

.ui-btn:hover, .ui-btn:focus {
  background: var(--btn-hover-bg);
}

.ui-btn:hover span, .ui-btn:focus span {
  color: var(--hover-btn-color);
}

.ui-btn:hover span::before, .ui-btn:focus span::before {
  animation: chitchat linear both var(--btn-animation-duration);
}

@keyframes chitchat {
  0% {
    content: "#";
  }

  5% {
    content: ".";
  }

  10% {
    content: "^{";
  }

  15% {
    content: "-!";
  }

  20% {
    content: "#$_";
  }

  25% {
    content: "№:0";
  }

  30% {
    content: "#{+.";
  }

  35% {
    content: "@}-?";
  }

  40% {
    content: "?{4@%";
  }

  45% {
    content: "=.,^!";
  }

  50% {
    content: "?2@%";
  }

  55% {
    content: "\;1}]";
  }

  60% {
    content: "?{%:%";
    right: 0;
  }

  65% {
    content: "|{f[4";
    right: 0;
  }

  70% {
    content: "{4%0%";
    right: 0;
  }

  75% {
    content: "'1_0<";
    right: 0;
  }

  80% {
    content: "{0%";
    right: 0;
  }

  85% {
    content: "]>'";
    right: 0;
  }

  90% {
    content: "4";
    right: 0;
  }

  95% {
    content: "2";
    right: 0;
  }

  100% {
    content: "";
    right: 0;
  }
}
/*素材库按钮---------------------------*/
/*自定义的样式-------------------------*/
.info{
  color: #C96567;
  font-family: 楷体;
  display: block;
  margin-left: 30px;
  margin-top: 40px;
  font-size: 25px;
}
.info-dialog{
  color: #C96567;
  font-family: 楷体;
  display: block;
  /*margin-left: 60px;*/
  margin-top: 25px;
  font-size: 20px;
  width: 100%;
}
.input-dialog{
  display: inline;
  font-size: 20px;
  font-family: 楷体;
}
.textarea-dialog{
  display: block;
  font-size: 15px;
  width: 100%;
  font-family: 楷体;
}
.displayInfo .title{
  display: block;
  text-align: center;
  font-family: 楷体;
  color: white;
  font-size: 30px;
}
.avatar-img{
  width: 80px;
  height: 80px;
}
.avatar-uploader{
  text-align: center;
}
/*自定义的样式-------------------------*/
.avatar-home{
  transform: translate(20%,30%);
}
.avatar-uploader .avatar-uploader-icon {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .avatar-uploader-icon:hover {
  border-color: #409EFF;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 80px;
  height: 80px;
  line-height: 80px;
  text-align: center;
}
.myDiv{
  width: 600px;
  font-size: 20px;
  line-height: 2;
  transform: translate(12%,200%);
  opacity: 0;
  transition: 1s;
}
.myDiv:hover{
  opacity: 0.5;
  transition: 1s;
}
/*@import url('https://fonts.googleapis.com/css?family=Work+Sans:400,300,700|Open+Sans:400italic,300italic');*/
.home {
  width: 100%;
  height: 100vh;
  position: relative;
  background-image: url("@/assets/homebg.jpg");
  background-size: cover;
  background-position: center center;
}
.HelloWords{
  font-size: 20px;
  cursor: pointer;
}
/* ====================================
Navigation
==================================== */

.overlay-navigation {
  position: fixed;
  z-index: 99;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: hsla(0, 0%, 100%, 0.2);
  display: none;
  opacity: 0;
}

nav,
nav ul {
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
}

nav ul {
  display: -webkit-box;
  display: -webkit-flex;
  display: -ms-flexbox;
  display: flex;
  list-style: none;
}

nav ul li {
  -webkit-flex-basis: 20%;
  -ms-flex-preferred-size: 20%;
  flex-basis: 20%;
  -webkit-box-pack: center;
  -webkit-justify-content: center;
  -ms-flex-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  -webkit-align-items: center;
  -ms-flex-align: center;
  align-items: center;
  height: 100%;
  overflow: hidden;
  opacity: 0;
  display: none;
}

nav li a {
  position: relative;
  top: 46%;
  color: #fff;
  text-transform: uppercase;
  font-family: 'Work sans', sans-serif;
  font-weight: 300;
  letter-spacing: 4px;
  display: block;
  text-align: center;
  font-size: 0.9rem;
  cursor: pointer;
  opacity: 0;
}

nav li a:before {
  content: '';
  width: 70px;
  height: 2px;
  background-color: #fff;
  position: absolute;
  top: 50%;
  left: 0;
  z-index: 100;
  -webkit-transform: translateX(-100%);
  -ms-transform: translateX(-100%);
  transform: translateX(-100%);
  opacity: 0;
  -webkit-transition: all .2s linear;
  transition: all .2s linear;
}

nav li a:after {
  content: attr(data-content);
  font-size: 0.7rem;
  -webkit-transition: all .2s linear;
  transition: all .2s linear;
  opacity: 0;
  position: absolute;
  z-index: 100;
  color: #fff;
  display: block;
  margin-right: auto;
  margin-left: auto;
  left: 0;
  right: 0;
  bottom: -50px;
  text-transform: none;
  font-family: 'Open sans', sans-serif;
  font-weight: 300;
  font-style: italic;
  letter-spacing: 0;
}

nav li a:hover:before {
  -webkit-transform: translateX(0);
  -ms-transform: translateX(0);
  transform: translateX(0);

  opacity: 1;
}

nav li a:hover:after {
  -webkit-transform: translateY(15px);
  -ms-transform: translateY(15px);
  transform: translateY(15px);
  opacity: 1;
}

nav li:nth-of-type(1) {
  background-color: #29363B
}

nav li:nth-of-type(2) {
  background-color: #EA495F
}

nav li:nth-of-type(3) {
  background-color: #F4837D
}

nav li:nth-of-type(4) {
  background-color: #FDCEA9
}

nav li:nth-of-type(5) {
  background-color: #99B998
}


/* ====================================
Burger king
==================================== */

.open-overlay {
  position: absolute;
  right: 5rem;
  top: 3.2rem;
  z-index: 100;
  width: 34px;
  display: block;
  cursor: pointer;
}

.open-overlay span {
  display: block;
  height: 1px;
  background-color: #fff;
  cursor: pointer;
  margin-top: 8px;
}

.animate-top-bar {
  -webkit-animation: animate-top-bar .6s linear 1 both;
  animation: animate-top-bar .6s linear 1 both
}

.animate-bottom-bar {
  -webkit-animation: animate-bottom-bar .6s linear 1 both;
  animation: animate-bottom-bar .6s linear 1 both
}

.animate-middle-bar {
  -webkit-animation: animate-middle-bar .6s linear 1 both;
  animation: animate-middle-bar .6s linear 1 both
}

.animate-out-top-bar {
  -webkit-animation: animate-out-top-bar .6s linear 1 both;
  animation: animate-out-top-bar .6s linear 1 both
}

.animate-out-bottom-bar {
  -webkit-animation: animate-out-bottom-bar .6s linear 1 both;
  animation: animate-out-bottom-bar .6s linear 1 both
}

.animate-out-middle-bar {
  -webkit-animation: animate-out-middle-bar .6s linear 1 both;
  animation: animate-out-middle-bar .6s linear 1 both
}


/* ====================================
Animation keyframes
==================================== */

@keyframes animate-top-bar {
  0% {
    background-color: #fff;
  }
  50% {
    -webkit-transform: translateY(9px);
    transform: translateY(9px)
  }
  80% {
    -webkit-transform: translateY(5px);
    transform: translateY(5px);
    background-color: #fff
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    background-color: #29363B;
  }
}

@keyframes animate-bottom-bar {
  0% {
    background-color: #fff;
  }
  50% {
    -webkit-transform: translateY(-9px);
    transform: translateY(-9px)
  }
  80% {
    -webkit-transform: translateY(-5px);
    transform: translateY(-5px);
    background-color: #fff;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    background-color: #29363B;
  }
}

@keyframes animate-middle-bar {
  0% {
    background-color: #fff;
  }
  80% {
    background-color: #fff;
  }
  100% {
    background-color: #29363B;
  }
}

@keyframes animate-out-top-bar {
  0% {
    background-color: #29363B
  }
  50% {
    -webkit-transform: translateY(9px);
    transform: translateY(9px)
  }
  80% {
    -webkit-transform: translateY(5px);
    transform: translateY(5px);
    background-color: #29363B
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    background-color: #FFF;
  }
}

@keyframes animate-out-bottom-bar {
  0% {
    background-color: #29363B
  }
  50% {
    -webkit-transform: translateY(-9px);
    transform: translateY(-9px)
  }
  80% {
    -webkit-transform: translateY(-5px);
    transform: translateY(-5px);
    background-color: #29363B;
  }
  100% {
    -webkit-transform: translateY(0);
    transform: translateY(0);
    background-color: #FFF;
  }
}

@keyframes animate-out-middle-bar {
  0% {
    background-color: #29363B
  }
  80% {
    background-color: #29363B;
  }
  100% {
    background-color: #fff;
  }
}

.home a {
  font-family: "Work Sans", sans-serif;
  color: #222;
  font-weight: 300;
  font-size: 12px;
  text-transform: uppercase;
  text-decoration: none;
  position: absolute;
  z-index: 10;
  top: 50px;
  left: 50px;
  padding-bottom: 3px;
  /*border-bottom: 1px solid #222;*/
}

@media (max-width: 640px) {
  nav ul {
    -webkit-box-orient: vertical;
    -webkit-box-direction: normal;
    -webkit-flex-direction: column;
    -ms-flex-direction: column;
    flex-direction: column;
  }
  nav ul li {
    height: 20%;
  }
  nav ul li a {
    font-size: 11px;
  }
  nav li a:after {
    font-size: 0.6rem;
    bottom: -25px;
  }
  nav li a:hover:after {
    transform: translateY(0);
  }
  .open-overlay {
    right: 1rem;
  }
  nav li a:before {
    width: 25px;
  }
}
</style>
