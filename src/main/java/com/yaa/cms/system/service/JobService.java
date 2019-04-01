package com.yaa.cms.system.service;

import com.yaa.cms.system.model.SysTask;
import org.quartz.SchedulerException;

import java.util.List;
import java.util.Map;

public interface JobService {

    List<SysTask> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    SysTask selectTaskByID(Integer id);

    int updateTaskById(SysTask task);

    int saveSysTask(SysTask task);

    int removeTaskById(Integer id);

    int batchRemoveTask(Integer[] ids);

    void changeStatus(Integer jobId, String cmd) throws SchedulerException;

    void initSchedule() throws SchedulerException;


}
