package com.best.web.htyt.util.wm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 传统的wm算法，覆盖所有情况，但是小数据的时候性能远不及 最土的办法
 * 
 * @author bl01904
 * 
 */
public class WMparser {
	private HashMap<LiteString, ShiftValue> shift;
	/**
	 * 块长度 一般为2-3//此处改成1，性能更好
	 */
	private static final int blockLength = 2;

	private int m = Integer.MAX_VALUE;

	private int noMatchingGap;

	public void buildTables(List<String> patterns) {

		for (String p : patterns) {
			if (p.length() < m) {
				m = p.length();
			}
		}
		noMatchingGap = m - blockLength + 1;
		shift = new HashMap<>(10000);
		int cindex = 0;
		LiteString ls;
		ShiftValue sv;
		int index;
		LiteString pls;
		for (String p : patterns) {
			cindex = 0;
			pls = new LiteString(p);
			while (cindex + blockLength <= m) {
				index = m - blockLength - cindex;
				ls = new LiteString(p.substring(cindex, cindex + blockLength));
				sv = shift.get(ls);

				if (sv == null) {
					sv = new ShiftValue();
					shift.put(ls, sv);
				}

				sv.setIndex(index, m);
				sv.addPattern(pls);
				cindex += blockLength;
			}
		}

	}

	public List<String> match(String target) {
		List<LiteString> res = null;
		int i = m - 1;
		int targetLength = target.length();
		LiteString matchingLS = new LiteString(target);
		ShiftValue sv;
		int svIndex;
		int willMatchStart;
		List<LiteString> matched;
		while (i < targetLength) {
			matchingLS.setByRange(i - blockLength + 1, blockLength);
			sv = shift.get(matchingLS);
			if (sv == null) {
				i += noMatchingGap;
				continue;
			} else {

				svIndex = sv.index;

				if (svIndex != 0) {
					i += svIndex;
				} else {
					willMatchStart = i - m + 1;
					// 指针回到头部
					matched = sv.match(matchingLS, willMatchStart);
					if (matched != null) {
						if (res == null) {
							res = new ArrayList<>();
						}
						res.addAll(matched);
					}

					i += sv.matchedjumpIndex;

				}

			}
		}
		// if (res != null) {
		// List<String> resx = new ArrayList<>();
		// for (LiteString ss : res) {
		// resx.add(ss.toString());
		// }
		// }

		return null;
	}

	public static void main(String[] args) {
		WMparser p = new WMparser();
		List<String> pst = Arrays.asList(new String[] { "汇通", "顺丰", "顺风", "申通", "圆通", "EMS", "邮政", "平邮", "邮局", "中通", "韵达", "运达", "宅急送" });
		p.buildTables(pst);
		String target = "希望快点发货呀，着回家过年送人呀";

		System.out.println(p.match(target));
	}
}
