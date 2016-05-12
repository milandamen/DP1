package controller;

import java.util.HashMap;

import controller.Parser.NodeInfo;
import model.Circuit;

public class Builder {
	private Parser parser;
	
	public Builder(){
		parser = new Parser();
	}
	
	public Circuit buildCirctuit(String file) throws Exception {
		HashMap<String, NodeInfo> blueprint = retrieveBluePrint(file);
		printBluePrint(blueprint);
		
		Circuit circuit = buildCircuit(blueprint);
		
		return circuit;
	}
	
	private Circuit buildCircuit(HashMap<String, NodeInfo> blueprint){
		Circuit circuit = null;
		
		
		
		
		return circuit;
	}
	
	private void printBluePrint(HashMap<String, NodeInfo> blueprint){
		for (NodeInfo node : blueprint.values()) {
			System.out.println(node);
		}
	}
	
	private HashMap<String, NodeInfo> retrieveBluePrint(String file) throws Exception {
		return parser.getBlueprint("circuit2.txt");
	}
}
