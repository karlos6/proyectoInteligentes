/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import Algorithms.AStarEuclidean;
import Algorithms.AStarManhattan;
import Algorithms.BeamSearch;
import Algorithms.BreadthFirstSearch;
import Algorithms.DepthFirstSearch;
import Algorithms.HillClimbing;
import Algorithms.UniformCostSearch;
import Models.Edge;
import Models.Graph;
import ReadFile.TextToMatrix;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 *
 * @author Asus
 */
public class Main {
    public static void main(String[] args) {
        /*Graph<String> grafo = new Graph<String>();
        Random rand = new Random();
        
        
        String[][] matrix = TextToMatrix.readMatrixFromFile("C:\\Users\\Asus\\OneDrive - CONSENSUS SAS\\Escritorio\\Matriz.txt");
        
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.println("Vertex : "+matrix[i][j]+idVertex);
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
        
        System.out.println("RECORRIDOS : ");
        List<String> depthFirstSearch = DepthFirstSearch.dfs(grafo, "A", "Z1");
        System.out.println("Depth-First Search  : " + depthFirstSearch);
        
        List<String> breadthFirstSearch = BreadthFirstSearch.bfs(grafo, "A", "Z1");
        System.out.println("Breadth-First Search  : " + breadthFirstSearch);
        
        List<String> aStarEuclidiana = AStarEuclidean.aStarEuclidiana(grafo, "A", "Z1");
        System.out.println("A* Euclidiana : " + aStarEuclidiana);
        
        List<String> aStarManhattan =AStarManhattan.aStar(grafo, "A", "Z1");
        System.out.println("A* Manhattan : " + aStarManhattan);   
        
        List<String> costoMinimo = UniformCostSearch.costoUniforme(grafo.adjacencyList, "A","H");
        System.out.println("Recorrido Costo Uniforme: "+costoMinimo);
        
        List<String> recHillEucli = HillClimbing.hillClimbing(grafo.adjacencyList, "A", "Z1", grafo,true);        
        System.out.println("Recorrido hill Climbing Euclidiana: " + recHillEucli);
        List<String> recHillman = HillClimbing.hillClimbing(grafo.adjacencyList, "A", "Z1", grafo,false);        
        System.out.println("Recorrido hill Climbing Manhattan: " + recHillman);
        
        List<String> recorridoBean = BeamSearch.beamSearch(grafo.adjacencyList,"A","Z",grafo,true);        
        System.out.println("Recorrido beam Search Euclidiana" + recorridoBean);
        List<String> recorridoBeans = BeamSearch.beamSearch(grafo.adjacencyList,"A","Z",grafo,false);        
        System.out.println("Recorrido beam Search Manhattan" + recorridoBeans);   
       
        
        String[][] matrix = {
            {"C1", "C2", "C3", "C4", "C5", "R6", "C7"},
            {"C8", "M9", "C10", "M11", "C12", "M13", "C14"},
            {"R15", "C16", "C17", "C18", "C18", "C20", "C21"},
            {"C22", "M23", "C24", "M25", "C26", "M27", "C28"}
        };*/
        
        String[][] matrix = TextToMatrix.readMatrixFromFile("C:\\Users\\Asus\\OneDrive - CONSENSUS SAS\\Escritorio\\Matriz.txt");
        int rows = matrix.length;
        int cols = matrix[0].length;

        Graph<String> graph = new Graph<>();
        Random rand = new Random();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {   
                
                if(!obstaculos(matrix[i][j])){
                    
                    //String vertex = matrix[i][j] + (i * cols + j + 1);
                    String vertex = matrix[i][j];
                    graph.addVertex(vertex, i, j);
                    if (i > 0 && !obstaculos(matrix[i - 1][j])) {      
                        //String upVertex = matrix[i - 1][j] + ((i - 1) * cols + j + 1);  
                        String upVertex = matrix[i - 1][j];  
                        graph.addEdge(vertex, upVertex, 1);                    
                    }
                    if (j > 0 && !obstaculos(matrix[i][j - 1])) {
                        //String leftVertex =  matrix[i][j - 1]+ (i * cols + j);
                        String leftVertex =  matrix[i][j - 1];
                        graph.addEdge(vertex, leftVertex, 1);
                    }
                }                
            }
        }

        System.out.println("Grafo : \n" + graph.toString());    
        System.out.println("Vertex : "+graph.getVertices());
        
        System.out.println("  "); 
        
        System.out.println("RECORRIDOS : ");
        
        List<String> depthFirstSearchSustentación = DepthFirstSearch.dfsSustentación(graph, "A", "U");
        System.out.println("Depth-First Search SUSTENTACIÓN : " + depthFirstSearchSustentación);
        System.out.println("  "); 
        
        List<String> depthFirstSearch = DepthFirstSearch.dfs(graph, "A", "U");
        System.out.println("Depth-First Search  : " + depthFirstSearch);
        
        
        List<String> breadthFirstSearch = BreadthFirstSearch.bfs(graph, "A", "U");
        System.out.println("Breadth-First Search  : " + breadthFirstSearch);
        System.out.println("Nivel Breadth-First Search  : " + BreadthFirstSearch.bfsNivel(graph, "A", "U"));
        
        List<String> aStarEuclidiana = AStarEuclidean.aStarEuclidiana(graph, "A", "U");
        System.out.println("A* Euclidiana : " + aStarEuclidiana);
        
        List<String> aStarManhattan =AStarManhattan.aStar(graph, "A", "U");
        System.out.println("A* Manhattan : " + aStarManhattan);   
        
        List<String> costoMinimo = UniformCostSearch.costoUniforme(graph.adjacencyList, "A","U");
        System.out.println("Recorrido Costo Uniforme: "+costoMinimo);
        
        List<String> recHillEucli = HillClimbing.hillClimbing(graph.adjacencyList, "A", "U", graph,true);        
        System.out.println("Recorrido hill Climbing Euclidiana: " + recHillEucli);
        List<String> recHillman = HillClimbing.hillClimbing(graph.adjacencyList, "A", "U", graph,false);        
        System.out.println("Recorrido hill Climbing Manhattan: " + recHillman);
        
        List<String> recorridoBean = BeamSearch.beamSearch(graph.adjacencyList,"A","U",graph,true);        
        System.out.println("Recorrido beam Search Euclidiana" + recorridoBean);
        List<String> recorridoBeans = BeamSearch.beamSearch(graph.adjacencyList,"A","U",graph,false);        
        System.out.println("Recorrido beam Search Manhattan" + recorridoBeans); 
    }    
    
    public static boolean obstaculos(String letra){
        String[] obstaculos = {"D", "I", "M", "Q","R","T","W","AA", "AE"};
        for (int i = 0; i < obstaculos.length; i++) {
            if(letra.equals(obstaculos[i])){
                return true;
            }
        }
        return false;
    }
}