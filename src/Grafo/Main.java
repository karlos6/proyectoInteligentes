/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author Asus
 */
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[][] matriz1 = {
	            {"C", "C", "C", "C", "C", "R", "C"},
	            {"C", "M", "C", "M", "C", "M", "C"},
	            {"R", "C", "C", "C", "C", "C", "C"},
	            {"C", "M", "C", "M", "C", "M", "C"}
	        };
                
                String[][] matriz = {
	            {"A", "B", "C", "D", "E", "F", "G"},
	            {"H", "I", "J", "K", "L", "M", "N"},
	            {"Ñ", "O", "P", "Q", "R", "S", "T"},
	            {"U", "V", "W", "X", "Y", "Z", "Z1"}
	        };
		
		
		Graph grafo = new Graph();
		
		// Agregar vértices
		for (int i = 0; i < matriz.length; i++) {
		    for (int j = 0; j < matriz[i].length; j++) {
		    	Vertex vertex = new Vertex(matriz[i][j]);
	            grafo.agregarVertice(vertex);
		    }
		}				
        
                // Conectar vértices
                for (int i = 0; i < matriz.length; i++) {
                    for (int j = 0; j < matriz[i].length; j++) {
                        if (j < matriz[i].length - 1) {
                            // Conectar con el vecino horizontal derecho
                                Vertex origen = grafo.obtenerVertices().get(i * matriz[i].length + j);
                                Vertex destino = grafo.obtenerVertices().get(i * matriz[i].length + j + 1);
                                Edge arista = new Edge(origen, destino);
                            grafo.agregarArista(arista);
                        }
                        if (i < matriz.length - 1) {
                            // Conectar con el vecino vertical inferior
                                Vertex origen = grafo.obtenerVertices().get(i * matriz[i].length + j);
                                Vertex destino = grafo.obtenerVertices().get((i + 1) * matriz[i].length + j);
                                Edge arista = new Edge(origen, destino);
                            grafo.agregarArista(arista);
                        }
                    }
                }
                
        // Imprimir el grafo
        System.out.println(grafo.obtenerVertices());        
        System.out.println(grafo.obtenerAristas());
        
	}
}