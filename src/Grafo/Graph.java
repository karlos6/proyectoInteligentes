/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class Graph<T> {
    private Map<T, Set<T>> adjacencyList;

    public Graph() {
        adjacencyList = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacencyList.putIfAbsent(vertex, new HashSet<>());
    }

    public void addEdge(T v1, T v2) {
        adjacencyList.get(v1).add(v2);
        adjacencyList.get(v2).add(v1);
    }

    public List<T> getAdjacentVertices(T vertex) {
        return new ArrayList<>(adjacencyList.get(vertex));
    }

    public int getNumVertices() {
        return adjacencyList.size();
    }

    public int getNumEdges() {
        int count = 0;
        for (T v : adjacencyList.keySet()) {
            count += adjacencyList.get(v).size();
        }
        return count / 2;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T v : adjacencyList.keySet()) {
            sb.append(v.toString() + ": ");
            for (T neighbor : adjacencyList.get(v)) {
                sb.append(neighbor.toString() + " ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

