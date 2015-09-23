package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.ByteOrder;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import schedule.Schedule;

public class MainFrame extends JFrame {
	
	private Schedule schedule;
	
	private TextPanel textPanel;
	
	private ToolBar toolBar;
	
	private Overview overview;
	
	public MainFrame(Schedule schedule) {
		super("ScheduleScraper");
		
		this.schedule = schedule;
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		toolBar = new ToolBar();
		overview = new Overview(schedule);
		
		DateField dateField = new DateField();
		dateField.setSchedule(schedule);
		
		textPanel.setSchedule(schedule);
		
		toolBar.setDateField(dateField);
		toolBar.setTextPanel(textPanel);
		
		add(textPanel, BorderLayout.CENTER);
		add(toolBar, BorderLayout.NORTH);
		add(overview, BorderLayout.WEST);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		
		setVisible(true);
		
		textPanel.printThisWeek();
	}
	
	public Schedule getSchedule() {
		return this.schedule;
	}

}
