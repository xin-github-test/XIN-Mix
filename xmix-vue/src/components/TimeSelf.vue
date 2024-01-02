<template>
  <div class="time-container">
    {{ hours }}:{{ minutes }}:{{seconds}}
  </div>
</template>

<script>

export default {
  name: 'TimeSelf',
  data(){
    return{
      hours:('0' + new Date().getHours()).slice(-2),
      minutes:('0' + new Date().getMinutes()).slice(-2),
      seconds: ('0' + new Date().getSeconds()).slice(-2),
      timer:''
    }
  },
  created() {
    this.timer = this.getTime(1000)
  },
  beforeDestroy() {
    //组件销毁时清除定时任务
    clearTimeout(this.timer)
  },
  methods:{

    getTime(time){
      return setInterval(()=>{
        this.seconds = ('0' + new Date().getSeconds()).slice(-2)
        if(this.seconds === '00'){
          this.minutes = ('0' + new Date().getMinutes()).slice(-2)
        }
        if(this.minutes === '00'){
          this.hours = ('0' + new Date().getHours()).slice(-2)
        }
      },time)
    },
  }
}
</script>
<style scoped>
.time-container{
  width: 500px;
  height: 100px;
  /*border: 1px red solid;*/
  background: linear-gradient(
      to right,
      hsl(340deg,100%,50%),
      hsl(300deg,100%,50%)
  );
  transition: filter 1s;
  font-family: "楷体", sans-serif;
  text-align: center;
  font-size: 100px;
  line-height: 100px;
  color: transparent;
  -webkit-background-clip: text;
}
.time-container:hover{
  filter: hue-rotate(-40deg);
  transition: 1s;
}
</style>