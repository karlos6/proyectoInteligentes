/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Algorithms;

import Models.Edge;
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
public class UniformCostSearch {
    
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
            recorrido.add(entry.getKey());
            if(entry.getKey() == nodoDestino){
                break;                
            }
        }     
        
        return recorrido;
        //return costoMinimo;
    }
    
}
