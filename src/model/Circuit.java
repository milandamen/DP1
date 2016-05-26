package model;

import java.util.ArrayList;

import controller.logging.Logger;
import exceptions.NoInputNodesException;

public class Circuit {
    public ArrayList<OutputNode> outputNodes;
    public ArrayList<InputNode> iputNodes;
    
    public Circuit(){
        outputNodes = new ArrayList<OutputNode>();
        iputNodes = new ArrayList<InputNode>();
    }
    
}
