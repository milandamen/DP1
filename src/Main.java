import controller.Mediator;
import model.Circuit;
import model.Node;

public class Main {

	public static void main(String[] args) throws Exception {
		Mediator mediator = Mediator.getInstance();
		
		Circuit circuit = mediator.buildCirctuit("circuit1.txt");
		
		circuit.iputNodes.get(0).setState(Node.STATE_LOW);
		circuit.iputNodes.get(1).setState(Node.STATE_LOW);
        
		mediator.simulate(circuit);
		
	}

}
