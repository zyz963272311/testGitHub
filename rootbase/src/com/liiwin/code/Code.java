package com.liiwin.code;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.liiwin.date.DateUtil;
import com.liiwin.utils.StrUtil;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 SNSOFT</p>
 * <p>公司: 北京南北天地科技股份有限公司</p>
 * <p>创建日期：2017年5月26日 下午2:32:39</p>
 * <p>类全名：com.liiwin.code.Code</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Code
{
	private List<CodePart>	code;

	public Code()
	{
	}

	public Code(List<CodePart> code)
	{
		if (code != null && !code.isEmpty())
		{
			this.code = code;
		}
	}

	public Code(CodePart... codePart)
	{
		this.code = new ArrayList<>();
		if (codePart != null && codePart.length != 0)
		{
			for (int i = 0; i < codePart.length; i++)
			{
				code.add(codePart[i]);
			}
		}
	}

	public synchronized String makeCode()
	{
		Random random = new Random();
		try
		{
			int l = 1 + random.nextInt(3);
			Thread.sleep(l);
			System.out.println(l);
		} catch (InterruptedException e)
		{
			throw new RuntimeException("报错内容", e);
		}
		if (code == null || code.isEmpty())
		{
			return null;
		}
		StringBuffer codeBuffer = new StringBuffer();
		for (int i = 0; i < code.size(); i++)
		{
			codeBuffer.append(makeCodePart(code.get(i)));
		}
		return codeBuffer.toString();
	}

	private synchronized String makeCodePart(CodePart codePart)
	{
		String codePartString = "";
		if (codePart != null)
		{
			try
			{
				String codePartFormate = codePart.getCodePartFormate();
				int length = codePart.getLength();
				int type = codePart.getType();
				int[] comp = new int[] { CodeType.FIX_LENGTH, CodeType.RANDOM_NUMBER, CodeType.TIME };
				if (!StrUtil.isIntIn(type, comp))
				{
					throw new RuntimeException(codePart.toString() + "的type类型必须在" + StrUtil.intListToString(comp) + "之间");
				}
				if (StrUtil.isStrTrimNull(codePartFormate))
				{
					//只有是随机数的情况可以为空，
					if ((type & CodeType.RANDOM_NUMBER) >= 1)
					{
						codePartString = getRandomNum(codePart);
					}
				} else
				{
					if ((type & CodeType.RANDOM_NUMBER) >= 1)
					{
						codePartString = getRandomNum(codePart);
					} else if ((type & CodeType.TIME) >= 1)
					{
						//时间类型，需要获取时间格式
						codePartString = getTimeString(codePart);
					} else if ((type & CodeType.FIX_LENGTH) >= 1)
					{
						//获取一个固定长度的字符串
						String fixLengthString = (length < codePartFormate.length() ? codePartFormate.substring(0, length) : codePartFormate);
						codePartString = fixLengthString;
					}
				}
			} catch (Exception e)
			{
				throw new RuntimeException("生成编码错误" + codePart.toString() + e.getMessage());
			}
		}
		return codePartString;
	}

	private String getRandomNum(CodePart codePart)
	{
		int length = codePart.getLength();
		//随机数
		long randomNum = DateUtil.getCurMillisecondOnToday();
		String randomString = String.valueOf(randomNum);
		int randomLength = randomString.length();
		if (length > randomLength)
		{
			final StringBuffer tempSB = new StringBuffer();
			final int diff = randomLength - length;
			for (int i = 0; i < diff; i++)
			{
				tempSB.append('0');
			}
			randomString = randomString + tempSB.toString();
		} else
		{
			randomString = randomString.substring(0, length);
		}
		return randomString;
	}

	private String getTimeString(CodePart codePart)
	{
		String codePartFormate = codePart.getCodePartFormate();
		int length = codePart.getLength();
		String dateString = DateUtil.formateDate(DateUtil.getCurDate(), codePartFormate);
		if (StrUtil.isStrTrimNull(dateString) || dateString.length() != length)
		{
			throw new RuntimeException("生成时间格式错误" + codePartFormate);
		}
		return dateString;
	}

	public static void main(String[] args)
	{
		List<CodePart> codeParts = new ArrayList<>();
		CodePart codePart1 = new CodePart("kkkk", 4, 4);
		CodePart codePart2 = new CodePart("MMMM", 2, 2);
		CodePart codePart3 = new CodePart("", 8, 1);
		System.out.println(codePart1.toString());
		System.out.println(codePart2.toString());
		System.out.println(codePart3.toString());
		Code code = new Code(codePart1, codePart2, codePart3);
		for (int i = 0; i < 10; i++)
		{
			System.out.println(code.makeCode());
		}
		codeParts.add(codePart3);
		codeParts.add(codePart2);
		codeParts.add(codePart1);
		Code code2 = new Code(codeParts);
		for (int i = 0; i < 10; i++)
		{
			System.out.println(code2.makeCode());
		}
	}
}
