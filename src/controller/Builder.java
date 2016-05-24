package controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import model.Circuit;
import model.InputNode;
import model.Node;
import model.NodeFactory;
import model.NodeInfo;
import model.OutputNode;

public class Builder {
	private Parser parser;
	private NodeFactory nodeFactory;
	
	public Builder(){
		parser = new Parser();
		nodeFactory = new NodeFactory();
	}
	
	public Circuit buildCirctuit(String file) throws Exception {
	    Collection<NodeInfo> blueprint = retrieveBluePrint(file);
		printBluePrint(blueprint);
		
		Circuit circuit = new Circuit();
		HashMap<String, Node> nodes = createNodes(blueprint, circuit);
		setRefrences(nodes, blueprint);
		
		if (!validCircuit(circuit)){
		    Logger.getInstance().log("Invalid circuit");
		}

		return circuit;
	}
	
	private boolean validCircuit(Circuit circuit){
	    for (Node node: circuit.outputNodes){
	        if (!node.getState())
	            return false;
	    }	    	    
	    return true;
	}
	
	private void setRefrences(HashMap<String, Node> nodes, Collection<NodeInfo> blueprint){
	    for (NodeInfo nodeInfo: blueprint){
	        for (String refName: nodeInfo.references){
	            nodes.get(nodeInfo.name).addInputNode(nodes.get(refName));
	        }
	    }
	}
	
	private HashMap<String, Node> createNodes(Collection<NodeInfo> blueprint, Circuit circuit){
	   HashMap<String, Node> nodes = new HashMap<String, Node>();
       for (NodeInfo info : blueprint) {
           Node node = nodeFactory.getNode(info);
           nodes.put(info.name, node);
           // Add start and endnode to seprate list
           if (info.type.equals("PROBE")){
               circuit.outputNodes.add(node);
           } else if (info.type.equals("INPUT_LOW") || info.type.equals("INPUT_HIGH")){
               circuit.iputNodes.add(node);
           }
       }    

       return nodes;
	}
	
	private void printBluePrint(Collection<NodeInfo> blueprint){
	    for (NodeInfo info : blueprint) {
	        Logger.getInstance().log("name: " + info.name + ", type: " + info.type + ", refrenches " + info.references);
	    }
	}
	
	private Collection<NodeInfo> retrieveBluePrint(String file) throws Exception {
		return parser.getBlueprint("circuit2.txt");
	}
}
