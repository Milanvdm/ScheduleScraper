package testing;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.jaunt.MultipleFound;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.SearchException;

import schedule.Course;
import schedule.Schedule;
import scraper.CourseScraper;
import scraper.CourseScraperImpl;
import util.Browser;
import util.BrowserImpl;


public class UriTest {




	public static void main(String[] args) throws URISyntaxException, IOException, ParseException, ClassNotFoundException, InterruptedException, NotFound, MultipleFound, FailingHttpStatusCodeException, ResponseException, SearchException {

		CourseScraperImpl scraper = new CourseScraperImpl();
		
		scraper.getCourses(new Date());
		
		System.out.println();
		
		System.exit(0);
	}

	
}


