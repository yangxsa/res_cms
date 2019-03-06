package com.yaa.cms.dao;

import com.yaa.cms.model.Dict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictDao {

	Dict get(Long id);

	List<Dict> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(Dict dict);

	int update(Dict dict);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<Dict> listType();
}
