/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Eli
 */
public class Main {

    public static void main(String[] args) {
        ExamenMetodos m = new ExamenMetodos();
        // Metodo para determinar si una cadena conitiene todo el ABECEDARIO
        boolean esta = m.contieneElementos(m.ABECEDARIO, "Hola Soy Goku ".toUpperCase());
        System.out.println(esta);

        m.bomberman(5, 5, 2, 2);
        m.RotarMatriz(4, 4, 1);
    }
}
