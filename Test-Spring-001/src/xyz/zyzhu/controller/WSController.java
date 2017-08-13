package xyz.zyzhu.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <p>
 * 标题： TODO
 * </p>
 * <p>
 * 功能：
 * </p>
 * <p>
 * 所属模块： TODO
 * </p>
 * <p>
 * 版权： Copyright © 2017 zyzhu
 * </p>
 * <p>
 * 公司: zyzhu.xyz
 * </p>
 * <p>
 * 创建日期：2017年8月12日 下午12:31:31
 * </p>
 * <p>
 * 类全名：xyz.zyzhu.controller.WSController
 * </p>
 * 作者：赵玉柱 初审： 复审： 监听使用界面:
 * 
 * @version 8.0
 */
@Component()
@RequestMapping("/api")
public class WSController {
	@RequestMapping("/executeApi")
	public void executeApi(HttpServletRequest request,
			HttpServletResponse response) {
		System.out.println("调用WSController.executeApi");
	}
}
