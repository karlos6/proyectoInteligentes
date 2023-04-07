/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Models.Edge;
import Models.Graph;
import Models.Node;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 *
 * @author carlo
 */
public class BeamSearch {
    
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
                
        List<String> recorridoBeamSearch = beamSearchAlgoritmo(mapa, start, goal, beamWidth, grafo, activarHeuristica);
               
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
