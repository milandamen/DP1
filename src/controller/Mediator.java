package controller;

import view.MainFrame;
import model.Circuit;

import java.util.Observable;

import controller.logging.ConsoleLogger;
import controller.logging.ILogger;

public class Mediator extends Observable {
	private static Mediator instance = null;
	
	private ILogger logger;
	private IBuilder builder;
	private ISimulator simulator;
	private Circuit circuit;
	
	// Defeat instantiation outside this class
	protected Mediator() {
		logger = new ConsoleLogger();	// Default logger
		builder = new Builder();		// Default builder
		simulator = new Simulator();	// Default simulator
	}
	
	public static Mediator getInstance() {
		if(instance == null) {
			instance = new Mediator();
		}
		return instance;
	}
	
	public void enableGUI() {
		new MainFrame();
	}
	
	public void setLogger(ILogger logger) {
		if (logger == null) {
			log("Cannot set logger to null in Mediator!");
			return;
		}
		this.logger = logger;
	}
	
	public void setBuilder(IBuilder builder) {
		if (builder == null) {
			log("Cannot set builder to null in Mediator!");
			return;
		}
		this.builder = builder;
	}
	
	public void setSimulator(ISimulator simulator) {
		if (simulator == null) {
			log("Cannot set simulator to null in Mediator!");
			return;
		}
		this.simulator = simulator;
	}
	
	public Circuit getCircuit() {
		return circuit;
	}
	
	public void log(String string) {
		logger.log(string);
	}
	
	public void buildCirctuit(String file) {
		try {
			circuit = builder.buildCirctuit(file);
			setChanged();
			notifyObservers();
		} catch (Exception e) {
			log("There was an error building the circuit! " + e.getMessage());
		}
	}
	
	public void simulate() {
		simulator.simulate(circuit);
        setChanged();
        notifyObservers();
	}
	
}
