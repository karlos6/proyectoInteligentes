/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import Algorithms.AStarEuclidean;
import Algorithms.AStarManhattan;
import Algorithms.BreadthFirstSearch;
import Algorithms.DepthFirstSearch;
import Models.Graph;
import ReadFile.TextToMatrix;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class Main {
    public static void main(String[] args) {
        Graph<String> grafo = new Graph<String>();
        Random rand = new Random();
        
        String[][] matrix = TextToMatrix.readMatrixFromFile("C:\\Users\\carlo\\Documents\\Universidad de Caldas\\Inteligentes\\Matriz.txt");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                grafo.addVertex(matrix[i][j], i,j);
            }
        }        
        System.out.println("Vertex : "+grafo.getVertices());
        
         // Agregar las aristas al grafo
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    // Agregar arista con el siguiente vértice en la fila
                    if (j < matrix[i].length - 1) {
                        grafo.addEdge(matrix[i][j], matrix[i][j+1], rand.nextInt(99) + 1);
                    }
                    // Agregar arista con el siguiente vértice en la columna
                    if (i < matrix.length - 1) {
                        grafo.addEdge(matrix[i][j], matrix[i+1][j], rand.nextInt(99) + 1);
                    }
                }
            }
        System.out.println("Grafo : " + grafo.toString());
        
        
         /*
        String[][] matriz = {
            {"A", "B", "C", "D", "E", "F", "G"},
            {"H", "I", "J", "K", "L", "M", "N"},
            {"Ñ", "O", "P", "Q", "R", "S", "T"},
            {"U", "V", "W", "X", "Y", "Z", "Z1"}
        };
        
           
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
        }*/
            
        System.out.println("RECORRIDOS : ");
        List<String> depthFirstSearch = DepthFirstSearch.dfs(grafo, "A", "Z1");
        System.out.println("Depth-First Search  : " + depthFirstSearch);
        
        List<String> breadthFirstSearch = BreadthFirstSearch.bfs(grafo, "A", "Z1");
        System.out.println("Breadth-First Search  : " + breadthFirstSearch);
        
        List<String> aStarEuclidiana = AStarEuclidean.aStarEuclidiana(grafo, "A", "Z1");
        System.out.println("A* Euclidiana : " + aStarEuclidiana);
        
        List<String> aStarManhattan =AStarManhattan.aStar(grafo, "A", "Z1");
        System.out.println("A* Manhattan : " + aStarManhattan);
       
    }
    
}