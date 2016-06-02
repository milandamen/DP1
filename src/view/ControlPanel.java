package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

import controller.Mediator;

public class ControlPanel extends JPanel{
	private static final long serialVersionUID = 1L;
	
	private JButton openButton;
	private JButton simulateButton;

    public ControlPanel(){
        openButton = new JButton("Open");
        openButton.setBounds(50, 50, 100, 50);      
        openButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {             
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    Mediator.getInstance().buildCirctuit(selectedFile.getPath());
                }
                //Mediator.getInstance().buildCirctuit("circuit1.txt");
            }
        });  
        
        simulateButton = new JButton("Simulate");
        simulateButton.setBounds(50, 150, 100, 50);
        simulateButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                Mediator.getInstance().simulate();
            }
        }); 
        
        this.setLayout(null);
        this.add(openButton);
        this.add(simulateButton);
    }
}
