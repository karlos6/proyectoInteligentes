/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comportamientos;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author carlo
 */
public class ComportamientoSimpleReceptor extends SimpleBehaviour{
    
    Agent miAgente;
    public ComportamientoSimpleReceptor(Agent miAgente){
        this.miAgente = miAgente;
    }

    @Override
    public void action() {
        
        ACLMessage mensajeRecibido = this.miAgente.blockingReceive();
        
        if(mensajeRecibido != null){
            System.out.println("acabo de recibiendo " + mensajeRecibido.getContent()); 
            ACLMessage respueta = mensajeRecibido.createReply();
            respueta.setContent("Hola desde el receptor");
            System.out.println("Enviando replica desde el receptor");
            this.miAgente.send(respueta);           
        }else{
            System.out.println("Receptor no me ha llegado mensaje");
        }
       
    }

    @Override
    public boolean done() {
        return true; 
    }
    
}
