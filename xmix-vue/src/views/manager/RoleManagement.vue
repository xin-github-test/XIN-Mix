<template>
  <div class="wrapper">
    <h1 style="height: 100px;padding-top: 20px">
      <el-page-header style="width: 100px;
                             margin-left: 170px;"
                      @back="goBack()"
      >
      </el-page-header>
      <span class="blue">&lt;<span class="yellow">Role Management</span>&gt;</span>
    </h1>
    <el-input
        style="width: 200px;margin-left: 175px"
        size="small" v-model="pageParams.searchItem"  placeholder="请输入内容">
    </el-input>
    <el-button style="margin-left: 5px"
               @click="load(pageParams)"
               type="primary" size="small" round icon="el-icon-search" >
      搜索
    </el-button>
    <el-button style="margin-left: 5px"
               @click="showDialog"
               type="success" size="small" round icon="el-icon-plus" >
      添加
    </el-button>
    <el-popconfirm
        confirm-button-text='确定'
        cancel-button-text='取消'
        icon="el-icon-info"
        icon-color="red"
        title="确定全部删除吗？"
        @confirm="batchDeleteUsers()"
    >
      <el-button style="margin-left: 795px"
                 slot="reference"
                 size="small" type="danger" round
                 icon="el-icon-delete">
        批量删除
      </el-button>
    </el-popconfirm>
    <el-button style="margin-left: 5px"
               @click="exportAllData"
               size="small" type="primary" round
               icon="el-icon-download">
      全部导出
    </el-button>
    <table class="container">
      <thead>
        <tr>
          <th><h1>username</h1></th>
          <th><h1>nickname</h1></th>
          <th><h1>privilege</h1></th>
          <th><h1>state</h1></th>
          <th><h1>operation</h1></th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="(user,index) in roleTable" :key="index">
          <td style="width: 150px"><input @click="handleChecked(pageParams.pageNum-1,index,user.id)" class="checkBox" type="checkbox" :checked="selectedItem[pageParams.pageNum-1][index]">
            {{ user.username }}</td>
          <td style="width: 150px">{{ user.nickname }}</td>
          <td style="width: 50px;text-align: left;">
            <el-tag style="font-size: 15px" size="medium" :type="user.privilege === 0?'danger':''">{{ user.privilege === 0?'管 理 员':'普通用户' }}</el-tag>
          </td>
          <td style="width: 50px;text-align: left;">
            <el-switch
                v-model="user.state"
                @change="updateState(user)"
                active-text="激活"
                inactive-text="冻结"
                active-color="#13ce66"
                inactive-color="#ff4949">
            </el-switch>
          </td>
          <td style="width: 80px">
            <el-popconfirm
                confirm-button-text='确定'
                cancel-button-text='取消'
                icon="el-icon-info"
                icon-color="red"
                title="确定删除吗？"
                @confirm="deleteUser(user.id,index)"
            >
              <el-button slot="reference" type="danger" icon="el-icon-delete-solid">删除</el-button>
            </el-popconfirm>
            <el-button @click="showEditUser(user)" style="margin-left: 5px" type="success" icon="el-icon-edit">修改</el-button>
          </td>
        </tr>
      </tbody>
    </table>
    <el-button style="margin-left: 175px;margin-top: 5px;width: 87.68px"
               @click="selectAll(pageParams.pageNum-1)"
               type="danger" round icon="el-icon-check">
      全选
    </el-button>
    <el-button style="margin-left: 5px;margin-top: 5px"
               @click="notSelectedAll(pageParams.pageNum-1)"
               type="danger" round icon="el-icon-close">
      全不选
    </el-button>

    <el-pagination
        style="margin-left: 580px"
        @current-change="handleCurrentChange"
        :current-page="pageParams.pageNum"
        :page-sizes="pages.pageSizes"
        :page-size="pages.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="pages.total">
    </el-pagination>

<!--  对话框-->
    <el-dialog :title="dialogTitle"
               :visible.sync="dialogVisible"
               width="30%"
               center
               @closed="loadData"
    >
      <el-form :model="newUser">
        <el-form-item label="用户名" label-width="120px">
          <el-input style="width: 250px" v-model="newUser.username" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="密 码" label-width="120px">
          <el-input style="width: 250px" v-model="newUser.password" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="昵 称" label-width="120px">
          <el-input style="width: 250px" v-model="newUser.nickname" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="权限" label-width="120px">
          <el-select style="width: 250px" v-model="newUser.privilege" placeholder="请选择用户权限">
            <el-option label="管理员" :value="0"></el-option>
            <el-option label="普通用户" :value="1"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="账号状态" label-width="120px">
          <el-select style="width: 250px" v-model="newUser.state" placeholder="是否激活账户">
            <el-option  label="激活" :value="true"></el-option>
            <el-option  label="冻结" :value="false"></el-option>
          </el-select>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="showDialog">取 消</el-button>
        <el-button v-if="dialogTitle === '添加用户'" type="primary" @click="addUser">添 加</el-button>
        <el-button v-else type="primary" @click="editUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {serverIp} from "../../../public/config";
import {serverPort} from "../../../public/config";
export default {
  name:'RoleManagement',
  data(){
    return{
      //导出数据的url
      exportUrl:`http://${serverIp}:${serverPort}/manager/role/export`,
      //传给后端的数据分页参数
      pageParams:{
        pageNum:1,   //当前页
        pageSize:5, //一页大小
        searchItem:""  //搜索项
      },
      //页面分页参数
      pages:{
        pageSizes: [5],
        pageSize: 5,
        total: 100
      },
      totalPages:1,  //总页数
      //表单数据
      roleTable: [],
      newUser: {},
      //对话框的显示
      dialogVisible: false,
      dialogTitle: '添加用户',
      //选中项
      selectedItem: [],
      selectedIds: new Set(),
    }
  },
  created() {
    //创建首页的选中数组 当首页的数据不满5条怎么办？？？？好像不影响（待定）
    this.selectedItem.push([...Array(this.pageParams.pageSize)].map(x => 0))
    //加载数据
    this.load(this.pageParams)
  },
  methods:{
    //返回上一级菜单
    goBack(){
      this.$router.back()
    },
    //加载数据,已能获取数据，需要对页面进行渲染
    load(pageParams){
      return this.request.get("/manager/role/page",{
        params:pageParams
      }).then(res =>{
        if(res.code === 200){
          this.roleTable = res.data.records
          //设置分页参数
          this.pages.total = res.data.total
          this.totalPages = res.data.pages
          return res.data.records.length
        }
      })
    },
    //导出所有的数据
    exportAllData(){
      window.open(this.exportUrl)
    },
    //控制分页
    async handleCurrentChange(val) {
      this.pageParams.pageNum = val
      let flag = false
      //每次进入新的一页，都创建一个新的checked数组，因为若是在load后面创建会导致页面第一时间无法读取数组的数据导致控制台报错
      //此时直接创建一个临时数组，等后面load完成之后将该数组进行替换，避免报错
      if(this.selectedItem.length < val){
        flag = true
        this.selectedItem.push([...Array(this.pageParams.pageSize)].map(x => 0))
      }
      //刷新数据,同时获取当前页的数据的个数
      const curRecords = await this.load(this.pageParams)
      if(!flag) {
        let addOrDelNum = this.selectedItem[val - 1].length - curRecords
        if (addOrDelNum > 0) {
          //当删除用户时
          this.selectedItem.splice(-addOrDelNum,addOrDelNum)
        } else if (addOrDelNum < 0) {
          //当新添加一个数据时
          for (let i = 0; i < -addOrDelNum; i++) {
            this.selectedItem[val - 1].push(0)
          }
        }
      }else{
        this.selectedItem[val - 1].splice(0, 5 - curRecords)
      }
    },
    //更新数据的请求
    updateUser(newUser){
      return this.request.post("/manager/role/update",newUser).then(
          res => {
            if(res.code === 200){
              //刷新数据
              this.load(this.pageParams)
              return res.code
            }
          }
      )
    },
    //更新数据 switch控制state
    updateState(newUser){
      this.updateUser(newUser)
    },
    //增加数据
    showDialog(){
      this.dialogTitle = '添加用户'
      this.newUser = {}
      this.dialogVisible = !this.dialogVisible
    },
    addUser(){
      //直接注册一个新用户
      this.request.post("/manager/role/register",this.newUser).then(
          data => {
            if(data.code === 200){
              //注册成功,刷新数据,并显示最后一页
              let temParams = this.pageParams
              temParams.pageNum = 1
              this.load(temParams)
              this.$message.success("添加成功！")
              this.dialogVisible = false
              this.newUser = {}
            }
          },
          err => {
            console.log(err)
            this.$message.error("添加失败！")
          }
      )
    },
    //删除单条数据
    deleteUser(id,index){
      this.request.delete("/manager/role/delete/"+id).then(
          res => {
            if(res.code === 200){
              //将选中数组中的值置为0
              this.selectedItem[this.pageParams.pageNum-1][index] = 0
              //删除成功,刷新数据
              this.pageParams.pageNum = 1
              this.load(this.pageParams)
              this.$message.success("删除成功！")
            }
          }
      )
    },
    //批量删除数据
    batchDeleteUsers(){
      //将selectedIds拼接成字符串，然后传给后端
      const ids = Array.from(this.selectedIds).join('-');
      if(ids === ''){
        this.$message.error("请选择至少一条数据！")
        return
      }
      this.request.delete("/manager/role/batch/delete",{
        params:{ids:ids}
      }).then(res => {
        if(res.code === 200){
          //刷新数据(需要重新定义刷新数据中的当前页:直接定义为第一页)并提示
          this.pageParams.pageNum = 1
          //删除成功之后将删除的userIdSet制置空
          this.selectedIds = new Set()
          //删除成功将选中数组中的值都设置为0（重新生成一个新的选中数组）
          let temList = []
          temList.push([...Array(this.pageParams.pageSize)].map(x => 0))
          this.selectedItem = temList
          this.load(this.pageParams)
          this.$message.success("删除成功！")
        }
      })

    },
    //修改数据 有bug,编辑之后取消。修改的内容还是显示在页面上
    showEditUser(oldUser){
      this.newUser = oldUser
      this.dialogTitle = '修改用户信息'
      this.dialogVisible = true
    },
    async editUser(){
      const code = await this.updateUser(this.newUser)
      this.dialogVisible = false
      this.load(this.pageParams)
      if(code === 200){
        this.$message.success("修改成功！")
      }
    },
    loadData(){
      this.load(this.pageParams)
    },
    //点击单个选中
    handleChecked(curPageNum,index,userId){
      let curVal = this.selectedItem[curPageNum][index]
      this.selectedItem[curPageNum].splice(index,1,curVal === 1?0:1)
      if(curVal === 0){
        this.selectedIds.add(userId)
      }else{
        this.selectedIds.delete(userId)
      }
    },
    //全选
    selectAll(curPageNum){
      let len = this.selectedItem[curPageNum].length
      for(let i = 0; i < len; i++){
        //若要是需要数据是响应式的，不能使用map
        this.selectedItem[curPageNum].splice(i,1,1)
      }
      let curUsers = this.roleTable.map(user => user.id)
      this.selectedIds = new Set([...this.selectedIds,...curUsers])
    },
    //全不选
    notSelectedAll(curPageNum){
      let len = this.selectedItem[curPageNum].length
      for(let i = 0; i < len; i++){
        //若要是需要数据是响应式的，不能使用map
        this.selectedItem[curPageNum].splice(i,1,0)
      }
      let curUsers = this.roleTable.map(user => user.id)
      if(curUsers.length > 0){
        curUsers.map(id => this.selectedIds.delete(id))
      }
    }
  }
}
</script>

<style scoped>

.wrapper {
  height: 100vh;
  font-family: 'Open Sans', sans-serif;
  font-weight: 300;
  line-height: 1.42em;
  color:#A7A1AE;
  background-color:#1F2739;
  /*background-image: linear-gradient(120deg, #487eb0, #fbc531);*/
}
.checkBox{
  margin-right: 5px;
}

h1 {
  font-size:3em;
  font-weight: 300;
  line-height:1em;
  text-align: center;
  color: #4DC3FA;
}

h2 {
  font-size:1em;
  font-weight: 300;
  text-align: center;
  display: block;
  line-height:1em;
  padding-bottom: 2em;
  color: #FB667A;
}

h2 a {
  font-weight: 700;
  text-transform: uppercase;
  color: #FB667A;
  text-decoration: none;
}

.blue { color: #185875; }
.yellow { color: #FFF842; }

.container th h1 {
  font-weight: bold;
  font-size: 1em;
  text-align: left;
  /*color: #185875;*/
  color: white;
}

.container td {
  font-weight: normal;
  font-size: 1em;
  -webkit-box-shadow: 0 2px 2px -2px #0E1119;
  -moz-box-shadow: 0 2px 2px -2px #0E1119;
  box-shadow: 0 2px 2px -2px #0E1119;
}

.container {
  text-align: left;
  overflow: hidden;
  width: 80%;
  margin: 0 auto;
  display: table;
  /*padding: 0 0 8em 0;*/
}

.container td, .container th {
  padding-bottom: 2%;
  padding-top: 2%;
  padding-left:2%;
}

/* Background-color of the odd rows */
.container tr:nth-child(odd) {
  background-color: #323C50;
}

/* Background-color of the even rows */
.container tr:nth-child(even) {
  background-color: #2C3446;
}

.container th {
  background-color: #1F2739;
}

.container td:first-child {
  color: #FB667A;
  width: 250px;
}
.container td:last-child {
  color: #FB667A;
  width: 200px;
}

.container tr:hover {
  background-color: #464A52;
  -webkit-box-shadow: 0 6px 6px -6px #0E1119;
  -moz-box-shadow: 0 6px 6px -6px #0E1119;
  box-shadow: 0 6px 6px -6px #0E1119;
}

/*这段特效有bug,就不用了*/
/*.container td:hover {
  background-color: #FFF842;
  color: #403E10;
  font-weight: bold;

  box-shadow: #7F7C21 -1px 1px, #7F7C21 -2px 2px, #7F7C21 -3px 3px, #7F7C21 -4px 4px, #7F7C21 -5px 5px, #7F7C21 -6px 6px;
  transform: translate3d(6px, -6px, 0);
  transition-delay: 0s;
  transition-duration: 0.4s;
  transition-property: all;
  transition-timing-function: linear;
}*/
/*当宽度小于等于800px时，隐藏container下的第四个td和th元素*/
@media (max-width: 800px) {
  .container td:nth-child(4),
  .container th:nth-child(4) { display: none; }
}
</style>