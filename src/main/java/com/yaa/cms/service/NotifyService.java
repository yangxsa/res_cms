package com.yaa.cms.service;

import com.yaa.cms.model.SysNotify;

import java.util.List;
import java.util.Map;

public interface NotifyService {

    int count(Map<String, Object> map);

    List<SysNotify> list(Map<String,Object> map);

}
