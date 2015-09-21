package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import schedule.Course;
import util.Browser;
import util.BrowserImpl;
import util.Parser;

public class CourseScraperImpl implements CourseScraper {
	
	public Browser browser = new BrowserImpl();
	
	private final static String COURSE_SEARCH_RESULTS = "div#results";
	private final static String COURSE_SEARCH_HIT_CLASS = "search-result";
	private final static String COURSE_SEARCH_HIT_TITLE = "title";
	
	private final static String COURSE_CONTENT = "div#content";
	private final static String COURSE_DURATION_CLASS = "span.duur";
	

	public Course getCourseWithId(String id, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	public Course getCourseWithName(String name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getCourseScheduleUrl(String courseUrl) throws IOException {
		
		Document document = Jsoup.connect(courseUrl).get();
		
		Element courseContent = document.select(COURSE_CONTENT).first();
		
		Element courseDuration = courseContent.select(COURSE_DURATION_CLASS).first();
		
		String scheduleUrlWithJs = courseDuration.getElementsByAttribute("href").attr("href");
		
		String scheduleUrl = Parser.getScheduleUrl(scheduleUrlWithJs);
		
		return scheduleUrl;
		
	}
	
	private String getCourseUrl(String query) throws URISyntaxException {
		String queryUrl = courseQuery(query);
		
		browser.waitForJS(queryUrl);
		
		String pageSource = browser.getPageSource();
		
		Document document = Jsoup.parse(pageSource);
		
		Element searchResults = document.select(COURSE_SEARCH_RESULTS).first();
		Element result = searchResults.getElementsByClass(COURSE_SEARCH_HIT_CLASS).first();
		
		String url = result.getElementsByClass(COURSE_SEARCH_HIT_TITLE).first().getElementsByAttribute("href").attr("href");
	
		return url;
	}
	
	private String courseQuery(String query) throws URISyntaxException {
		URIBuilder uriBuilder = new URIBuilder("https://onderwijsaanbod.kuleuven.be");
		
		URIBuilder queryUri = new URIBuilder();
		queryUri.addParameter("q", query)
				.addParameter("idx", "ALL")
				.addParameter("jaar", "2015")
				.addParameter("isvertaling", "0");
		
		
		uriBuilder	.setPath("/oa/find/")
					.setFragment("/" + queryUri.toString());
		
		
		return uriBuilder.toString().replace("+", "%20");
	}

}
