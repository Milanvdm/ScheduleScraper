package schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Course {
	
	private String name;
	private String url;

	private List<CourseMoment> courseMoments = new ArrayList<CourseMoment>();;
	
	public Course(String name, String url) {
		this.name = name;
		this.url = url;;
	}
	
	public List<CourseMoment> getCourseMomentsOnWeekDay(int dayOfWeek) {
		List<CourseMoment> toReturn = new ArrayList<CourseMoment>();
		
		Calendar c = Calendar.getInstance();
		
		for(CourseMoment courseMoment: courseMoments) {
			c.setTime(courseMoment.getDate());
			int toCheck = c.get(Calendar.DAY_OF_WEEK);
			
			if(toCheck == dayOfWeek) {
				toReturn.add(courseMoment);
			}
		}
		
		return toReturn;
		
	}

	public List<CourseMoment> getCourseMoments() {
		return courseMoments;
	}

	public void addCourseMoments(List<CourseMoment> courseMoments) {
		this.courseMoments.addAll(courseMoments);
	}

	public String getUrl() {
		return url;
	}


	public String getName() {
		return name;
	}

	
	@Override
	public String toString() {
		String toReturn = "Name: " + name + " - " + url + "\n" + courseMoments.toString();
		
		return toReturn;
		
	}
	
	
}
