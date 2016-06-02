package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

import controller.Mediator;
import exceptions.NoInputNodesException;
import model.Node;

public class IOPanel extends JPanel {

    private ArrayList<JCheckBox> checkBoxes;
    
    public IOPanel(){
        checkBoxes = new ArrayList<JCheckBox>();
        
        this.setMinimumSize(new Dimension(400, 400));
        this.setBackground(Color.red);
        
        int index = 0;
        for (Node node: Mediator.getInstance().getCircuit().iputNodes){
            JCheckBox box = new JCheckBox(node.getName());
            box.setBounds(0, index * 30, 100, 30);
            try {
                box.setSelected(node.getState());
            } catch (NoInputNodesException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
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
        for (Node node: Mediator.getInstance().getCircuit().outputNodes){
            JCheckBox box = new JCheckBox(node.getName());
            box.setBounds(150, index * 30, 100, 30);
            try {
                box.setSelected(node.getState());
            } catch (NoInputNodesException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            box.setEnabled(false);
            checkBoxes.add(box);
            index++;
        }
        
        for (JCheckBox checkBox: checkBoxes){
            this.add(checkBox);
        }

        this.setLayout (null);
    }
}
