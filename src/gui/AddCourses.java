package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import schedule.Schedule;

public class AddCourses extends JPanel implements ActionListener {

	private JButton addCourse;
	private JTextField courseName;
	
	private Schedule schedule;
	
	public AddCourses() {
		addCourse = new JButton("Add course");
		courseName = new JTextField();
		
		addCourse.addActionListener(this);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(courseName);
		add(addCourse);
	}
	
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if(clicked == addCourse) {
			String courseNameString = courseName.getText();
			
			try {
				schedule.addCourse(courseNameString);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			courseName.setText("");
		}
	}
	
}
