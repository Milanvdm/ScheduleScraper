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




	public static void main(String[] args) throws URISyntaxException, IOException, ParseException, ClassNotFoundException, InterruptedException {

		Schedule schedule = new Schedule();
		
		//schedule.addCourse("Vergelijkende studie van imperatieve programmeertalen");
		
		//schedule.removeCourse("Vergelijkende studie van imperatieve programmeertalen");
		//schedule.removeCourse("Comparative Programming Languages");
		
		schedule.addCourse("Comparative Programming Languages");
		
		System.out.println(schedule.getAllCourseNames());
		
		//schedule.linkCourses("Vergelijkende studie van imperatieve programmeertalen", "Comparative Programming Languages");
		
		schedule.getCourses();
		
		System.out.println(schedule.printSchedule());
		
		System.exit(0);
	}

	
}


