package com.best.web.htyt.util.wm;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.best.web.htyt.util.wm.newWm.NewWMparser;

public class PreformanceTest {
	static List<String> pst = Arrays.asList(new String[] { "汇通", "顺丰", "顺风", "申通", "圆通", "EMS", "邮政", "平邮", "邮局", "中通", "韵达", "运达", "宅急送",
			"天天", "邮政小包", "邮政国内小包", "国通", "E邮宝", "全峰", "优速", "速尔", "龙邦", "平邮", "德邦", "快捷", "澳邮", "增益", "递家", "百世", "佳吉", "女狗", "男猴", "红色",
			"保证", "质量", "品质", "记得", "可以" });
	static WMparser p = new WMparser();
	static NewWMparser np = new NewWMparser();
	static List<String> cases = new ArrayList<>();
	static List<String> cases2 = new ArrayList<>();
	static List<LiteString> lcases = new ArrayList<>();
	static List<LiteString> lcases2 = new ArrayList<>();
	static {
		p.buildTables(pst);
		np.buildTables(pst, pst);
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
				ta.add(tempString);
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
		testNewWM(cases);
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

	public static void testNewWM(List<String> cases) {
		System.out.println("testNewWM:");
		long l = System.currentTimeMillis();
		for (String cs : cases) {
			np.match(cs);
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
