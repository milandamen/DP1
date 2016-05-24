package exceptions;

public class NodeCannotHaveMultipleImputsException extends Exception {
	private static final long serialVersionUID = 1L;

	public NodeCannotHaveMultipleImputsException(String nodeName) {
		super("This node cannot have multiple inputs: " + nodeName);
	}
	
}
