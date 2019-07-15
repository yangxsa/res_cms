package com.yaa.cms.task;

import com.yaa.cms.system.model.vo.Response;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class WelcomeJob implements Job {

    @Autowired
    SimpMessagingTemplate template;

    @Override
    public void execute(JobExecutionContext arg0) throws JobExecutionException {
        Response response = new Response("通知：喜喜喜喜!!");
        template.convertAndSend("/topic/getResponse", response);
    }

}