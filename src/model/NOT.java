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
		
		return !inputNodes.get(0).getState();
	}
	
	@Override
	public void addInputNode(Node node) throws NodeCannotHaveMultipleImputsException {
		if (inputNodes.size() > 0) {
			throw new NodeCannotHaveMultipleImputsException(name);
		}
		
		inputNodes.add(node);
	}

}
