import java.util.HashMap;

import controller.Parser;
import controller.Parser.NodeInfo;


public class Main {

	public static void main(String[] args) throws Exception {
		// testing
		Parser parser = new Parser();
		HashMap<String, NodeInfo> blueprint = parser.getBlueprint("circuit2.txt");
		for (NodeInfo node : blueprint.values()) {
			System.out.println(node);
		}
		
	}

}
