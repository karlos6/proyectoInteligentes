/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

public class Edge {
	private Vertex origen;
    private Vertex destino;
    
    public Edge(Vertex origen, Vertex destino) {
        this.origen = origen;
        this.destino = destino;
    }
    
    public Vertex obtenerOrigen() {
        return origen;
    }
    
    public Vertex obtenerDestino() {
        return destino;
    }
    
    public String toString() {
        return origen + " -> " + destino;
    }
}
