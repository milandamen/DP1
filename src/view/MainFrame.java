package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;

import controller.Mediator;
import controller.logging.GUILogger;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;

    private IOPanel ioPanel;
    private CircuitPanel circuitPanel;
    private LogPanel logPanel;
    private ControlPanel controlPanel;
    
    public MainFrame() {
        this.setTitle("DP1 Simulator");
        this.setLayout (null);
        this.setMinimumSize(new Dimension(1000, 700));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Add all the panels to the gui
        controlPanel = new ControlPanel();    
        controlPanel.setBounds(0, 0, 200, 400);
        controlPanel.setBackground(Color.MAGENTA);
        
        circuitPanel = new CircuitPanel();    
        circuitPanel.setBounds(200, 0, 800, 400);
      
        ioPanel = new IOPanel();
        ioPanel.setBounds(0, 400, 300, 300);
        ioPanel.setBackground(Color.RED);
        
        logPanel = new LogPanel();    
        logPanel.setBounds(300, 400, 700, 300);
        logPanel.setBackground(Color.GREEN);
        
        Mediator.getInstance().setLogger(new GUILogger(logPanel));
        
        this.add(controlPanel);
        this.add(circuitPanel);
        this.add(ioPanel);
        this.add(logPanel);
        
        this.setVisible(true);
    }
}
