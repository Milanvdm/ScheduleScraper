package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class ToolBar extends JPanel implements ActionListener {

	private JButton prev;
	private JButton next;

	private DateField dateField;

	private TextPanel textPanel;

	public ToolBar() {
		next = new JButton("Next");
		prev = new JButton("Prev");

		next.addActionListener(this);

		prev.addActionListener(this);

		setLayout(new BorderLayout());

		add(prev, BorderLayout.WEST);
		add(next, BorderLayout.EAST);
	}

	public void setDateField(DateField dateField) {
		this.dateField = dateField;
		this.dateField.setDate();

		add(dateField, BorderLayout.CENTER);
	}

	public void setTextPanel(TextPanel textPanel) {
		this.textPanel = textPanel;
	}

	public void actionPerformed(final ActionEvent e) {
		new Thread(new Runnable() {
			public void run() {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						textPanel.setText("");
						textPanel.setText("Loading...");
					}
				});

				JButton clicked = (JButton) e.getSource();

				if(clicked == prev) {
					textPanel.printPrevWeek();
					dateField.setDate();
				}
				else {
					textPanel.printNextWeek();
					dateField.setDate();
				}
			}
		}).start();


	}

}
