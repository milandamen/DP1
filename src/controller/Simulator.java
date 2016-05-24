package controller;

import exceptions.NoInputNodesException;
import model.Circuit;
import model.Node;

public class Simulator {	
	public Simulator(){
	}
	
	public void simulate(Circuit circuit){
        try {
            for(Node node: circuit.outputNodes){
                Logger.getInstance().log(node.getName() + " - state " + node.getState() + " - delay " + node.getPropegationDelay());
		    }
        } catch (NoInputNodesException e) {
            Logger.getInstance().log("Simulator: Invalid circuit!");
        }  catch (StackOverflowError e) {
            Logger.getInstance().log("Simulator: Invalid circuit!");
        }
	}
}
