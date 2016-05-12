package controller;


public class Logger {
	private static Logger instance = null;
	
	protected Logger() {
		// Exists only to defeat instantiation.
	}
	
	public static Logger getInstance() {
		if(instance == null) {
			instance = new Logger();
		}
		return instance;
	}
	
	public void log(String data){
		System.out.println(data);
	}
}
