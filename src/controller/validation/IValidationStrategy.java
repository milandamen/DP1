package controller.validation;

import model.Circuit;

public interface IValidationStrategy {
	
	public boolean isValid(Circuit circuit);
	
}
