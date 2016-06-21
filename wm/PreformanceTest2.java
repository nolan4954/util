package com.best.web.htyt.util.wm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PreformanceTest2 {
	static List<String> pst = Arrays.asList(new String[] { "汇通速递汇通速递平邮", "顺丰物流顺丰物流放松放松", "顺风顺风顺风顺风上单反是否", "申通申通申通申通申通", "圆通圆通圆通圆通圆通",
			"EMSEMSEMSEMS", "邮政邮政邮政邮政", "平邮平邮平邮平邮平邮", "平邮平邮平邮平邮平邮平邮平邮平", "中通中通中通中通中通中通", "韵达韵达韵达韵达韵达", "运达运达运达运达运达运达运达",
			"宅急送宅急送宅急送宅急送宅急送宅急送", "天天第三方个大概", "邮政小包一体机红太阳与偶", "邮政国内小包瓦房店发送到发送到", "国通接口iU盾他忽然嘎嘎嘎", "E邮宝官方数据如第三方少分", "全峰沙发发呆发送到仿盛大冯绍峰打法上",
			"优速阿发第三方上大方到沙发上", "速尔啊飒飒的微微反而让人忍忍忍", "龙邦啊神烦大叔冯绍峰等", "平邮按省份撒上发发发", "德邦啊沙发上仿佛是飞洒发单", "快捷撒范德萨发发发送到", "澳邮沙发发撒范德萨发生非",
			"增益啊孙菲菲冯绍峰倒萨发放圣达菲", "递家沙发大发生分三大发发发第三方", "百世阿福说短发的范围为二位二位人为鹅鹅鹅", "佳吉大丰收的发放大丰收反对法是否", "女狗阿斯顿发放到沙发上放大法但是打单辅导费",
			"男猴啊孙菲菲收费打单分地方地方到", "红色大发发松岛枫大方方第三方" });
	static WMparser p = new WMparser();
	static List<String> cases = new ArrayList<>();
	static List<String> cases2 = new ArrayList<>();
	static List<LiteString> lcases = new ArrayList<>();
	static List<LiteString> lcases2 = new ArrayList<>();
	static {
		p.buildTables(pst);
		readFileByLines("D://Data/sqlresult_109296.csv", cases);
		readFileByLines("D://Data/sqlresult_109296.csv", cases2);
		for (String s : cases) {
			lcases.add(new LiteString(s));
		}
		for (String s : cases) {
			lcases2.add(new LiteString(s));
		}
	}

	public static void readFileByLines(String fileName, List<String> ta) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				ta.add(tempString + tempString + tempString + tempString + tempString + tempString + tempString + tempString);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	public static void main(String[] args) {
		testWM(cases);
		testOld(cases);
		// testNewWM(cases);
		// testLiteString();
		// testString();
	}

	public static void testLiteString() {
		System.out.println("testLiteString:");
		long l = System.currentTimeMillis();
		int ll = lcases.size();

		for (int i = 0; i < ll; i++) {
			lcases.get(i).equals(lcases2.get(i));
		}
		System.out.println("use:" + (System.currentTimeMillis() - l) + "ms");
	}

	public static void testString() {
		System.out.println("testString:");
		long l = System.currentTimeMillis();
		int ll = cases.size();

		for (int i = 0; i < ll; i++) {
			cases.get(i).equals(cases2.get(i));
		}
		System.out.println("use:" + (System.currentTimeMillis() - l) + "ms");
	}

	public static void testOld(List<String> cases) {
		System.out.println("testOld:");
		long l = System.currentTimeMillis();
		for (String cs : cases) {
			oldMatch(cs);
		}
		System.out.println("use:" + (System.currentTimeMillis() - l) + "ms");
	}

	public static void testWM(List<String> cases) {
		System.out.println("testWM:");
		long l = System.currentTimeMillis();
		for (String cs : cases) {
			p.match(cs);
		}
		System.out.println("use:" + (System.currentTimeMillis() - l) + "ms");
	}

	public static List<String> oldMatch(String match) {
		List<String> res = null;
		for (String p : pst) {
			if (match.contains(p)) {
				if (res == null) {
					res = new ArrayList<>();
				}
				res.add(p);
			}

		}
		return res;
	}
}
