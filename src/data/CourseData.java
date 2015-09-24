package data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CourseData implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private List<String> data = new ArrayList<String>();
	
	public void addData(String courseName) {
		data.add(courseName);
	}
	
	public void removeData(String courseName) {
		data.remove(courseName);
	}
	
	public List<String> getAllCourses() {
		return data;
	}
	
	
	
	public boolean containsCourse(String courseName) {
		for(String toCheck: data) {
			if(toCheck.equals(courseName)) {
				return true;
			}
		}
		return false;
	}
	
	
	
	

	public void removeAllData() {
		data = new ArrayList<String>();
		
	}
	
	

}
