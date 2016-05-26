package controller;

import controller.logging.ILogger;

public class Mediator {
	private static Mediator instance = null;
	
	private ILogger logger;
	
	protected Mediator() {
		// Exists only to defeat instantiation.
	}
	
	public static Mediator getInstance() {
		if(instance == null) {
			instance = new Mediator();
		}
		return instance;
	}
	
	public void setLogger(ILogger logger) {
		this.logger = logger;
	}
	
	public void log(String string) {
		logger.log(string);
	}
	
}
