package xyz.zyzhu.spring.boot.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.utils.BeanUtils;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.model.BootStrapDeleteRequestModel;
import xyz.zyzhu.spring.boot.model.BootStrapQueryRequestModel;
import xyz.zyzhu.spring.boot.model.BootStrapQueryResponseModel;
import xyz.zyzhu.spring.boot.model.BootStrapUpdateRequestModel;
import xyz.zyzhu.spring.boot.model.BootStrapUpdateResponseModel;
import xyz.zyzhu.spring.boot.utils.ModelUtils;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2017年12月3日 上午12:01:59</p>
 * <p>类全名：xyz.zyzhu.spring.boot.controller.MenuController</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@EnableAutoConfiguration
@RestController
@RequestMapping("/")
public class TestBootStarp_TableController
{
	@RequestMapping("/testBootStrap_table")
	public ModelAndView index()
	{
		ModelAndView mav = new ModelAndView("testBootStarp_table");
		return mav;
	}

	/**
	 * 数据查询
	 * @param request
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping("/customer-data")
	@ResponseBody
	public BootStrapQueryResponseModel getData(BootStrapQueryRequestModel request)
	{
		if (request == null)
		{
			return null;
		}
		String tablename = request.getTablename();
		if (tablename == null)
		{
			throw new RuntimeException("表名不可为空");
		}
		List<Object> queryParams = new ArrayList<>();
		List<Object> queryCountParams = new ArrayList<>();
		BootDatabase db = BootDatabasePoolManager.getReadDatabaseByTable(tablename);
		String sql = ModelUtils.getQueryString(request, db, queryParams);
		String sqlCount = ModelUtils.getQueryCountString(request, db, queryCountParams);
		List<Map<String,Object>> queryResult = db.getListMapFromSqlByListParam(sql, queryParams);
		Map<String,Object> countResult = db.getMapFromSqlByListParam(sqlCount, queryCountParams);
		int totalCount = StrUtil.obj2int(countResult.get("count"));
		BootStrapQueryResponseModel responseModel = new BootStrapQueryResponseModel();
		//设置数据
		responseModel.withPageMessage(request).withTotalSize(totalCount).withRows(queryResult);
		BootDatabasePoolManager.close(db);
		return responseModel;
	}

	/**
	 * 数据删除
	 * @param deleteRequest
	 * 赵玉柱
	 */
	@RequestMapping("/delete-customer")
	public void delete(BootStrapDeleteRequestModel deleteRequest)
	{
		if (deleteRequest != null)
		{
			String table = deleteRequest.getTable();
			if (StrUtil.isStrTrimNull(table))
			{
				throw new RuntimeException("表名不可为空");
			}
			List<Map<String,Object>> delRequest = deleteRequest.getDelRequest();
			if (delRequest != null && !delRequest.isEmpty())
			{
				BootDatabase db = BootDatabasePoolManager.getWriteDatabaseByTable(table);
				boolean rollback = true;
				try
				{
					db.beginTrans();
					db.deleteTableList(table, delRequest);
					db.commit();
					rollback = false;
				} finally
				{
					try
					{
						db.rollback(rollback);
					} finally
					{
						BootDatabasePoolManager.close(db);
					}
				}
			} else
			{
				throw new RuntimeException("表信息不可为空");
			}
		}
	}

	/**
	 * 数据更新 包括修改与新增
	 * @param requestModel
	 * @return
	 * 赵玉柱
	 */
	@RequestMapping("/update-customer")
	public BootStrapUpdateResponseModel updateTable(BootStrapUpdateRequestModel requestModel)
	{
		if (requestModel == null)
		{
			throw new RuntimeException("参数不可为空");
		}
		String table = requestModel.getTable();
		List<Map<String,Object>> tableValues = requestModel.getTableValues();
		String modelClass = requestModel.getModelClass();
		Integer options = requestModel.getOptions();
		if (StrUtil.isStrTrimNull(table))
		{
			throw new RuntimeException("表名不可为空");
		}
		if (tableValues == null || tableValues.isEmpty())
		{
			throw new RuntimeException("表数据不可为空");
		}
		if (options == null || options == 0)
		{
			options = 1;
		}
		if (StrUtil.isStrTrimNull(modelClass))
		{
			throw new RuntimeException("转换的类路径不可为空");
		}
		Class<BasModel> basModelClass = BeanUtils.getClassByPath(modelClass);
		List<BasModel> models = new ArrayList<>();
		for (Map<String,Object> map : tableValues)
		{
			JSONObject json = new JSONObject(map);
			BasModel model = JSONObject.toJavaObject(json, basModelClass);
			model.setSaveMode(options);
			models.add(model);
		}
		BootDatabase db = BootDatabasePoolManager.getWriteDatabaseByTable(table);
		boolean rollback = true;
		try
		{
			db.beginTrans();
			db.saveList(models);
			db.commit();
			rollback = false;
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
		JSONArray jsonarray = new JSONArray();
		for (BasModel model : models)
		{
			jsonarray.add(model.getMapValues());
		}
		BootStrapUpdateResponseModel responseModel = new BootStrapUpdateResponseModel();
		responseModel.setResultValues(jsonarray);
		return responseModel;
	}
}
