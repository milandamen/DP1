package model;

import java.util.HashMap;

import config.NodeConfig;

public class NodeFactory {
    private HashMap<String, Node> nodes;
        
    // On creation ask all the nodes to register themselves
    public NodeFactory(){
        nodes = new HashMap<String, Node>();
        for (Node node: NodeConfig.NODES){
            node.register(nodes);
        }
    }
    
    public Node getNode(NodeInfo nodeInfo){      
        try {
            // Get the node from the HashMap
            Node node = (Node) nodes.get(nodeInfo.type).clone();   
            node.setName(nodeInfo.name);
            return node;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
   }
	
}
