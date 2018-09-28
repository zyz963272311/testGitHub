package xyz.zyzhu.spring.boot.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月28日 下午10:48:16</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.FileUploadController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/fileupload")
public class FileUploadController
{
	@RequestMapping("/get")
	public ModelAndView get()
	{
		ModelAndView view = new ModelAndView("testFileUpload");
		return view;
	}
}
