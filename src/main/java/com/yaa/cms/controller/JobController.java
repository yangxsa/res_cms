package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;
import com.yaa.cms.model.SysTask;
import com.yaa.cms.service.JobService;
import com.yaa.cms.util.PageUtil;
import com.yaa.cms.util.PageUtils;
import com.yaa.cms.util.Query;
import com.yaa.cms.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/sys/job")
public class JobController extends BaseController {

    @Autowired
    private JobService jobService;

    String prefix = "system/job";

    /**
     * 任务列表
     * @return
     */
    @GetMapping("")
    public String list(@RequestParam(value = "page",defaultValue = "1")int page) {
        Map<String,Object> params = buildPageParam(page);
        // 查询列表数据
        int total = jobService.count(params);
        List<SysTask> taskScheduleJobList = jobService.list(params);
        this.setPageNavigation(page,total);
        request.setAttribute("jobs",taskScheduleJobList);
        return render(prefix + "/job");
    }

    @GetMapping("/add")
    String add() {
        return render(prefix + "/add");
    }


    /**
     * 编辑页面
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    String edit(@PathVariable("id") Integer id, Model model) {
        SysTask job = jobService.selectTaskByID(id);
        model.addAttribute("job", job);
        return render(prefix + "/edit");
    }

    /**
     * 修改
     */
    @ResponseBody
    @PostMapping("/update")
    public Result update(SysTask taskScheduleJob) {
        jobService.updateTaskById(taskScheduleJob);
        return Result.ok();
    }

    /**
     * 保存
     * @param task
     * @return
     */
    @ResponseBody
    @PostMapping("save")
    public Result save(SysTask task){
        jobService.saveSysTask(task);
        return Result.ok();
    }

    /**
     * 删除
     */
    @ResponseBody
    @PostMapping("/remove")
    public Result remove(Integer id) {
        if (jobService.removeTaskById(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 删除
     */
    @PostMapping("/batchRemove")
    @ResponseBody
    public Result remove(@RequestParam("ids[]") Integer[] ids) {
        jobService.batchRemoveTask(ids);
        return Result.ok();
    }

    /**
     * 停止 启动任务
     * @param id
     * @param cmd
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/changeJobStatus")
    public Result changeJobStatus(Integer id,String cmd ) {
        String label = "停止";
        if ("start".equals(cmd)) {
            label = "启动";
        } else {
            label = "停止";
        }
        try {
            jobService.changeStatus(id, cmd);
            return Result.ok("任务" + label + "成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.ok("任务" + label + "失败");
    }

}
