import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Juego {

  public static void main(String[] args) {
    Tablero tablero = null;
    // int turno = 0;
    boolean salir = false;
    Boolean tableroCreado = false;
    boolean usandoTablero = false;

    String reset = "\u001B[0m";
    String red = "\033[31m";
    String green = "\033[32m";
    String yellow = "\033[33m";
    /*
     * String blue="\033[34m";
     * String purple="\033[35m";
     * String cyan="\033[36m";
     * String white="\033[37m";
     * String black="\033[30m";
     */
    Scanner kb = new Scanner(System.in);

    while (!salir) {
      System.out.println("Bienvenido al juego Concéntrate");
      System.out.println("1. Jugar entre dos jugadores");
      System.out.println("2. Jugar contra la máquina");
      System.out.println("3. Cargar tablero desde un archivo");
      System.out.println("4. Crear tablero");
      System.out.println("5. Salir");
      System.out.print("Ingrese la opción que desea ejecutar: ");

      int opcion = kb.nextInt();
      switch (opcion) {
        case 1:
          if (tablero == null || !tableroCreado || !usandoTablero) {
            System.out.println(
              red + "Primero debes crear o cargar un tablero." + reset
            );
            break;
          }

          System.out.print("Ingrese el nombre del primer jugador: ");
          String nombreJugador1 = kb.next();
          Jugador jugador1 = new Jugador(nombreJugador1, 0);

          System.out.print("Ingrese el nombre del segundo jugador: ");
          String nombreJugador2 = kb.next();
          Jugador jugador2 = new Jugador(nombreJugador2, 0);

          boolean juegoTerminado = false;
          while (!juegoTerminado) {
            for (Jugador jugadorActual : new Jugador[] { jugador1, jugador2 }) {
              System.out.println("Turno de " + jugadorActual.getNombre());
              tablero.imprimirNumeros();

              System.out.print("Elige el primer número: ");
              int eleccion1 = kb.nextInt() - 1;
              System.out.print("Elige el segundo número: ");
              int eleccion2 = kb.nextInt() - 1;

              int fila1 = eleccion1 / tablero.getTableroVisual().length;
              int columna1 = eleccion1 % tablero.getTableroVisual().length;
              int fila2 = eleccion2 / tablero.getTableroVisual().length;
              int columna2 = eleccion2 % tablero.getTableroVisual().length;

              if (
                tablero.getTableroLetras()[fila1][columna1] ==
                tablero.getTableroLetras()[fila2][columna2]
              ) {
                System.out.println("¡Encontraste una pareja!");
                jugadorActual.setPuntos(jugadorActual.getPuntos() + 1);

                int marca = jugadorActual == jugador1 ? -1 : -2; // -1 para jugador1, -2 para jugador2
                tablero.getTableroVisual()[fila1][columna1] = marca;
                tablero.getTableroVisual()[fila2][columna2] = marca;
              } else {
                System.out.println("No son una pareja. Siguiente turno.");
              }

              juegoTerminado = true;
              for (int[] fila : tablero.getTableroVisual()) {
                for (int num : fila) {
                  if (num != -1) {
                    juegoTerminado = false;
                    break;
                  }
                }
                if (!juegoTerminado) break;
              }

              if (juegoTerminado) {
                break;
              }
            }
          }

          System.out.println("El juego ha terminado!");
          System.out.println(
            jugador1.getNombre() + " tiene " + jugador1.getPuntos() + " puntos."
          );
          System.out.println(
            jugador2.getNombre() + " tiene " + jugador2.getPuntos() + " puntos."
          );
          // Determinar y mostrar quién es el ganador
          if (jugador1.getPuntos() > jugador2.getPuntos()) {
            System.out.println(jugador1.getNombre() + " gana!");
          } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
            System.out.println(jugador2.getNombre() + " gana!");
          } else {
            System.out.println("Es un empate!");
          }

          break;
        case 2:
          if (tableroCreado == false || usandoTablero == false) {
            System.out.println(
              red +
              "Primero tienes que crear o cargar un archivo para jugar" +
              reset
            );
            break;
          }

          break;
        case 3:
          try (
            BufferedReader reader = new BufferedReader(
              new FileReader("tablero.txt")
            )
          ) {
            int lineCount = 0;
            String line;

            // Contar el número de líneas para determinar el tamaño del tablero
            while ((line = reader.readLine()) != null) {
              if (!line.trim().isEmpty()) {
                lineCount++;
              }
            }

            // Verificar si el archivo tiene contenido
            if (lineCount > 0) {
              // Crear un nuevo tablero con el tamaño adecuado
              tablero = new Tablero(lineCount);

              // Volver a abrir el archivo para cargar el tablero
              try (
                BufferedReader secondReader = new BufferedReader(
                  new FileReader("tablero.txt")
                )
              ) {
                int fila = 0;
                while ((line = secondReader.readLine()) != null) {
                  if (!line.trim().isEmpty()) {
                    String[] letras = line.trim().split("\\s+");
                    for (
                      int columna = 0;
                      columna < letras.length &&
                      columna < tablero.getTableroLetras()[fila].length;
                      columna++
                    ) {
                      tablero.getTableroLetras()[fila][columna] =
                        letras[columna].charAt(0);
                    }
                    fila++;
                  }
                }
              } catch (IOException e) {
                System.out.println(
                  "Error al volver a leer el archivo: " + e.getMessage()
                );
              }

              System.out.println(
                green + "El tablero se cargó exitosamente" + reset
              );
              tableroCreado = true;
              usandoTablero = true;
            } else {
              System.out.println(
                red + "El archivo está vacío o no existe." + reset
              );
            }
          } catch (IOException e) {
            System.out.println(
              "Ocurrió un error al leer el archivo: " + e.getMessage()
            );
          }
          break;
        case 4:
          System.out.println("Digita el tamaño del tablero");
          int dimension = kb.nextInt();
          if (dimension != 5 && dimension != 7 && dimension != 9) {
            System.out.println(
              red + "La matriz debe ser de 5x5, 7x7  ó 9x9" + reset
            );
            break;
          }

          tablero = new Tablero(dimension);

          tablero.llenarTableroLetras();
          tablero.llenarTableroVisual();

          tablero.imprimirLetras();
          tablero.imprimirNumeros();
          tablero.guardarTableroEnArchivo();

          System.out.println(green + "Tablero creado" + reset);
          tableroCreado = true;
          usandoTablero = true;
          break;
        case 5:
          salir = true;
          System.out.println(
            yellow + "Gracias por jugar. ¡Hasta luego!" + reset
          );

          // System.out.print("\033[H\033[2J");
          // System.out.flush();
          break;
        default:
          System.out.println(
            red +
            "Opción inválida. Por favor, ingrese una opción válida." +
            reset
          );
      }
    }
    kb.close();
  }
}
