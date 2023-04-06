/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

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
    public static List<String> costoUniforme(Map<String, Set<String>> grafo, String nodoInicio, String nodoDestino ) {
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
            for (String nodoAdyacente : grafo.get(nodoActual)) {
                int costoNuevo = costoMinimo.get(nodoActual) + 1; // Se asume que todos los arcos tienen costo igual a 1

                if (costoNuevo < costoMinimo.get(nodoAdyacente)) {
                    colaPrioridad.remove(nodoAdyacente);
                    costoMinimo.put(nodoAdyacente, costoNuevo);
                    colaPrioridad.offer(nodoAdyacente);
        
                   
                    
                   
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
    
    
    
    
    
    


    

}
