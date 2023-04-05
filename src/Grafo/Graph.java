/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Asus
 */
public class Graph {
    private List<Vertex> vertices;
    private List<Edge> aristas;
    
    public Graph() {
        vertices = new ArrayList<>();
        aristas = new ArrayList<>();
    }
    
    public void agregarVertice(Vertex vertice) {
        vertices.add(vertice);
    }
    
    public void agregarArista(Edge arista) {
        aristas.add(arista);
    }
    
    public List<Vertex> obtenerVertices() {
        return vertices;
    }
    
    public List<Edge> obtenerAristas() {
        return aristas;
    }   
    
}
