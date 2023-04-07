/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author carlo
 */
 public class Node {
        public String state;
        public Node parent;
        public double g;
        public double h;
        public double f;
        
        public Node(String state, Node parent, double g, double h) {
            this.state = state;
            this.parent = parent;
            this.g = g;
            this.h = h;
            this.f = g + h;
        }
 }

