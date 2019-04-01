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


@Controller
@RequestMapping("/bsy/cert")
public class CertController extends BaseController {

    String prefix = "main/cert";

    @Autowired
    private CertService certService;


    @GetMapping(value = "")
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

    @GetMapping("/add")
    @RequiresPermissions("bsy:cert:add")
    public String add() {
        return "main/cert/add";
    }


    @GetMapping("/edit/{id}")
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
    @PostMapping("/save")
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
    @RequestMapping("/update")
    @RequiresPermissions("bsy:cert:edit")
    public Result update( Cert cert) {
			certService.updateCert(cert);
        return Result.ok();
    }

    /**
     * 删除
     */
    @PostMapping("/remove")
    @ResponseBody
    @RequiresPermissions("bsy:cert:remove")
    public Result remove( Integer id) {
        if (certService.removeCertByID(id) > 0) {
            return Result.ok();
        }
        return Result.error();
    }

}
