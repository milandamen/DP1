package model;

import java.util.ArrayList;

public class Circuit {
    public ArrayList<OutputNode> outputNodes;
    public ArrayList<InputNode> iputNodes;
    public ArrayList<Node> nodes;
    
    public Circuit(){
        outputNodes = new ArrayList<OutputNode>();
        iputNodes = new ArrayList<InputNode>();
        nodes = new ArrayList<Node>();
    }
    
    public void setInputNodeValue(boolean state, String name){
        for (InputNode node: iputNodes){
            if (node.getName() == name)
                node.setState(state);
        }
    }
}
