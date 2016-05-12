import java.util.HashMap;

import controller.Builder;
import controller.Parser;
import controller.Simulator;
import controller.Parser.NodeInfo;
import model.Circuit;


public class Main {

	public static void main(String[] args) throws Exception {
		
		Builder build = new Builder();
		Circuit circuit = build.buildCirctuit("circuit2.txt");
		
		Simulator simulator = new Simulator();
		simulator.simulate(circuit);
		
		// testing
//		Parser parser = new Parser();
//		HashMap<String, NodeInfo> blueprint = parser.getBlueprint("circuit2.txt");
//		for (NodeInfo node : blueprint.values()) {
//			System.out.println(node);
//		}
		
	}

}
