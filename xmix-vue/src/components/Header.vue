<template>
  <div>
    <!-- Navigation -->
    <nav class="navbar-default">
      <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
          <a class="navbar-brand" href="#page-top">Celine Is Awesome</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="layout-ul" id="bs-example-navbar-collapse-1">
          <ul class="nav navbar-nav navbar-right">
            <li>
              <a  href="#services">Services</a>
            </li>
            <li>
              <a  href="#portfolio">Portfolio</a>
            </li>
            <li>
              <a  href="#about">About</a>
            </li>
            <li>
              <a  href="#team">Team</a>
            </li>
            <li>
              <a  href="#contact">Contact</a>
            </li>
          </ul>
        </div>

        <!--        右边的登陆-->
        <div class="navbar-hello">欢迎您,<span>管理员！</span></div>
      </div>
      <!-- /.container-fluid -->
    </nav>
  </div>
</template>

<script>
export default {
  name: "Header"
}
</script>

<style scoped>
/*自己加的样式start-------------*/
a{
  text-decoration: none;   /*去掉a标签的下划线*/
}
.container{
  display: flex;
  justify-content: space-between;  /*分散在俩端*/
  align-items: center;
}
.layout-ul{
  display: flex;
  margin-left: 400px;
}
.navbar-right li{
  display: inline;
  padding: 5px;
  margin: 5px;
  font-family: "Times New Roman",Georgia,Serif;;
}
.navbar-right li:last-child{
  border-right: 2px solid #1ee2e7;
}
.navbar-header{
  margin-left: 30px;
}
.navbar-hello{
  font-family: "Times New Roman",Georgia,Serif;  /*设置字体*/
  color: #1ee2e7;
  margin-right: 100px;
  font-size: 20px;
}
/*自己加的样式end-------------*/

.navbar-default {
  border-color: transparent;  /*边框设置为透明*/
  background-color: #222;
}

.navbar-default .navbar-brand {
  font-family: "Times New Roman",Georgia,Serif;  /*设置字体*/
  color: #1ee2e7;
  font-size: 50px;
}
.navbar-default .navbar-collapse {
  border-color: rgba(255,255,255,.02);
}

.navbar-default {
  border-color: #1ee2e7;
  background-color: #1ee2e7;
}

.navbar-default {
  background-color: #fff;
}
.navbar-default :hover,
.navbar-default :focus {
  background-color: #1ee2e7;
}

.navbar-default .nav li a {
  text-transform: uppercase;
  font-family: Montserrat,"Helvetica Neue",Helvetica,Arial,sans-serif;
  font-weight: 400;
  letter-spacing: 1px;
  color: #fff;
}

.navbar-default .nav li a:hover,
.navbar-default .nav li a:focus {
  outline: 0;
  color: #1ee2e7;
}

.navbar-default .navbar-nav>.active>a {
  border-radius: 0;
  color: #fff;
  background-color: #1ee2e7;
}

.navbar-default .navbar-nav>.active>a:hover,
.navbar-default .navbar-nav>.active>a:focus {
  color: #fff;
  background-color: #17d0d5;
}

/*@media(min-width:768px) {  !*当屏幕的宽度达到或超过 768px时，应用其中的样式*!
  .navbar-default {
    padding: 25px 0;
    border: 0;
    background-color: transparent;
    -webkit-transition: padding .3s;
    -moz-transition: padding .3s;
    transition: padding .3s;
  }

  .navbar-default .navbar-brand {
    font-size: 2em;
    -webkit-transition: all .3s;
    -moz-transition: all .3s;
    transition: all .3s;
  }

  .navbar-default .navbar-nav>.active>a {
    border-radius: 3px;
  }

  .navbar-default.navbar-shrink {
    padding: 10px 0;
    background-color: #222;
  }

  .navbar-default.navbar-shrink .navbar-brand {
    font-size: 1.5em;
  }
}*/
.navbar-default .navbar-brand:hover,
.navbar-default .navbar-brand:focus,
.navbar-default .navbar-brand:active,
.navbar-default .navbar-brand {
  color: #fec503;
}
</style>