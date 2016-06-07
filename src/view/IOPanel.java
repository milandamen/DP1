package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controller.Mediator;
import model.InputNode;
import model.OutputNode;

public class IOPanel extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
    private ArrayList<JCheckBox> checkBoxes;
    
    public IOPanel(){
        // Register this class as observer in the mediator
        Mediator.getInstance().addObserver(this);
        checkBoxes = new ArrayList<JCheckBox>();
        
        this.setMinimumSize(new Dimension(400, 400));
        this.setBackground(Color.red);
  
        createCheckBoxes();
        this.setLayout (null);
    }
    
    private void createInputCheckBoxes(){
        int index = 0;
        for (InputNode node: Mediator.getInstance().getCircuit().iputNodes){
            JCheckBox box = new JCheckBox(node.getName());
            box.setBounds(0, index * 30, 100, 30);
            box.setSelected(node.getState());
            
            // Add listener to check if boxes are being checked
            box.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JCheckBox box = (JCheckBox) e.getSource();
                    
                    // Update the circuit with new value
                    Mediator.getInstance().getCircuit().setInputNodeValue(box.isSelected(), box.getText());
                }
            });
            
            checkBoxes.add(box);
            index++;
        }
    }
    
    private void createOutputCheckBoxes(){
        int index = 0;
        for (OutputNode node: Mediator.getInstance().getCircuit().outputNodes){
            JCheckBox box = new JCheckBox(node.getName());
            
            box.setBounds(150, index * 30, 100, 30); 
            box.setSelected(node.getStateValue()); 
            
            // These boxes cannot be checked
            box.setEnabled(false);
            
            checkBoxes.add(box);
            index++;
        }
    }
    
    private void createCheckBoxes(){    
        if (Mediator.getInstance().getCircuit() != null){
            createInputCheckBoxes();
            createOutputCheckBoxes();
          
            for (JCheckBox checkBox: checkBoxes){
                this.add(checkBox);
            }

            this.repaint();       
        }
    }

    // On a update from the mediator (trough observer), remove old checkboxes and place new ones
    @Override
    public void update(Observable arg0, Object arg1) {
        if (checkBoxes.size() > 0){
            for (JCheckBox box: checkBoxes){
                this.remove(box);
            }
            checkBoxes = new ArrayList<JCheckBox>();
        }
        createCheckBoxes();
    }
}
