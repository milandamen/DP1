package exceptions;

public class NodeNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;

	public NodeNotFoundException(String nodeName) {
		super("No node with this name could be found: " + nodeName);
	}
	
}
