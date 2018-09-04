package xyz.zyzhu.spring.boot.datafile.export;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.config.BasConfig;
import com.liiwin.date.DateUtil;
import com.liiwin.file.utils.FileUtil;
import com.liiwin.file.utils.FileZipUtil;
import com.liiwin.random.RandomString;
import com.liiwin.random.RandomStringImpl;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.datafile.domain.DataExportDetail;
import xyz.zyzhu.spring.boot.model.DataexpDef;
import xyz.zyzhu.spring.boot.model.DataexpgDef;
/**
 * <p>标题： Zip文件格式数据导出</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年8月29日 下午2:06:10</p>
 * <p>类全名：com.liiwin.datafile.export.ZipDataExport</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ZipDataExport extends DefaultDataExport
{
	@Override
	protected File buildExportFile(DataexpDef exportDef, List<DataExportDetail> details)
	{
		File resultFile = null;
		List<Writer> fileWriters = new ArrayList<>();
		List<XMLWriter> xmlWriters = new ArrayList<>();
		File file = null;
		try
		{
			if (details != null && !details.isEmpty())
			{
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
					file = new File(zipTempDir);
					exist = file.exists();
					if (!exist)
					{
						file.mkdirs();
						break;
					}
				}
				File export = new File(zipTempDir + "export.info");
				//创建新文件
				export.createNewFile();
				//设置文件可以写
				export.setWritable(true);
				FileWriter write = new FileWriter(export);
				fileWriters.add(write);
				JSONObject json = new JSONObject();
				JSONArray fileinfo = new JSONArray();
				for (DataExportDetail detail : details)
				{
					JSONObject fileJsonInfo = new JSONObject();
					exist = true;
					File xmlFile = null;
					String xmlTempDir = null;
					String xmlTempFile = null;
					//创建临时文件夹，用于存放当前数据
					while (exist)
					{
						String randomString = random.getRandomString(10, 'a', 'z');
						xmlTempFile = pathNamePrefix + randomString + ".xml";
						xmlTempDir = zipTempDir + xmlTempFile;
						xmlFile = new File(xmlTempDir);
						exist = xmlFile.exists();
						if (!exist)
						{
							xmlFile.createNewFile();
							break;
						}
					}
					FileWriter writer = new FileWriter(xmlFile);
					fileWriters.add(writer);
					DataexpgDef dataexpgDef = detail.getDataexpDef();
					//表名
					String tablename = dataexpgDef.getTablename();
					//查询列名
					String querycolumns = dataexpgDef.getQuerycolumns();
					//查询条件
					//					String queryfilter = dataexpgDef.getQueryfilter();
					//备注
					String remark = dataexpgDef.getRemark();
					//序号
					String rno = dataexpgDef.getRno();
					String beforeExec = dataexpgDef.getBeforeExec();
					int dataSaveMode = dataexpgDef.getDataSaveMode();
					//查询出的数据
					List<Map<String,Object>> exportData = detail.getExportData();
					Element table = DocumentHelper.createElement("table");
					//版本号
					Element version = DocumentHelper.createElement("version");
					setText(version, "1.0");
					table.add(version);
					//备注
					Element remarkElement = DocumentHelper.createElement("remark");
					setText(remarkElement, remark);
					table.add(remarkElement);
					//执行前删除
					Element beforeExecElement = DocumentHelper.createElement("beforeExec");
					setText(beforeExecElement, beforeExec);
					table.add(beforeExecElement);
					//存盘类型
					Element saveMode = DocumentHelper.createElement("saveMode");
					setText(saveMode, dataSaveMode + "");
					table.add(saveMode);
					//表名
					Element tablenameElement = DocumentHelper.createElement("tablename");
					setText(tablenameElement, tablename);
					table.add(tablenameElement);
					//列名
					Element columnsElement = DocumentHelper.createElement("columns");
					setText(columnsElement, querycolumns);
					table.add(columnsElement);
					//数据
					Element dataElement = DocumentHelper.createElement("data");
					JSONArray jsonArray = (JSONArray) JSONArray.toJSON(exportData);
					setText(dataElement, jsonArray.toJSONString());
					table.add(dataElement);
					Document document = DocumentHelper.createDocument();
					document.add(table);
					OutputFormat formater = OutputFormat.createPrettyPrint();
					formater.setEncoding("UTF-8");
					StringWriter out = new StringWriter();
					XMLWriter xmlWriter = new XMLWriter(out, formater);
					xmlWriter.write(document);
					xmlWriters.add(xmlWriter);
					String xmlFileInfor = out.toString();
					writer.write(xmlFileInfor);
					writer.flush();
					fileJsonInfo.put("rno", rno);
					fileJsonInfo.put("filename", xmlTempFile);
					fileinfo.add(fileJsonInfo);
				}
				json.put("fileinfo", fileinfo);
				write.write(json.toString());
				write.flush();
				String zipFileName = pathName.substring(0, pathName.length() - 1) + ".zip";
				FileZipUtil.file2Zip(new String[] { zipTempDir }, zipTempDir.substring(0, zipFileName.length() - 1), zipFileName);
				resultFile = new File(zipTempDir.substring(0, zipTempDir.length() - 1) + ".zip");
			}
		} catch (Exception e)
		{
			throw new RuntimeException(e);
		} finally
		{
			for (Writer write : fileWriters)
			{
				if (write != null)
				{
					try
					{
						write.close();
					} catch (IOException e)
					{
					}
				}
			}
			for (XMLWriter xmlWriter : xmlWriters)
			{
				if (xmlWriter != null)
				{
					try
					{
						xmlWriter.close();
					} catch (Exception e)
					{
					}
				}
			}
		}
		if (file != null)
		{
			try
			{
				FileUtil.deleteFile(file);
			} catch (Exception e)
			{
			}
		}
		return resultFile;
	}

	private void setText(Element e, String text)
	{
		if (e == null || StrUtil.isStrTrimNull(text))
		{
			return;
		}
		e.setText(text);
	}
}
