package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import controller.Mediator;
import exceptions.NoInputNodesException;
import model.Node;
import model.SimulationStep;

public class CircuitPanel extends JPanel implements Observer {
    private static final long serialVersionUID = 1L;
    private HashMap<String, NodePanel> nodePanels;
    private final int ARR_SIZE = 4;
    private final int NODE_WITDH = 80;
    private final int NODE_HEIGHT = 40;
    private final int MAX_WITDH = 700;
    private final int MAX_HEIGHT = 350;
    private final int MAX_RETRIES = 100;
    private final float MIN_MARGIN = NODE_WITDH;
    
	public CircuitPanel() {
	    // Register this class as observer in the mediator
	    Mediator.getInstance().addObserver(this);
	    nodePanels = new HashMap<String, NodePanel>();
        this.setLayout(null);      	
	}
	
	// On a update from the mediator (trough observer), remove old nodes and place new ones
	@Override
    public void update(Observable arg0, Object arg1) {
	    if (arg1 != null){
	        simulateCircuit(arg1);
	    } else {
            this.removeAll(); 
            nodePanels = new HashMap<String, NodePanel>();
            
            addNodesToPanel();
	    }
        
        this.revalidate();
        this.repaint();
    }
	
	private void simulateCircuit(Object argument){
	    SimulationStep step = (SimulationStep) argument;
        for (Node node: step.nodes){
            for (Entry<String, NodePanel> entry : nodePanels.entrySet()) {
                String key = entry.getKey();
                NodePanel value = entry.getValue();
                if (key == node.getName()){
                    try {
                        value.changeLabel(node.getStateValue());
                    } catch (NoInputNodesException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
            //System.out.println(node);
        }
        //System.out.println("----");
	}
	
	private void addNodesToPanel(){
        if (Mediator.getInstance().getCircuit() == null) return;
        
        for (Node node: Mediator.getInstance().getCircuit().nodes){
            addNode(node, Color.PINK);
        }
        
        for (Node node: Mediator.getInstance().getCircuit().iputNodes){
            addNode(node, Color.CYAN);
        }
        
        for (Node node: Mediator.getInstance().getCircuit().outputNodes){
            addNode(node, Color.GREEN);
        }
	}
	
	// Create and add all nodepanels to the GUI
	private void addNode(Node node, Color color){
	    NodePanel nodePanel = new NodePanel(node, color);
	    
	    for (int i = 0; i <= MAX_RETRIES; i++){
	        int xPos = (int) (Math.random() * MAX_WITDH);
	        int yPos = (int) (Math.random() * MAX_HEIGHT);
	        
	        // Make sure the postion is free
	        if (positionIsFree(xPos, yPos)){
	            nodePanel.setBounds(xPos, yPos, NODE_WITDH, NODE_HEIGHT);
	            break;
	        }	            
	        
	        if (i == MAX_RETRIES){
	            nodePanel.setBounds(xPos, yPos, NODE_WITDH, NODE_HEIGHT);
	        }  
	    }    
	    
	    nodePanels.put(node.getName(), nodePanel);
	    this.add(nodePanel);
	}
	
	// Check if there is an other node placed within a certain distant of the next one
    private boolean positionIsFree(int xPos, int yPos){
        for (NodePanel nodePanel : nodePanels.values()) {      
            float distance = (float) Math.sqrt(
                                    Math.pow((xPos + NODE_WITDH / 2) - (nodePanel.getX() + NODE_WITDH / 2), 2) + 
                                    Math.pow((yPos + NODE_HEIGHT / 2) - (nodePanel.getY() + NODE_HEIGHT / 2), 2) );

            if (distance < MIN_MARGIN)
                return false;
        }   
        return true;
    }
	
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
    }
    
    // Paint the panels on the field
	public void paintChildren(Graphics g) {
	    super.paintChildren(g);
	    if (nodePanels.size() > 0){
    	    for (NodePanel nodePanel : nodePanels.values()) {
                for (Node node: nodePanel.node.getInputNodes()){
                    NodePanel from = nodePanels.get(node.getName());
                    
                    int xTo = nodePanel.getX() + NODE_WITDH / 2;
                    int yTo = nodePanel.getY() + NODE_HEIGHT / 2;
                    int xFrom = from.getX() + NODE_WITDH / 2;
                    int yFrom = from.getY() + NODE_HEIGHT / 2;                   
                    int xRel = xTo - xFrom;
                    int yRel = yTo - yFrom;

                    // Check on what sides the panels are connected to each other to draw the arrow
                    if (xTo < xFrom && yTo < yFrom){
                        if (Math.abs(xRel) < Math.abs(yRel)) {
                            drawArrow(g, xFrom, yFrom - NODE_HEIGHT / 2, xTo, yTo + NODE_HEIGHT / 2);
                        } else {
                            drawArrow(g, xFrom - NODE_WITDH / 2, yFrom, xTo + NODE_WITDH / 2, yTo);
                        }
                    }                   
                    else if (xTo > xFrom && yTo < yFrom){
                        if (Math.abs(xRel) < Math.abs(yRel)) {
                            drawArrow(g, xFrom, yFrom - NODE_HEIGHT / 2, xTo, yTo + NODE_HEIGHT / 2);
                        } else {
                            drawArrow(g, xFrom + NODE_WITDH / 2, yFrom, xTo - NODE_WITDH / 2, yTo);
                        }
                    }                   
                    else if (xTo < xFrom && yTo > yFrom){
                        if (Math.abs(xRel) < Math.abs(yRel)) {
                            drawArrow(g, xFrom, yFrom + NODE_HEIGHT / 2, xTo, yTo - NODE_HEIGHT / 2);
                        } else {
                            drawArrow(g, xFrom - NODE_WITDH / 2, yFrom, xTo + NODE_WITDH / 2, yTo);
                        }
                    }
                    else if (xTo > xFrom && yTo > yFrom){
                        if (Math.abs(xRel) < Math.abs(yRel)) {
                            drawArrow(g, xFrom, yFrom + NODE_HEIGHT / 2, xTo, yTo - NODE_HEIGHT / 2);
                        } else {
                            drawArrow(g, xFrom + NODE_WITDH / 2, yFrom, xTo - NODE_WITDH / 2, yTo);
                        }
                    }
                }
            }
	    }
	}
	
	// Draw an arrow between 2 panels
    private void drawArrow(Graphics g1, int x1, int y1, int x2, int y2) {
        Graphics2D g = (Graphics2D) g1.create();

        double dx = x2 - x1, dy = y2 - y1;
        double angle = Math.atan2(dy, dx);
        int len = (int) Math.sqrt(dx*dx + dy*dy);
        AffineTransform at = AffineTransform.getTranslateInstance(x1, y1);
        at.concatenate(AffineTransform.getRotateInstance(angle));
        g.transform(at);

        // Draw horizontal arrow starting in (0, 0)
        g.drawLine(0, 0, len, 0);
        g.fillPolygon(new int[] {len, len-ARR_SIZE, len-ARR_SIZE, len},
                      new int[] {0, -ARR_SIZE, ARR_SIZE, 0}, 4);
    }

}
