package com.yaa.cms.service.impl;

import com.yaa.cms.common.Constant;
import com.yaa.cms.dao.SysTaskDao;
import com.yaa.cms.model.SysTask;
import com.yaa.cms.quartz.utils.QuartzManager;
import com.yaa.cms.service.JobService;
import com.yaa.cms.util.ScheduleJobUtils;
import com.yaa.cms.vo.ScheduleJob;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    private SysTaskDao taskDao;
    @Autowired
    private QuartzManager quartzManager;

    @Override
    public List<SysTask> list(Map<String, Object> map) {
        return taskDao.list(map);
    }

    @Override
    public int count(Map<String, Object> map) {
        return taskDao.count(map);
    }

    @Override
    public SysTask selectTaskByID(Integer id) {
        return taskDao.selectTaskByID(id);
    }

    @Override
    public int updateTaskById(SysTask task) {
        return taskDao.updateTaskById(task);
    }

    @Override
    public int saveSysTask(SysTask task) {
        return taskDao.saveSysTask(task);
    }

    @Override
    public int removeTaskById(Integer id) {
        try {
            SysTask task = taskDao.selectTaskByID(id);
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(task));
            return taskDao.removeTaskById(id);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int batchRemoveTask(Integer[] ids) {
        for (Integer id : ids) {
            try {
                SysTask scheduleJob = taskDao.selectTaskByID(id);
                quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            } catch (Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        return taskDao.batchRemoveTask(ids);
    }

    @Override
    public void changeStatus(Integer jobId, String cmd) throws SchedulerException {
        SysTask scheduleJob = taskDao.selectTaskByID(jobId);
        if (scheduleJob == null) {
            return;
        }
        if (Constant.STATUS_RUNNING_STOP.equals(cmd)) {
            quartzManager.deleteJob(ScheduleJobUtils.entityToData(scheduleJob));
            scheduleJob.setJobStatus(ScheduleJob.STATUS_NOT_RUNNING);
        } else {
            if (!Constant.STATUS_RUNNING_START.equals(cmd)) {
            } else {
                scheduleJob.setJobStatus(ScheduleJob.STATUS_RUNNING);
                quartzManager.addJob(ScheduleJobUtils.entityToData(scheduleJob));
            }
        }
        taskDao.updateTaskById(scheduleJob);
    }

    @Override
    public void initSchedule() throws SchedulerException {
        // 这里获取任务信息数据
        List<SysTask> jobList = taskDao.list(new HashMap<String, Object>(16));
        for (SysTask scheduleJob : jobList) {
            if ("1".equals(scheduleJob.getJobStatus())) {
                ScheduleJob job = ScheduleJobUtils.entityToData(scheduleJob);
                quartzManager.addJob(job);
            }

        }
    }
}
