package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import schedule.CourseMoment;



public class Util {
	
	public static Date parseDate(String possibleDate) throws ParseException {
		String regex = "[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(possibleDate);

		String foundDateString = null;
		while (matcher.find()) {
			foundDateString = matcher.group(0);
		}
		
		if(foundDateString == null) {
			return null;
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date result = dateFormat.parse(foundDateString);  

		return result;


	}
	
	public static List<Date> parseTime(String possibleTimes) throws ParseException {

		List<Date> toReturn = new ArrayList<Date>();

		String regex = "([01]?[0-9]|2[0-3]):[0-5][0-9]";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(possibleTimes);

		DateFormat dateFormat = new SimpleDateFormat("HH:mm");

		String foundTimeString = null;
		while (matcher.find()) {
			foundTimeString = matcher.group(0);

			Date result = dateFormat.parse(foundTimeString);

			toReturn.add(result);
		} 

		return toReturn;
	}

	public static void waitSeconds(int seconds) {
		System.out.print("Pausing for " + seconds + " seconds: ");
		try {
			Thread.currentThread();		
			int x = 1;
			while(x <= seconds) {
				Thread.sleep(1000);
				System.out.print(" " + x );
				x = x + 1;
			}
			System.out.print('\n');
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}	
	}

	public static CourseMoment parseCourseDescription(Date date, String info) throws ParseException {
		
		String html = info.replace("return overlib('", "").replace("');", "");

		Document document = Jsoup.parse(html);

		String building = document.getElementsByAttributeValue("src", "icons/building.gif").first().nextElementSibling().text();

		String possibleTimes = document.getElementsByAttributeValue("color", "white").first().nextSibling().toString();

		List<Date> times = parseTime(possibleTimes);


		Date startTime;
		Date endTime;
		if(times.get(0).after(times.get(1))) {
			startTime = times.get(1);
			endTime = times.get(0);
		}
		else {
			startTime = times.get(0);
			endTime = times.get(1);
		}

		CourseMoment courseMoment = new CourseMoment(building, date, startTime, endTime);

		return courseMoment;
	}
	
	

	
}
