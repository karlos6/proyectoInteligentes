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
public class Edge<T> {
        T destination;
        int weight;

        public Edge(T destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }    
}
