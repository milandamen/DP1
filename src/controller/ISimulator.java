package controller;

import model.Circuit;
import model.SimulationStep;

public interface ISimulator {
	
	public void simulate(Circuit circuit);
	public SimulationStep simulateStep(Circuit circuit);
	
}
