package controller;

import java.util.Collection;
import java.util.HashMap;

import controller.validation.CircuitValidationStrategy;
import controller.validation.CircuitValidator;

import exceptions.NodeCannotHaveMultipleImputsException;
import model.Circuit;
import model.InputNode;
import model.Node;
import model.NodeFactory;
import model.NodeInfo;
import model.OutputNode;

public class Builder implements IBuilder {
	private Parser parser;
	private NodeFactory nodeFactory;
	
	public Builder(){
		parser = new Parser();
		nodeFactory = new NodeFactory();
	}
	
	public Circuit buildCirctuit(String file) throws Exception {
	    Collection<NodeInfo> blueprint = retrieveBluePrint(file);
		printBluePrint(blueprint);
		
		Mediator.getInstance().log("Builder: Building circuit..");
		Circuit circuit = new Circuit();
		HashMap<String, Node> nodes = createNodes(blueprint, circuit);
		setRefrences(nodes, blueprint);
		
		CircuitValidator circuitValidator = new CircuitValidator();
		if (!circuitValidator.isValid(circuit)) {
			return null;
		}
		
		Mediator.getInstance().log("Builder: Successfully builded circuit.");
		return circuit;
	}
	
	
	
	private void setRefrences(HashMap<String, Node> nodes, Collection<NodeInfo> blueprint) throws NodeCannotHaveMultipleImputsException{
	    for (NodeInfo nodeInfo: blueprint){
	        if (nodeInfo.references != null)
    	        for (String refName: nodeInfo.references){
    	            nodes.get(refName).addInputNode(nodes.get(nodeInfo.name));
    	        }
	    }
	}
	
	private HashMap<String, Node> createNodes(Collection<NodeInfo> blueprint, Circuit circuit){
	   HashMap<String, Node> nodes = new HashMap<String, Node>();
       for (NodeInfo info : blueprint) {
           Node node = nodeFactory.getNode(info);
           nodes.put(info.name, node);
           // Add start- and endnode to seprate list
           if (info.type.equals("PROBE")){
               circuit.outputNodes.add((OutputNode) node);
           } else if (info.type.equals("INPUT_LOW") || info.type.equals("INPUT_HIGH")){
               circuit.iputNodes.add((InputNode) node);
           }
       }    

       return nodes;
	}
	
	private void printBluePrint(Collection<NodeInfo> blueprint){
	    for (NodeInfo info : blueprint) {
	        System.out.println(info.toString());
	    }
	}
	
	private Collection<NodeInfo> retrieveBluePrint(String file) throws Exception {
		return parser.getBlueprint(file);
	}
}
