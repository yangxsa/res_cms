package com.yaa.cms.controller;

import com.yaa.cms.model.Dict;
import com.yaa.cms.service.DictService;
import com.yaa.cms.util.PageUtils;
import com.yaa.cms.util.Query;
import com.yaa.cms.util.Result;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/sys/dict")
public class DictController {

	@Autowired
	private DictService dictService;

	String prefix = "system/dict";

	@GetMapping()
	@RequiresPermissions("sys:dict:dict")
	String dict() {
		return prefix + "/dict";
	}

	@ResponseBody
	@GetMapping("/list")
	@RequiresPermissions("sys:dict:dict")
	public PageUtils list(@RequestParam Map<String, Object> params) {
		// 查询列表数据
		Query query = new Query(params);
		List<Dict> dictList = dictService.list(query);
		int total = dictService.count(query);
		PageUtils pageUtils = new PageUtils(dictList, total);
		return pageUtils;
	}

	@GetMapping("/add")
	@RequiresPermissions("sys:dict:add")
	String add() {
		return prefix + "/add";
	}

	@GetMapping("/edit/{id}")
	@RequiresPermissions("sys:dict:edit")
	String edit(@PathVariable("id") Long id, Model model) {
		Dict dict = dictService.get(id);
		model.addAttribute("dict", dict);
		return prefix + "/edit";
	}

	/**
	 * 保存
	 */
	@ResponseBody
	@PostMapping("/save")
	@RequiresPermissions("sys:dict:add")
	public Result save(Dict dict) {
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
	public Result update(Dict dict) {
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
	public List<Dict> listType() {
		return dictService.listType();
	};

	// 类别已经指定增加
	@GetMapping("/add/{type}/{description}")
	@RequiresPermissions("sys:dict:add")
	String addD(Model model, @PathVariable("type") String type, @PathVariable("description") String description) {
		model.addAttribute("type", type);
		model.addAttribute("description", description);
		return prefix + "/add";
	}

	@ResponseBody
	@GetMapping("/list/{type}")
	public List<Dict> listByType(@PathVariable("type") String type) {
		// 查询列表数据
		Map<String, Object> map = new HashMap<>(16);
		map.put("type", type);
		List<Dict> dictList = dictService.list(map);
		return dictList;
	}
}
