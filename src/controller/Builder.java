package controller;

import java.util.Collection;
import java.util.HashMap;

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
	    // Get the blueprint
	    Collection<NodeInfo> blueprint = retrieveBluePrint(file);
		printBluePrint(blueprint);
		
		Mediator.getInstance().log("Builder: Building circuit..");
		
		// Create the circuit
		Circuit circuit = new Circuit();
		
		// Create the nodes
		HashMap<String, Node> nodes = createNodes(blueprint, circuit);
		
		/// Set the references between nodes
		setReferences(nodes, blueprint);

		// Validate circuit
		CircuitValidator circuitValidator = new CircuitValidator();
		if (!circuitValidator.isValid(circuit)) {
			return null;
		}
		
		Mediator.getInstance().log("Builder: Successfully builded circuit.");
		
		// Return circuit
		return circuit;
	}		
	
	private void setReferences(HashMap<String, Node> nodes, Collection<NodeInfo> blueprint) throws NodeCannotHaveMultipleImputsException{
	    for (NodeInfo nodeInfo: blueprint){
	        if (nodeInfo.references != null)
    	        for (String refName: nodeInfo.references){
    	            // Connection input is front to back, our circuit is back to front
    	            nodes.get(refName).addInputNode(nodes.get(nodeInfo.name));
    	        }
	    }
	}
	
	private HashMap<String, Node> createNodes(Collection<NodeInfo> blueprint, Circuit circuit){
	   HashMap<String, Node> nodes = new HashMap<String, Node>();
       for (NodeInfo info : blueprint) {
           Node node = nodeFactory.getNode(info);
           nodes.put(info.name, node);
           
           // Add nodes to lists in the circuit
           addNodeToCircuitList(circuit, info, node);
       }    
       return nodes;
	}
	
	private void addNodeToCircuitList(Circuit circuit, NodeInfo info, Node node){
        if (info.type.equals("PROBE")){
            circuit.outputNodes.add((OutputNode) node);
        } else if (info.type.equals("INPUT_LOW") || info.type.equals("INPUT_HIGH")){
            circuit.iputNodes.add((InputNode) node);
        } else {
            circuit.nodes.add(node); 
        }    
	}
	
	private void printBluePrint(Collection<NodeInfo> blueprint){
	    for (NodeInfo info : blueprint) {
	        Mediator.getInstance().log(info.toString());
	    }
	}
	
	private Collection<NodeInfo> retrieveBluePrint(String file) throws Exception {
		return parser.getBlueprint(file);
	}
}
