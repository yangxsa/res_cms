package com.yaa.cms.service.impl;

import com.yaa.cms.util.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yaa.cms.dao.CertDao;
import com.yaa.cms.model.Cert;
import com.yaa.cms.service.CertService;
import org.springframework.web.multipart.MultipartFile;


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

    @Override
    public Result importExcel(MultipartFile file) {
        Long size = file.getSize();
        String fileType = file.getContentType();
        if(file == null){
            return Result.error("文件不能为空");
        }
        // 文件大小超过2M
        if (size > 2097152) {
            return Result.error("资源解析失败,导入文件超过2M！");
        }
        if(!fileType.contains("xls")){
            return Result.error("仅支持xls文件");
        }
        return Result.ok();
    }

}
