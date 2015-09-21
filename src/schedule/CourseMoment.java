package schedule;

import java.util.Date;

public class CourseMoment {
	
	private String location;
	private Date startTime;
	private Date endTime;
	
	public CourseMoment(String location, Date startTime, Date endTime) {
		this.location = location;
		this.startTime = startTime;
		this.endTime = endTime;
	}
	
	
	public String getLocation() {
		return location;
	}
	
	public Date getStartTime() {
		return startTime;
	}
	
	public Date getEndTime() {
		return endTime;
	}
	

}
