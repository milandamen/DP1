package model;

import exceptions.NoInputNodesException;
import exceptions.NodeCannotHaveMultipleImputsException;

public class OutputNode extends Node {
    private boolean state = STATE_LOW;
        
	public OutputNode(String type) {
	    super();
	    this.type = type;
		propegationDelay = 0;
	}
	
	public boolean getStateValue() {
        return state;
    }

	@Override
	public boolean getState() throws NoInputNodesException {
		if (inputNodes.size() == 0) {
			throw new NoInputNodesException(name);
		}
		
		state = inputNodes.get(0).getState();
		return state;
	}
	
	@Override
	public void addInputNode(Node node) throws NodeCannotHaveMultipleImputsException {
		if (inputNodes.size() > 0) {
			throw new NodeCannotHaveMultipleImputsException(name);
		}
		
		inputNodes.add(node);
	}

}
