package xml.junit.marshalling.config;

import java.sql.Date;
import java.text.SimpleDateFormat;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;

public class DateTimeAdapter extends XmlAdapter<String, Date> {

	@Override
	public Date unmarshal(String v) throws Exception {
		return Date.valueOf(v);
	}

	@Override
	public String marshal(Date v) throws Exception {
		return new SimpleDateFormat("yyyy-MM-dd").format(v);
	}

}
