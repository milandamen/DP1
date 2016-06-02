package controller;

import exceptions.NoInputNodesException;
import model.Circuit;
import model.Node;

public class Simulator implements ISimulator {
	
	public void simulate(Circuit circuit){
		Mediator.getInstance().log("Simulator: Starting simulation..");
		if (circuit == null) {
			Mediator.getInstance().log("Simulator: No circuit given, it was probably invalid.");
			return;
		}
		
		Mediator.getInstance().log("Simulator: Probe results:\n");
        try {
            for(Node node: circuit.outputNodes){
            	Mediator.getInstance().log(node.getName() + " - state " + node.getState() + " - delay " + node.getPropegationDelay());
		    }
            
            Mediator.getInstance().log("\nSimulator: Simulation completed.");
        } catch (NoInputNodesException e) {
        	Mediator.getInstance().log("Simulator: Invalid circuit! " + e.getMessage());
        } catch (StackOverflowError e) {
        	Mediator.getInstance().log("Simulator: Invalid circuit! Feedback loop.");
        }
	}
	
}
