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
        Mediator.getInstance().addObserver(this);
        checkBoxes = new ArrayList<JCheckBox>();
        
        this.setMinimumSize(new Dimension(400, 400));
        this.setBackground(Color.red);
  
        CreateCheckBoxes();
        this.setLayout (null);
    }
    
    private void CreateCheckBoxes(){    
        if (Mediator.getInstance().getCircuit() != null){
            int index = 0;
            for (InputNode node: Mediator.getInstance().getCircuit().iputNodes){
                JCheckBox box = new JCheckBox(node.getName());
                box.setBounds(0, index * 30, 100, 30);
                box.setSelected(node.getState());
                
                box.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JCheckBox box = (JCheckBox) e.getSource();
                        Mediator.getInstance().getCircuit().setInputNodeValue(box.isSelected(), box.getText());
                    }
                });
                
                checkBoxes.add(box);
                index++;
            }
            
            index = 0;
            for (OutputNode node: Mediator.getInstance().getCircuit().outputNodes){
                JCheckBox box = new JCheckBox(node.getName());
                
                box.setBounds(150, index * 30, 100, 30); 
                box.setSelected(node.getStateValue());   
                box.setEnabled(false);
                
                checkBoxes.add(box);
                index++;
            }
            
            for (JCheckBox checkBox: checkBoxes){
                this.add(checkBox);
            }

            this.repaint();       
        }
    }

    @Override
    public void update(Observable arg0, Object arg1) {
        if (checkBoxes.size() > 0){
            for (JCheckBox box: checkBoxes){
                this.remove(box);
            }
            checkBoxes = new ArrayList<JCheckBox>();
        }
        CreateCheckBoxes();
    }
}
