<template>
  <div class="wrapper">
    <div class="header">
      <button @click="$router.back()">go back</button>
    </div>
    <el-upload
        class="upload-demo"
        drag
        :action="uploadUrl+'/1'"
        @on-success="handleRes"
        multiple>
      <i class="el-icon-upload"></i>
      <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
    </el-upload>
    <span class="Title">
      CV PDF
      <button @click="curPage > 1?curPage--:curPage">back</button>
      <button @click="curPage < pageNum? curPage++:curPage">next</button></span>
    <span class="PDF-content">
      <pdf
        :src="pdfSrc"
        :page="curPage"
      >
      </pdf>
    </span>

  </div>
</template>

<script>
import pdf from 'vue-pdf'
import {serverIp, serverPort} from "../../../public/config";
export default {
  name: "CvPage",
  components:{
    pdf
  },
  data(){
    return{
      //展示pdf的当前页
      curPage: 1,
      //pdf的总页数
      pageNum: 2,
      //文件上传url
      uploadUrl: 'http://'+serverIp+':'+serverPort+'/manager/file/upload/',
      // pdfSrc: '/Files/cvPdf.pdf'
      pdfSrc: ''
    }
  },
  created() {
    //发送请求获取作者的简历  需要从blog服务中获取相应的数据
    this.request.get('/manager/file/9a9b07bdf0c9498eb0f248a50e2ee165.pdf',
        {
          responseType:'blob' //希望从后台拿到二进制的数据
        }
        ).then(
        res => {
          //拿到传回的二进制数据,并形成一个url映射
          const url = URL.createObjectURL(res)
          this.pdfSrc = url
        }
    )
  },
  methods:{
    //文件上传成功后，对结果进行处理
    handleRes(res){
      if(res.code === 200){
        this.uploadUrl = res.data
      }
    },
  }
}
</script>

<style scoped>

</style>