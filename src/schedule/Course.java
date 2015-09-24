package schedule;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Course {
	
	private String name;

	private List<CourseMoment> courseMoments = new ArrayList<CourseMoment>();;
	
	public Course(String name) {
		this.name = name;
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



	public String getName() {
		return name;
	}

	
	@Override
	public String toString() {
		String toReturn = "Name: " + name + "\n" + courseMoments.toString();
		
		return toReturn;
		
	}

	public void addCourseMoment(CourseMoment courseMoment) {
		courseMoments.add(courseMoment);
		
	}
	
	
}
