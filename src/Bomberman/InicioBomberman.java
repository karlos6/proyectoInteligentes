/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bomberman;

import Agentes.EnemigoAgent;
import Algorithms.AStarEuclidean;
import Algorithms.AStarManhattan;
import Algorithms.BeamSearch;
import Algorithms.BreadthFirstSearch;
import Algorithms.DepthFirstSearch;
import Algorithms.HillClimbing;
import Algorithms.UniformCostSearch;
import Models.Graph;
import ReadFile.TextToMatrix;
import jade.Boot;
import jade.tools.gui.ACLComboBox;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

/**
 *
 * @author carlo
 */
public class InicioBomberman extends JFrame{
    //Panel
    JFrame inicio = new JFrame("Iniciando Juego");
    
    //Heuristicas
    JRadioButton euclidiana = new JRadioButton("Euclidiana",false);
    JRadioButton manhattan = new JRadioButton("Manhattan",false);
    
    //Busquedas
    JRadioButton anchura = new JRadioButton("Anchura",false);
    JRadioButton profundidad = new JRadioButton("Profundidad",false);
    JRadioButton costoUniforme = new JRadioButton("Costo Uniforme",false);
    JRadioButton hillClimbing = new JRadioButton("Hill Climbing",false);
    JRadioButton beam = new JRadioButton("Beam",false);
    JRadioButton aEstrella = new JRadioButton("A Estrella",false);
    
    //Boton para iniciar el juego
    JButton buttonComenzar = new JButton();
    JButton buttonCargarArchivo = new JButton();
    
    List<String> recorridoObjetivo;
    boolean esEuclidiana = false;
    String[][] matrix;
    String[][] matrizRecorridos;
    
    Graph<String> graph;
    
    //Listener
    ActionListener oyenteBoton = new ActionListener() {
        
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
         int x = 0;
         int y = 0;
        
         for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j].equals("P")) {
                    x=j;
                    y=i;
                }
            }
         }
 
    
                       
            if(euclidiana.isSelected()){
                esEuclidiana = true;
            } 
            if(manhattan.isSelected()){
                esEuclidiana = false;
            }
                       
            matrizRecorridos = TextToMatrix.matrizCaminos(matrizRecorridos);
                       
            if(anchura.isSelected()) {
                recorridoObjetivo = DepthFirstSearch.dfs(graph, matrizRecorridos[1][1], matrizRecorridos[y][x]); 
            }
            if(profundidad.isSelected()){
                recorridoObjetivo = BreadthFirstSearch.bfs(graph, matrizRecorridos[1][1], matrizRecorridos[y][x]);
            }
            if(costoUniforme.isSelected()) {
                recorridoObjetivo = UniformCostSearch.costoUniforme(graph.adjacencyList, matrizRecorridos[1][1], matrizRecorridos[y][x]);
            }
            if(hillClimbing.isSelected()){
                if(esEuclidiana){
                    recorridoObjetivo = HillClimbing.hillClimbing(graph.adjacencyList, matrizRecorridos[1][1], matrizRecorridos[y][x], graph,true);   
                }else{
                    recorridoObjetivo = HillClimbing.hillClimbing(graph.adjacencyList, matrizRecorridos[1][1], matrizRecorridos[y][x], graph,false);                     
                }
            }
            if(beam.isSelected()){
                if(esEuclidiana){
                     recorridoObjetivo = BeamSearch.beamSearch(graph.adjacencyList, matrizRecorridos[1][1], matrizRecorridos[y][x],graph,true); 
                }else{
                    recorridoObjetivo =  BeamSearch.beamSearch(graph.adjacencyList, matrizRecorridos[1][1], matrizRecorridos[y][x],graph,false);                        
                }                 
            }
            if(aEstrella.isSelected()){
                if(esEuclidiana){
                     recorridoObjetivo =  AStarEuclidean.aStarEuclidiana(graph, matrizRecorridos[1][1], matrizRecorridos[y][x]);
                }else{
                    recorridoObjetivo =  AStarManhattan.aStar(graph, matrizRecorridos[1][1], matrizRecorridos[y][x]);                 
                }  
            }
            
            if(recorridoObjetivo.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Seleccione una heuristica y una forma de busqueda");
            }else{
                inicio.dispose();
                
                
                //matrix[matrix.length - 2][matrix[0].length - 2] = "P";
                BomberMan bomberMan = new BomberMan(matrix,matrizRecorridos, recorridoObjetivo,matrix.length,matrix[0].length);
                //bomberMan.setScene(scene);
                bomberMan.inicioJuego();
                
                //System.out.println("Grafo : \n" + graph.toString());    
                //System.out.println("Vertex : "+graph.getVertices());
                System.out.println("recorrido: "+recorridoObjetivo);
                //System.out.println("Salida: "+matrix[1][1]);
                //System.out.println("Meta: "+matrix[matrix.length - 2][matrix[0].length - 2]);
                
                //enemigo:Agentes.EnemigoAgent;bomberman:Bomberman.BombermanAgente;bombermanInterfaz:Bomberman.InterfazBomberman
                //String[] opciones2 = { "-gui", "-agents", "bomberman:Bomberman.BombermanAgente"};
                //Boot.main(opciones2);
                //EnemigoAgent enemigoAgent = new EnemigoAgent();
                //enemigoAgent.setup();
                //BombermanAgente bombermanInter = new BombermanAgente();
                //bombermanInter.setup();
                //InterfazBomberman bombermanIn = new InterfazBomberman();
                //bombermanIn.setup();
                //String[] opciones = { "-gui", "-agents", "bomberman:Bomberman.BombermanAgente" };
                //Boot.main(opciones);
                //System.out.println("hola");
                
                
            }
            
            
        }
    };
    
    
     ActionListener oyenteBotonCargar = new ActionListener() {
        
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            
           JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    String filePath = selectedFile.getAbsolutePath();
                    System.out.println("Ruta del archivo: " + filePath);
                    matrix = TextToMatrix.readMatrixFromFile(filePath);
                    matrizRecorridos = TextToMatrix.readMatrixFromFile(filePath);
                    int rows = matrix.length;
                    int cols = matrix[0].length;
                    System.out.println("Matris: " + matrix);
                    graph = new Graph<>();
                    Random rand = new Random();

                    for (int i = 0; i < rows; i++) {
                        for (int j = 0; j < cols; j++) {   
                            if(!matrix[i][j].equals("M")){
                                String vertex = matrix[i][j] + (i * cols + j + 1);
                                graph.addVertex(vertex, i, j);
                                if (i > 0 && !matrix[i - 1][j].equals("M")) {      
                                    String upVertex = matrix[i - 1][j] + ((i - 1) * cols + j + 1);                            
                                    graph.addEdge(vertex, upVertex, 1);                    
                                }
                                if (j > 0 && !matrix[i][j - 1].equals("M")) {
                                    String leftVertex =  matrix[i][j - 1]+ (i * cols + j);
                                    graph.addEdge(vertex, leftVertex, 1);
                                }
                            }                
                        }
                    }

                    //System.out.println("Grafo : \n" + graph.toString());    
                    //System.out.println("Vertex : "+graph.getVertices());

                            }
            
            
            
        }
    };
     
    public static boolean obstaculos(String letra){
        String[] obstaculos = {"D", "I", "M", "Q","R","T","W","AA", "AE"};
        for (int i = 0; i < obstaculos.length; i++) {
            if(letra.equals(obstaculos[i])){
                return true;
            }
        }
        return false;
    }
    

    

    public void inicializacionJFlame(){
        inicio.setLayout(null);
        inicio.setResizable(false);
        inicio.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        inicio.pack();
        inicio.setSize(500,500);
        inicio.setLocationRelativeTo(null);
        inicio.setVisible(true);
        inicializacionGrupoBotonesBusqueda();
    }
    
    public void inicializacionLabels(){
        JLabel labelHeuristica = new JLabel();
        JLabel labelBusqueda = new JLabel();
        
        labelHeuristica.setText("Seleccione la heuristica:");
        labelHeuristica.setBounds(180, 10, 150, 30);       
        inicio.add(labelHeuristica);
        
        labelBusqueda.setText("Seleccione la busqueda:");
        labelBusqueda.setBounds(180, 80, 150, 30);  
        inicio.add(labelBusqueda);
    }
    
    public void inicializacionBonones(){
        buttonComenzar.setText("Iniciar");
        buttonComenzar.setBounds(250, 250, 100, 20);
        buttonComenzar.addActionListener(oyenteBoton);
        inicio.add(buttonComenzar);
        
        buttonCargarArchivo.setText("Cargar Archivo");
        buttonCargarArchivo.setBounds(120, 250, 120, 20);
        buttonCargarArchivo.addActionListener(oyenteBotonCargar);
        inicio.add(buttonCargarArchivo);
            
    }
    
     private void loadFile(File file) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    public void inicializacionGrupoBotonesHeuristica(){
        
        manhattan.setBounds(140, 40, 90, 20);
        inicio.add(manhattan);
        
        
        euclidiana.setBounds(250, 40, 100, 20);
        inicio.add(euclidiana);
        
        ButtonGroup grupoBotonesHeuristica = new ButtonGroup();
        grupoBotonesHeuristica.add(manhattan);
        grupoBotonesHeuristica.add(euclidiana);       
    }
    
    public void inicializacionGrupoBotonesBusqueda(){
        
        anchura.setBounds(10, 130, 90, 20);
        inicio.add(anchura);
        
        profundidad.setBounds(100, 130, 100, 20);
        inicio.add(profundidad);
        
        costoUniforme.setBounds(210, 130, 120, 15);
        inicio.add(costoUniforme);
        
        hillClimbing.setBounds(340, 130, 100, 15);
        inicio.add(hillClimbing);
        
        beam.setBounds(130, 190, 60, 20);
        inicio.add(beam);        
        
        aEstrella.setBounds(230, 190, 100, 20);
        inicio.add(aEstrella);
        
        ButtonGroup grupoBotonesBusqueda = new ButtonGroup();        
        grupoBotonesBusqueda.add(anchura);
        grupoBotonesBusqueda.add(profundidad); 
        grupoBotonesBusqueda.add(costoUniforme); 
        grupoBotonesBusqueda.add(hillClimbing); 
        grupoBotonesBusqueda.add(beam); 
        grupoBotonesBusqueda.add(aEstrella);      
        
    }
    
    public InicioBomberman(){        
        inicializacionJFlame();
        inicializacionLabels();
        inicializacionGrupoBotonesHeuristica();
        inicializacionGrupoBotonesBusqueda();
        inicializacionBonones();        
    }
    
}
