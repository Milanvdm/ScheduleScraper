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

		Browser browser = new BrowserImpl();
		
		String html = browser.getScheduleHtml("https://webwsp.aps.kuleuven.be/sap(bD1lbiZjPTIwMA==)/public/bsp/sap/z_mijnuurrstrs/uurrooster_sem_lijst.htm", "51230411", "1 ");
		
		System.out.println(html);
		
		System.exit(0);
	}

	
}


