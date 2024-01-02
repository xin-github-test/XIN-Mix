<template>
  <div class="time-container">
    <div class="hour-container">
      <div class="hour-card card1">{{ seconds + 1 }}</div>
      <div class="hour-card card2">{{ seconds + 1 }}</div>
      <div class="hour-card card3">{{ seconds }}</div>
      <div class="hour-card card4">{{ seconds }}</div>
    </div>
    <div class="minute-container">
      <div class="minute-card card1">{{ seconds + 1 }}</div>
      <div class="minute-card card2">{{ seconds + 1 }}</div>
      <div class="minute-card card3">{{ seconds }}</div>
      <div class="minute-card card4">{{ seconds }}</div>
    </div>
    <div class="second-container">
      <div class="second-card card1">{{ seconds + 1 }}</div>
      <div class="second-card card2">{{ seconds + 1 }}</div>
      <div class="second-card card3">{{ seconds }}</div>
      <div class="second-card card4">{{ seconds }}</div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'TimeDisplay',
  data() {
    return {
      hours: '',
      minutes: '',
      seconds: Number(('0' + new Date().getSeconds()).slice(-2)),
      timer: ''
    }
  },
  created() {
    // this.timer = this.getTime(1000)
  },
  methods: {

    getTime(time) {
      return setInterval(() => {
        this.seconds = Number(('0' + new Date().getSeconds()).slice(-2))
      }, time)
    },
    getCurTime() {
      const now = new Date();

      const year = now.getFullYear();
      const month = ('0' + (now.getMonth() + 1)).slice(-2);
      const day = ('0' + now.getDate()).slice(-2);
      const hours = ('0' + now.getHours()).slice(-2);
      const minutes = ('0' + now.getMinutes()).slice(-2);
      const seconds = ('0' + now.getSeconds()).slice(-2);

      const formattedTime = year + month + day + hours + minutes + seconds;
      this.seconds = seconds

    }
  }
}
</script>
<style scoped>
.time-container {
  display: flex;
  gap: 20px;
}

.hour-container,
.minute-container {
  width: 100px;
  height: 100px;
  background-color: #57606f;
  /*border: 1px black solid;*/
  position: relative;
}

.second-container {
  width: 50px;
  height: 50px;
  background-color: #57606f;
  /*border: 1px black solid;*/
  position: relative;
}

.hour-card,
.minute-card {
  display: inline-block;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 50%;
  /*text-align: center;*/
  font-size: 80px;
  overflow: hidden;
}

.second-card {
  display: inline-block;
  position: absolute;
  left: 0;
  top: 0;
  width: 100%;
  height: 50%;
  /*text-align: center;*/
  font-size: 40px;
  overflow: hidden;
}

.hour-container .card1 {
  line-height: 100px;
  background-color: #4DC3FA;
}

.minute-container .card1 {
  line-height: 100px;
  background-color: #4DC3FA;
}

.second-container .card1 {
  line-height: 50px;
  background-color: #4DC3FA;
}

.hour-container .card2 {
  top: 50%;
  line-height: 0;
  background-color: forestgreen;
  transform: rotateX(180deg);
  transform-origin: center top;
  backface-visibility: hidden; /*设置元素背面的可见性*/
  transition: .5s;
  animation: flipedTime 1s linear infinite;
  z-index: 10;
}

.minute-container .card2 {
  top: 50%;
  line-height: 0;
  background-color: forestgreen;
  transform: rotateX(180deg);
  transform-origin: center top;
  backface-visibility: hidden; /*设置元素背面的可见性*/
  transition: .5s;
  animation: flipedTime 1s linear infinite;
  z-index: 10;
}

.second-container .card2 {
  top: 50%;
  line-height: 0;
  background-color: forestgreen;
  transform: rotateX(180deg);
  transform-origin: center top;
  backface-visibility: hidden; /*设置元素背面的可见性*/
  transition: .5s;
  animation: flipedTime 1s linear infinite;
  z-index: 10;
}

.hour-container .card3 {
  line-height: 100px;
  background-color: #e69a42;
  transition: .5s;
  transform-origin: center bottom;
  backface-visibility: hidden;
  animation: flipedTime1 1s linear infinite;
}

.minute-container .card3 {
  line-height: 100px;
  background-color: #e69a42;
  transition: .5s;
  transform-origin: center bottom;
  backface-visibility: hidden;
  animation: flipedTime1 1s linear infinite;
}

.second-container .card3 {
  line-height: 50px;
  background-color: #e69a42;
  transition: .5s;
  transform-origin: center bottom;
  backface-visibility: hidden;
  animation: flipedTime1 1s linear infinite;
}

.hour-container .card4 {
  top: 50%;
  line-height: 0;
  background-color: #C96567;
}

.minute-container .card4 {
  top: 50%;
  line-height: 0;
  background-color: #C96567;
}

.second-container .card4 {
  top: 50%;
  line-height: 0;
  background-color: #C96567;
}

@keyframes flipedTime1 {
  100% {
    transform: rotateX(-180deg);
  }
}

@keyframes flipedTime {
  from {
    transform: rotateX(180deg);
  }
  to {
    transform: rotateX(0);
  }
}
</style>