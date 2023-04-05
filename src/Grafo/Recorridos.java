/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author carlo
 */
public class Recorridos {
    public static void dfs(Graph<String> graph, String start) {
        Set<String> visited = new HashSet<>();
        dfsHelper(graph, start, visited);
    }

    private static void dfsHelper(Graph<String> graph, String current, Set<String> visited) {
        System.out.print(current + " ");
        visited.add(current);
        List<String> neighbors = graph.getAdjacentVertices(current);
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                dfsHelper(graph, neighbor, visited);
            }
        }
    }
    
    public static void bfs(Graph<String> graph, String startVertex) {
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.add(startVertex);
        bfsHelper(graph, visited, queue);
    }

    private static void bfsHelper(Graph<String> graph, Set<String> visited, Queue<String> queue) {
        if (queue.isEmpty()) {
            return;
        }
        String current = queue.poll();
        visited.add(current);
        System.out.print(current + " ");
        List<String> neighbors = graph.getAdjacentVertices(current);
        for (String neighbor : neighbors) {
            if (!visited.contains(neighbor)) {
                queue.add(neighbor);
            }
        }
        bfsHelper(graph, visited, queue);
    }
}
