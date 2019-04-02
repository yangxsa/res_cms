package com.yaa.cms.service;

import com.yaa.cms.model.Cert;
import com.yaa.cms.util.Result;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * Generator code
 */
public interface CertService {

	Cert selectCertByID(Integer id);

    List<Cert> selectCertByPage(Map<String, Object> map);

    int countCertRecord(Map<String, Object> map);

    int saveCert(Cert cert);

    int updateCert(Cert cert);

    int removeCertByID(Integer id);

    Result importExcel(MultipartFile file);

}
