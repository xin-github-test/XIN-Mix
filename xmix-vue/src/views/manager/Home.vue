<template>
  <div class="wrapper">
    <div ref="overlay-navigation" class="overlay-navigation" :class="overlayNavigationClass">
      <nav  role="navigation">
        <ul>
          <li ref="li0"><a ref="a0" href="#" data-content="The beginning">Home</a></li>
          <li ref="li1"><a ref="a1" href="#" data-content="Curious?">About</a></li>
          <li ref="li2"><a ref="a2" href="#" data-content="I got game">Skills</a></li>
          <li ref="li3"><a ref="a3" href="#" data-content="Only the finest">Works</a></li>
          <li ref="li4"><a ref="a4" href="#" data-content="Don't hesitate">Contact</a></li>
        </ul>
      </nav>
    </div>

    <section class="home">
      <a @click.prevent="void 0" href="#https://codepen.io/fluxus/pen/gPWxXJ" target="_blank"><span class="HelloWords">你好, 管理员</span></a>
      <div  @click="changeStyle" class="open-overlay" :style="openOverlayCss">
        <span class="bar-top" :class="barTopClass"></span>
        <span class="bar-middle" :class="barMiddleClass"></span>
        <span class="bar-bottom" :class="barBottomClass"></span>
      </div>
    </section>
  </div>
</template>

<script>
import Velocity from 'velocity-animate';
import 'velocity-animate/velocity.ui';
export default {
  name: "Home",
  data(){
    return{
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
      openOverlayCss:{

      }
    }
  },
  methods:{
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
        debugger
        var onaRef = this.$refs["overlay-navigation"]


        debugger
        // TODO 1.Velocity函数的改造，第一个参数是 el 弄清处el是什么,利用动画进入钩子函数进行测试
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


  }
}
</script>
<style scoped>
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
  text-decoration: none;
  display: block;
  text-align: center;
  font-size: 0.9rem;
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
  border-bottom: 1px solid #222;
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
