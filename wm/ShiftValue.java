package com.best.web.htyt.util.wm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShiftValue {

	public static final int prefixLength = 2;

	public int index = Integer.MAX_VALUE;
	public int maxIndex = -1;
	/**
	 * 利用java的语言特性优化wm算法，如果没有匹配 不只移动1，也可以移动m的距离
	 */
	public int matchedjumpIndex = 1;
	public Map<LiteString, List<LiteString>> prefix;
	public LiteString singlePattern;

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
		LiteString pfx = pattern.subString(0, prefixLength);
		if (prefix == null) {
			prefix = new HashMap<>();
		}
		List<LiteString> set = prefix.get(pfx);
		if (set != null) {
			set.add(pattern);
		} else {
			set = new ArrayList<>();
			set.add(pattern);
			prefix.put(pfx, set);
		}
	}

	public List<LiteString> match(LiteString s, int start) {
		s.setByRange(start, prefixLength);
		List<LiteString> finds = prefix.get(s);
		if (finds == null) {
			return null;
		}
		List<LiteString> res = new ArrayList<>();
		// 目标串剩余长度
		int lsl = s.getRemainLength();

		for (LiteString find : finds) {
			if (find.length > lsl) {
				continue;
			}
			s.setLength(find.length);
			if (find.equals(s)) {
				res.add(find);
			}
		}

		return res;
	}

	@Override
	public String toString() {
		return index + "|" + prefix;
	}

}
