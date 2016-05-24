package model;

public class InputNode extends Node {
	private boolean state = STATE_LOW;
	
	public InputNode(String name, boolean state) {
		super(name);
		this.state = state;
		propegationDelay = 0;
	}

	@Override
	public boolean getState() {
		return state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}

}
