/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes.Agentes;

import jade.core.Agent;

/**
 *
 * @author carlo
 */
public class Agente1 extends Agent{
    
    public void setup(String s) {
      System.out.println("Hola, soy un agente JADE"+s);
   }

   public void takeDown() {
      System.out.println("Adiós, agente JADE saliendo");
   }
    
}
