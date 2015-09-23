package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import schedule.Schedule;

public class CourseOverview extends JPanel implements ActionListener {
	
	private Schedule schedule;
	
	private JTextArea courseOverview;
	
	private JButton refresh;
	
	public CourseOverview() {
		courseOverview = new JTextArea();
		refresh = new JButton("Refresh");
		
		refresh.addActionListener(this);
		
		add(new JScrollPane(courseOverview), BorderLayout.CENTER);
		add(refresh, BorderLayout.SOUTH);
	}
	
	public void printCourses() {
		String toShow = schedule.printCourses();
		
		courseOverview.setText(toShow);
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if(clicked == refresh) {
			System.out.println("Refreshing courses");
			printCourses();
		}
		
	}

}
