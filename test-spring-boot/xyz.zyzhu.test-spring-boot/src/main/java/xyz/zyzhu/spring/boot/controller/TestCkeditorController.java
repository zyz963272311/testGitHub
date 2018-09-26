package xyz.zyzhu.spring.boot.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.CkeditorFileUploadResponse;
import xyz.zyzhu.spring.boot.model.CkeditorModel;
import xyz.zyzhu.spring.boot.model.CkeditorSubmitRequest;
import xyz.zyzhu.spring.boot.model.CkeditorSubmitResponse;
import xyz.zyzhu.spring.boot.service.CkeditorSubmitService;
import xyz.zyzhu.spring.boot.utils.PropertiesUtils;
import xyz.zyzhu.spring.boot.utils.SpringBeanUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午6:30:48</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.TestCkeditorController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/ckeditor")
public class TestCkeditorController
{
	Logger logger = LoggerFactory.getLogger(TestCkeditorController.class);

	@RequestMapping("/testCkeditor")
	public ModelAndView ueditor(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("id");
		ModelAndView mav = new ModelAndView("testCkeditor");
		mav.addObject("id", id);
		return mav;
	}

	@RequestMapping("/testCKEditorList")
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("testCKEditorList");
		return mav;
	}

	@PostMapping("/uploadImage")
	@ResponseBody
	public CkeditorFileUploadResponse uploadImage(@RequestParam("upload") MultipartFile multipartFile)
	{
		CkeditorFileUploadResponse res = new CkeditorFileUploadResponse();
		res.setUploaded(0);
		if (multipartFile == null || multipartFile.isEmpty())
			return res;
		//生成新的文件名及存储位置
		String fileName = multipartFile.getOriginalFilename();
		String newFileName = UUID.randomUUID().toString().replaceAll("-", "").concat(fileName.substring(fileName.lastIndexOf(".")));
		String fullPath = PropertiesUtils.getPropValue("web.upload-path").concat(newFileName);
		//		String fullPath = "E:/upload/".concat(newFileName);
		try
		{
			File target = new File(fullPath);
			if (!target.getParentFile().exists())
			{ //判断文件父目录是否存在
				target.getParentFile().mkdirs();
			}
			multipartFile.transferTo(target);
			String imgUrl = "/upload/".concat(newFileName);
			res.setUploaded(1);
			res.setFileName(fileName);
			res.setUrl(imgUrl);
			return res;
		} catch (IOException ex)
		{
			logger.error("上传图片异常", ex);
		}
		return res;
	}

	@RequestMapping("/submit")
	@ResponseBody
	public CkeditorSubmitResponse submit(CkeditorSubmitRequest req)
	{
		CkeditorSubmitResponse response = null;
		if (req == null)
		{
			response = new CkeditorSubmitResponse();
			response.setStatus(1);
			response.setMsg("上传数据不可位空");
			return response;
		}
		String serviceName = req.getService();
		if (StrUtil.isStrTrimNull(serviceName))
		{
			response = new CkeditorSubmitResponse();
			response.setStatus(2);
			response.setMsg("ServiceName不可为空");
			return response;
		}
		CkeditorSubmitService service = SpringBeanUtils.getBean(serviceName, CkeditorSubmitService.class);
		if (service == null)
		{
			response = new CkeditorSubmitResponse();
			response.setStatus(2);
			response.setMsg("根据service未找到对应的实现类");
			return response;
		}
		CkeditorSubmitResponse result = service.submit(req);
		return result;
	}

	/**
	 * 数据查询
	 * @param id
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(path = { "/get" }, method = { RequestMethod.POST })
	@ResponseBody
	public CkeditorModel get(Map<String,Object> params)
	{
		String id = (String) params.get("id");
		if (StrUtil.isStrTrimNull(id))
		{
			return null;
		}
		BootDatabase db = null;
		try
		{
			db = BootDatabasePoolManager.getDatabaseByClass(CkeditorModel.class, false);
			CkeditorModel query = new CkeditorModel();
			query.setId(id);
			CkeditorModel result = db.query1(query);
			return result;
		} catch (Exception e)
		{
			logger.error(e.getMessage());
		} finally
		{
			if (db != null)
			{
				BootDatabasePoolManager.close(db);
			}
		}
		return null;
	}
}
