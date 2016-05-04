package controller;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import exceptions.NodeAlreadyExistsException;
import exceptions.NodeNotFoundException;

/**
 * Validates and parses input text file
 */
public class Parser {
	
	public HashMap<String, NodeInfo> getBlueprint(String filePath) throws Exception {
		return parseLines(readFile(filePath));
	}
	
	private List<String> readFile(String filePath) throws IOException {
		System.out.println("Reading file " + Paths.get(filePath).toAbsolutePath());
		return Files.readAllLines(Paths.get(filePath).toAbsolutePath(), Charset.forName("UTF-8"));
	}
	
	private HashMap<String, NodeInfo> parseLines(List<String> lines) throws Exception {
		HashMap<String, NodeInfo> nodes = new HashMap<String, NodeInfo>();
		
		boolean namesState = true;
		int lineCount = -1;
		
		for (String line : lines) {
			lineCount++;
			
			if (line.length() == 0 && nodes.size() > 0) {
				namesState = false;
				continue;
			}
			
			if (line.charAt(0) == '#') {
				continue;
			}
			
			if (namesState) {
				// Definition of node names and corresponding types
		        String patternString = "^(\\w+):(?:\\s+)?(\\w+);";

		        Pattern pattern = Pattern.compile(patternString);
		        Matcher matcher = pattern.matcher(line);

		        if (matcher.find()) {
		        	String nodeName = matcher.group(1);
		        	String nodeType = matcher.group(2);
		        	
		        	if (nodes.containsKey(nodeName)) {
		    			System.out.println("Error at blueprint file line: " + lineCount);
			        	throw new NodeAlreadyExistsException(nodeName);
			        }
			        
			        nodes.put(nodeName, new NodeInfo(nodeName, nodeType));
		        }
			} else {
				// Definition of node references
				String patternString = "^(\\w+):\\s+((?:(?:\\w+?),)*(?:\\w+));";

		        Pattern pattern = Pattern.compile(patternString);
		        Matcher matcher = pattern.matcher(line);

		        if (matcher.find()) {
		        	String nodeName = matcher.group(1);
		        	String nodeReferencesString = matcher.group(2);
		        	
		        	NodeInfo node = nodes.get(nodeName);
		        	if (node == null) {
		        		System.out.println("Error at blueprint file line: " + lineCount);
		        		throw new NodeNotFoundException(nodeName);
		        	}
		        	
		        	nodeReferencesString = nodeReferencesString.replace(" ", "");
		        	String[] nodeReferences = nodeReferencesString.split(",");
		        	
		        	// Check validity of references
		        	for (String name : nodeReferences) {
		        		if (nodes.get(name) == null) {
		        			System.out.println("Error at blueprint file line: " + lineCount);
		        			throw new NodeNotFoundException(nodeName);
		        		}
		        	}
		        	
		        	node.references = nodeReferences;
		        }
			}
		}
		
		return nodes;
	}
	
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
	
}
