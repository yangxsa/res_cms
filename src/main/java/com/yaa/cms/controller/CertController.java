package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;

import java.util.List;
import java.util.Map;

import com.yaa.cms.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.yaa.cms.model.Cert;
import com.yaa.cms.service.CertService;
import org.springframework.web.multipart.MultipartFile;


@Controller
public class CertController extends BaseController {

    String prefix = "main/cert";

    @Autowired
    private CertService certService;


    @GetMapping(value = "/bsy/cert")
    @RequiresPermissions("bsy:cert:cert")
    public String list(@RequestParam(value = "page", defaultValue = "1") int page) {
        Map<String, Object> param = buildPageParam(page);
        //查询列表数据
        int total = certService.countCertRecord(param);
        List<Cert> certList = certService.selectCertByPage(param);
        this.setPageNavigation(page, total);
        request.setAttribute("certList", certList);
        return prefix + "/cert";
    }

    @GetMapping("/bsy/cert/add")
    @RequiresPermissions("bsy:cert:add")
    public String add() {
        return "main/cert/add";
    }


    @GetMapping("/bsy/cert/edit/{id}")
    @RequiresPermissions("bsy:cert:edit")
    public String edit(@PathVariable("id") Integer id, Model model) {
		Cert cert =certService.selectCertByID(id);
        model.addAttribute("cert", cert);
        return "main/cert/edit";
    }

    /**
     * 保存
     */
    @ResponseBody
    @PostMapping("/bsy/cert/save")
    @RequiresPermissions("bsy:cert:add")
    public Result save( Cert cert) {
        if (certService.saveCert(cert) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("/bsy/cert/update")
    @RequiresPermissions("bsy:cert:edit")
    public Result update( Cert cert) {
        certService.updateCert(cert);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/bsy/cert/remove")
    @ResponseBody
    @RequiresPermissions("bsy:cert:remove")
    public Result remove( Integer id) {
        if (certService.removeCertByID(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

    @ResponseBody
    @PostMapping("/bsy/cert/importExcel")
    @RequiresPermissions("bsy:cert:import")
    public Result importExcel(@RequestParam("file") MultipartFile file){
        return certService.importExcel(file);
    }

    @GetMapping("/cert/{id}")
    public String cert(@PathVariable("id") Integer id, Model model){
        if(id > 0) {
            Cert cert = certService.selectCertByID(id);
            model.addAttribute("cert", cert);
        }else{
            return "error/404.html";
        }
        return "main/cert/certpaper";
    }

}
