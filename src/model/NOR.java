package model;

import exceptions.NoInputNodesException;

public class NOR extends Node {

	public NOR() {
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
				return false;
			}
		}
		return true;
	}

}
