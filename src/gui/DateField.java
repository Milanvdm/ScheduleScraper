package gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JTextField;

import schedule.Schedule;

public class DateField extends JTextField {
	
	private Schedule schedule;
	
	public DateField() {
		super();
		this.setHorizontalAlignment(JTextField.CENTER);
	}
	
	public void setDate() {
		
		
		Date date = schedule.getScheduleDate();
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		Date startDate = cal.getTime();
		cal.add(Calendar.DAY_OF_WEEK, 6);
		Date endDate = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		String toShow = sdf.format(startDate) + " - " + sdf.format(endDate);
		
		this.setText(toShow);
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

}
