package com.yaa.cms.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yaa.cms.system.dao.NotifyDao;
import com.yaa.cms.system.model.Notify;
import com.yaa.cms.system.service.NotifyService;


/**
 * Generator code
 */
@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public Notify selectNotifyByID(Integer id) {
        return notifyDao.selectNotifyByID(id);
    }

    @Override
    public List<Notify> selectNotifyByPage(Map<String, Object> map) {
        return notifyDao.selectNotifyByPage(map);
    }

    @Override
    public int countNotifyRecord(Map<String, Object> map) {
        return notifyDao.countNotifyRecord(map);
    }

    @Override
    public int saveNotify(Notify notify) {
        return notifyDao.saveNotify(notify);
    }

    @Override
    public int updateNotify(Notify notify) {
        return notifyDao.updateNotify(notify);
    }

    @Override
    public int removeNotifyByID(Integer id) {
        return notifyDao.removeNotifyByID(id);
    }

}
