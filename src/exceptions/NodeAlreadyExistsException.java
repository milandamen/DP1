package exceptions;

public class NodeAlreadyExistsException extends Exception {
	private static final long serialVersionUID = 1L;

	public NodeAlreadyExistsException(String nodeName) {
		super("This node already exists: " + nodeName);
	}
	
}
