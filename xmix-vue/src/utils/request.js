import axios from 'axios'
import {serverIp} from '../../public/config'
import {serverPort} from '../../public/config'
import ElementUI from "element-ui";

const request = axios.create({
    baseURL: `http://${serverIp}:${serverPort}`, //统一给加上url前缀 localhost:8000
    timeout: 5000
})

//还可以对请求进行拦截，在请求发送前，对请求进行修改
request.interceptors.request.use(config => {
    //将请求参数封装成json
    config.headers['Content-Type'] = 'application/json;charset=utf-8';
    return config
}, error => {
    return Promise.reject(error)
});

//对请求返回的相应进行相应的拦截
//可以在接口响应后统一处理结果
request.interceptors.response.use(
    response => {
        //取出响应中我们需要的数据 data
        let res = response.data;
        // 如果是返回的是二进制数据则直接返回
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        //错误码的处理，暂时先这样。后面按需修改
        if(res.code !== 200){
            ElementUI.Message({
                message:"code:"+res.code+" msg:"+res.msg,
                type:'error'
            })
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        //跳转到404
        location.href="http://"+serverIp+":8080/404"
        return Promise.reject(error)
    }
)


export default request;
