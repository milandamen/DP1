package model;

import java.util.ArrayList;

public abstract class Node {
	public static final boolean STATE_HIGH = true;
	public static final boolean STATE_LOW = false;
	
	protected ArrayList<Node> inputNodes = new ArrayList<Node>();			// Nodes to be used as input
	protected boolean state = STATE_LOW;									// Default state is LOW
	protected int propegationDelay = 15;
	
	public abstract boolean getState();
	
	public void addInputNode(Node node) {
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
	
	public String getName(){
	    return name;
	}
}
