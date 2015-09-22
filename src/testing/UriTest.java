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
import scraper.CourseScraper;
import scraper.CourseScraperImpl;


public class UriTest {




	public static void main(String[] args) throws URISyntaxException, IOException, ParseException {

		CourseScraper scraper = new CourseScraperImpl();
		
		Course course = scraper.getCourseWithName("Vergelijkende studie van imperatieve programmeertalen", new Date());
		
		System.out.println(course.toString());
		
		System.exit(0);
	}

	
}


