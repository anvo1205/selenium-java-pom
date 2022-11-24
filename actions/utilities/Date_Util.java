package utilities;

import java.time.LocalDate;

public class Date_Util {
	public static String getDayOfMonth(String dateYYYMMDD) {
		LocalDate currentDate = LocalDate.parse(dateYYYMMDD);
		return Integer.toString(currentDate.getDayOfMonth());
	}
	
	public static String getMonth(String dateYYYMMDD) {
		LocalDate currentDate = LocalDate.parse(dateYYYMMDD);
		return currentDate.getMonth().toString();
	}
	
	public static String getYear(String dateYYYMMDD) {
		LocalDate currentDate = LocalDate.parse(dateYYYMMDD);
		return Integer.toString(currentDate.getYear());
	}

}
