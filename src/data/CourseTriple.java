package data;

import java.io.Serializable;

public class CourseTriple implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String url = null;
	private String linkedCourse = null;
	
	public CourseTriple(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getLinkedCourse() {
		return linkedCourse;
	}

	public void setLinkedCourse(String linkedCourse) {
		this.linkedCourse = linkedCourse;
	}
	
	
}
