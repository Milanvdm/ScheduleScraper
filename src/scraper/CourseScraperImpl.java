package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;

import org.apache.http.client.utils.URIBuilder;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import schedule.Course;
import util.Browser;

public class CourseScraperImpl implements CourseScraper {
	
	public Browser browser = new Browser();

	public Course getCourseWithId(String id, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	public Course getCourseWithName(String name, Date date) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private String getCourseUrl(String query) throws URISyntaxException, IOException {
		String queryUrl = courseQuery(query);
		
		String pageSource = browser.getPageSourceAfterJS(queryUrl);
		
		
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
