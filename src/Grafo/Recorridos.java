/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import java.util.Map;
import java.util.PriorityQueue;

import java.util.Queue;

import java.util.Set;

/**
 *
 * @author carlo
 */
public class Recorridos {
    
    // Busqueda en profundidad    
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
    
    // Busqueda en anchura
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
    
    //Busqueda por costo uniforme
    public static List<String> costoUniforme(Map<String, List<Edge<String>>> grafo, String nodoInicio, String nodoDestino ) {
        Map<String, Integer> costoMinimo = new HashMap<>(); // Mapa para almacenar el costo mínimo actual de cada nodo
        PriorityQueue<String> colaPrioridad = new PriorityQueue<>(Comparator.comparingInt(costoMinimo::get)); // Cola de prioridad para mantener los nodos por visitar
    
        // Inicializar los costos mínimos para cada nodo a infinito, excepto para el nodo de inicio
        for (String nodo : grafo.keySet()) {
            costoMinimo.put(nodo, nodo.equals(nodoInicio) ? 0 : Integer.MAX_VALUE);
            colaPrioridad.offer(nodo);
        }

        while (!colaPrioridad.isEmpty()) {
            String nodoActual = colaPrioridad.poll();
            
            // Si se llega al nodo destino, se puede terminar la búsqueda
          

            // Recorrer los nodos adyacentes al nodo actual y actualizar sus costos mínimos
            for (Edge<String> nodoAdyacente : grafo.get(nodoActual)) {
                int costoNuevo = costoMinimo.get(nodoActual) + 1; // Se asume que todos los arcos tienen costo igual a 1

                if (costoNuevo < costoMinimo.get(nodoAdyacente.destination)) {
                    colaPrioridad.remove(nodoAdyacente.destination);
                    costoMinimo.put(nodoAdyacente.destination, costoNuevo);
                    colaPrioridad.offer(nodoAdyacente.destination);                  
                }
            }
        }       
        
        List<String> recorrido = new ArrayList<>();
        
        for (Map.Entry<String, Integer> entry : costoMinimo.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            recorrido.add(entry.getKey());
            if(entry.getKey() == nodoDestino){
                break;                
            }
        }
        
        System.out.println("Recorrido");
        System.out.println(recorrido);      
        
        return recorrido;
        //return costoMinimo;
    }
    
    
    
    //Set<String>> graph
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
    
    
   
    
    public static List<String> beamSearch(Map<String, List<Edge<String>>> graph, String start, String goal, Graph<String> grafo, boolean activarHeuristica){
        Map<String, Set<String>> mapa = new HashMap<>();
        int beamWidth = 0;
        
        for (Map.Entry<String, List<Edge<String>>> entry : graph.entrySet()) {
            
            String key = entry.getKey();
            List<Edge<String>> value = entry.getValue();
            Set<String> setLista = new HashSet<>();
            
            if(value.size()> beamWidth){
                beamWidth = value.size();
            }
            
            for(Edge nodo: value){
                setLista.add(nodo.destination.toString());                
            }            
            mapa.put(key, setLista);           
        }
                
        List<String> recorridoBeamSearch = beamSearchAlgoritmo(mapa, "A", "Z", beamWidth, grafo, activarHeuristica);
               
        //System.out.println("aaaaaaaaaaaaaaaa");
        //System.out.println(mapa);
        //System.out.println(beamWidth);
        //List<String> x = beamSearchAlgoritmo(mapa, "A", "Z", beamWidth, grafo, true);
        //System.out.println(x);
        //List<String> c = beamSearchAlgoritmo(mapa, "A", "Z", beamWidth, grafo, false);
        //System.out.println(c);
        
        return recorridoBeamSearch;       
    }   
    
    public static List<String> beamSearchAlgoritmo(Map<String, Set<String>> graph, String start, String goal, int beamWidth, Graph<String> grafo, boolean activarHeuristica) {
        
        Queue<Node> frontier = new PriorityQueue<Node>(Comparator.comparingDouble(n -> n.f));
        Set<String> explored = new HashSet<String>();
        
        // Crear el nodo inicial y agregarlo a la frontera
        Node startNode = new Node(start, null, 0, heuristic(start, goal, grafo,activarHeuristica));
        frontier.add(startNode);
        
        while (!frontier.isEmpty()) {
            // Obtener los mejores nodos de la frontera, limitado por el ancho del haz
            List<Node> candidates = new ArrayList<Node>();
            int n = Math.min(beamWidth, frontier.size());
            for (int i = 0; i < n; i++) {
                candidates.add(frontier.poll());
            }
            
            // Explorar cada nodo candidato
            for (Node node : candidates) {
                if (node.state.equals(goal)) {
                    // Se ha encontrado el destino, construir y retornar el camino
                    List<String> path = new ArrayList<String>();
                    while (node != null) {
                        path.add(0, node.state);
                        node = node.parent;
                    }
                    return path;
                }
                
                explored.add(node.state);
                for (String neighbor : graph.get(node.state)) {
                    if (!explored.contains(neighbor)) {
                        double g = node.g + 1; // Distancia entre nodos adyacentes es 1
                        double h = heuristic(neighbor, goal, grafo, activarHeuristica);
                        frontier.add(new Node(neighbor, node, g, h));
                    }
                }
            }
        }
        
        // No se ha encontrado un camino al destino
        return null;
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
