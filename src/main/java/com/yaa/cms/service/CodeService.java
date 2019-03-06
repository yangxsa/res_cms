package com.yaa.cms.service;

import java.util.List;
import java.util.Map;

public interface CodeService {

    List<Map<String, Object>> list();

    byte[] generatorCode(String[] tableNames);

}
