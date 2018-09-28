package xyz.zyzhu.spring.boot.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.datafile.export.DataExport;
import xyz.zyzhu.spring.boot.datafile.export.ZipDataExport;
/**
 * <p>标题： 数据导入导出定义与实现controller</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月28日 下午1:45:56</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.DataExportAndImportController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/exp")
public class DataExportController
{
	@RequestMapping(path = "/get")
	public ModelAndView get()
	{
		ModelAndView view = new ModelAndView("exp");
		return view;
	}

	/**
	 * 测试数据导出
	 * @param expcode
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/dataExp", method = { RequestMethod.GET, RequestMethod.POST })
	public void dataExp(@RequestParam(name = "expcode") String expcode, @RequestParam(name = "type", defaultValue = "1") int type, HttpServletResponse response)
	{
		DataExport ecport = null;
		switch (type)
		{
		case 1:
			ecport = new ZipDataExport();
			break;
		default:
			throw new RuntimeException("不存在对应的类型");
		}
		String export = ecport.export(expcode);
		if (!StrUtil.isStrTrimNull(export))
		{
			int p = export.lastIndexOf(File.separator);
			String filename = null;
			if (p > 0)
			{
				filename = export.substring(p + 1);
			}
			File file = new File(export);
			if (file.exists() && file.isFile() && StrUtil.isNoStrTrimNull(filename))
			{
				response.setContentType("application/force-download");//设置文件为下载
				response.addHeader("Content-Disposition", "attachment;fileName=" + filename);// 设置文件名
				byte[] buffer = new byte[1024];
				FileInputStream fis = null;
				BufferedInputStream bis = null;
				try
				{
					fis = new FileInputStream(file);
					bis = new BufferedInputStream(fis);
					OutputStream os = response.getOutputStream();
					int i = bis.read(buffer);
					while (i != -1)
					{
						os.write(buffer, 0, i);
						i = bis.read(buffer);
					}
				} catch (Exception e)
				{
				} finally
				{
					if (fis != null)
					{
						try
						{
							fis.close();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
					}
					if (bis != null)
					{
						try
						{
							bis.close();
						} catch (IOException e)
						{
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
}
