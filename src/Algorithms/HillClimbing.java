/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Models.Edge;
import Models.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author carlo
 */
public class HillClimbing {
    public static List<String> hillClimbing(Map<String, List<Edge<String>>> graph, String start, String goal, Graph<String> grafo, boolean activarHeuristica) {
        List<String> path = new ArrayList<>();
        path.add(start);
        String current = start;
        while (!current.equals(goal)) {
            
            Set<String> neighbors = new HashSet<>();
            
            for(Edge<String> nodo : graph.get(current)){
                neighbors.add(nodo.destination);                
            }
            
            //Set<String> neighbors = graph.get(current);
            if (neighbors.contains(goal)) {
                path.add(goal);
                return path;
            }
            String next = null;
            double shortestDistance = Integer.MAX_VALUE;
            for (String neighbor : neighbors) {
                double distance = heuristic(neighbor, goal, grafo,activarHeuristica );
                 // calcula la distancia heurística entre el vecino y el objetivo
                if (distance < shortestDistance) {
                    next = neighbor;
                    shortestDistance = distance;
                }
            }
            if (next == null) {
                return null; // no hay un camino posible
            }
            path.add(next);
            current = next;
        }
        return path;
    }
    
    private static double heuristic(String node, String goal, Graph<String> grafo, boolean activar) {
        // Implementa una función heurística para estimar la distancia entre el nodo y el objetivo.
        // En este ejemplo, se usa la distancia euclidiana en un grafo no ponderado.
        int x1 = grafo.getNodeCoordinates(node)[0];
        int y1 = grafo.getNodeCoordinates(node)[1];
        int x2 = grafo.getNodeCoordinates(goal)[0];
        int y2 = grafo.getNodeCoordinates(goal)[1];  
        
        if(activar){
            //Heuristica Euclidiana
            return  Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            
        }else{
            //Heuristica Manhattan
            return Math.abs(x1 - x2) + Math.abs(y1 - y2);
        }
    }
    
}
