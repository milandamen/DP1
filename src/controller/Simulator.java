package controller;

import java.util.ArrayList;
import java.util.Stack;

import exceptions.NoInputNodesException;
import model.Circuit;
import model.InputNode;
import model.Node;
import model.OutputNode;
import model.SimulationStep;

public class Simulator implements ISimulator {
    private Stack <SimulationStep> simulationSteps;
    
    public Simulator(){
        simulationSteps = new Stack<SimulationStep>();
    }
    
	public void simulate(Circuit circuit){
	    simulationSteps = new Stack<SimulationStep>();
	    
		Mediator.getInstance().log("Simulator: Starting simulation..");
		if (circuit == null) {
			Mediator.getInstance().log("Simulator: No circuit given, it was probably invalid.");
			return;
		}
		
		Mediator.getInstance().log("Simulator: Probe results:\n");
        try {
            // Run the simulation by getting states from all nodes
            for(Node node: circuit.outputNodes){
            	Mediator.getInstance().log(node.getName() + " - state " + node.getState() + " - delay " + node.getPropegationDelay());
		    }
            
            Mediator.getInstance().log("\nSimulator: Simulation completed.");
        } catch (NoInputNodesException e) {
        	Mediator.getInstance().log("Simulator: Invalid circuit! " + e.getMessage());
        } catch (StackOverflowError e) {
        	Mediator.getInstance().log("Simulator: Invalid circuit! Feedback loop.");
        }
	}
	
	// Execute one simulation step
	public SimulationStep simulateStep(Circuit circuit){
	    // Split the current circuit up in multiple steps
        if (simulationSteps.size() == 0 && circuit != null){
            // STart with setting the output nodes
            ArrayList<Node> startNodes = new ArrayList<Node>();
            for (OutputNode outputNode: circuit.outputNodes){
                startNodes.add((Node) outputNode);
            }
          
            ArrayList<Node> nodes = startNodes;
            boolean nextStep = true;
            // Create each step
            while(nextStep) {
                ArrayList<Node> nextNodes = new ArrayList<Node>();
                for (Node node: nodes){
                    if (node.getInputNodes() == null || node.getInputNodes().size() == 0){
                        nextStep = false;
                    } else {
                        nextStep = true;
                        for (Node inputNode: node.getInputNodes()){
                            if (!(inputNode instanceof InputNode)) {
                                nextNodes.add(inputNode);
                            }
                        }
                        
                    }     
                }
                if (nodes.size() == 0){
                    nextStep = false;
                }
                
                if (nextNodes.size() > 0)
                    simulationSteps.add(new SimulationStep(nextNodes));
                
                nodes = nextNodes;
            }
        } 
        // Return the first value of the stack and simulation step
        return simulationSteps.pop();
    }
}
