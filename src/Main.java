import controller.Builder;
import controller.Simulator;
import model.Circuit;
import model.Node;

public class Main {

	public static void main(String[] args) throws Exception {

		
		Builder build = new Builder();
		Circuit circuit = build.buildCirctuit("circuit1.txt");
		
		circuit.iputNodes.get(0).setState(Node.STATE_LOW);
        circuit.iputNodes.get(1).setState(Node.STATE_LOW);
		
		Simulator simulator = new Simulator();
		simulator.simulate(circuit);
	}

}
