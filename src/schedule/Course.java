package schedule;

import java.util.List;

public class Course {
	
	private String name;
	private String url;

	private List<CourseMoment> courseMoments;
	
	public Course(String name, String url) {
		this.name = name;
		this.url = url;;
	}

	public List<CourseMoment> getCourseMoments() {
		return courseMoments;
	}

	public void setCourseMoments(List<CourseMoment> courseMoments) {
		this.courseMoments = courseMoments;
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
