package com.liiwin.test;

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
public class Test_84
{
	/**
	 * @param args
	 */
	public static void main(String[] args)
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
