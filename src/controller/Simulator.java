package controller;

import controller.logging.Logger;
import controller.validation.CircuitValidator;
import exceptions.NoInputNodesException;
import model.Circuit;
import model.Node;

public class Simulator {
	
	public void simulate(Circuit circuit){
		Logger.getInstance().log("Simulator: Starting simulation..");
		if (circuit == null) {
			Logger.getInstance().log("Simulator: No circuit given, it was probably invalid.");
			return;
		}
		
		CircuitValidator circuitValidator = new CircuitValidator();
		if (!circuitValidator.isValid(circuit)) {
			Logger.getInstance().log("The circuit is not valid. Simulation aborted.");
			return;
		}
		
		Logger.getInstance().log("Simulator: Probe results:\n");
        try {
            for(Node node: circuit.outputNodes){
                Logger.getInstance().log(node.getName() + " - state " + node.getState() + " - delay " + node.getPropegationDelay());
		    }
            
            Logger.getInstance().log("\nSimulator: Simulation completed.");
        } catch (NoInputNodesException e) {
            Logger.getInstance().log("Simulator: Invalid circuit! " + e.getMessage());
        }  catch (StackOverflowError e) {
            Logger.getInstance().log("Simulator: Invalid circuit! Feedback loop.");
        }
	}
	
}
