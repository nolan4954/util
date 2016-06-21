package com.best.web.htyt.util;

import org.supercsv.encoder.DefaultCsvEncoder;
import org.supercsv.prefs.CsvPreference;
import org.supercsv.util.CsvContext;

public class MyAwesomeEncoder extends DefaultCsvEncoder {

	public MyAwesomeEncoder() {
	}

	@Override
	public String encode(String input, CsvContext context, CsvPreference preference) {
		input = StringUtil.strFiter(input);
		return super.encode(input, context, preference);
	}
}
