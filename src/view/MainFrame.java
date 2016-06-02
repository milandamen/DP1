package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private IOPanel ioPanel;
    private CircuitPanel circuitPanel;
    private LogPanel logPanel;
    private ControlPanel controlPanel;
    
    public MainFrame() {
        this.setTitle("DP1 Simulator");
      
        ioPanel = new IOPanel();    
        ioPanel.setBounds(0, 400, 300, 300);

        circuitPanel = new CircuitPanel();    
        circuitPanel.setBounds(200, 0, 500, 400);
        
        logPanel = new LogPanel();    
        logPanel.setBounds(400, 300, 400, 300);
        
        controlPanel = new ControlPanel();    
        controlPanel.setBounds(0, 0, 400, 200);
        
        this.add(ioPanel);
        this.add(circuitPanel);
        this.add(logPanel);
        this.add(controlPanel);
        
        this.setLayout (null);
        this.setMinimumSize(new Dimension(1000, 700));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
