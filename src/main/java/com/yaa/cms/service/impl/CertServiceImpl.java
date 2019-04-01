package com.yaa.cms.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yaa.cms.dao.CertDao;
import com.yaa.cms.model.Cert;
import com.yaa.cms.service.CertService;


/**
 * Generator code
 */
@Service
public class CertServiceImpl implements CertService {

    @Autowired
    private CertDao certDao;

    @Override
    public Cert selectCertByID(Integer id) {
        return certDao.selectCertByID(id);
    }

    @Override
    public List<Cert> selectCertByPage(Map<String, Object> map) {
        return certDao.selectCertByPage(map);
    }

    @Override
    public int countCertRecord(Map<String, Object> map) {
        return certDao.countCertRecord(map);
    }

    @Override
    public int saveCert(Cert cert) {
        return certDao.saveCert(cert);
    }

    @Override
    public int updateCert(Cert cert) {
        return certDao.updateCert(cert);
    }

    @Override
    public int removeCertByID(Integer id) {
        return certDao.removeCertByID(id);
    }

}
