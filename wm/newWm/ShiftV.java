package com.best.web.htyt.util.wm.newWm;

import java.util.ArrayList;
import java.util.List;

import com.best.web.htyt.util.wm.LiteString;

public class ShiftV {

	public static final int prefixLength = 1;

	public int index = Integer.MAX_VALUE;
	public int maxIndex = -1;
	/**
	 * 利用java的语言特性优化wm算法，如果没有匹配 不只移动1，也可以移动m的距离
	 */
	public int matchedjumpIndex = 1;

	private List<LiteString> patterns;

	public void setIndex(int i, int m) {

		if (i > maxIndex) {
			maxIndex = i;
			matchedjumpIndex = m - maxIndex;
		}
		if (i < index) {
			index = i;
		}
	}

	public void addPattern(LiteString pattern) {
		if (index != 0) {
			return;
		}

		if (patterns == null) {
			patterns = new ArrayList<>();
		}
		patterns.add(pattern);
	}

	public String match(LiteString s, int start) {

		s.setByRange(start, 1);
		int lsl = s.getRemainLength();
		int pl;
		for (LiteString p : patterns) {
			pl = p.length;
			if (pl <= lsl) {
				s.setLength(pl);
				if (p.equals(s)) {
					return p.trueVal;
				}

			}

		}

		return null;
	}

	@Override
	public String toString() {
		return index + "|";
	}

}
