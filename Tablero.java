import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {

  private char tableroLetras[][];
  private int tableroVisual[][];
  private char semilla[] = {
    'a',
    'A',
    'b',
    'B',
    'c',
    'C',
    'd',
    'D',
    'e',
    'E',
    'f',
    'F',
    'g',
    'G',
    'h',
    'H',
    'i',
    'I',
    'j',
    'J',
    'k',
    'K',
    'l',
    'L',
    'm',
    'M',
    'n',
    'N',
    'o',
    'O',
    'p',
    'P',
    'q',
    'Q',
    'r',
    'R',
    's',
    'S',
    't',
    'T',
    'u',
    'U',
    'v',
    'V',
    'w',
    'W',
    'x',
    'X',
    'y',
    'Y',
    'z',
    'Z',
  };
  String reset = "\u001B[0m";
  String cyan="\033[36m";
  String yellow = "\033[33m";

  public Tablero(int tamanio) {
    this.tableroLetras = new char[tamanio][tamanio];
    this.tableroVisual = new int[tamanio][tamanio];
  }

  public void setTablero(
    char[][] tableroLetras,
    int tableroVisual[][],
    char semilla[]
  ) {
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
    int tamanioTablero = tableroLetras.length * tableroLetras[0].length;
    int numParejas = (tamanioTablero - 1) / 2;

    for (int i = 0; i < numParejas; i++) {
      listaSemilla.add(semilla[i]);
      listaSemilla.add(semilla[i]); // Agregar la misma letra otra vez para formar una pareja
    }

    // Agregar un asterisco
    listaSemilla.add('*');
    Collections.shuffle(listaSemilla);

    // Llenar el tablero con los elementos de la lista
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
    for (int i = 0; i < tableroVisual.length; i++) {
      for (int j = 0; j < tableroVisual[i].length; j++) {
        if (tableroVisual[i][j] == -1) {
          System.out.print(cyan + " X "+ reset);
        } else if (tableroVisual[i][j] == -2) {
          System.out.print(yellow + " O " + reset);
        } else {
          System.out.print(tableroVisual[i][j] + " ");
        }
      }
      System.out.println(" ");
    }
    System.out.println("------------");
    System.out.println(" ");
  }

  public void guardarTableroEnArchivo() {
    try (
      BufferedWriter writer = new BufferedWriter(new FileWriter("tablero.txt"))
    ) {
      for (int i = 0; i < tableroLetras.length; i++) {
        for (int j = 0; j < tableroLetras[i].length; j++) {
          writer.write(tableroLetras[i][j] + " ");
        }
        writer.newLine();
      }
    } catch (IOException e) {
      System.out.println(
        "Ocurrió un error al escribir en el archivo: " + e.getMessage()
      );
    }
  }

  public void cargarTableroDesdeArchivo() {
    try (
      BufferedReader reader = new BufferedReader(new FileReader("tablero.txt"))
    ) {
      String line;
      int fila = 0;

      while ((line = reader.readLine()) != null) {
        String[] letras = line.trim().split("\\s+");
        for (
          int columna = 0;
          columna < letras.length && columna < tableroLetras[fila].length;
          columna++
        ) {
          tableroLetras[fila][columna] = letras[columna].charAt(0);
        }
        fila++;
      }
    } catch (IOException e) {
      System.out.println(
        "Ocurrió un error al leer el archivo: " + e.getMessage()
      );
    }
  }
}
