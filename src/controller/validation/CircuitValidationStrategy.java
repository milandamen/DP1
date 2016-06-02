package controller.validation;

import controller.Mediator;
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
            	Mediator.getInstance().log("CircuitValidator: Invalid circuit! " + e.getMessage());
                return false;
            } catch (StackOverflowError e) {
            	Mediator.getInstance().log("CircuitValidator: Invalid circuit! Feedback loop.");
                return false;
            }
        }
		
		Mediator.getInstance().log("CircuitValidator: Valid circuit.");
        return true;
	}

}
