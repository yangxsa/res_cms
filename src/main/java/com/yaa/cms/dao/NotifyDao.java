package com.yaa.cms.dao;

import com.yaa.cms.model.SysNotify;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface NotifyDao {

    List<SysNotify> list(Map<String, Object> map);

    int count(Map<String, Object> map);

}
