/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Agentes ;

/**
 *
 * @author carlo
 */
import Comportamientos.ComportamientoSimpleReceptor;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;


public class EnemigoAgent extends Agent {
    
    public int posicionX = 0;
    public int posicionY = 0;
    
    public void setup() {
        addBehaviour(new TickerBehaviour(this, 1000) {
            protected void onTick() {
                
                
                //ACLMessage mensaje = myAgent.receive();
                //String contenido = mensaje.getContent();
                //System.out.println("Mensaje recibido: " + contenido);
                
                //posicionX++;
                
                //if(posicionX > 100 && posicionX < 120){
                //    posicionX++;
                    
                //}else{
                //    posicionX = 100;
                    
                //}
                //System.out.println("Posicion actual ");
                //System.out.println(posicionX);
            }
        });
      
        
          
        addBehaviour(new CyclicBehaviour(this) {
        
        @Override
        public void action() {

            ACLMessage mensajeRecibido = blockingReceive();

            if(mensajeRecibido != null){
                System.out.println("acabo de recibiendo " + mensajeRecibido.getContent()); 
                ACLMessage respueta = mensajeRecibido.createReply();
                respueta.setContent("Hola desde el receptor");
                System.out.println("Enviando replica desde el receptor");
                send(respueta);           
                //block();
            }else{
                System.out.println("Receptor no me ha llegado mensaje");
                //block();
            }

        }
            });


            //addBehaviour(new ComportamientoSimpleReceptor(this));
        }
    
    
    
    public void cambiarPosicionX(int pos){
        this.posicionX = pos;
        
    }
    
    
    
    
}
