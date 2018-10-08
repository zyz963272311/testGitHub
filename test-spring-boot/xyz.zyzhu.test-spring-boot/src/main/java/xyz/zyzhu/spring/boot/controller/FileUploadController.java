package xyz.zyzhu.spring.boot.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.liiwin.file.utils.FileUtil;
import xyz.zyzhu.spring.boot.constance.ResponseConstance;
import xyz.zyzhu.spring.boot.model.BasResponse;
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

	/**
	 * 单文件上传
	 * @param file
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping("/upload")
	@ResponseBody
	public BasResponse handleFileUpload(@RequestParam("file") MultipartFile file)
	{
		BasResponse resp = new BasResponse(ResponseConstance.CODE_SUCCESS, "文件上传成功");
		if (!file.isEmpty())
		{
			BufferedOutputStream out = null;
			try
			{
				String originalFilename = file.getOriginalFilename();
				String uploadPath = FileUtil.getUploadPathByFileName(originalFilename);
				if (uploadPath.endsWith(File.separator))
				{
					uploadPath += File.separator;
				}
				String md5Path = FileUtil.getMD5Path(originalFilename);
				if (!md5Path.endsWith(File.separator))
				{
					md5Path += File.separator;
				}
				out = new BufferedOutputStream(new FileOutputStream(new File(uploadPath + md5Path + originalFilename)));
				out.write(file.getBytes());
				out.flush();
			} catch (FileNotFoundException e)
			{
				e.printStackTrace();
				resp.setErrMessage("上传失败," + e.getMessage());
				return resp;
			} catch (IOException e)
			{
				e.printStackTrace();
				resp.setErrMessage("上传失败," + e.getMessage());
				return resp;
			} finally
			{
				if (out != null)
				{
					try
					{
						out.close();
					} catch (IOException e)
					{
						resp.setErrMessage("关闭流失败" + e.getMessage());
					}
				}
			}
			return resp;
		} else
		{
			resp.setErrMessage("上传失败，因为文件是空的.");
			return resp;
		}
	}

	/**
	 * 多文件上传
	 * @param request
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping(value = "/batch/upload", method = RequestMethod.POST)
	public @ResponseBody BasResponse handleFileUpload(HttpServletRequest request)
	{
		BasResponse response = new BasResponse(ResponseConstance.CODE_SUCCESS, "文件上传成功");
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file-es[]");
		MultipartFile file = null;
		BufferedOutputStream stream = null;
		int size = files.size();
		for (int i = 0; i < size; ++i)
		{
			file = files.get(i);
			if (!file.isEmpty())
			{
				try
				{
					String originalFilename = file.getOriginalFilename();
					String uploadPath = FileUtil.getUploadPathByFileName(originalFilename);
					if (uploadPath.endsWith(File.separator))
					{
						uploadPath += File.separator;
					}
					String md5Path = FileUtil.getMD5Path(originalFilename);
					if (!md5Path.endsWith(File.separator))
					{
						md5Path += File.separator;
					}
					byte[] bytes = file.getBytes();
					File file2 = new File(uploadPath + md5Path + originalFilename);
					if (!file2.exists())
					{
						FileUtil.newFile(file2);
						file2.setWritable(true);
					}
					stream = new BufferedOutputStream(new FileOutputStream(file2));
					stream.write(bytes);
					stream.flush();
				} catch (Exception e)
				{
					response.setErrMessage("You failed to upload " + i + " =>" + e.getMessage());
					return response;
				} finally
				{
					if (stream != null)
					{
						try
						{
							stream.close();
						} catch (IOException e)
						{
							throw new RuntimeException("报错内容", e);
						}
					}
				}
			} else
			{
				response.setErrMessage("文件上传失败:You failed to upload " + i + " becausethe file was empty.");
				return response;
			}
		}
		return response;
	}
}
