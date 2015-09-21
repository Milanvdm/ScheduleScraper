package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import schedule.CourseMoment;

public class Parser {

	public static String getScheduleUrl(String urlWithJs) {
		String link = null;

		String regex = "\\(?\\b((https?://|www[.]|ftp://)|www[.])[-A-Za-z0-9+&amp;@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&amp;@#/%=~_()|]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(urlWithJs);
		if(m.find()) {
			String urlStr = m.group();
			
			if (urlStr.startsWith("(") && urlStr.endsWith(")")) {
				urlStr = urlStr.substring(1, urlStr.length() - 1);
			}
			link = urlStr;
		}
		return link;
	}

	public static Date parseDate(String possibleDate) throws ParseException {
		String regex = "[0-9]{2}\\.[0-9]{2}\\.[0-9]{4}";
		
		Pattern pattern = Pattern.compile(regex);
	    Matcher matcher = pattern.matcher(possibleDate);
	    
	    String foundDateString = null;
	    while (matcher.find()) {
	    	foundDateString = matcher.group(0);
	    }
		
		DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
		Date result = dateFormat.parse(foundDateString);  
		
		return result;
		
		
	}

	public static CourseMoment parseCourseMoment(String info) {
		// TODO Auto-generated method stub
		return null;
	}

	
	/*
	 "return overlib('<img src=icons/teacher.gif border=0><a href= javascript:webadres
	 (\'http://www.kuleuven.be/wieiswie/nl/person/00018905\')>Prof. dr. ir. Piessens Frank 
	 </a><BR><img src=icons/syllabus.gif border=0><a href= javascript:webadres(\'http://www.kul
	 euven.be/onderwijs/aanbod/syllabi/H04L5AN.htm\')>H04L5a</a><br><img src=icons/building.gif 
	 border=0><a href=javascript:gebouw(\'50721558\',\'X\')>200A - lokaal 00.225 ( AUDITORIUM 
	 COMPUTERWETENSCHAPPEN )</a><br><I>Om de links te bezoeken, eerst 1 maal klikken om dit 
	 venster vast te zetten</I>', CAPTION, '<I> Vergelijkende studie van imperatieve 
	 programmeertalen: hoorcollege </I><BR><hr size=1 color=white>16:00 tot 18:30');"

	 */
	

}
