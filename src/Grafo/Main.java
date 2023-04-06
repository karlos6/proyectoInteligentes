/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author Asus
 */
public class Main {
    public static void main(String[] args) {
     String[][] matriz = {
        {"A", "B", "C", "D", "E", "F", "G"},
        {"H", "I", "J", "K", "L", "M", "N"},
        {"Ñ", "O", "P", "Q", "R", "S", "T"},
        {"U", "V", "W", "X", "Y", "Z", "Z1"}
    };
    
    String[][] matriz1 = {
	            {"C", "C", "C", "C", "C", "R", "C"},
	            {"C", "M", "C", "M", "C", "M", "C"},
	            {"R", "C", "C", "C", "C", "C", "C"},
	            {"C", "M", "C", "M", "C", "M", "C"}
	        };

            Graph<String> grafo = new Graph<String>();
            Random rand = new Random();
            
           // Agregar los vértices al grafo
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    grafo.addVertex(matriz[i][j], i,j);
                }
            }

            // Agregar las aristas al grafo
            for (int i = 0; i < matriz.length; i++) {
                for (int j = 0; j < matriz[i].length; j++) {
                    // Agregar arista con el siguiente vértice en la fila
                    if (j < matriz[i].length - 1) {
                        grafo.addEdge(matriz[i][j], matriz[i][j+1], rand.nextInt(99) + 1);
                    }
                    // Agregar arista con el siguiente vértice en la columna
                    if (i < matriz.length - 1) {
                        grafo.addEdge(matriz[i][j], matriz[i+1][j], rand.nextInt(99) + 1);
                    }
                }
            }

            System.out.println("Grafo : " + grafo.toString());

            
           // dfs(grafo, "A");
            //System.out.println(" ");
            //bfs(grafo, "A");

        ///System.out.println("Grafo : \n" + grafo.toString());

        // Obtener la lista de adyacencia del vértice "Q"
        List<String> adyacentesDeQ = grafo.getAdjacentVertices("Q");

        // Imprimir la lista de adyacencia de "Q"
        System.out.println("Adyacentes de Q: " + adyacentesDeQ);


        //Recorridos.dfs(grafo, "A");
         
        //System.out.println("Adyacentes de Q: " + grafo.adjacencyList);
         
         
         
         //consto uniforme
        //Map<String, Integer> costoMinimo = Recorridos.costoUniforme(grafo.adjacencyList, "A","U");
        
        //List<String> costoMinimo = Recorridos.costoUniforme(grafo.adjacencyList, "A","H");
        //System.out.println(costoMinimo);

        //Recorridos.dfs(grafo, "A");

    }
    
}