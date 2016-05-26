package model;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.NoInputNodesException;
import exceptions.NodeCannotHaveMultipleImputsException;

public abstract class Node implements Cloneable {
	public static final boolean STATE_HIGH = true;
	public static final boolean STATE_LOW = false;
	
	protected ArrayList<Node> inputNodes;			// Nodes to be used as input
	protected int propegationDelay = 15;
	protected String name = "";
	protected String type = "";
	
	public Node() {}
	
	public abstract boolean getState() throws NoInputNodesException;
	
	public void addInputNode(Node node) throws NodeCannotHaveMultipleImputsException {
		if (!inputNodes.contains(node)) {
			inputNodes.add(node);
		}
	}
	
	public void removeInputNode(Node node) {
		inputNodes.remove(node);
	}
	
	public void clearInputNodes() {
		inputNodes.clear();
	}
	
	public ArrayList<Node> getInputNodes() {
		return inputNodes;
	}
	
	public int getPropegationDelay() {
		int maxPropDelay = 0;
		
		for (Node node : inputNodes) {
			int propDelay = node.getPropegationDelay();
			if (propDelay > maxPropDelay) {
				maxPropDelay = propDelay;
			}
		}
		
		return maxPropDelay + propegationDelay;
	}
	
	public void setName(String name){
        this.name = name;
    }
	
	public String getName(){
	    return name;
	}
	
	// Clone is a shallow copy...
	@Override
    public Node clone() throws CloneNotSupportedException {
        Node node = (Node) super.clone();
        
        // Make sure new list is created
        node.inputNodes = new ArrayList<Node>();
        
        return node;
    }
	
	public HashMap<String, Node> register(HashMap<String, Node> nodes){
	    nodes.put(type, this);
	    return nodes;
	}
}
