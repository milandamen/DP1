package view;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.Node;

public class NodePanel extends JPanel{
    private static final long serialVersionUID = 1L;

    private JLabel label;
    
    public Node node;
    
    public NodePanel(Node node, Color color){
        this.node = node;
        this.setBackground(color);

        this.setLayout(new GridBagLayout());
        label = new JLabel(node.getGUIName());
        label.setForeground(Color.BLUE);
       
        this.add(label);
        this.setBorder(BorderFactory.createLineBorder(Color.ORANGE)); 
    }
}
