package view;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class LogPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JTextArea textArea;
	
	public LogPanel() {
		this.setLayout(new BorderLayout());
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		JScrollPane scrollPane = new JScrollPane(textArea);
		this.add(scrollPane, BorderLayout.CENTER);
	}

	public void log(String string) {
		textArea.append(string);
	}

}
