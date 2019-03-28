package com.yaa.cms.service;

import com.yaa.cms.model.SysDict;

import java.util.List;
import java.util.Map;

public interface DictService {

    SysDict get(Long id);

    List<SysDict> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(SysDict SysDict);

    int update(SysDict SysDict);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<SysDict> listType();

    String getName(String type, String value);

    /**
     * 根据type获取数据
     * @param type
     * @return
     */
    List<SysDict> listByType(String type);


}
