package com.yaa.cms.system.dao;


import java.util.List;
import java.util.Map;

import com.yaa.cms.system.model.Notify;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface NotifyDao {

	Notify selectNotifyByID(Integer id);

    List<Notify> selectNotifyByPage(Map<String, Object> map);

    int countNotifyRecord(Map<String, Object> map);

    int saveNotify(Notify notify);

    int updateNotify(Notify notify);

    int removeNotifyByID(Integer id);

}
