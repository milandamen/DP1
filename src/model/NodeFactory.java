package model;

import java.util.HashMap;

import config.NodeConfig;

public class NodeFactory {
    private HashMap<String, Node> NODES;
        
    public NodeFactory(){
        NODES = new HashMap<String, Node>();
        for (Node node: NodeConfig.NODES){
            node.register(NODES);
        }
    }
    
    public Node getNode(NodeInfo nodeInfo){      
        try {
            Node node = (Node) NODES.get(nodeInfo.type).clone();   
            node.setName(nodeInfo.name);
            return node;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            System.out.println("iets gaat fout");
            return null;
        }
   }
	
}
