package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public MainFrame() {
        this.setTitle("DP1 Simulator");
      
        GridLayout gridLayout = new GridLayout(2,2);
        JPanel gridPanel = new JPanel(gridLayout);
        gridPanel.add(new IOPanel(), BorderLayout.WEST);
        gridPanel.add(new ControlPanel(), BorderLayout.NORTH);
        gridPanel.add(new CircuitPanel(), BorderLayout.SOUTH);
        gridPanel.add(new LogPanel(), BorderLayout.EAST);
        this.add(gridPanel);
        
        this.setMinimumSize(new Dimension(1000, 700));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
