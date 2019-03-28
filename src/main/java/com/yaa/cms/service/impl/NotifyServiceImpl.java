package com.yaa.cms.service.impl;

import com.yaa.cms.dao.NotifyDao;
import com.yaa.cms.model.SysNotify;
import com.yaa.cms.service.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class NotifyServiceImpl implements NotifyService {

    @Autowired
    private NotifyDao notifyDao;

    @Override
    public int count(Map<String, Object> map) {
        return notifyDao.count(map);
    }

    @Override
    public List<SysNotify> list(Map<String, Object> map) {
        return notifyDao.list(map);
    }
}
