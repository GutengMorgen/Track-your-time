package src;

import java.time.format.*;
import java.time.*;

public class DateHandler {
	private static String dateFormatter = "dd/MM/yyyy";
	private static String timeFormatter = "HH:mm:ss";
	
	public static String getDate(LocalDateTime now) {
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(dateFormatter);
		return now.format(formatterDate);
	}
	
	public static String getTime(LocalDateTime now) {
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(timeFormatter);
		return now.format(formatterTime);
	}
}
