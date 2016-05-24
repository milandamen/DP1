package model;

import exceptions.NoInputNodesException;

public class AND extends Node {

	public AND(String name) {
		super(name);
	}

	@Override
	public boolean getState() throws NoInputNodesException {
		if (inputNodes.size() < 2) {
			throw new NoInputNodesException(name);
		}
		
		for (Node node : inputNodes) {
			if (!node.getState()) {
				return false;
			}
		}
		return true;
	}

}
