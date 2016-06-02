package model;

public class InputNode extends Node {
	private boolean state = STATE_LOW;
	
	public InputNode(String type, boolean state) {
		super();
		this.state = state;
		this.type = type;
		propegationDelay = 0;
	}

	@Override
	public boolean getState() {
		return state;
	}
	
	@Override
	public String getGUIName(){
	    return this.getName() + ": " + state;
	}
	
	public void setState(boolean state) {
		this.state = state;
	}

}
