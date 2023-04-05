/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectointeligentes;
import jade.Boot;
import jade.wrapper.ControllerException;
import jade.core.Runtime;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.wrapper.AgentController;
import java.util.ArrayList;
import java.util.List;
import proyectointeligentes.Agentes.Agente1;


/**
 *
 * @author carlo
 */
public class ProyectoInteligentes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ControllerException {
        
      String[] opciones = { "-gui", "-agents", "Agente1:proyectoInteligentes.Agente1" };
      Boot.main(opciones);

      Agente1 agente = new Agente1();
      agente.setup("resivi los datos que me dijeron mi so");
      agente.takeDown();
      
     
      
        
    }
    
}
