package com.yaa.cms.system.service;


import com.yaa.cms.system.model.Notify;

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

    int removeNotifyByID(Integer id);

}
