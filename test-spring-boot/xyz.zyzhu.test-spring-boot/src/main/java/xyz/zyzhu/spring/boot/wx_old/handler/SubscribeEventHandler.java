package xyz.zyzhu.spring.boot.wx_old.handler;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.liiwin.date.DateUtil;
import com.liiwin.db.Database;
import com.liiwin.db.pool.DatabasePoolManager;
import com.liiwin.utils.StrUtil;
import com.soecode.wxtools.api.IService;
import com.soecode.wxtools.api.WxMessageHandler;
import com.soecode.wxtools.bean.WxXmlMessage;
import com.soecode.wxtools.bean.WxXmlOutMessage;
import com.soecode.wxtools.exception.WxErrorException;
import xyz.zyzhu.spring.boot.model.User;
import xyz.zyzhu.spring.boot.model.UserConstance;
import xyz.zyzhu.spring.config.RedisConfig;
/**
 * <p>标题： 关注消息处理器</p>
 * <p>功能： </p>
 * <p>所属模块： rootbas</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年2月4日 下午12:41:29</p>
 * <p>类全名：xyz.zyzhu.spring.boot.wx.handler.SubscribeEventHandler</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
//@EnableAutoConfiguration
//@Service
public class SubscribeEventHandler extends RedisConfig implements WxMessageHandler
{
	//	@Autowired
	//	@Resource
	private RedisTemplate<String,User> redisTemplete;

	@Override
	public WxXmlOutMessage handle(WxXmlMessage message, Map<String,Object> context, IService iService) throws WxErrorException
	{
		redisTemplete = getRedisTemplete(String.class, User.class);
		Database db = DatabasePoolManager.getNewInstance().getConfigDatabase();
		//获取关注人ID
		String fromUserName = message.getFromUserName();
		ValueOperations<String,User> opsForValue = redisTemplete.opsForValue();
		String key = getKey(message);
		//		Object o = ;
		Object o = opsForValue.get(key);
		//		opsForValue.getOperations().delete(key);
		//		user = opsForValue.get(key);
		User user = null;
		if (o != null)
		{
			JSON json = (JSON) JSONObject.toJSON(o);
			user = JSONObject.toJavaObject(json, User.class);
		}
		//坐标X
		Double posx = message.getLocationX();
		BigDecimal bigPosx = null;
		if (posx != null)
		{
			bigPosx = new BigDecimal(posx);
		}
		//坐标Y
		Double posy = message.getLocationY();
		BigDecimal bigPosy = null;
		if (posy != null)
		{
			bigPosy = new BigDecimal(posy);
		}
		Date curDate = DateUtil.getCurDate();
		if (user != null)
		{
			//关注时间
			user.setFollowtime(curDate);
			//修改时间
			user.setModifydate(curDate);
			//位置X
			user.setPosx(bigPosx);
			//位置Y
			user.setPosy(bigPosy);
			//用户已经关注过，直接更新数据
			//设置启用标识
			user.setFollowflags(1);
			//用户类型，默认卖家
			Integer originUsertype = StrUtil.obj2int(user.getUsertype(), 0);
			user.setUsertype(originUsertype | UserConstance.USERTYPE4);
		} else
		{
			//用户没有关注过，插入数据
			user = new User();
			user.setFollowflags(1);
			user.setFollowtime(curDate);
			user.setOpenid(fromUserName);
			user.setPosx(bigPosx);
			user.setPosy(bigPosy);
			user.setUsertype(UserConstance.USERTYPE4);
		}
		opsForValue.set(key, user);
		return null;
	}

	private String getKey(WxXmlMessage message)
	{
		String fromUserName = message.getFromUserName();
		String prefix = "wechat_foods_users:";
		return prefix + fromUserName;
	}
}
