package xyz.zyzhu.spring.boot.controller;

import java.io.File;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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
import xyz.zyzhu.spring.boot.datafile.fileimport.DataImport;
import xyz.zyzhu.spring.boot.datafile.fileimport.ZipDataImport;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月28日 下午2:26:39</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.DataImportController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/imp")
public class DataImportController
{
	@RequestMapping(path = "/get")
	public ModelAndView get()
	{
		ModelAndView view = new ModelAndView("imp");
		return view;
	}

	/**
	 * 测试数据导入
	 * @param file
	 * @param type
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = "/dataImp", method = { RequestMethod.GET, RequestMethod.POST })
	public String dataImp(@RequestParam("fileName") MultipartFile file, @RequestParam(name = "type", defaultValue = "1") int type)
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
}
