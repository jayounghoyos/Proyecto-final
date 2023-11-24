import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Tablero {
    private char tableroLetras[][];
    private int tableroVisual[][];
    private char semilla[] = {
            'a', 'a', 'b', 'b', 'c', 'c', 'd', 'd', 'e', 'e', 'f', 'f', 'g', 'g', 'h', 'h', 'i', 'i',
            'j', 'j', 'k', 'k', 'l', 'l', 'm', 'm', 'n', 'n', 'o', 'o', 'p', 'p', 'q', 'q', 'r', 'r',
            's', 's', 't', 't', 'u', 'u', 'v', 'v', 'w', 'w', 'x', 'x', 'y', 'y', 'z', 'z',
            'A', 'A', 'B', 'B', 'C', 'C', 'D', 'D', 'E', 'E', 'F', 'F', 'G', 'G', 'H', 'H', 'I', 'I',
            'J', 'J', 'K', 'K', 'L', 'L', 'M', 'M', 'N', 'N', 'O', 'O', 'P', 'P', 'Q', 'Q', 'R', 'R',
            'S', 'S', 'T', 'T', 'U', 'U', 'V', 'V', 'W', 'W', 'X', 'X', 'Y', 'Y', 'Z', 'Z'
    };

    public Tablero(int tamanio) {
        this.tableroLetras = new char[tamanio][tamanio];
        this.tableroVisual = new int[tamanio][tamanio];
    }

    public void setTablero(char[][] tableroLetras, int tableroVisual[][], char semilla[]) {
        this.tableroLetras = tableroLetras;
        this.tableroVisual = tableroVisual;
        this.semilla = semilla;
    }

    public char[][] getTableroLetras() {
        return tableroLetras;
    }

    public int[][] getTableroVisual() {
        return tableroVisual;
    }

    public void llenarTableroLetras() {
        List<Character> listaSemilla = new ArrayList<>();
        for (char c : semilla) {
            listaSemilla.add(c);
        }
        Collections.shuffle(listaSemilla);
        int contador = 0;

        for (int i = 0; i < tableroLetras.length; i++) {
            for (int j = 0; j < tableroLetras[i].length; j++) {
                tableroLetras[i][j] = listaSemilla.get(contador);
                contador++;
            }
        }
    }

    public void llenarTableroVisual() {
        int contador = 1;

        for (int i = 0; i < tableroVisual.length; i++) {
            for (int j = 0; j < tableroVisual[i].length; j++) {
                tableroVisual[i][j] = contador;
                contador++;
            }
        }
    }

    public void imprimirLetras() {
        System.out.println(" ");
        System.out.println("------------");
        for (int columna = 0; columna < tableroLetras.length; columna++) {
            for (int fila = 0; fila < tableroLetras[0].length; fila++) {
                System.out.print(tableroLetras[columna][fila] + "  ");
            }
            System.out.println(" ");
        }

        System.out.println("------------");
        System.out.println(" ");
    }

    public void imprimirNumeros() {
        System.out.println(" ");
        System.out.println("------------");
        for (int columna = 0; columna < tableroVisual.length; columna++) {
            for (int fila = 0; fila < tableroVisual[0].length; fila++) {
                System.out.print(tableroVisual[columna][fila] + "  ");
            }
            System.out.println(" ");
        }

        System.out.println("------------");
        System.out.println(" ");
    }

  /*public void llenarTableroLetrasPorTeclado() {
        System.out.println("Digita las letras con las que lo vas a llenar");
        Scanner input = new Scanner(System.in);

        for (int columna = 0; columna < tableroLetras.length; columna++) {
            for (int fila = 0; fila < tableroLetras.length; fila++) {
                tableroLetras[fila][columna] = input.next().charAt(0);
            }
        }
    }*/
}
