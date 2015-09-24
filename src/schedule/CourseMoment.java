package schedule;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CourseMoment implements Comparable<CourseMoment> {
	
	private String location;
	private Date date;
	private Date startTime;
	private Date endTime;
	
	public CourseMoment(String location, Date date, Date startTime, Date endTime) {
		this.location = location;
		this.date = date;
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
		SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
		SimpleDateFormat sdfDate = new SimpleDateFormat("dd.MM.yyyy");
		
		return location + " - " + sdfDate.format(date) + ": " + sdfTime.format(startTime) + " tot " + sdfTime.format(endTime);
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date day) {
		this.date = day;
	}



	public int compareTo(CourseMoment moment2) {
		if(this.getStartTime().before(moment2.getStartTime())){
            return -1;
        }
        if(this.getStartTime().after(moment2.getStartTime())){
            return 1;
        }
        return 0;
	}
	

}
