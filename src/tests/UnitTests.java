package tests;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import controller.Builder;
import controller.Mediator;
import controller.Parser;
import exceptions.NodeAlreadyExistsException;
import exceptions.NodeNotFoundException;
import model.Circuit;
import model.NodeInfo;

public class UnitTests {
    @Test
    public void ParseCircuit1() {
        Parser parses = new Parser();
        
        try {
            Collection<NodeInfo> bluePrint = parses.getBlueprint("circuit1.txt");
            assertNotNull(bluePrint);
        } catch (NodeAlreadyExistsException e){
            // Circuit is invalid
        } catch (NodeNotFoundException e){
            // Circuit is invalid
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed in parsing circuit1");
        }
    }
    
    @Test
    public void buildCirctuit1() {
        Builder builder = new Builder();
        
        try {
            Circuit circuit = builder.buildCirctuit("circuit1.txt");
            assertNotNull(circuit);
            assertTrue (circuit.iputNodes.size() > 0);
            assertTrue (circuit.outputNodes.size() > 0);
            assertTrue (circuit.nodes.size() > 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Failed in building circuit1");
        }
    }

    @Test
    public void ParseCircuit2() {
        Parser parses = new Parser();
        
        try {
            Collection<NodeInfo> bluePrint = parses.getBlueprint("circuit2.txt");
            assertNotNull(bluePrint);
        } catch (NodeAlreadyExistsException e){
            // Circuit is invalid
        } catch (NodeNotFoundException e){
            // Circuit is invalid
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed in parsing circuit2");
        }
    }
    
    @Test
    public void buildCirctuit2() {
        Builder builder = new Builder();
        
        try {
            Circuit circuit = builder.buildCirctuit("circuit2.txt");
            assertNull(circuit);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Failed in building circuit2");
        }
    }
    
    @Test
    public void ParseCircuit3() {
        Parser parses = new Parser();
        
        try {
            Collection<NodeInfo> bluePrint = parses.getBlueprint("circuit3.txt");
            assertNotNull(bluePrint);
        } catch (NodeAlreadyExistsException e){
            // Circuit is invalid
        } catch (NodeNotFoundException e){
            // Circuit is invalid
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed in parsing circuit3");
        }
    }
    
    @Test
    public void buildCirctuit3() {
        Builder builder = new Builder();
        
        try {
            Circuit circuit = builder.buildCirctuit("circuit3.txt");
            assertNotNull(circuit);
            assertTrue (circuit.iputNodes.size() > 0);
            assertTrue (circuit.outputNodes.size() > 0);
            assertTrue (circuit.nodes.size() > 0);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Failed in building circuit3");
        }
    }

    @Test
    public void ParseCircuit4() {
        Parser parses = new Parser();
        
        try {
            Collection<NodeInfo> bluePrint = parses.getBlueprint("circuit4.txt");
            assertNotNull(bluePrint);
        } catch (NodeAlreadyExistsException e){
            // Circuit is invalid
        } catch (NodeNotFoundException e){
            // Circuit is invalid
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Failed in parsing circuit4");
        }
    }
    
    @Test
    public void buildCirctuit4() {
        Builder builder = new Builder();
        
        try {
            Circuit circuit = builder.buildCirctuit("circuit4.txt");
            assertNull(circuit);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            fail("Failed in building circuit4");
        }
    }
}
