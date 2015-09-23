package gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import schedule.Schedule;

public class FancyGui {

	private Schedule schedule;

	public FancyGui() throws ClassNotFoundException, IOException{
		schedule = new Schedule();
		prepareGUI();
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException{
		FancyGui gui = new FancyGui();      
	}

	private void prepareGUI(){
		new MainFrame(schedule);
		
	}

}


