/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Models.Graph;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Clase que implementa el algoritmo A* con la heurística de distancia de Manhattan.
 * Utiliza una implementación de grafo para representar el mapa del problema.
 * @author Fabian Palacios & Carlos Muñoz
 */
public class AStarManhattan {   
    /**
     * Método que realiza la búsqueda A* con la heurística de distancia de Manhattan.
     * @param graph Grafo que representa el mapa del problema.
     * @param start Nodo de inicio de la búsqueda.
     * @param end Nodo objetivo de la búsqueda.
     * @return Lista con el camino encontrado desde el nodo de inicio hasta el nodo objetivo.
     */
    public static List<String> aStar(Graph<String> graph, String start, String end) {

        Set<String> closedSet = new HashSet<>();
        Map<String, String> cameFrom = new HashMap<>();
        Map<String, Integer> gScore = new HashMap<>();
        Map<String, Integer> fScore = new HashMap<>();

        PriorityQueue<String> openSet = new PriorityQueue<>(Comparator.comparingInt(fScore::get));

        gScore.put(start, 0);
        fScore.put(start, heuristicManhattan(graph.getNodeCoordinates(start), graph.getNodeCoordinates(end)));
        openSet.add(start);

        while (!openSet.isEmpty()) {
            String current = openSet.poll();

            if (current.equals(end)) {
                return reconstructPath(cameFrom, end);
            }

            closedSet.add(current);

            for (String neighbor : graph.getAdjacentVertices(current)) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = gScore.get(current) + graph.getEdgeWeight(current, neighbor);
                if (!openSet.contains(neighbor) || tentativeGScore < gScore.get(neighbor)) {
                    cameFrom.put(neighbor, current);
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristicManhattan(graph.getNodeCoordinates(neighbor), graph.getNodeCoordinates(end)));
                    openSet.add(neighbor);
                }
            }
        }

        return null;
    }


    /**
     * Método que calcula la heurística de distancia de Manhattan entre dos nodos.
     * @param coordinates1 Coordenadas del primer nodo.
     * @param coordinates2 Coordenadas del segundo nodo.
     * @return Valor de la heurística de distancia de Manhattan.
     */
    private static int heuristicManhattan(int[] coordinates1, int[] coordinates2) {
        return Math.abs(coordinates1[0] - coordinates2[0]) + Math.abs(coordinates1[1] - coordinates2[1]);
    }

    /**
     * El método reconstruye el camino encontrado desde el nodo inicial hasta el nodo final 
     * utilizando el objeto Map que contiene los nodos visitados para llegar a cada nodo en el camino.
     * @param cameFrom Mapa que contiene la relación entre nodos y la ruta óptima tomada para llegar a ese nodo.
     * @param current Nodo final actual.
     * @return  Lista de cadenas que contiene la ruta óptima desde el nodo inicial hasta el nodo final.
     */
    private static List<String> reconstructPath(Map<String, String> cameFrom, String current) {
        List<String> path = new ArrayList<>();
        path.add(current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            path.add(0, current);
        }
        return path;
    }
}
