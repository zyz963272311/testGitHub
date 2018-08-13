package xyz.zyzhu.spring.boot.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.liiwin.code.Code;
import com.liiwin.code.CodePart;
import com.liiwin.code.MakeCodeUtil;
import com.liiwin.date.DateUtil;
import com.liiwin.db.Database;
import com.liiwin.utils.BigUtils;
import com.liiwin.utils.RedisUtil;
import com.liiwin.utils.StrUtil;
import xyz.zyzhu.spring.boot.annotation.FieldDef;
import xyz.zyzhu.spring.boot.model.AutoCode;
import xyz.zyzhu.spring.boot.model.AutoCodeG;
import xyz.zyzhu.spring.boot.model.BasModel;
import xyz.zyzhu.spring.boot.service.AutoCodeService;
/**
 * <p>标题： 默认值工具类</p>
 * <p>功能： </p>
 * <p>所属模块： 默认值工具类</p>
 * <p>版权： Copyright © 2018 SNSOFT</p>
 * <p>公司: 赵玉柱练习</p>
 * <p>创建日期：2018年7月30日 下午5:25:26</p>
 * <p>类全名：xyz.zyzhu.spring.boot.utils.DefaultValueUtils</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class DefaultValueUtils
{
	/**
	 * 根据class获取默认值，需要clazz具有默认的构造函数
	 * @param clazz
	 * @param recursion 递归参数列表
	 * @return
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	public static <E extends BasModel> E buildDefaultValueByClass(Class<E> clazz, boolean recursion, Database db)
	{
		if (clazz == null)
		{
			return null;
		}
		E instance = null;
		try
		{
			instance = (E) ObjectUtils.newInstance(clazz);
			buildDefaultValue(instance, recursion, db);
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | SecurityException | IllegalArgumentException | InvocationTargetException e)
		{
			throw new RuntimeException("设置默认值异常", e);
		}
		return instance;
	}

	/**
	 * 补充默认值
	 * @param o
	 * @param recursion
	 * 赵玉柱
	 */
	@SuppressWarnings("unchecked")
	public static <E extends BasModel> void buildDefaultValue(E o, boolean recursion, Database db)
	{
		Date curDate = DateUtil.getCurDate();
		long lastDayTime = curDate.getTime() - 24 * 60 * 60 * 1000;
		String dayStr = new SimpleDateFormat("dd").format(curDate);
		String lastDayStr = new SimpleDateFormat("dd").format(DateUtil.getCurDate(lastDayTime));
		if (o == null)
		{
			return;
		}
		Class<?> clazz = o.getClass();
		List<Field> fields = ModelUtils.getClassFields(o.getClass());
		if (fields == null || fields.size() == 0)
		{
			return;
		}
		Map<String,Method> getterMethods = ObjectUtils.getClassGetterMethods(clazz, BasModel.class);
		Map<String,Method> setterMethods = ObjectUtils.getClassSetterMethods(clazz, BasModel.class);
		for (Field field : fields)
		{
			Class<?> fieldClazz = field.getType();
			boolean simpleType = ObjectUtils.isSimpleType(fieldClazz);
			try
			{
				Method getterMethod = getterMethods.get(field.getName());
				Method setterMethod = setterMethods.get(field.getName());
				if (getterMethod != null && setterMethod != null)
				{
					if (simpleType)
					{
						Object invoke = getterMethod.invoke(o);
						if (invoke == null)
						{
							FieldDef annotation = field.getAnnotation(FieldDef.class);
							if (annotation != null)
							{
								String defaultValueAnno = null;
								String defaultValue = annotation.defaultValue();
								if (StrUtil.isNoStrTrimNull(defaultValue))
								{
									if (defaultValue.startsWith("AutoCode:"))
									{
										String autoCode = defaultValue.substring("AutoCode:".length());
										if (StrUtil.isStrTrimNull(autoCode))
										{
											throw new RuntimeException("编码格式错误");
										}
										AutoCodeService autoCodeService = SpringBeanUtils.getBean(AutoCodeService.class);
										if (autoCodeService == null)
										{
											throw new RuntimeException("获取编码格式Service" + AutoCodeService.class + "失败");
										}
										AutoCode autoCodeQuery = new AutoCode();
										autoCodeQuery.setCode(autoCode);
										List<AutoCode> autoCodeList = autoCodeService.queryListMByFilter(autoCodeQuery, null);
										if (autoCodeList != null && !autoCodeList.isEmpty())
										{
											AutoCode codeEnable = null;
											for (AutoCode code : autoCodeList)
											{
												if (code.isEnabled())
												{
													codeEnable = code;
													break;
												}
											}
											List<AutoCodeG> autoCodeGs = codeEnable.getAutoCodeGs();
											if (autoCodeGs != null && autoCodeGs.size() > 0)
											{
												List<CodePart> parts = new ArrayList<>();
												for (AutoCodeG codeG : autoCodeGs)
												{
													CodePart part = new CodePart(autoCode, codeG.getFormate(), StrUtil.obj2int(codeG.getLength()), StrUtil.obj2int(codeG.getType()));
													parts.add(part);
												}
												if (!parts.isEmpty())
												{
													Code code = new Code(parts);
													String makeCode = code.makeCode();
													setterMethod.invoke(o, makeCode);
												}
											}
										} else
										{
											String tableName = ObjectUtils.getTableName(o);
											if (tableName == null)
											{
												tableName = clazz.getName();
											}
											String columnName = annotation.column();
											if (StrUtil.isStrTrimNull(columnName))
											{
												columnName = field.getName();
											}
											autoCode = dealSpecChar(autoCode);
											Class<? extends BasModel> class1 = o.getClass();
											Map<String,Field> classFieldColumns = ModelUtils.getClassFieldColumns(class1, db);
											String lastKey = tableName + "|" + columnName + "|" + dayStr;
											String key = tableName + "|" + columnName + "|" + dayStr;
											if (classFieldColumns.containsKey(columnName))
											{
												//当前字段是表中的字段，需要进行数据库中校验
												Object redisValue = RedisUtil.get(key);
												if (redisValue == null)
												{
													Object lastRedisValue = RedisUtil.get(lastKey);
													if (lastRedisValue == null)
													{
														//如果数据为空，先从数据库中查询最新的数据
														Map<String,Object> params = new HashMap<>();
														params.put(columnName, autoCode);
														Map<String,Object> mapFromSql = db.getMapFromSql(
																"select max(" + columnName + ") as max from " + tableName + " where " + columnName + " like (:" + columnName + ")", params);
														if (mapFromSql != null && !mapFromSql.isEmpty())
														{
															String max = StrUtil.obj2str(mapFromSql.get("max"));
															if (max != null)
															{
																String bitGetStr = StrUtil.bitGetStr(autoCode, max, "_", null);
																int bitGetInt = StrUtil.obj2int(bitGetStr);
																RedisUtil.set(key, bitGetInt, 2, RedisUtil.DAY);
															}
														}
													}
												}
											}
											int allLength = autoCode.length();
											int defLength = autoCode.replace("_", "").length();
											String code = autoCode;
											if (allLength > defLength)
											{
												String autoCodePart = MakeCodeUtil.makeOuttercode("", allLength - defLength, key);
												code = StrUtil.strReplaceBit(autoCode, autoCodePart, '_', '_', true);
											}
											setterMethod.invoke(o, code);
										}
									} else if (defaultValue.startsWith("ExecMethod:"))
									{
										String substring = defaultValue.substring("ExecMethod:".length());
										if (StrUtil.isStrTrimNull(substring))
										{
											throw new RuntimeException("编码格式错误" + defaultValue);
										}
										Object executeMethodValue = ObjectUtils.executeMethod(substring);
										setterMethod.invoke(o, executeMethodValue);
									} else
									{
										defaultValueAnno = defaultValue;
									}
								}
								if (StrUtil.isNoStrTrimNull(defaultValueAnno))
								{
									Object value = null;
									if (Integer.class.equals(fieldClazz) || int.class.equals(fieldClazz))
									{
										value = StrUtil.obj2int(defaultValueAnno);
									}
									if (long.class.equals(fieldClazz) || Long.class.equals(fieldClazz))
									{
										value = StrUtil.obj2long(defaultValueAnno);
									}
									if (short.class.equals(fieldClazz) || Short.class.equals(fieldClazz))
									{
										value = (short) StrUtil.obj2int(defaultValueAnno);
									}
									if (byte.class.equals(fieldClazz) || Byte.class.equals(fieldClazz))
									{
										value = (byte) StrUtil.obj2int(defaultValueAnno);
									}
									if (char.class.equals(fieldClazz) || Character.class.equals(fieldClazz))
									{
										value = StrUtil.trim(defaultValueAnno).charAt(0);
									}
									if (Boolean.class.equals(fieldClazz) || boolean.class.equals(fieldClazz))
									{
										value = StrUtil.obj2bool(defaultValueAnno);
									}
									if (float.class.equals(fieldClazz) || Float.class.equals(fieldClazz))
									{
										value = (float) StrUtil.obj2double(defaultValueAnno);
									}
									if (double.class.equals(fieldClazz) || Double.class.equals(fieldClazz))
									{
										value = StrUtil.obj2double(defaultValueAnno);
									}
									if (Date.class.equals(fieldClazz))
									{
										value = DateUtil.formateDate(defaultValueAnno);
									}
									if (BigDecimal.class.equals(fieldClazz))
									{
										value = BigUtils.obj2big(defaultValueAnno);
									}
									if (String.class.equals(fieldClazz))
									{
										value = defaultValueAnno;
									}
									setterMethod.invoke(o, value);
								}
							}
						}
					} else
					{
						if (Map.class.isAssignableFrom(fieldClazz))
						{
							//Map
							Map<Object,Object> value = (Map<Object,Object>) getterMethod.invoke(o);
							if (value != null)
							{
								for (Entry<Object,Object> valEntry : value.entrySet())
								{
									buildDefaultValue((E) valEntry.getValue(), recursion, db);
								}
							}
						} else if (Collection.class.isAssignableFrom(fieldClazz))
						{
							//集合
							Collection<Object> value = (Collection<Object>) getterMethod.invoke(o);
							if (value != null)
							{
								for (Object coll : value)
								{
									buildDefaultValue((E) coll, recursion, db);
								}
							}
						} else
						{
							Object value = getterMethod.invoke(o);
							if (value != null)
							{
								buildDefaultValue((E) value, recursion, db);
							} else
							{
								value = buildDefaultValueByClass((Class<E>) fieldClazz, true, db);
								setterMethod.invoke(o, value);
							}
						}
					}
				}
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e)
			{
				throw new RuntimeException("设置默认值异常", e);
			}
		}
	}

	/**
	 * 处理特殊字符
	 * @param autocode
	 * @return
	 * 赵玉柱
	 */
	private static String dealSpecChar(String autocode)
	{
		Date curDate = DateUtil.getCurDate();
		SimpleDateFormat format = new SimpleDateFormat();
		dealDateSpecChar(autocode, "YYYY", curDate, format);
		dealDateSpecChar(autocode, "yyyy", curDate, format);
		dealDateSpecChar(autocode, "YY", curDate, format);
		dealDateSpecChar(autocode, "yy", curDate, format);
		dealDateSpecChar(autocode, "MM", curDate, format);
		dealDateSpecChar(autocode, "DD", curDate, format);
		dealDateSpecChar(autocode, "HH", curDate, format);
		dealDateSpecChar(autocode, "hh", curDate, format);
		dealDateSpecChar(autocode, "mm", curDate, format);
		dealDateSpecChar(autocode, "ss", curDate, format);
		return autocode;
	}

	private static String dealDateSpecChar(String autocode, String specCode, Date curdate, SimpleDateFormat format)
	{
		if (autocode.indexOf(specCode) > 0)
		{
			format.applyPattern(specCode);
			String afterDeal = format.format(curdate);
			autocode = autocode.replace(specCode, afterDeal);
		}
		return autocode;
	}
}
