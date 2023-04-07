package Algorithms;

import Models.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase que implementa una búsqueda en profundidad recursiva en un grafo dado.
 * @author Fabian Palacios & Carlos Muñoz
 */
public class DepthFirstSearch {
    /**
     * Método público que realiza la búsqueda en profundidad en el grafo dado.
     * @param graph el grafo en el que se realizará la búsqueda
     * @param start el vértice de inicio de la búsqueda
     * @param end el vértice destino de la búsqueda
     * @return una lista con los vértices del camino encontrado, o una lista vacía si no se encontró camino
     */
    public static List<String> dfs(Graph<String> graph, String start, String end) {
        Set<String> visited = new HashSet<>(); // conjunto para almacenar los vértices visitados
        List<String> path = new ArrayList<>(); // lista para almacenar el camino encontrado
        dfsHelper(graph, start, end, visited, path); // llamada al método auxiliar que realiza la búsqueda
        return path; // se devuelve el camino encontrado
    }

    /**
     * Método privado auxiliar que realiza la búsqueda en profundidad de manera recursiva.
     * @param graph el grafo en el que se realizará la búsqueda
     * @param current el vértice actual en el que se encuentra la búsqueda
     * @param end el vértice destino de la búsqueda
     * @param visited el conjunto de vértices visitados
     * @param path la lista con los vértices del camino encontrado
     * @return true si se encontró un camino, false en caso contrario
     */
    private static boolean dfsHelper(Graph<String> graph, String current, String end, Set<String> visited, List<String> path) {
        visited.add(current); // se marca el vértice actual como visitado
        path.add(current);  // se agrega el vértice actual al camino

        if (current.equals(end)) {  // si se llegó al vértice destino, se devuelve true
            return true;
        }

        // se recorren todos los vecinos del vértice actual
        for (String neighbor : graph.getAdjacentVertices(current)) {
            if (!visited.contains(neighbor)) {  // si el vecino no ha sido visitado, se realiza la búsqueda recursivamente
                if (dfsHelper(graph, neighbor, end, visited, path)) {
                    return true; // si se encontró un camino, se devuelve true
                }
            }
        }

        path.remove(path.size() - 1); // si se llegó a un callejón sin salida, se elimina el último vértice del camino
        return false; // se devuelve false
    }
}
