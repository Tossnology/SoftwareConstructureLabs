package applications;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter{
 
	@Override
	public String format(LogRecord record) {
		ZonedDateTime zdt = ZonedDateTime.ofInstant(Instant.ofEpochMilli(record.getMillis()), ZoneId.systemDefault());
		String format = zdt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	    
		return "Time-->"+format+"   "+record.getMessage()+"\n";
	}
}