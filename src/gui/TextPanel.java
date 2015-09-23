package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import schedule.Schedule;

public class TextPanel extends JPanel implements ActionListener {
	
	private JTextArea textArea = new JTextArea();
	
	private JButton refresh;
	
	private Schedule schedule;
	
	public TextPanel() {
		refresh = new JButton("Refresh");
		
		refresh.addActionListener(this);
		
		setLayout(new BorderLayout());
		
		add(new JScrollPane(textArea), BorderLayout.CENTER);
		add(refresh, BorderLayout.SOUTH);
		
		textArea.setText("Loading...");
	}
	
	public void printThisWeek() {

		try {
			schedule.getCourses();
		} catch (Exception exception) {
			textArea.setText(exception.getMessage());
		}

		String toPrint = schedule.printSchedule();

		textArea.setText(toPrint);
		
	}

	public void printNextWeek() {
		setText("");
		schedule.nextWeek();

		try {
			schedule.getCourses();
		} catch (Exception exception) {
			textArea.setText(exception.getMessage());
		}

		String toPrint = schedule.printSchedule();

		textArea.setText(toPrint);
	}
	
	public void printPrevWeek() {
		setText("");
		schedule.previousWeek();
		
		try {
			schedule.getCourses();
		} catch (Exception exception) {
			textArea.setText(exception.getMessage());
		}

		String toPrint = schedule.printSchedule();

		textArea.setText(toPrint);
	}
	
	public void setText(String text) {
		textArea.setText(text);
	}
	
	public void setSchedule(Schedule schedule) {
		this.schedule = schedule;
	}

	public void actionPerformed(ActionEvent e) {
		JButton clicked = (JButton) e.getSource();

		if(clicked == refresh) {
			System.out.println("Refreshing courses");
			printThisWeek();
		}
		
	}
	
	

}
