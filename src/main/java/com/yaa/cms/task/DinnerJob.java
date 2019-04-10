package com.yaa.cms.task;

import com.alibaba.fastjson.JSONObject;
import com.yaa.cms.system.model.vo.Response;
import com.yaa.cms.util.HttpUtils;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class DinnerJob implements Job {

    @Autowired
    SimpMessagingTemplate template;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        String url = "http://58.221.72.190:7089/user/submitEat";
        String param = "userid=72&currUserid=72&type=2";
        String result = HttpUtils.sendPost(url,param);
        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        String msg = jsonObject.getString("msg");
        Response response = new Response("通知："+msg+"!!");
        template.convertAndSend("/topic/getResponse", response);
    }

}