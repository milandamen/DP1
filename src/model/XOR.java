package model;

import exceptions.NoInputNodesException;

public class XOR extends Node {
	
	public XOR(String name) {
		super(name);
	}
	
	@Override
	public boolean getState() throws NoInputNodesException {
		if (inputNodes.size() < 2) {
			throw new NoInputNodesException(name);
		}
		
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
