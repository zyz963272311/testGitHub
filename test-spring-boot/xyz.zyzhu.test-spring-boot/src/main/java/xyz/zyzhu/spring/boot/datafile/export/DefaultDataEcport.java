package xyz.zyzhu.spring.boot.datafile.export;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.liiwin.db.DatabaseCacheUtils;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.datafile.export.domain.DataExportDetail;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.DataexpDef;
import xyz.zyzhu.spring.boot.model.DataexpgDef;
/**
 * <p>标题： zip文件格式数据导出</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2018年8月29日 下午2:05:12</p>
 * <p>类全名：com.liiwin.datafile.export.DefaultDataEcport</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public abstract class DefaultDataEcport implements DataEcport
{
	@Override
	public String export(String exportcode)
	{
		BootDatabase configDb = BootDatabasePoolManager.getReadDatabaseByClass(DataexpDef.class);
		List<BootDatabase> dbs = new ArrayList<>();
		try
		{
			DataexpDef exportDef = getExportDef(exportcode, configDb);
			if (exportDef == null)
			{
				throw new RuntimeException("根据导出编号未找到数据导出定义" + exportcode);
			}
			List<DataexpgDef> exportgDefs = getExportgDefs(exportDef, configDb);
			if (exportgDefs == null || exportgDefs.isEmpty())
			{
				throw new RuntimeException("根据导出定义编号未找到数据导出定义明细" + exportcode);
			}
			List<DataExportDetail> exportDetails = getExportDetails(exportgDefs, dbs);
			File resultFile = buildExportFile(exportDetails);
			String absolutePath = resultFile.getAbsolutePath();
			return absolutePath;
		} finally
		{
			try
			{
				BootDatabasePoolManager.close(configDb);
			} catch (Exception e)
			{
			}
			if (dbs != null && !dbs.isEmpty())
			{
				for (BootDatabase db : dbs)
				{
					try
					{
						BootDatabasePoolManager.close(db);
					} catch (Exception e)
					{
					}
				}
			}
		}
	}

	/**
	 * 1、根据编号获取数据导出定义
	 * @param exportcode
	 * @param configDb
	 * @return
	 * 赵玉柱
	 */
	protected DataexpDef getExportDef(String exportcode, BootDatabase configDb)
	{
		DataexpDef queryDef = new DataexpDef();
		queryDef.setExportcode(exportcode);
		DataexpDef result = configDb.query1(queryDef);
		return result;
	}

	/**
	 * 2、根据导出定义获取导出定义明细
	 * @param dataexpDef
	 * @param configDb
	 * @return
	 * 赵玉柱
	 */
	protected List<DataexpgDef> getExportgDefs(DataexpDef dataexpDef, BootDatabase configDb)
	{
		DataexpgDef queryGdef = new DataexpgDef();
		queryGdef.setMid(dataexpDef.getId());
		List<DataexpgDef> redult = configDb.query(queryGdef);
		return redult;
	}

	/**
	 * 3、根据导出定义明细获取导出数据
	 * @param dataexpDefs
	 * @param db
	 * @return
	 * 赵玉柱
	 */
	protected List<DataExportDetail> getExportDetails(List<DataexpgDef> dataexpDefs, List<BootDatabase> dbs)
	{
		List<DataExportDetail> details = new ArrayList<>();
		for (DataexpgDef dataexpDef : dataexpDefs)
		{
			DataExportDetail exportDetail = getExportDetail(dataexpDef, dbs);
			details.add(exportDetail);
		}
		return details;
	}

	/**
	 * 3.1、根据导出定义明细获取导出数据
	 * @param dataexpDef
	 * @param dbs
	 * @return
	 * 赵玉柱
	 */
	protected DataExportDetail getExportDetail(DataexpgDef dataexpDef, List<BootDatabase> dbs)
	{
		DataExportDetail detail = new DataExportDetail();
		detail.setDataexpDef(dataexpDef);
		String tableName = dataexpDef.getTableName();
		int dbIdx = DatabaseCacheUtils.getUseableDbByDbName(dbs, tableName);
		BootDatabase db = null;
		if (dbIdx >= 0)
		{
			db = dbs.get(dbIdx);
		} else
		{
			db = BootDatabasePoolManager.getReadDatabaseByTable(tableName);
			dbs.add(db);
		}
		String sql = buildSql(dataexpDef);
		List<Map<String,Object>> esprotData = db.getListMapFromSql(sql);
		detail.setExportData(esprotData);
		return detail;
	}

	/**
	 * 根据定义组装sql
	 * @param dataexpDef
	 * @return
	 * 赵玉柱
	 */
	protected String buildSql(DataexpgDef dataexpDef)
	{
		StringBuffer sqlSb = new StringBuffer("select ");
		String tablename = dataexpDef.getTablename();
		String querycolumns = dataexpDef.getQuerycolumns();
		String queryfilter = dataexpDef.getQueryfilter();
		queryfilter = StrUtil.isStrTrimNull(queryfilter) ? " 1=1 " : queryfilter;
		sqlSb.append(querycolumns).append(" ");
		sqlSb.append(" from ").append(tablename);
		sqlSb.append(" where ").append(queryfilter);
		return sqlSb.toString();
	}

	/**
	 * 4、将导出明细组装为文件
	 * @param details
	 * @return
	 * 赵玉柱
	 */
	protected abstract File buildExportFile(List<DataExportDetail> details);
}
