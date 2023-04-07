package Algorithms;

import Models.Graph;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Esta clase implementa el algoritmo de búsqueda en anchura en un grafo.
 * 
 * La clase proporciona un método bfs que toma un grafo, un vértice inicial y un vértice final
 * y devuelve una lista de vértices que representan el camino más corto desde el vértice inicial
 * hasta el vértice final en el grafo.
 * @author Fabian Palacios & Carlos Muñoz
 */
public class BreadthFirstSearch {    
    
    /**
     * Este método implementa el algoritmo de búsqueda en anchura en el grafo dado y devuelve
     * una lista de vértices que representan el camino más corto desde el vértice inicial hasta
     * el vértice final en el grafo.
     * 
     * @param graph el grafo en el que se busca el camino
     * @param start el vértice inicial
     * @param end el vértice final
     * @return una lista de vértices que representan el camino más corto desde el vértice inicial
     * hasta el vértice final en el grafo, o null si no se encontró un camino.
     */
    public static List<String> bfs(Graph<String> graph, String start, String end) {
        // Conjunto para mantener el registro de los nodos visitados
        Set<String> visited = new HashSet<>();
        // Mapa para guardar el nodo padre de cada nodo visitado
        Map<String, String> parent = new HashMap<>();
        // Cola para procesar los nodos por nivel
        Queue<String> queue = new LinkedList<>();
        // Lista para almacenar el camino encontrado
        List<String> path = new ArrayList<>();

        // Marcar el nodo de inicio como visitado y agregarlo a la cola
        visited.add(start);
        queue.add(start);

        // Mientras la cola no esté vacía, continuar procesando los nodos
        while (!queue.isEmpty()) {
            // Sacar el primer nodo de la cola
            String current = queue.poll();
            // Agregarlo al camino encontrado
            path.add(current);

            // Si se ha encontrado el nodo final, reconstruir y devolver el camino
            if (current.equals(end)) {
                return reconstructPath(parent, end);
            }

            // Para cada nodo adyacente no visitado, marcarlo como visitado, guardar su nodo padre y agregarlo a la cola
            for (String neighbor : graph.getAdjacentVertices(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    queue.add(neighbor);
                }
            }
        }

        // Si el camino no ha sido encontrado, devolver null
        return null;
    }

    
     /**
     * Este método toma un mapa que representa los padres de cada vértice en el camino más corto
     * desde el vértice inicial hasta el vértice final en el grafo, y el vértice final, y devuelve
     * una lista de vértices que representan el camino más corto desde el vértice inicial hasta
     * el vértice final en el grafo.
     * 
     * @param parent un mapa que representa los padres de cada vértice en el camino más corto
     * @param end el vértice final del camino más corto
     * @return una lista de vértices que representan el camino más corto desde el vértice inicial
     * hasta el vértice final en el grafo
     */
    private static List<String> reconstructPath(Map<String, String> parent, String end) {
        // Lista para almacenar el camino encontrado
        List<String> path = new ArrayList<>();
        // Agregar el nodo final al camino
        path.add(end);
        // Reconstruir el camino agregando el nodo padre del nodo actual hasta llegar al nodo inicial
        while (parent.containsKey(end)) {
            end = parent.get(end);
            path.add(0, end);
        }
        // Devolver el camino encontrado
        return path;
    }
}
