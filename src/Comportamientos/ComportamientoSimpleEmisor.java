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
public class ComportamientoSimpleEmisor extends SimpleBehaviour{
    
    Agent miAgente;
    public ComportamientoSimpleEmisor(Agent miAgente){
        this.miAgente = miAgente;
    }

    @Override
    public void action() {
        
        ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
        mensaje.addReceiver(new AID("EnemigoAgent", AID.ISLOCALNAME));
        mensaje.setContent("Today its raining");
        this.miAgente.send(mensaje);
        ACLMessage mensajeRespuesta = this.miAgente.blockingReceive();
        if(mensajeRespuesta != null){
            System.out.println("acabo de enviar " + mensajeRespuesta.getContent());            
        }
    }

    @Override
    public boolean done() {
        System.out.println("ya termine");
        return true; 
    }
    
    
    
}
