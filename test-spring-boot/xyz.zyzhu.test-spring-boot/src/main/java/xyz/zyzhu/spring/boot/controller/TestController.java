package xyz.zyzhu.spring.boot.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.config.BasConfig;
import com.liiwin.date.DateUtil;
import com.liiwin.random.RandomString;
import com.liiwin.random.RandomStringImpl;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.datafile.export.DataExport;
import xyz.zyzhu.spring.boot.datafile.export.ZipDataExport;
import xyz.zyzhu.spring.boot.datafile.fileimport.DataImport;
import xyz.zyzhu.spring.boot.datafile.fileimport.ZipDataImport;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.AutoCode;
import xyz.zyzhu.spring.boot.model.Menu1;
import xyz.zyzhu.spring.boot.service.TestService;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
import xyz.zyzhu.spring.config.DruidConfig;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年8月1日 下午4:00:21</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.TestController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/test")
public class TestController
{
	private static Logger	logger	= LoggerFactory.getLogger(TestController.class);
	@Autowired
	TestService				testService;

	@RequestMapping(path = "/testGoodsList", method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView testGoodsList()
	{
		ModelAndView view = new ModelAndView("testGoodsList");
		return view;
	}

	@RequestMapping(path = "/test", method = { RequestMethod.GET, RequestMethod.POST })
	public String test()
	{
		BootDatabase db = BootDatabasePoolManager.getDatabase("zyztest");
		boolean rollback = false;
		try
		{
			db.beginTrans();
			Menu1 menu = new Menu1();
			menu.setMname("0001");
			List<Menu1> query = db.query(menu);
			logger.error("查询完成");
			Menu1 menu1 = new Menu1();
			menu1.setMname("测试名称");
			menu1.setUrl("www.baidu.com");
			menu1.setLimits("*");
			db.save(menu1);
			if (query != null && !query.isEmpty())
			{
				Menu1 menu12 = query.get(0);
				menu12.setLimits(menu12.getLimits() + menu12.getLimits());
				menu12.setSaveMode(2);
				db.save(menu12);
				if (query.size() > 0)
				{
					Menu1 menu13 = query.get(1);
					menu13.setLimits(null);
					menu13.setSaveMode(4);
					db.save(menu13);
				}
			}
			db.commit();
			rollback = false;
			return query != null ? query.toString() : null;
		} finally
		{
			try
			{
				db.rollback(rollback, false);
			} finally
			{
				BootDatabasePoolManager.close(db);
			}
		}
	}

	/**
	 * 测试数据导出
	 * @param expcode
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/testDataExp", method = { RequestMethod.GET, RequestMethod.POST })
	public void testDataExp(@RequestParam(name = "expcode") String expcode, @RequestParam(name = "type", defaultValue = "1") int type, HttpServletResponse response)
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

	/**
	 * 测试数据导入
	 * @param file
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/testDataImp", method = { RequestMethod.GET, RequestMethod.POST })
	public String testDataImp(@RequestParam("fileName") MultipartFile file, @RequestParam(name = "type", defaultValue = "1") int type)
	{
		try
		{
			if (file == null || file.isEmpty())
			{
				throw new RuntimeException("file不可为空");
			}
			File tempDir = null;
			String tempFilePath = BasConfig.getPropertie("temp_file-path");
			if (StrUtil.isStrTrimNull(tempFilePath))
			{
				throw new RuntimeException("请配置临时文件夹路径");
			}
			if (!tempFilePath.endsWith(File.separator))
			{
				tempFilePath = tempFilePath + File.separator;
			}
			String zipTempFilePath = tempFilePath + "zip" + File.separator;
			File zipDir = new File(zipTempFilePath);
			if (!zipDir.exists())
			{
				zipDir.mkdirs();
			}
			RandomString random = new RandomStringImpl();
			String pathNamePrefix = DateUtil.formateDate(DateUtil.DATA_FORMATE_9);
			String zipTempDir = null;
			boolean exist = true;
			String pathName = null;
			//创建临时文件夹，用于存放当前数据
			while (exist)
			{
				String randomString = random.getRandomString(10, 'a', 'z');
				pathName = pathNamePrefix + randomString + File.separator;
				zipTempDir = zipTempFilePath + pathName;
				tempDir = new File(zipTempDir);
				exist = tempDir.exists();
				if (!exist)
				{
					tempDir.mkdirs();
					break;
				}
			}
			exist = true;
			File tempFile = null;
			String tempFileDir = null;
			String tempFileName = null;
			//创建临时文件夹，用于存放当前数据
			while (exist)
			{
				String randomString = random.getRandomString(10, 'a', 'z');
				tempFileName = pathNamePrefix + randomString + ".zip";
				tempFileDir = zipTempDir + tempFileName;
				tempFile = new File(tempFileDir);
				exist = tempFile.exists();
				if (!exist)
				{
					tempFile.createNewFile();
					break;
				}
			}
			file.transferTo(tempFile);
			DataImport dataImport = null;
			switch (type)
			{
			case 1:
				dataImport = new ZipDataImport(tempFile, null);
				break;
			default:
				throw new RuntimeException("不存在对应的类型");
			}
			String result = dataImport.dataImport();
			return result;
		} catch (Exception e)
		{
		}
		return null;
	}

	/**
	 * @return
	 * 赵玉柱
	 */
	private String test1()
	{
		DruidConfig cfg = SpringBeanUtils.getBean(DruidConfig.class);
		System.out.println(cfg);
		System.out.println(cfg.defaultDataSource());
		Environment bean = SpringBeanUtils.getBean(Environment.class);
		System.out.println(bean.getProperty("spring.datasource.type"));
		AutoCode test = testService.test();
		return test.toString();
	}
}
