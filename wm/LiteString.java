package com.best.web.htyt.util.wm;

import java.util.Arrays;

/**
 * @author bl01904 用于替代String，由于String不可变会创建大量的String对象，shiftKey可变提高性能
 */
public class LiteString {
	public int length;
	public char[] value;
	public int prefix;
	public String trueVal;

	public LiteString() {

	}

	public int getRemainLength() {
		return value.length - prefix;
	}

	public LiteString subString(int begin, int leng) {
		char[] tmpvalue = Arrays.copyOfRange(value, begin, leng);
		return new LiteString(tmpvalue);
	}

	public LiteString(char[] chars) {
		value = chars;
		hash = 0;
		length = value.length;
	}

	public LiteString(String s) {
		value = s.toCharArray();
		hash = 0;
		length = value.length;
	}

	public void setByRange(int begin, int leng) {
		if (prefix == begin && leng == length) {
			return;
		}
		prefix = begin;
		length = leng;
		hash = 0;
	}

	public void setLength(int leng) {
		if (leng == length) {
			return;
		}
		length = leng;
		hash = 0;
	}

	private int hash;

	@Override
	public int hashCode() {
		int h = hash;
		if (h == 0 && length > 0) {
			char val[] = value;
			int pfl = prefix + length;

			for (int i = prefix; i < pfl; i++) {
				h = 31 * h + val[i];
			}
			hash = h;
		}
		return h;
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	@Override
	public boolean equals(Object anObject) {
		if (this == anObject) {
			return true;
		}
		LiteString anotherKey = (LiteString) anObject;
		int n = length;
		if (n == anotherKey.length) {
			char v1[] = value;
			char v2[] = anotherKey.value;
			int v2p = anotherKey.prefix;
			int v1p = prefix;
			int i = 0;
			while (n-- != 0) {
				if (v1[i + v1p] != v2[i + v2p])
					return false;
				i++;
			}
			return true;
		}
		return false;
	}

}
