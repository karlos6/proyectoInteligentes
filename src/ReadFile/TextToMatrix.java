/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ReadFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Fabian Palacios & Carlos Muñoz
 */
public class TextToMatrix {
    
   public static String[][] readMatrixFromFile(String fileName) {
    String[][] matrix = null;
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
        String line;
        int numRows = 0;
        int numCols = 0;

        // contar el número de filas y columnas
        while ((line = br.readLine()) != null) {
            numRows++;
            numCols = line.split(" ").length; // dividir las filas por espacios en blanco
        }

        // inicializar la matriz con el número de filas y columnas contados
        matrix = new String[numRows][numCols];

        // llenar la matriz con los valores del archivo
        br.close();
        BufferedReader br2 = new BufferedReader(new FileReader(fileName));
        int i = 0;
        while ((line = br2.readLine()) != null) {
            line = line.replace(",", ""); // quitar la coma
            String[] values = line.split(" "); // dividir las filas por espacios en blanco
            for (int j = 0; j < numCols; j++) {
                matrix[i][j] = values[j];
            }
            i++;
        }
        br2.close();
    } catch (IOException e) {
        e.printStackTrace();
    }
    return matrix;
}
   
   public static String[][] matrizCaminos(String[][] matrizTotal){
        int rows = matrizTotal.length;
        int cols = matrizTotal[0].length;
        int count = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) 
            {
                matrizTotal[i][j] = matrizTotal[i][j]+count;
                count++;         
            }
        }
        return matrizTotal;
     }
     
     
}
