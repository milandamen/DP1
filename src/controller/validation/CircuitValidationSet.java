package controller.validation;

import java.util.ArrayList;

import model.Circuit;

public class CircuitValidationSet implements ICircuitValidationSet {
	private ArrayList<IValidationStrategy> strategies = new ArrayList<IValidationStrategy>();
	
	public CircuitValidationSet() {
		// List of strategies to execute when checking for valid circuit
		strategies.add(new CircuitValidationStrategy());
		// ...
	}

	@Override
	public boolean isValid(Circuit circuit) {
	    // Validate circuit with selected strategy
		for (IValidationStrategy strategy : strategies) {
			if (!strategy.isValid(circuit)) {
				return false;
			}
		}
		return true;
	}

}
