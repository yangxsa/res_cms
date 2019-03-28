package com.yaa.cms.dao;

import com.yaa.cms.model.SysDict;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface DictDao {

	SysDict get(Long id);

	List<SysDict> list(Map<String, Object> map);

	int count(Map<String, Object> map);

	int save(SysDict SysDict);

	int update(SysDict SysDict);

	int remove(Long id);

	int batchRemove(Long[] ids);

	List<SysDict> listType();
}
