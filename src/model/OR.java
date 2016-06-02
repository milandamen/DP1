package model;

import exceptions.NoInputNodesException;

public class OR extends Node {

	public OR() {
        super();
        type = this.getClass().getSimpleName();
    }
	
	@Override
	public boolean getState() throws NoInputNodesException {
		if (inputNodes.size() < 2) {
			throw new NoInputNodesException(name);
		}
		
		for (Node node : inputNodes) {
			if (node.getState()) {
				return true;
			}
		}
		return false;
	}

}
