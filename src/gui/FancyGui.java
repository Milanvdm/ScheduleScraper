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

	private JFrame mainFrame;
	private JLabel headerLabel;
	private JLabel statusLabel;
	private JPanel controlPanel;

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

	private class ButtonClickListener implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String command = e.getActionCommand(); 
			
			if( command.equals( "Next" ))  {
				statusLabel.setText("Next Button clicked.");
			}
			else if( command.equals( "Previous" ) )  {
				statusLabel.setText("Previous Button clicked."); 
			}
			else  {
				statusLabel.setText("Cancel Button clicked.");
			}  	
		}		
	}
}


