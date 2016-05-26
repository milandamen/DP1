package controller.validation;

import java.util.HashMap;

import model.Circuit;

public class CircuitValidator {
	private HashMap<String, ICircuitValidationSet> validationSets = new HashMap<String, ICircuitValidationSet>();
	private ICircuitValidationSet validationSet;
	
	public CircuitValidator() {
		// Set names are UPPERCASE
		validationSets.put("DEFAULT", new CircuitValidationSet());		// Default validation set
		// ...
		
		setValidationSet("DEFAULT");
	}
	
	public void setValidationSet(String setName) {
		validationSet = validationSets.get(setName.toUpperCase());
	}
	
	public boolean isValid(Circuit circuit) {
		return validationSet.isValid(circuit);
	}
	
}
