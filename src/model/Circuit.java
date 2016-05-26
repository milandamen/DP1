package model;

import java.util.ArrayList;

import controller.Logger;
import exceptions.NoInputNodesException;

public class Circuit {
    public ArrayList<OutputNode> outputNodes;
    public ArrayList<InputNode> iputNodes;
    
    public Circuit(){
        outputNodes = new ArrayList<OutputNode>();
        iputNodes = new ArrayList<InputNode>();
    }
    
    // Strategy pattern gebruiken?
    public boolean isValid(){
        for (Node node: outputNodes){
            try {
                node.getState();                   
            } catch (NoInputNodesException e) {
                Logger.getInstance().log("Circuit: Invalid circuit! " + e.getMessage());
                return false;
            } catch (StackOverflowError e) {
                Logger.getInstance().log("Circuit: Invalid circuit! Feedback loop." );
                return false;
            }
        }   
        Logger.getInstance().log("Circuit: Valid circuit.");
        return true;
    }
}
