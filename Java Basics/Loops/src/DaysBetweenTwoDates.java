import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;


public class DaysBetweenTwoDates {

	public static void main(String[] args) throws ParseException {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String firstDate = scanner.nextLine();
		String secondDate = scanner.nextLine();
		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
		Date date = df.parse(firstDate);
		Date _date = df.parse(secondDate);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(date);
		cal2.setTime(_date);
		int days;
		if (cal1.before(cal2)) {
			days = calculateDaysBetween(cal1, cal2);
		} else {
			days = calculateDaysBetween(cal2, cal1);
			days *= -1;
		}
		System.out.println(days);
	}

	private static int calculateDaysBetween(Calendar cal1, Calendar cal2) {
		int daysBetween = 0;
		while (cal1.before(cal2)) {
			daysBetween++;
			cal1.add(Calendar.DAY_OF_MONTH, 1);
		}
		return daysBetween;
	}
}
