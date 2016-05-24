package model;

public class NodeInfo {
    public String name;
    public String type;
    public String[] references;
    
    public NodeInfo(String name, String type) {
        this(name, type, null);
    }
    public NodeInfo(String name, String type, String[] references) {
        this.name = name;
        this.type = type;
        this.references = references;
    }
    
    public String toString() {
        String string = "Node: {name: " + this.name + ", type: " + this.type + ", references: ";
        
        if (this.references != null) {
            string += "[";
            for (String reference : this.references) {
                string += reference + ',';
            }
            string = string.substring(0, string.length() - 1) + "]";
        } else {
            string += "null";
        }
        
        return string + "}";
    }
}