import axios from 'axios'
import {serverIp} from '../../public/config'
import {serverPort} from '../../public/config'

const request = axios.create({
    baseURL: `http://${serverIp}:${serverPort}`, //统一给加上url前缀
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
/*
request.interceptors.response.use(
    response => {
        let res = response.data;
        // 如果是返回的文件
        if (response.config.responseType === 'blob') {
            return res
        }
        // 兼容服务端返回的字符串数据
        if (typeof res === 'string') {
            res = res ? JSON.parse(res) : res
        }
        if(res.code === '401'){
            ElementUI.Message({
                message:res.msg,
                type:'error'
            })
        }
        return res;
    },
    error => {
        console.log('err' + error) // for debug
        return Promise.reject(error)
    }
)
*/

export default request;
