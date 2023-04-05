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
public class Vertex {
    private String Name;
    private Boolean Status;
    
    public Vertex(String Name) {
        this.Name = Name;
        this.Status = false;
    }
    
    public String obtenerNombre() {
        return Name;
    }
    
    public String toString() {
        return Name;
    }

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean Status) {
        this.Status = Status;
    }
}
