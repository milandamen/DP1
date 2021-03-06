package model;

import exceptions.NoInputNodesException;

public class XOR extends Node {
	
	public XOR() {
		super();
		type = this.getClass().getSimpleName();
	}
	
	@Override
	public boolean getState() throws NoInputNodesException {
		if (inputNodes.size() < 2) {
			throw new NoInputNodesException(name);
		}
		
		// Get state of all connected nodes and return state of this node
		return getRecursiveXorState(1, inputNodes.get(0).getState());
	}
	
	private boolean getRecursiveXorState(int index, boolean previousBit) throws NoInputNodesException {
		boolean bit = previousBit ^ inputNodes.get(index).getState();
		if (inputNodes.size() > (index + 1)) {
			return getRecursiveXorState(index + 1, bit);
		}
		return bit;
	}
	
}
