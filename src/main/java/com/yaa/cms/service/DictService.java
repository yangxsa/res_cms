package com.yaa.cms.service;

import com.yaa.cms.model.Dict;

import java.util.List;
import java.util.Map;

public interface DictService {

    Dict get(Long id);

    List<Dict> list(Map<String, Object> map);

    int count(Map<String, Object> map);

    int save(Dict dict);

    int update(Dict dict);

    int remove(Long id);

    int batchRemove(Long[] ids);

    List<Dict> listType();

    String getName(String type, String value);

    /**
     * 根据type获取数据
     * @param type
     * @return
     */
    List<Dict> listByType(String type);


}
