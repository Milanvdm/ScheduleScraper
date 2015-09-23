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

public class RemoveCourses extends JPanel implements ActionListener {

	private JButton removeCourse;
	private JTextField courseName;
	
	private Schedule schedule;
	
	public RemoveCourses() {
		removeCourse = new JButton("Remove course");
		courseName = new JTextField();
		
		removeCourse.addActionListener(this);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(courseName);
		add(removeCourse);
	}
	
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if(clicked == removeCourse) {
			String courseNameString = courseName.getText();
			
			try {
				schedule.removeCourse(courseNameString);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			courseName.setText("");
		}
	}

}
