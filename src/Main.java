import controller.Builder;
import controller.Simulator;
import model.Circuit;

public class Main {

	public static void main(String[] args) throws Exception {

		
		Builder build = new Builder();
		Circuit circuit = build.buildCirctuit("circuit2.txt");
		
		Simulator simulator = new Simulator();
		simulator.simulate(circuit);
	}

}
