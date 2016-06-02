package controller;

import model.Circuit;

public interface IBuilder {
	
	public Circuit buildCirctuit(String file) throws Exception;
	
}
