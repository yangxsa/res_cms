package com.yaa.cms.dao;

import com.yaa.cms.model.Cert;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface CertDao {

	Cert selectCertByID(Integer id);

    List<Cert> selectCertByPage(Map<String, Object> map);

    int countCertRecord(Map<String, Object> map);

    int saveCert(Cert cert);

    int updateCert(Cert cert);

    int removeCertByID(Integer id);

}
