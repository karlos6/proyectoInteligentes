/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Models.Edge;
import Models.Graph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 *
 * @author Asus
 */
public class AStarEuclidean {
    public static List<String> aStarEuclidiana(Graph<String> graph, String start, String goal) {
        // Verificar si los nodos de inicio y meta están en el grafo
        if (!graph.getVertices().contains(start) || !graph.getVertices().contains(goal)) {
            return null;
        }

        // Crear un mapa para almacenar los nodos que ya hemos visitado
        Map<String, String> cameFrom = new HashMap<>();

        // Crear un mapa para almacenar la distancia del inicio a cada nodo
        Map<String, Integer> gScore = new HashMap<>();
        graph.getVertices().forEach(v -> gScore.put(v, Integer.MAX_VALUE));
        gScore.put(start, 0);

        // Crear un mapa para almacenar la distancia estimada desde cada nodo al objetivo
        Map<String, Integer> fScore = new HashMap<>();
        graph.getVertices().forEach(v -> fScore.put(v, Integer.MAX_VALUE));
        fScore.put(start, euclideanDistance(graph.getNodeCoordinates(start), graph.getNodeCoordinates(goal)));

        // Crear una cola de prioridad para almacenar los nodos sin explorar
        PriorityQueue<String> openSet = new PriorityQueue<>(Comparator.comparingInt(fScore::get));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            String current = openSet.poll();

            // Si el nodo actual es el objetivo, se termina la búsqueda
            if (current.equals(goal)) {
                return reconstructPath(cameFrom, current);
            }

            // Explorar los vecinos del nodo actual
            for (Edge<String> neighborEdge : graph.getAdjacentEdges(current)) {
                String neighbor = neighborEdge.destination;
                int tentativeGScore = gScore.get(current) + neighborEdge.weight;

                if (tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    //System.out.println("Nodo Actual : " + neighbor);
                    fScore.put(neighbor, tentativeGScore + euclideanDistance(graph.getNodeCoordinates(neighbor), graph.getNodeCoordinates(goal)));

                    if (!openSet.contains(neighbor)) {
                        openSet.add(neighbor);
                    }
                }
            }
        }

        // Si no se encontró ningún camino, devuelve null
        return null;
    }

    private static List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();        
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }
        return path;
    }

    private static int euclideanDistance(int[] coordinates1, int[] coordinates2) {
        int x1 = coordinates1[0];
        int y1 = coordinates1[1];
        int x2 = coordinates2[0];
        int y2 = coordinates2[1];
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
