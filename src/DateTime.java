package src;

import java.time.format.*;
import java.time.*;

public class DateTime {

	public static void main(String[] args) {
		DateTime dateTime = new DateTime();
		System.out.println(dateTime.getDate());
		System.out.println(dateTime.getTime());
	}
	
	public String getDate() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String outDate = now.format(formatterDate);
		
		return "Date: " + outDate;
	}
	
	public String getTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm:ss");
		String outTime = now.format(formatterTime);

		return "Time: " + outTime;
	}

}
