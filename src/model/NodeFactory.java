package model;

public class NodeFactory {

 //use getShape method to get object of type shape 
    public Node getNode(NodeInfo nodeInfo){
       
        switch(nodeInfo.type){
            case "AND": return new AND(nodeInfo.name);
            case "NOR": return new NOR(nodeInfo.name);
            case "NOT": return new NOT(nodeInfo.name);
            case "OR": return new OR(nodeInfo.name);
            case "XOR": return new XOR(nodeInfo.name);
            case "PROBE": return new OutputNode(nodeInfo.name);
            case "INPUT_LOW": return new InputNode(nodeInfo.name, Node.STATE_LOW);
            case "INPUT_HIGH": return new InputNode(nodeInfo.name, Node.STATE_HIGH);
        }
        
        
        // Expetion node does not exist?       
        return null;
   }
	
}
