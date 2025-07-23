package com.tts.stock.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class DateFormatSerializer extends StdSerializer<Date> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

	public DateFormatSerializer() {
		this(null);
	}

	protected DateFormatSerializer(Class<Date> t) {
		super(t);
	}

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
		gen.writeString(formatter.format(value));
	}
}
