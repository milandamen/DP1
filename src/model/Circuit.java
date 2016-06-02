package model;

import java.util.ArrayList;

public class Circuit {
    public ArrayList<OutputNode> outputNodes;
    public ArrayList<InputNode> iputNodes;
    
    public Circuit(){
        outputNodes = new ArrayList<OutputNode>();
        iputNodes = new ArrayList<InputNode>();
    }
    
    public void setInputNodeValue(boolean state, String name){
        for (InputNode node: iputNodes){
            if (node.getName() == name)
                node.setState(state);
        }
    }
}
