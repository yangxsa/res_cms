package com.yaa.cms.service;

import com.yaa.cms.model.Notify;

import java.util.List;
import java.util.Map;

/**
 * Generator code
 */
public interface NotifyService {

	Notify selectNotifyByID(Integer id);

    List<Notify> selectNotifyByPage(Map<String, Object> map);

    int countNotifyRecord(Map<String, Object> map);

    int saveNotify(Notify notify);

    int updateNotify(Notify notify);

    int removeNotify(Integer id);

}
