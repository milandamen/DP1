package controller.validation;

import controller.logging.Logger;
import exceptions.NoInputNodesException;
import model.Circuit;
import model.Node;

public class CircuitValidationStrategy implements IValidationStrategy {

	@Override
	public boolean isValid(Circuit circuit) {
		for (Node node: circuit.outputNodes){
            try {
                node.getState();     
            } catch (NoInputNodesException e) {
                Logger.getInstance().log("CircuitValidator: Invalid circuit! " + e.getMessage());
                return false;
            } catch (StackOverflowError e) {
                Logger.getInstance().log("CircuitValidator: Invalid circuit! Feedback loop.");
                return false;
            }
        }
		
        Logger.getInstance().log("CircuitValidator: Valid circuit.");
        return true;
	}

}
