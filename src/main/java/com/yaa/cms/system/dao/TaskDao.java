package com.yaa.cms.system.dao;

import com.yaa.cms.system.model.SysTask;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface TaskDao {

    List<SysTask> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    SysTask selectTaskByID(Integer id);

    int updateTaskById(SysTask task);

    int saveSysTask(SysTask task);

    int removeTaskById(Integer id);

    int batchRemoveTask(Integer[] ids);

}
