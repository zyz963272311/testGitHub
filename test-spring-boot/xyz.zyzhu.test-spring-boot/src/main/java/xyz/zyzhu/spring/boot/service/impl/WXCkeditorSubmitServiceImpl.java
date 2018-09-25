package xyz.zyzhu.spring.boot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.liiwin.date.DateUtil;
import xyz.zyzhu.spring.boot.db.BootDatabase;
import xyz.zyzhu.spring.boot.db.BootDatabasePoolManager;
import xyz.zyzhu.spring.boot.model.CkeditorModel;
import xyz.zyzhu.spring.boot.model.CkeditorSubmitRequest;
import xyz.zyzhu.spring.boot.model.CkeditorSubmitResponse;
import xyz.zyzhu.spring.boot.service.CkeditorSubmitService;
/**
 * <p>标题： 微信checkeditor</p>
 * <p>功能： </p>
 * <p>所属模块： boot-wx</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年9月24日 下午7:43:21</p>
 * <p>类全名：xyz.zyzhu.spring.boot.service.impl.CkeditorSubmitServiceImpl</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
@Service(value = "WXCkeditorSubmitServiceImpl")
public class WXCkeditorSubmitServiceImpl implements CkeditorSubmitService
{
	private Logger logger = LoggerFactory.getLogger(WXCkeditorSubmitServiceImpl.class);

	@Override
	public CkeditorSubmitResponse submit(CkeditorSubmitRequest r)
	{
		CkeditorSubmitResponse response = new CkeditorSubmitResponse();
		try
		{
			logger.info(r == null ? "接入数据为空" : r.toString());
			String id = r.getId();
			CkeditorModel query = new CkeditorModel();
			query.setId(id);
			BootDatabase db = null;
			boolean rollback = true;
			try
			{
				db = BootDatabasePoolManager.getDatabaseByClass(CkeditorModel.class, true);
				CkeditorModel m = db.query1(query);
				boolean isInsert = false;
				if (m == null)
				{
					isInsert = true;
					m = new CkeditorModel();
					m.setContext(r.getContext());
					m.setCreatedate(DateUtil.getCurDate());
					m.setCreater(r.getUser());
				}
				m.setModifydate(DateUtil.getCurDate());
				m.setModifyer(r.getUser());
				m.setTitle(r.getTitle());
				m.setContext(r.getContext());
				db.beginTrans();
				if (isInsert)
				{
					db.insert(m);
				} else
				{
					db.update(m);
				}
				id = m.getId();
				db.commit();
				rollback = false;
			} finally
			{
				if (db != null)
				{
					try
					{
						db.rollback(rollback);
					} finally
					{
						BootDatabasePoolManager.close(db);
					}
				}
			}
			response.setStatus(0);
			response.setId(id);
		} catch (Exception e)
		{
			response.setStatus(1);
			response.setMsg("执行失败" + e.getMessage());
		}
		return response;
	}
}
