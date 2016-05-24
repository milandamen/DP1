package exceptions;

public class NoInputNodesException extends Exception {
	private static final long serialVersionUID = 1L;

	public NoInputNodesException(String nodeName) {
		super("This does not have any or not enough inputs: " + nodeName);
	}
	
}
