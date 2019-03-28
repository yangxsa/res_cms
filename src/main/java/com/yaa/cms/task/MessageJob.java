package com.yaa.cms.task;

import com.yaa.cms.common.Constant;
import com.yaa.cms.util.AlgorithmUtil;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageJob implements Job {

    @Autowired
    private SimpMessagingTemplate template;

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String user = AlgorithmUtil.encryptAESToString("9", Constant.AES_KEY);
        template.convertAndSendToUser(user, "/queue/notifications", "新消息：");

    }
}
