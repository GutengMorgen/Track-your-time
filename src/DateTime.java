package src;

import java.time.format.*;
import java.time.*;

public class DateTime {
	private static String dateFormatter = "dd/MM/yyyy";
	private static String timeFormatter = "HH:mm:ss";
	
	public static void main(String[] args) {
		DateTime dateTime = new DateTime();
		LocalDateTime now = LocalDateTime.now();
		System.out.println(dateTime.getDate(now));
		System.out.println(dateTime.getTime(now));
	}
	
	public String getDate(LocalDateTime now) {
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern(dateFormatter);
		String outDate = now.format(formatterDate);
		
		return outDate;
	}
	
	public String getTime(LocalDateTime now) {
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern(timeFormatter);
		String outTime = now.format(formatterTime);

		return outTime;
	}

}
