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
    public Map<T, List<Edge<T>>> adjacencyList;
    private Map<T, int[]> coordinates;

    public Graph() {
        adjacencyList = new HashMap<>();
        coordinates = new HashMap<>();
    }

    public void addVertex(T vertex, int x, int y) {
        adjacencyList.put(vertex, new ArrayList<>());
        coordinates.put(vertex, new int[]{x, y});
    }

    public void removeVertex(T vertex) {
        adjacencyList.remove(vertex);
        coordinates.remove(vertex);
    }

    public void addEdge(T source, T destination, int weight) {
        List<Edge<T>> sourceNeighbors = adjacencyList.get(source);
        List<Edge<T>> destinationNeighbors = adjacencyList.get(destination);
        sourceNeighbors.add(new Edge<>(destination, weight));
        destinationNeighbors.add(new Edge<>(source, weight));
    }

    public void removeEdge(T source, T destination) {
        List<Edge<T>> sourceNeighbors = adjacencyList.get(source);
        List<Edge<T>> destinationNeighbors = adjacencyList.get(destination);
        sourceNeighbors.removeIf(edge -> edge.destination.equals(destination));
        destinationNeighbors.removeIf(edge -> edge.destination.equals(source));
    }

    public List<T> getVertices() {
        return new ArrayList<>(adjacencyList.keySet());
    }

    public List<Edge<T>> getAdjacentEdges(T vertex) {
        return adjacencyList.get(vertex);
    }

    public List<T> getAdjacentVertices(T vertex) {
        List<T> adjacentVertices = new ArrayList<>();
        List<Edge<T>> neighbors = adjacencyList.get(vertex);
        for (Edge<T> neighbor : neighbors) {
            adjacentVertices.add(neighbor.destination);
        }
        return adjacentVertices;
    }

    public int getEdgeWeight(T source, T destination) {
        List<Edge<T>> neighbors = adjacencyList.get(source);
        for (Edge<T> neighbor : neighbors) {
            if (neighbor.destination.equals(destination)) {
                return neighbor.weight;
            }
        }
        return -1; // Si no existe la arista devuelve -1
    }

    public int[] getNodeCoordinates(T vertex) {
        return coordinates.get(vertex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T vertex : adjacencyList.keySet()) {
            sb.append(vertex.toString()).append(": ");
            for (Edge<T> edge : adjacencyList.get(vertex)) {
                sb.append(edge.destination.toString()).append("(").append(edge.weight).append(")").append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

