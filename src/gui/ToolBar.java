package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ToolBar extends JPanel {
	
	private JButton prev;
	private JButton next;
	
	private JTextField dateField;
	
	public ToolBar() {
		dateField = new JTextField();
		next = new JButton("Next");
		prev = new JButton("Prev");
		
		next.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				textPanel.setText("Loading...");
				
				getSchedule().nextWeek();
			
				try {
					getSchedule().getCourses();
				} catch (Exception exception) {
					textPanel.setText(exception.getMessage());
				}
				
				String toPrint = getSchedule().printSchedule();
				
				textPanel.setText(toPrint);
				
			}
		});
		
		
		prev.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				textPanel.setText("Loading...");
				
				getSchedule().previousWeek();
				try {
					getSchedule().getCourses();
				} catch (Exception exception) {
					textPanel.setText(exception.getMessage());
				}
				
				String toPrint = getSchedule().printSchedule();
				
				textPanel.setText(toPrint);
				
			}
		});
		
		
		
		setLayout(new BorderLayout());
		
		add(prev, BorderLayout.WEST);
		add(next, BorderLayout.EAST);
		add(dateField, BorderLayout.CENTER);
	}

}
