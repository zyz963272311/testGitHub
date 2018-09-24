package com.baidu.ueditor.upload;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import com.baidu.ueditor.PathFormat;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.FileType;
import com.baidu.ueditor.define.State;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午1:04:22</p>
 * <p>类全名：xyz.zyzhu.spring.boot.baidu.ueditor.upload.BinaryUploader</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class BinaryUploader
{
	public static final State save(HttpServletRequest request, Map<String,Object> conf)
	{
		// FileItemStream fileStream = null;
		// boolean isAjaxUpload = request.getHeader( "X_Requested_With" ) != null;
		if (!ServletFileUpload.isMultipartContent(request))
		{
			return new BaseState(false, AppInfo.NOT_MULTIPART_CONTENT);
		}
		// ServletFileUpload upload = new ServletFileUpload(
		// 	new DiskFileItemFactory());
		//
		// if ( isAjaxUpload ) {
		//     upload.setHeaderEncoding( "UTF-8" );
		// }
		try
		{
			// FileItemIterator iterator = upload.getItemIterator(request);
			//
			// while (iterator.hasNext()) {
			// 	fileStream = iterator.next();
			//
			// 	if (!fileStream.isFormField())
			// 		break;
			// 	fileStream = null;
			// }
			//
			// if (fileStream == null) {
			// 	return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			// }
			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			MultipartFile multipartFile = multipartRequest.getFile(conf.get("fieldName").toString());
			if (multipartFile == null)
			{
				return new BaseState(false, AppInfo.NOTFOUND_UPLOAD_DATA);
			}
			String savePath = (String) conf.get("savePath");
			//String originFileName = fileStream.getName();
			String originFileName = multipartFile.getOriginalFilename();
			String suffix = FileType.getSuffixByFilename(originFileName);
			originFileName = originFileName.substring(0, originFileName.length() - suffix.length());
			savePath = savePath + suffix;
			long maxSize = ((Long) conf.get("maxSize")).longValue();
			if (!validType(suffix, (String[]) conf.get("allowFiles")))
			{
				return new BaseState(false, AppInfo.NOT_ALLOW_FILE_TYPE);
			}
			savePath = PathFormat.parse(savePath, originFileName);
			String physicalPath = (String) conf.get("rootPath") + savePath;
			//InputStream is = fileStream.openStream();
			InputStream is = multipartFile.getInputStream();
			State storageState = StorageManager.saveFileByInputStream(is, physicalPath, maxSize);
			is.close();
			if (storageState.isSuccess())
			{
				storageState.putInfo("url", PathFormat.format(savePath));
				storageState.putInfo("type", suffix);
				storageState.putInfo("original", originFileName + suffix);
			}
			return storageState;
			// } catch (FileUploadException e) {
			// 	return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e)
		{
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}

	private static boolean validType(String type, String[] allowTypes)
	{
		List<String> list = Arrays.asList(allowTypes);
		return list.contains(type);
	}
}
