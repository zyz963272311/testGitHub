package xyz.zyzhu.spring.boot.datafile.fileimport;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.datafile.domain.DataImportDetail;
import xyz.zyzhu.spring.boot.model.DataimpgDef;
/**
 * <p>标题： Zip文件数据导入</p>
 * <p>功能： </p>
 * <p>所属模块： boot</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月3日 下午8:47:49</p>
 * <p>类全名：xyz.zyzhu.spring.boot.datafile.fileimport.ZipDataImport</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class ZipDataImport extends DefaultDataImport
{
	private ZipFile zipFile;

	/**
	 * 
	 */
	public ZipDataImport(File file, Map<String,Object> params)
	{
		super(params);
		if (file == null)
		{
			throw new RuntimeException("file不可为空");
		}
		this.params = params;
		try
		{
			zipFile = new ZipFile(file);
		} catch (IOException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	@Override
	public List<DataImportDetail> getImportDetails()
	{
		try
		{
			List<DataImportDetail> details = new ArrayList<>();
			ZipEntry entry = zipFile.getEntry("export.info");
			if (entry != null)
			{
				Map<String,String> fileInfoMap = new HashMap<>();
				InputStream inputStream = zipFile.getInputStream(entry);
				BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
				String line;
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null)
				{
					sb.append(line);
				}
				if (sb.length() == 0)
				{
					throw new RuntimeException("zip文件格式错误");
				}
				br.close();
				JSONObject json = JSONObject.parseObject(sb.toString());
				if (json == null || json.isEmpty())
				{
					throw new RuntimeException("数据不可为空");
				}
				JSONArray jsonArray = json.getJSONArray("fileinfo");
				if (jsonArray == null || jsonArray.isEmpty())
				{
					throw new RuntimeException("zip文件格式错误");
				}
				int length = jsonArray.size();
				for (int i = 0; i < length; i++)
				{
					JSONObject js = jsonArray.getJSONObject(i);
					fileInfoMap.put(js.getString("rno"), js.getString("filename"));
				}
				List<String> keyList = new ArrayList<>(fileInfoMap.keySet());
				Collections.sort(keyList);
				for (String rno : keyList)
				{
					String fileName = fileInfoMap.get(rno);
					ZipEntry element = zipFile.getEntry(fileName);
					if (element == null)
					{
						throw new RuntimeException("zip文件格式错误");
					}
					InputStream eis = zipFile.getInputStream(element);
					BufferedReader ebr = new BufferedReader(new InputStreamReader(eis));
					String eline;
					StringBuffer esb = new StringBuffer();
					while ((eline = ebr.readLine()) != null)
					{
						esb.append(eline);
					}
					Document parseText = DocumentHelper.parseText(esb.toString());
					Element tableElement = parseText.getRootElement();
					Element versionElement = tableElement.element("version");
					String version = versionElement.getText();
					Element remarkElement = tableElement.element("remark");
					String remark = remarkElement.getText();
					Element beforeExecElement = tableElement.element("beforeExec");
					String beforeExec = beforeExecElement.getText();
					Element saveModeElement = tableElement.element("saveMode");
					String saveMode = saveModeElement.getText();
					Element tablenameElement = tableElement.element("tablename");
					String tablename = tablenameElement.getText();
					Element columnsElement = tableElement.element("columns");
					String columns = columnsElement.getText();
					Element dataElement = tableElement.element("data");
					String data = dataElement.getText();
					DataImportDetail detail = new DataImportDetail();
					DataimpgDef impgDef = new DataimpgDef();
					impgDef.setBeforeExec(beforeExec);
					impgDef.setColumns(columns);
					impgDef.setSavemode(StrUtil.obj2int(saveMode));
					impgDef.setTablename(tablename);
					impgDef.setRemark(remark);
					impgDef.setVersion(version);
					detail.setImpgDef(impgDef);
					JSONArray impDataArray = JSONArray.parseArray(data);
					if (impDataArray != null)
					{
						List<JSONObject> javaList = (List<JSONObject>) impDataArray.toJavaList(JSONObject.class);
						List<Map<String,Object>> list = new ArrayList<>();
						for (JSONObject obj : javaList)
						{
							Map<String,Object> map = obj;
							list.add(map);
						}
						detail.setImpDatas(list);
					}
					details.add(detail);
					br.close();
				}
			} else
			{
				throw new RuntimeException("zip文件格式错误");
			}
			return details;
		} catch (Exception e)
		{
			throw new RuntimeException("zip数据导入失败");
		}
	}
}
