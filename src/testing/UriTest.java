package testing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import schedule.Course;
import schedule.Schedule;
import scraper.CourseScraper;
import scraper.CourseScraperImpl;


public class UriTest {




	public static void main(String[] args) throws URISyntaxException, IOException, ParseException, ClassNotFoundException {

		Schedule schedule = new Schedule();
		
		System.out.println(schedule.getScheduleDate().toString());
		
		schedule.nextWeek();
		
		System.out.println(schedule.getScheduleDate().toString());
		
		schedule.previousWeek();
		
		System.out.println(schedule.getScheduleDate().toString());
		
		System.exit(0);
	}

	
}


