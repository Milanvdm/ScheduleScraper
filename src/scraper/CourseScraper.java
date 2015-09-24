package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;

import schedule.Course;

public interface CourseScraper {
	
	public abstract Map<String, Course> getCourses(Date weekDate) throws URISyntaxException, IOException, ParseException;

}
