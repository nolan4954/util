package com.best.web.htyt.util.wm.newWm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.best.web.htyt.util.wm.LiteString;

/**
 * 
 * 根据自身业务修改的wm算法，比最土的办法快一倍
 * 
 * @author bl01904
 * 
 */
public class NewWMparser {
	private final ShiftV[] shift = new ShiftV[65535];

	private int m = Integer.MAX_VALUE;

	private int noMatchingGap;

	public void buildTables(List<String> patterns, List<String> trueVals) {

		for (String p : patterns) {
			if (p.length() < m) {
				m = p.length();
			}
		}
		noMatchingGap = m;
		int cindex = 0;
		char lc;
		ShiftV sv;
		int index;
		LiteString pls;
		int i = 0;
		for (String p : patterns) {

			cindex = 0;
			pls = new LiteString(p);
			pls.trueVal = trueVals.get(i);
			while (cindex < m) {
				index = m - 1 - cindex;
				lc = p.charAt(cindex);
				sv = shift[lc];
				if (sv == null) {
					sv = new ShiftV();
					shift[lc] = sv;
				}

				sv.setIndex(index, m);
				sv.addPattern(pls);
				cindex += 1;
			}
			i++;
		}

	}

	public List<String> match(String target) {
		List<String> res = null;
		int i = m - 1;
		int targetLength = target.length();
		LiteString matchingLS = new LiteString(target);
		char[] tchars = matchingLS.value;
		ShiftV sv;
		int svIndex;
		int willMatchStart;
		String matched;
		char curchar;
		while (i < targetLength) {
			curchar = tchars[i];
			sv = shift[curchar];
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
						res.add(matched);

					}

					i += sv.matchedjumpIndex;
				}

			}
		}

		return res;
	}

	public static void main(String[] args) {
		NewWMparser p = new NewWMparser();
		List<String> pst = Arrays.asList(new String[] { "汇通", "顺丰", "顺风", "申通", "圆通", "EMS", "邮政", "平邮", "邮局", "中通", "韵达", "运达", "宅急送" });
		p.buildTables(pst, pst);
		String target = "急希望快点发货呀，着回家过年送人圆通呀";

		System.out.println(p.match(target));
	}
}
