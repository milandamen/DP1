package controller.validation;

import model.Circuit;

public class CircuitValidator {
	private IValidationStrategy validationStrategy;
	
	public CircuitValidator(IValidationStrategy validationStrategy) {
		setValidationStrategy(validationStrategy);
	}
	
	public void setValidationStrategy(IValidationStrategy validationStrategy) {
		this.validationStrategy = validationStrategy;
	}
	
	public boolean isValid(Circuit circuit) {
		return validationStrategy.isValid(circuit);
	}
	
}
