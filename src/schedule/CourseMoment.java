package schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseMoment implements Comparable<CourseMoment> {
	
	private String location;
	private Date date;
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
	
	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		
		return location + " - " + sdf.format(startTime) + " tot " + sdf.format(endTime);
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date day) {
		this.date = day;
	}



	public int compareTo(CourseMoment moment2) {
		if(this.getStartTime().before(moment2.getDate())){
            return -1;
        }
        if(this.getStartTime().after(moment2.getDate())){
            return 1;
        }
        return 0;
	}
	

}
