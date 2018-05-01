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
public class Pregunta1 {

    public final String[] ABECEDARIO = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    
    
    // Determina si todos los elementos en un arreglo los contiene una cadena
    public boolean contieneElementos(String[] abecedario, String cadena) {
        int c = 0;
        for (int i = 0; i < abecedario.length; i++) {
            if (cadena.contains(abecedario[i])) {
                c++;
            }
        }
        // Devuelve true si en la cadena se encuetran todos los elementos del arreglo
        return c == abecedario.length;
    }

    public static void main(String[] args) {
        Pregunta1 p = new Pregunta1();
        System.out.println(p.contieneElementos(p.ABECEDARIO, "Soy Una Cadena De Prueba"));
    }
}
