package com.xin.xmix.blog.config;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;

import java.util.*;

/*
* 由于返回结果没有存储过程中的输出参数以及游标数据，因此直接实现第一个方法即可
* */
public class MyBatisResultHandler implements ResultHandler<SkillInfoResult> {
    private final List<Map<String, List<String>>> result;
    public MyBatisResultHandler(){
        this.result = new ArrayList<>();
    }
    @Override
    public void handleResult(ResultContext<? extends SkillInfoResult> resultContext) {
        //1.获取当前的数据
        SkillInfoResult resultObject = resultContext.getResultObject();
        //2.自定义封装的逻辑
        String[] res = resultObject.getSkills().split(",");
        HashMap<String, List<String>> data = new HashMap<>();
        data.put(resultObject.getCategory(), Arrays.asList(res));
        result.add(data);
    }
    public List<Map<String, List<String>>> getResult(){
        return this.result;
    }
}
