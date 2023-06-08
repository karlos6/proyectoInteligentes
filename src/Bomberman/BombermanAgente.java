/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import Comportamientos.ComportamientoSimpleEmisor;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 *
 * @author carlo
 */
public class BombermanAgente extends Agent{
    BomberMan bomberMan;
    //BombermanAgente a = new BombermanAgente();
    int conteo = 0;
    @Override
    protected void setup() {
        
         //bomberMan = new BomberMan();
         bomberMan = new BomberMan();
         bomberMan.inicioJuego();        
        //bomberMan.start();
        //bomberMan.update();
         //bomberMan.draw();
        
         
         addBehaviour(new TickerBehaviour(this, 4000) {
            protected void onTick() {
                
                //bomberMan.update();
                //bomberMan.draw();
                //ACLMessage mensaje = myAgent.receive();
                //String contenido = mensaje.getContent();
                //System.out.println("Mensaje recibido: " + contenido);
                
                //System.out.println("Posicion enemigo hilo");
                //System.out.println(bomberMan.getEnemigoX());
                System.out.println("Posicion enemigo dentro de el agente");
                //System.out.println(bomberMan.getEnemigoX());
                //System.out.println(bomberMan.getEnemigoY());
                //bomberMan.setEnemigoX(10000);
                //bomberMan.setEnemigoY(10000);
                if(conteo <= 0 && conteo < 10){
                    conteo++;
                    
                }else{
                    conteo = 100;
                    
                }
                //System.out.println("Posicion actual ");
                //System.out.println(conteo);
            }
        });
         
         //addBehaviour(new ComportamientoSimpleEmisor(this));
         
         addBehaviour(new CyclicBehaviour(this) {
             
            public void action() {
               
                
               
            }
            
        });
        
         /*
          addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                
               //bomberMan.update();
               //bomberMan.draw();
                
                ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
                mensaje.addReceiver(new AID("enemigo", AID.ISLOCALNAME));
                mensaje.setContent("Hola, soy el agente emisor");
                // Reemplaza "nombreAgenteReceptor" con el nombre correcto del agente receptor
                send(mensaje);
                System.out.println(mensaje.getContent());
                ACLMessage mensajeRespuesta = blockingReceive();
                System.out.println(mensajeRespuesta.getContent());
                //block();
               //System.out.println("Posicion enemigo hilo");
               // System.out.println(bomberMan.getEnemigoX());
            }
        });
        */
        
    /*
          
        addBehaviour(new CyclicBehaviour(this) {
            @Override
    public void action() {
        
            

            ACLMessage mensaje = new ACLMessage(ACLMessage.INFORM);
            mensaje.addReceiver(new AID("EnemigoAgent", AID.ISLOCALNAME));
            mensaje.setContent("Today its raining");
            send(mensaje);
            ACLMessage mensajeRespuesta = blockingReceive();
            if(mensajeRespuesta != null){
                System.out.println("acabo de recibir " + mensajeRespuesta.getContent());            
            }
        }

            
            
            private boolean condicionCumplida() {
                // Lógica para verificar si se cumple la condición
                // Retorna true si la condición se cumple, false de lo contrario
                // Por ejemplo:
                if(conteo == 10){
                    return true;
                }
                
                return false;
            }
        });     
 */   
} 
    
}
