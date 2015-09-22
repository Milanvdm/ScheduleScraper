package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;

import schedule.Course;

public interface CourseScraper {
	
	public abstract Course getCourseWithId(String id, Date date);
	
	public abstract Course getCourseWithName(String name, Date date) throws URISyntaxException, IOException, ParseException;

}
