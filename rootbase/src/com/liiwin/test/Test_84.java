package com.liiwin.test;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.util.EntityUtils;
import com.liiwin.constant.RegExpConstant;
import com.liiwin.date.DateUtil;
import com.liiwin.encryption.AES;
import com.liiwin.encryption.DES;
import com.liiwin.encryption.Encryption;
import com.liiwin.http.HttpClientUtil;
import com.liiwin.random.RandomString;
import com.liiwin.random.RandomStringImpl;
/**
 * <p>标题： TODO</p>
 * <p>功能： </p>
 * <p>所属模块： TODO</p>
 * <p>版权： Copyright © 2017 zyzhu</p>
 * <p>公司: xyz.zyzhu</p>
 * <p>创建日期：2017年8月28日 下午10:23:31</p>
 * <p>类全名：com.liiwin.test.Test_84</p>
 * 作者：赵玉柱
 * 初审：
 * 复审：
 * 监听使用界面:
 * @version 8.0
 */
public class Test_84 extends T
{
	static
	{
		System.out.println("1");
	}

	/**
	 * 
	 */
	public Test_84()
	{
		System.out.println("2");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		String a = "test赵玉柱xxx";
		Encryption encryption1 = new DES();
		Encryption encryption2 = new DES();
		try
		{
			String encryption = encryption1.getEncryption(a);
			System.out.println(encryption);
			String decrypt = encryption1.getDecrypt(encryption);
			String decrypt1 = encryption2.getDecrypt(encryption);
			System.out.println(decrypt);
			System.out.println(decrypt1);
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e)
		{
			throw new RuntimeException("报错内容", e);
		}
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void test1()
	{
		SimpleDateFormat fmt = new SimpleDateFormat();
		Date date = DateUtil.getCurDate();
		fmt.applyPattern("YYYY");
		String format = fmt.format(date);
		System.out.println(format);
		fmt.applyPattern("YY");
		format = fmt.format(date);
		System.out.println(format);
		fmt.applyPattern("yy");
		format = fmt.format(date);
		System.out.println(format);
		fmt.applyPattern("yyyy");
		format = fmt.format(date);
		System.out.println(format);
		fmt.applyPattern("MM");
		format = fmt.format(date);
		System.out.println(format);
		fmt.applyPattern("ss");
		format = fmt.format(date);
		System.out.println(format);
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void testIdCard()
	{
		String id1 = "123123123123123";
		String id2 = "12312312312312X";
		String id3 = "12312312312312x";
		String id4 = "1231231231231x3";
		String id5 = "123123123123123123";
		String id6 = "12312312312312312x";
		String id7 = "12312312312312312X";
		String id8 = "1231231231231231x2";
		boolean m1 = id1.matches(RegExpConstant.ID_CARD);
		boolean m2 = id2.matches(RegExpConstant.ID_CARD);
		boolean m3 = id3.matches(RegExpConstant.ID_CARD);
		boolean m4 = id4.matches(RegExpConstant.ID_CARD);
		boolean m5 = id5.matches(RegExpConstant.ID_CARD);
		boolean m6 = id6.matches(RegExpConstant.ID_CARD);
		boolean m7 = id7.matches(RegExpConstant.ID_CARD);
		boolean m8 = id8.matches(RegExpConstant.ID_CARD);
		System.out.println();
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void testAes()
	{
		String key = "abc";
		RandomString randomString = new RandomStringImpl();
		String rdm = randomString.getRandomString(10, 'a', 'z') + randomString.getRandomString(10, 'A', 'Z') + randomString.getRandomString(10, '0', '9');
		byte[] encrypt = AES.encrypt(rdm, key);
		String str = AES.parseByte2HexStr(encrypt);
		byte[] oByte = AES.parseHexStr2Byte(str);
		byte[] decrypt = AES.decrypt(oByte, key);
		String dstr = new String(decrypt);
		System.out.println(str);
		System.out.println(dstr);
	}

	/**
	 * 测试静态内部类
	 * <p>标题： TODO</p>
	 * <p>功能： </p>
	 * <p>所属模块： TODO</p>
	 * <p>版权： Copyright © 2018 zyzhu</p>
	 * <p>公司: xyz.zyzhu</p>
	 * <p>创建日期：2018年7月31日 下午4:33:23</p>
	 * <p>类全名：com.liiwin.test.TTT</p>
	 * 作者：赵玉柱
	 * 初审：
	 * 复审：
	 * 监听使用界面:
	 * @version 8.0
	 */
	public static class TTTStatic
	{
		public static String getA()
		{
			return "aaa";
		}

		public String getB()
		{
			return "bbb";
		}
	}

	public class TTTNoStatic
	{
		public String getC()
		{
			return "ccc";
		}
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void testHttp()
	{
		String url = "https://biz.trace.ickd.cn/auto/540368551819?mailNo=540368551819&spellName=&exp-textName=&tk=f28276a4&tm=1527218753102&callback=_jqjsp&_1527218753103=";
		HttpPost request = new HttpPost(url);
		request.addHeader("Cookie", "Hm_lvt_39418dcb8e053c84230016438f4ac86c=1527216285");
		request.addHeader("Cookie", "Hm_lpvt_39418dcb8e053c84230016438f4ac86c=1527216285");
		request.addHeader("Content-Type", "text/javascript;charset=utf-8");
		request.addHeader("Accept", "*/*");
		request.addHeader("Accept-Encoding", "gzip, deflate, br");
		request.addHeader("Accept-Language", "zh-CN,zh;q=0.9");
		request.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/66.0.3359.181 Safari/537.36");
		String execute = execute(request);
		System.out.println(execute);
	}

	private static String execute(HttpUriRequest request)
	{
		try
		{
			HttpResponse response = HttpClientUtil.getHttpClient().execute(request);
			StatusLine statusLine = response.getStatusLine();
			if (null == statusLine)
			{
				throw new RuntimeException("http request fail, no status line");
			}
			if (statusLine.getStatusCode() != HttpStatus.SC_OK)
			{
				throw new RuntimeException(String.format("http request fail[status=%d|message=%s]", statusLine.getStatusCode(), EntityUtils.toString(response.getEntity(), "utf-8")));
			}
			return EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (RuntimeException ex)
		{
			throw ex;
		} catch (Exception ex)
		{
			throw new RuntimeException("http request fail");
		} finally
		{
			if (null != request && !request.isAborted())
			{
				request.abort();
			}
		}
	}

	/**
	 * 
	 * 赵玉柱
	 */
	private static void testaaa()
	{
		double[] CL = new double[8];
		CL[0] = 152757.80;
		CL[1] = 390484.90;
		CL[2] = 430278.80;
		CL[3] = 494820.60;
		CL[4] = 473306.60;
		CL[5] = 425440.30;
		CL[6] = 319487.70;
		CL[7] = 140028.00;
		double[] PLR = new double[8];
		PLR[0] = 0.36;
		PLR[1] = 0.51;
		PLR[2] = 0.61;
		PLR[3] = 0.61;
		PLR[4] = 0.61;
		PLR[5] = 0.58;
		PLR[6] = 0.51;
		PLR[7] = 0.36;
		double[] E = new double[8];
		E[0] = 18216;
		E[1] = 33396;
		E[2] = 30360;
		E[3] = 34914;
		E[4] = 33396;
		E[5] = 31878;
		E[6] = 27324;
		E[7] = 16698;
		double[] Epump_tower = new double[8];
		Epump_tower[0] = 14520;
		Epump_tower[1] = 52030;
		Epump_tower[2] = 48400;
		Epump_tower[3] = 55660;
		Epump_tower[4] = 53240;
		Epump_tower[5] = 49665;
		Epump_tower[6] = 42570;
		Epump_tower[7] = 13310;
		double[] Ehav = new double[8];
		Ehav[0] = 91322.5487;
		Ehav[1] = 191683.0321;
		Ehav[2] = 213602.3744;
		Ehav[3] = 242971.0753;
		Ehav[4] = 241709.6368;
		Ehav[5] = 215652.3986;
		Ehav[6] = 144218.8908;
		Ehav[7] = 78355.59496;
		double a_min = 0;
		double b_min = 0;
		double Uannual = 0.0;
		//double Uannual_min=100;
		double Uannual_min = -1;
		//for(double a=0.0;a<9;a+=0.09){
		for (double a = 0.0 + 0.09; a < 9; a += 0.09)
		{
			//for(double b=2.0+0.05;b<10;b+=0.05){
			for (double b = 2.0; b < 10; b += 0.05)
			{
				Uannual = 0.0;
				for (int m = 0; m <= 7; m++)
				{
					Uannual += Math.abs(Um(a, b, CL[m], PLR[m], Epump_tower[m], E[m], Ehav[m]));
				}
				//if(Uannual<Uannual_min){
				if (Uannual_min == -1 || Uannual < Uannual_min)
				{
					a_min = a;
					b_min = b;
					Uannual_min = Uannual;
					//System.out.println("Uannual_min:" + Uannual_min + "  a_min:" + a_min + "    b_min:" + b_min);
				}
			}
		}
		System.out.println("Uannual_min:" + Uannual_min + "  a_min:" + a_min + "    b_min:" + b_min);
	}

	public static double CCOP(double a, double PLR)
	{
		double result = 0.09 * a + 3.57 - (a * (PLR - 0.7) * (PLR - 0.7));
		return result;
	}

	public static double Esource(double a, double PLR, double CL)
	{
		double result = CL / CCOP(a, PLR);
		return result;
	}

	public static double Z(double b, double PLR)
	{
		double result = 0.9 * Math.log(b + PLR) / Math.log(b + 1);
		return result;
	}

	public static double Eenduse(double E, double b, double PLR)
	{
		double result = E * Z(b, PLR);
		return result;
	}

	public static double Ehav_(double a, double b, double CL, double PLR, double Epump_tower, double E)
	{
		double result = Esource(a, PLR, CL) + Epump_tower + Eenduse(E, b, PLR);
		return result;
	}

	public static double Um(double a, double b, double CL, double PLR, double Epump_tower, double E, double Ehav)
	{
		double result = (Ehav - Ehav_(a, b, CL, PLR, Epump_tower, E)) / (Ehav + Ehav_(a, b, CL, PLR, Epump_tower, E));
		return result;
	}
}
class T
{
	static
	{
		System.out.println("3");
	}

	/**
	 * 
	 */
	public T()
	{
		System.out.println("4");
	}
}
