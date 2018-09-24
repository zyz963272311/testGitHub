package xyz.zyzhu.spring.boot.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.model.CkeditorSubmitRequest;
import xyz.zyzhu.spring.boot.model.CkeditorSubmitResponse;
import xyz.zyzhu.spring.boot.service.CkeditorSubmitService;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
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
@Service(value = "DefaultCkeditorSubmitServiceImpl")
public class DefaultCkeditorSubmitServiceImpl implements CkeditorSubmitService
{
	Logger logger = LoggerFactory.getLogger(DefaultCkeditorSubmitServiceImpl.class);

	@Override
	public CkeditorSubmitResponse submit(CkeditorSubmitRequest request)
	{
		logger.info(request == null ? "接入数据为空" : request.toString());
		CkeditorSubmitResponse response = new CkeditorSubmitResponse();
		response.setId(StrUtil.obj2str(request.getId(), request.getId() + "1"));
		response.setStatus(0);
		response.setName("测试name");
		return response;
	}
}
