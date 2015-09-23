package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;

import schedule.Schedule;

public class MainFrame extends JFrame {
	
	private Schedule schedule;
	
	private TextPanel textPanel;
	
	private ToolBar toolBar;
	private JButton next;
	private JButton prev;
	
	public MainFrame(Schedule schedule) {
		super("ScheduleScraper");
		
		this.schedule = schedule;
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		toolBar = new ToolBar();
		
		
		
		add(textPanel, BorderLayout.CENTER);
		add(prev, BorderLayout.NORTH);
		add(next, BorderLayout.NORTH);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600,600);
		setVisible(true);
	}
	
	public Schedule getSchedule() {
		return this.schedule;
	}

}
