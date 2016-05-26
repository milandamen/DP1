package config;

import java.util.ArrayList;
import java.util.Arrays;
import model.AND;
import model.InputNode;
import model.NAND;
import model.NOR;
import model.NOT;
import model.Node;
import model.OR;
import model.OutputNode;
import model.XOR;

public class NodeConfig {
    public static ArrayList<Node> NODES = new ArrayList<Node>(Arrays.asList(new AND(), new NAND(), new NOR(), new NOT(), new OR(), new XOR(), new OutputNode("PROBE"), new InputNode("INPUT_LOW", Node.STATE_LOW), new InputNode("INPUT_HIGH", Node.STATE_HIGH)));
}