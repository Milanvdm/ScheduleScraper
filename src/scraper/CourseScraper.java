package scraper;

import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import schedule.CourseMoment;

public interface CourseScraper {
	
	public abstract List<CourseMoment> getCourseMoments(String courseUrl, Date weekDate)  throws URISyntaxException, IOException, ParseException, InterruptedException;

	public abstract String getCourseUrl(String courseName) throws URISyntaxException;

}
