package gui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import schedule.Schedule;

public class Overview extends JPanel {
	
	private Schedule schedule;
	
	private CourseOverview courseOverview;
	
	private AddCourses addCourses;
	private RemoveCourses removeCourses;
	
	private LinkCourses linkCourses;
	private UnLinkCourses unlinkCourses;
	
	public Overview(Schedule schedule) {
		this.schedule = schedule;
		
		courseOverview = new CourseOverview();
		courseOverview.setSchedule(this.schedule);
		courseOverview.printCourses();
		
		addCourses = new AddCourses();
		addCourses.setSchedule(this.schedule);
		
		removeCourses = new RemoveCourses();
		removeCourses.setSchedule(this.schedule);
		
		linkCourses = new LinkCourses();
		linkCourses.setSchedule(this.schedule);
		
		unlinkCourses = new UnLinkCourses();
		unlinkCourses.setSchedule(this.schedule);
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		add(courseOverview);
		add(addCourses);
		add(removeCourses);
		add(linkCourses);
		add(unlinkCourses);
	}

}
