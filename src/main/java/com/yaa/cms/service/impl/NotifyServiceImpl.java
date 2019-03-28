package com.yaa.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yaa.cms.dao.NotifyDao;
import com.yaa.cms.model.Notify;
import com.yaa.cms.service.NotifyService;


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
    public int removeNotify(Integer id) {
        return notifyDao.removeNotify(id);
    }

}
