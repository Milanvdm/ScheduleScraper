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

public class UnLinkCourses extends JPanel implements ActionListener {

	private JButton unLinkCourse;
	private JTextField courseName;
	private JTextField toBeLinked;
	
	private Schedule schedule;
	
	public UnLinkCourses() {
		unLinkCourse = new JButton("Unlink course");
		courseName = new JTextField();
		toBeLinked = new JTextField();
		
		unLinkCourse.addActionListener(this);

		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		add(courseName);
		add(toBeLinked);
		add(unLinkCourse);
	}
	
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}
	
	public void actionPerformed(ActionEvent e) {

		JButton clicked = (JButton) e.getSource();

		if(clicked == unLinkCourse) {
			String courseNameString = courseName.getText();
			String toBeLinkedString = toBeLinked.getText();
			
			try {
				schedule.unlinkCourses(courseNameString, toBeLinkedString);;
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			courseName.setText("");
			toBeLinked.setText("");
		}
	}

}
