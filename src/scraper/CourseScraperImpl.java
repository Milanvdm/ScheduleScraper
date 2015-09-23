package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.jaunt.MultipleFound;
import com.jaunt.NotFound;
import com.jaunt.ResponseException;
import com.jaunt.SearchException;

import schedule.Course;
import schedule.CourseMoment;
import util.Browser;
import util.BrowserImpl;
import util.Parser;
import util.Util;

public class CourseScraperImpl //implements CourseScraper {
{
	public Browser browser = new BrowserImpl();
	
	private final static String DUTCH_COURSES_URL = "http://www.kuleuven.be/sapredir/uurrooster/keuze_studiejaar.htm?OBJID_SC=51230411&TAAL=N&SEL_JAAR=2015";
	private final static String ENGLISH_COURSES_URL = "http://www.kuleuven.be/sapredir/uurrooster/keuze_studiejaar.htm?OBJID_SC=52364422&TAAL=N&SEL_JAAR=2015";
			
	private final static String DUTCH_COURSES_CODE = "51230411";
	private final static String ENGLISH_COURSES_CODE = "51230422";
	
	public List<Course> getCourses(Date weekDate) {
		return null;
	}
	
	public List<String> getCoursesUrls() throws URISyntaxException {
		List<String> urls = new ArrayList<String>();
		
		urls.add(DUTCH_COURSES_URL);
		urls.add(ENGLISH_COURSES_URL);
		
		return urls;
	}

}
