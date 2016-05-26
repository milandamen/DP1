package model;

import exceptions.NoInputNodesException;
import exceptions.NodeCannotHaveMultipleImputsException;

public class OutputNode extends Node {

	public OutputNode(String type) {
	    super();
	    this.type = type;
		propegationDelay = 0;
	}

	@Override
	public boolean getState() throws NoInputNodesException {
		if (inputNodes.size() == 0) {
			throw new NoInputNodesException(name);
		}
		
		return inputNodes.get(0).getState();
	}
	
	@Override
	public void addInputNode(Node node) throws NodeCannotHaveMultipleImputsException {
		if (inputNodes.size() > 0) {
			throw new NodeCannotHaveMultipleImputsException(name);
		}
		
		inputNodes.add(node);
	}

}
