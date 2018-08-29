package xyz.zyzhu.spring.boot.datafile.export;

import java.io.File;
import java.util.List;
import xyz.zyzhu.spring.boot.datafile.export.domain.DataExportDetail;
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
public class ZipDataExport extends DefaultDataEcport
{
	@Override
	protected File buildExportFile(List<DataExportDetail> details)
	{
		return null;
	}
}
