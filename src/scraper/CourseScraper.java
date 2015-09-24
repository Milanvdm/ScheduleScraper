package scraper;

import java.util.Date;
import java.util.List;

import schedule.Course;

public interface CourseScraper {
	
	public abstract List<Course> getCourses(Date weekDate);

}
