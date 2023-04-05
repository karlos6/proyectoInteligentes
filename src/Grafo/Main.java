/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.List;

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

    Graph<String> grafo = new Graph<String>();
    
   // Agregar los vértices al grafo
    for (int i = 0; i < matriz.length; i++) {
        for (int j = 0; j < matriz[i].length; j++) {
            grafo.addVertex(matriz[i][j]);
        }
    }

    // Agregar las aristas al grafo
    for (int i = 0; i < matriz.length; i++) {
        for (int j = 0; j < matriz[i].length; j++) {
            // Agregar arista con el siguiente vértice en la fila
            if (j < matriz[i].length - 1) {
                grafo.addEdge(matriz[i][j], matriz[i][j+1]);
            }
            // Agregar arista con el siguiente vértice en la columna
            if (i < matriz.length - 1) {
                grafo.addEdge(matriz[i][j], matriz[i+1][j]);
            }
        }
    }

    System.out.println("Grafo : " + grafo.toString());
   
    // Obtener la lista de adyacencia del vértice "Q"
    List<String> adyacentesDeQ = grafo.getAdjacentVertices("Z");

    // Imprimir la lista de adyacencia de "Q"
    System.out.println("Adyacentes de Q: " + adyacentesDeQ);
    
    }
}