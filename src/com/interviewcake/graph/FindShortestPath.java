package com.interviewcake.graph;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class FindShortestPath {
    
    /*
        1. Because it's a single path, we can use a global visited set to track of visited nodes
        2. We can represent LinkedList<List<String>> & last value in list is the polled value
    */
    public static String[] getPath(Map<String, String[]> graph, String startNode, String endNode) {
        if(!graph.containsKey(startNode) || !graph.containsKey(endNode))
            throw new IllegalArgumentException();
        Set<String> visited = new HashSet<>();
        LinkedList<List<String>> queue = new LinkedList<>();
        List<String> startList = new ArrayList<>();
        startList.add(startNode);
        queue.offer(startList);
        boolean foundEndNode = false;
        while(queue.size() > 0 && !foundEndNode) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                List<String> currentPath = queue.peek();
                String currentNode = currentPath.get(currentPath.size() - 1);
                if(currentNode.equals(endNode)) {
                    foundEndNode = true;
                    break;
                }
                queue.poll();
                for(String nei : graph.get(currentNode)) {
                    if(visited.contains(nei))
                        continue;
                    visited.add(nei);
                    List<String> visitedPath = new ArrayList<>(currentPath);
                    visitedPath.add(nei);
                    queue.offer(visitedPath);
                }
            }
        }
        if(!foundEndNode)
            return null;
        List<String> lstPath = queue.peek();
        String[] path = new String[lstPath.size()];
        for(int i = 0; i < path.length; i++) {
            path[i] = lstPath.get(i);
        }
        return path;
    }

    // tests

    @Test
    public void twoHopPath1Test() {
        final String[] expected = {"a", "c", "e"};
        final String[] actual = getPath(getNetwork(), "a", "e");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void twoHopPath2Test() {
        final String[] expected = {"d", "a", "c"};
        final String[] actual = getPath(getNetwork(), "d", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath1Test() {
        final String[] expected = {"a", "c"};
        final String[] actual = getPath(getNetwork(), "a", "c");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath2Test() {
        final String[] expected = {"f", "g"};
        final String[] actual = getPath(getNetwork(), "f", "g");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void oneHopPath3Test() {
        final String[] expected = {"g", "f"};
        final String[] actual = getPath(getNetwork(), "g", "f");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void zeroHopPath() {
        final String[] expected = {"a"};
        final String[] actual = getPath(getNetwork(), "a", "a");
        assertNotNull(actual);
        assertArrayEquals(expected, actual);
    }

    @Test
    public void noPathTest() {
        final String[] actual = getPath(getNetwork(), "a", "f");
        assertNull(actual);
    }

    @Test(expected = Exception.class)
    public void startNodeNotPresentTest() {
        getPath(getNetwork(), "h", "a");
    }

    @Test(expected = Exception.class)
    public void endNodeNotPresentTest() {
        getPath(getNetwork(), "a", "h");
    }

    private static Map<String, String[]> getNetwork() {
        return new HashMap<String, String[]>() { {
            put("a", new String[] {"b", "c", "d"});
            put("b", new String[] {"a", "d"});
            put("c", new String[] {"a", "e"});
            put("d", new String[] {"a", "b"});
            put("e", new String[] {"c"});
            put("f", new String[] {"g"});
            put("g", new String[] {"f"});
        }};
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(FindShortestPath.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }
    }
}