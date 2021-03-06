package com.interviewcake.graph;

import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import com.interviewcake.datastructures.GraphNode;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class GraphColoring {

    public void colorGraph(GraphNode[] children, String[] colors) {
        if(children.length == 0)
            return;
        for(GraphNode node : children) {
            Set<String> usedColors = new HashSet<>();
            for(GraphNode nei : node.getNeighbors()) {
                if(node.equals(nei))
                    throw new IllegalArgumentException("Node is not allowed to link with itself.");
                if(nei.hasColor())
                    usedColors.add(nei.getColor());
            }
            for(String color : colors) {
                if(!usedColors.contains(color)) {
                    node.setColor(color);
                    break;
                }
            }
        }
    }

     // tests

     @Test
     public void lineGraphTest() {
         final GraphNode nodeA = new GraphNode("A");
         final GraphNode nodeB = new GraphNode("B");
         final GraphNode nodeC = new GraphNode("C");
         final GraphNode nodeD = new GraphNode("D");
         nodeA.addNeighbor(nodeB);
         nodeB.addNeighbor(nodeA);
         nodeB.addNeighbor(nodeC);
         nodeC.addNeighbor(nodeB);
         nodeC.addNeighbor(nodeD);
         nodeD.addNeighbor(nodeC);
         final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
         colorGraph(graph, getColors());
         validateGraphColoring(graph);
     }
 
     @Test
     public void separateGraphTest() {
         final GraphNode nodeA = new GraphNode("A");
         final GraphNode nodeB = new GraphNode("B");
         final GraphNode nodeC = new GraphNode("C");
         final GraphNode nodeD = new GraphNode("D");
         nodeA.addNeighbor(nodeB);
         nodeB.addNeighbor(nodeA);
         nodeC.addNeighbor(nodeD);
         nodeD.addNeighbor(nodeC);
         final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD};
         colorGraph(graph, getColors());
         validateGraphColoring(graph);
     }
 
     @Test
     public void triangleGraphTest() {
         final GraphNode nodeA = new GraphNode("A");
         final GraphNode nodeB = new GraphNode("B");
         final GraphNode nodeC = new GraphNode("C");
         nodeA.addNeighbor(nodeB);
         nodeA.addNeighbor(nodeC);
         nodeB.addNeighbor(nodeA);
         nodeB.addNeighbor(nodeC);
         nodeC.addNeighbor(nodeA);
         nodeC.addNeighbor(nodeB);
         final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC};
         colorGraph(graph, getColors());
         validateGraphColoring(graph);
     }
 
     @Test
     public void envelopeGraphTest() {
         final GraphNode nodeA = new GraphNode("A");
         final GraphNode nodeB = new GraphNode("B");
         final GraphNode nodeC = new GraphNode("C");
         final GraphNode nodeD = new GraphNode("D");
         final GraphNode nodeE = new GraphNode("E");
         nodeA.addNeighbor(nodeB);
         nodeA.addNeighbor(nodeC);
         nodeB.addNeighbor(nodeA);
         nodeB.addNeighbor(nodeC);
         nodeB.addNeighbor(nodeD);
         nodeB.addNeighbor(nodeE);
         nodeC.addNeighbor(nodeA);
         nodeC.addNeighbor(nodeB);
         nodeC.addNeighbor(nodeD);
         nodeC.addNeighbor(nodeE);
         nodeD.addNeighbor(nodeB);
         nodeD.addNeighbor(nodeC);
         nodeD.addNeighbor(nodeE);
         nodeE.addNeighbor(nodeB);
         nodeE.addNeighbor(nodeC);
         nodeE.addNeighbor(nodeD);
         final GraphNode[] graph = new GraphNode[] {nodeA, nodeB, nodeC, nodeD, nodeE};
         colorGraph(graph, getColors());
         validateGraphColoring(graph);
     }
 
     @Test(expected = Exception.class)
     public void loopGraphTest() {
         final GraphNode nodeA = new GraphNode("A");
         nodeA.addNeighbor(nodeA);
         final GraphNode[] graph = new GraphNode[] {nodeA};
         colorGraph(graph, getColors());
     }
 
     private static String[] getColors() {
         return new String[] {"red", "green", "blue", "orange", "yellow", "white"};
     }
 
     private static void validateGraphColoring(GraphNode[] graph) {
 
         for (final GraphNode node : graph) {
             if (!node.hasColor()) {
                 fail(String.format("Found non-colored node %s", node.getLabel()));
             }
         }
 
         int maxDegree = 0;
         final Set<String> usedColors = new HashSet<>();
 
         for (final GraphNode node : graph) {
             final Set<GraphNode> neighbors = node.getNeighbors();
             maxDegree = Math.max(maxDegree, neighbors.size());
             usedColors.add(node.getColor());
         }
 
         final int allowedColorCount = maxDegree + 1;
 
         if (usedColors.size() > allowedColorCount) {
             fail(String.format("Too many colors: %d allowed, but %d actually used",
                                allowedColorCount, usedColors.size()));
         }
 
         for (final GraphNode node : graph) {
             final Set<GraphNode> neighbors = node.getNeighbors();
             for (final GraphNode neighbor: neighbors) {
                 if (neighbor.getColor().equals(node.getColor())) {
                     fail(String.format("Neighbor nodes %s and %s have the same color %s",
                                        node.getLabel(), neighbor.getLabel(), node.getColor()));
                 }
             }
         }
     }
 
     public static void main(String[] args) {
         Result result = JUnitCore.runClasses(GraphColoring.class);
         for (Failure failure : result.getFailures()) {
             System.out.println(failure.toString());
         }
         if (result.wasSuccessful()) {
             System.out.println("All tests passed.");
         }
     }
}