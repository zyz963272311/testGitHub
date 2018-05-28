package xyz.zyzhu.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import xyz.zyzhu.spring.boot.model.Menu;
import xyz.zyzhu.spring.boot.service.MenuService;

/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月3日 上午12:01:59</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.MenuController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/menu")
public class MenuController {
	@Autowired
	MenuService service;
	@RequestMapping(value = "list", method = { RequestMethod.GET })
	public ModelAndView getListMenu()
	{
		List<Menu> listMenu = service.listMenu();
		ModelAndView mav = new ModelAndView("menu");
		mav.addObject("menuList", listMenu);
		return mav;
	}
	@RequestMapping(value = "insert", method = { RequestMethod.GET })
	public ModelAndView insert(Menu menu)
	{
		ModelAndView mav = new ModelAndView("menu");
		service.insert(menu);
		return mav;
	}
	@RequestMapping(value = "listpage", method = { RequestMethod.GET })
	public ModelAndView listPage(int page,int size)
	{
		Page<Menu> listPage = service.listPage(page, size);
		ModelAndView mav = new ModelAndView("menu");
		List<Menu> content = listPage.getContent();
		long totalElements = listPage.getTotalElements();
		int size2 = listPage.getSize();
		int totalPages = listPage.getTotalPages();
		mav.addObject("menuList", content);
		mav.addObject("total", totalElements);
		mav.addObject("size", size2);
		mav.addObject("pages", totalPages);
		return mav;
	}
	@RequestMapping(value = "listparamspage", method = { RequestMethod.GET })
	public ModelAndView listParamsPage(int page,int size,Menu menu)
	{
		Page<Menu> listPage = service.listPage(page, size,menu);
		ModelAndView mav = new ModelAndView("menu");
		List<Menu> content = listPage.getContent();
		long totalElements = listPage.getTotalElements();
		int size2 = listPage.getSize();
		int totalPages = listPage.getTotalPages();
		mav.addObject("menuList", content);
		mav.addObject("total", totalElements);
		mav.addObject("size", size2);
		mav.addObject("pages", totalPages);
		return mav;
	}
	@RequestMapping(value = "listparamspagea", method = { RequestMethod.GET })
	public ModelAndView listParamsPage(int page,int size,ArrayList<String> a)
	{
		Page<Menu> listPage = service.listPage(page, size,null);
		ModelAndView mav = new ModelAndView("menu");
		List<Menu> content = listPage.getContent();
		long totalElements = listPage.getTotalElements();
		int size2 = listPage.getSize();
		int totalPages = listPage.getTotalPages();
		mav.addObject("menuList", content);
		mav.addObject("total", totalElements);
		mav.addObject("size", size2);
		mav.addObject("pages", totalPages);
		return mav;
	}
}
