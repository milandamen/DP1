package model;

public class AND extends Node {

	@Override
	public boolean getState() {
		for (Node node : inputNodes) {
			if (!node.getState()) {
				return false;
			}
		}
		return true;
	}
}
