/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import java.util.Scanner;

/**
 *
 * @author Eli
 */
public class ExamenMetodos {

    public final String[] ABECEDARIO = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public int[] empiezaExplosion;

    public ExamenMetodos() {
        empiezaExplosion = new int[2];
    }

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

    public int[][] bomberman(int M, int N, int D, int B) {
        int coordenadas[][];
        int universoVacio[][] = CrearMatriz(M, N, true);
        coordenadas = DefineCoordenadasBombas(B);
        // Ahora modificaremos el universo para que esta en blanco 0s para asignar 1s a las casillas con bombas
        int universoModificado[][] = ModificarUniverso(universoVacio, coordenadas);
        // Logica del juego. 
        // Una exploxion tendra un alcance de D para señalar que una bomba alla explotado se asignara el numero 2 a dicha casilla
        universoModificado = LogicaBomberman(universoModificado, D);
        return universoModificado;
    }

    public int[][] CrearMatriz(int filas, int columnas, boolean esVacio) {
        // Si es vacio crear con ceros
        int matriz[][] = new int[filas][columnas];
        int numero = 1;
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                if (esVacio) {
                    matriz[x][y] = 0;
                } else {
                    matriz[x][y] = numero++;
                }
            }
        }
        ImprimeMatriz(matriz);
        return matriz;
    }

    public int[][] DefineCoordenadasBombas(int B) {
        Scanner leer = new Scanner(System.in);
        // x e y
        int coordenadas[][] = new int[B][B];
        for (int x = 0; x < coordenadas.length; x++) {
            for (int y = 0; y < coordenadas[x].length; y++) {
                System.out.println("Introduzca el elemento [" + x + "," + y + "]");
                coordenadas[x][y] = leer.nextInt();
            }
        }
        ImprimeMatriz(coordenadas);
        return coordenadas;
    }

    public void ImprimeMatriz(int matriz[][]) {
        for (int x = 0; x < matriz.length; x++) {
            for (int y = 0; y < matriz[x].length; y++) {
                //System.out.println("[" + x + "," + y + "] = " + matriz[x][y]);
                System.out.print(matriz[x][y] + " : ");
            }
            System.out.println();
        }
        System.out.println("***************************************************************");
    }

    public int[][] ModificarUniverso(int[][] universoVacio, int[][] coordenadas) {
        // Este metodo asigna las bombas al universo
        int universoModificado[][] = universoVacio;
        int arrayTemp[] = new int[coordenadas.length * 2];
        int i = 0;
        for (int x = 0; x < coordenadas.length; x++) {
            int ex = 0;
            int ey = 0;
            for (int y = 0; y < coordenadas[x].length; y++) {
                arrayTemp[i] = coordenadas[x][y];
                i++;
            }
        }
        System.out.println("Universo ");

        for (int e = 0; e < arrayTemp.length; e += 2) {
            universoModificado[arrayTemp[e + 1]][arrayTemp[e]] = 1;
        }

        empiezaExplosion[0] = arrayTemp[0];
        empiezaExplosion[1] = arrayTemp[1];
        ImprimeMatriz(universoModificado);
        return universoModificado;
    }

    private int[][] LogicaBomberman(int[][] universoModificado, int D) {
        // Ahora modificaremos el universo para que esta en blanco 0s para asignar 1s a las casillas con bombas
        int universo[][] = universoModificado;
        for (int x = 0; x < universoModificado.length; x++) {
            for (int y = 0; y < universoModificado[x].length; y++) {
                //
                if (IniciaExplosion(x, y)) {
                    if (universo[x][y] == 1) {
                        // Explota 
                        universo[x][y] = 2;

                        if (universo[x][y] == 2) {
                            // Desencadena la explosion
                            // Busca Bombas a D de distancia y la explota tras la primera explosion el universo ha cambiado
                            //universo =
                            explotaVecinas(universo, D, x, y);

                        }
                    }
                }
            }
        }

        ImprimeMatriz(universo);
        return universo;
    }

    public boolean IniciaExplosion(int x, int y) {
        return x == empiezaExplosion[0] && y == empiezaExplosion[1];
    }

    private int[][] explotaVecinas(int[][] universo, int D, int ex, int ey) {
        System.out.println("Explota Vecinos");
        System.out.println(ex + ":" + ey);
        int limiteUniversoX = universo.length;
        int limiteUniversoY = universo[0].length;
        for (int x = 0; x < universo.length; x++) {
            for (int y = 0; y < universo[x].length; y++) {
                // Nos encontramaos en la celda que se detono
                if (x == ex && y == ey) {
                    System.out.println("Estamos en la raiz");
                    // Buscamos D a izq a der a abajo y a arriba
                    // Verticales
                    int xD = x + D;
                    int xd = x - D;
                    int exH = ex;
                    int exV = ex;
                    //Horizontales
                    int yd = y - D;
                    int yD = y + D;
                    int eyH = ey;
                    int eyV = ey;

                    if (estanEnuniverso(limiteUniversoX, xD)) {
                        while (exH < xD) {
                            if (universo[exH][ey] == 1) {
                                universo[exH][ey] = 2;
                            }
                            ImprimeMatriz(universo);
                            if (!HayBombas(universo)) {
                                break;
                            }
                            exH++;
                        }
                    }
                    if (estanEnuniverso(limiteUniversoX, xd)) {
                        while (exV > xd) {
                            if (universo[exV][ey] == 1) {
                                universo[exV][ey] = 2;
                            }
                            ImprimeMatriz(universo);
                            if (!HayBombas(universo)) {
                                break;
                            }
                            exV--;
                        }
                    }
                    if (estanEnuniverso(limiteUniversoY, yD)) {
                        while (eyH < yD) {
                            if (universo[ex][eyH] == 1) {
                                universo[ex][eyH] = 2;
                            }
                            ImprimeMatriz(universo);
                            if (!HayBombas(universo)) {
                                break;
                            }
                            eyH++;
                        }
                    }

                    if (estanEnuniverso(limiteUniversoY, yd)) {
                        while (eyV > yd) {
                            if (universo[ex][eyV] == 1) {
                                universo[ex][eyV] = 2;
                            }
                            ImprimeMatriz(universo);
                            if (!HayBombas(universo)) {
                                break;
                            }
                            eyV--;
                        }

                    }
                }

            }
            System.out.println();
        }

        return null;
    }

    private boolean estanEnuniverso(int limiteUniverso, int x) {
        return limiteUniverso > x && x > 0;
    }

    private boolean HayBombas(int[][] universo) {
        boolean hayBomba = false;
        for (int x = 0; x < universo.length; x++) {
            for (int y = 0; y < universo[x].length; y++) {
                if (universo[x][y] == 1) {
                    hayBomba = true;
                }
            }
        }
        return hayBomba;
    }

    public void RotarMatriz(int M, int N, int R) {
        int universo[][] = CrearMatriz(M, N, false);
        ImprimeMatriz(universo);
        for (int i = 1; i <= R; i++) {
            universo = Rotar(universo);
            ImprimeMatriz(universo);

        }
    }

    private int[][] Rotar(int[][] universo) {
        // Gira en direccion opuesta a las manecillas del reloj
        int[][] universoModificado = universo;
        int a = universo[0][0]; 
        int A = universo[0][universo.length];
   

        return universoModificado;
    }
}
