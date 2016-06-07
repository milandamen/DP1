package model;

import exceptions.NoInputNodesException;
import exceptions.NodeCannotHaveMultipleImputsException;

public class NOT extends Node {

	public NOT() {
        super();
        type = this.getClass().getSimpleName();
    }

	@Override
	public boolean getState() throws NoInputNodesException {
		if (inputNodes.size() == 0) {
			throw new NoInputNodesException(name);
		}
		// Get state of the connected node and return state of this node
		return !inputNodes.get(0).getState();
	}
	
	// This node can have only one input
	@Override
	public void addInputNode(Node node) throws NodeCannotHaveMultipleImputsException {
		if (inputNodes.size() > 0) {
			throw new NodeCannotHaveMultipleImputsException(name);
		}
		
		inputNodes.add(node);
	}

}
