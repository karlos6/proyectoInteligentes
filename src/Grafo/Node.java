/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author carlo
 */
 class Node {
        String state;
        Node parent;
        double g;
        double h;
        double f;
        
        Node(String state, Node parent, double g, double h) {
            this.state = state;
            this.parent = parent;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }
 }

