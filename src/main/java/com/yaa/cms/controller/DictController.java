package com.yaa.cms.controller;

import com.yaa.cms.controller.base.BaseController;
import com.yaa.cms.model.SysDict;
import com.yaa.cms.service.DictService;
import com.yaa.cms.util.PageUtil;
import com.yaa.cms.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sys/dict")
public class DictController extends BaseController {

	@Autowired
	private DictService dictService;

	String prefix = "system/dict";

	@GetMapping()
	@RequiresPermissions("sys:dict:dict")
	public String list(@RequestParam(value = "page",defaultValue = "1")int page) {
		// 查询列表数据
		Map<String,Object> param = buildPageParam(page);
		int total = dictService.count(param);
		List<SysDict> dictList = dictService.list(param);
		this.setPageNavigation(page,total);
		request.setAttribute("dictList",dictList);
		return prefix + "/dict";
	}

	@GetMapping("/add")
	@RequiresPermissions("sys:dict:add")
	String add() {
		return prefix + "/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:dict:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		SysDict dict = dictService.get(id);
		model.addAttribute("dict", dict);
		return prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dict:add")
	public Result save(SysDict dict) {
		if (dictService.save(dict) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * 修改
	 */
	@ResponseBody
	@RequestMapping("/update")
	@RequiresPermissions("sys:dict:edit")
	public Result update(SysDict dict) {
		dictService.update(dict);
		return Result.ok();
	}

	/**
	 * 删除
	 */
	@PostMapping("/remove")
	@ResponseBody
	@RequiresPermissions("sys:dict:remove")
	public Result remove(Long id) {
		if (dictService.remove(id) > 0) {
			return Result.ok();
		}
		return Result.error();
	}

	/**
	 * 删除
	 */
	@PostMapping("/batchRemove")
	@ResponseBody
	@RequiresPermissions("common:dict:batchRemove")
	public Result remove(@RequestParam("ids[]") Long[] ids) {
		dictService.batchRemove(ids);
		return Result.ok();
	}

	@GetMapping("/type")
	@ResponseBody
	public List<SysDict> listType() {
		return dictService.listType();
	}

	// 类别已经指定增加
	@GetMapping("/add/{type}/{description}")
	@RequiresPermissions("sys:dict:add")
	String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
		model.addAttribute("type", type);
		model.addAttribute("description", description);
		return prefix + "/add";
	}

}
