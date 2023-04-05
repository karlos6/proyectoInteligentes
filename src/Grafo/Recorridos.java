/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.HashSet;
import java.util.List;
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
}
