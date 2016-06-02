package view;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JPanel;

public class IOPanel extends JPanel {
    final JCheckBox chkApple = new JCheckBox("Apple");
    final JCheckBox chkMango = new JCheckBox("Mango");
    final JCheckBox chkPeer = new JCheckBox("Peer");
    
    private ArrayList<JCheckBox> checkBoxes;
    
    public IOPanel(){
        checkBoxes = new ArrayList<JCheckBox>();
        
        this.setMinimumSize(new Dimension(400, 400));
        this.repaint();
        
        for (JCheckBox checkBox: checkBoxes){
            this.add(checkBox);
        }
        
        this.add(chkApple);
        this.add(chkMango);
        this.add(chkPeer);
    }
}
