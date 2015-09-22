package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseData implements Serializable {
	
	private List<CourseTriple> data = new ArrayList<CourseTriple>();
	
	public void addData(CourseTriple triple) {
		data.add(triple);
	}
	
	public CourseTriple getCourseTriple(String courseName) {
		for(CourseTriple toCheck: data) {
			if(toCheck.getName().equals(courseName)) {
				return toCheck;
			}
		}
		return null;
	}
	
	public boolean containsCourse(String courseName) {
		for(CourseTriple toCheck: data) {
			if(toCheck.getName().equals(courseName)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean containsUrl(String courseName) {
		for(CourseTriple toCheck: data) {
			if(toCheck.getName().equals(courseName)) {
				if(toCheck.getUrl() != null) {
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean containsLinkedCourse(String courseName) {
		for(CourseTriple toCheck: data) {
			if(toCheck.getName().equals(courseName)) {
				if(toCheck.getLinkedCourse() != null) {
					return true;
				}
			}
		}
		return false;
	}

}
