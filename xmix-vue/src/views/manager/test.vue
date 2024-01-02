<template>
    <div class="wrapper">
      <div class=comment>
        <div class="avatarBox">
          <img src="https://dl.dropboxusercontent.com/u/28235353/07-1.jpg" class="avatar"/>
        </div>
        <div class="commentContent">
          テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！<br>
          テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！<br>
          テンセンが大好きです！テンセンが大好きです！テンセンが大好きです！<br>
        </div>
      </div>
    </div>

</template>

<script>

export default {
  name: 'test',

  methods:{

  }
}
</script>
<style scoped>
.wrapper{
  background-color: black;
  background-size: cover;
  position: absolute;
  /* for expanding to all */
  top:0;
  bottom:0;
  left:0;
  right:0;
  overflow: auto;
  z-index: 1;
}

.avatarBox {
  float: left;
}

.avatar {
  border-radius: 65px;
  width: 130px;
  height: 130px;
  display: block;
}

.commentContent{
  font-size:20px;
  margin: 0px 20px 10px 180px;
  padding: 10px;
  color: white;
  border: 2px solid white;
  border-radius: 10px;
  min-height: 130px;
  position:relative;
}

.commentContent:before{
  content: "";
  display: block;
  width: 0px;
  height: 0px;
  border-style: solid;
  border-width: 20px 24px 20px 0;
  border-color: transparent rgba(255, 255, 255, 1) transparent transparent;
  position: absolute;
  left: -24px;
  top: 50px;
}

.commentContent:after{
  content: "";
  display: block;
  width: 0px;
  height: 0px;
  border-style: solid;
  border-width:24px 30px 24px 0;
  border-color: transparent #000 transparent transparent;
  position: absolute;
  left:-21px;
  top:46px;
  z-index: 0;
}

.comment{
  position: relative;
}

</style>