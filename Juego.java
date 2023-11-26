import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Juego {

  public static void main(String[] args) {
    Tablero tablero = null;
    boolean salir = false;
    Boolean tableroCreado = false;
    boolean usandoTablero = false;
    String nombreArchivo;

    String reset = "\u001B[0m";
    String red = "\033[31m";
    String green = "\033[32m";
    String yellow = "\033[33m";

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

          
          int totalParejas =
            (
              tablero.getTableroLetras().length *
              tablero.getTableroLetras()[0].length -
              1
            ) /
            2;
          int puntosParaGanar = totalParejas / 2 + 1; // Más de la mitad de las parejas para ganar

          System.out.println(
            green +
            "El primer jugador está marcado con una X y el segundo jugador con una O" +
            reset
          );

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

              if (tablero.getTableroLetras()[fila1][columna1] == '*') {
                System.out.println("¡Has encontrado un comodín!");
                jugadorActual.setPuntos(jugadorActual.getPuntos() + 1);
              } else if (tablero.getTableroLetras()[fila2][columna2] == '*') {
                System.out.println("¡Has encontrado un comodín!");
                jugadorActual.setPuntos(jugadorActual.getPuntos() + 1);
              } else if (
                tablero.getTableroLetras()[fila1][columna1] ==
                tablero.getTableroLetras()[fila2][columna2]
              ) {
                System.out.println("¡Encontraste una pareja!");
                jugadorActual.setPuntos(jugadorActual.getPuntos() + 1);
                int marca = jugadorActual == jugador1 ? -1 : -2;
                tablero.getTableroVisual()[fila1][columna1] = marca;
                tablero.getTableroVisual()[fila2][columna2] = marca;

                if (jugadorActual.getPuntos() >= puntosParaGanar) {
                  System.out.println(
                    jugadorActual.getNombre() + " ha ganado el juego!"
                  );
                  juegoTerminado = true;
                  break; 
                }
              } else {
                System.out.println("No son una pareja. Siguiente turno.");
              }
              if((jugador1.getPuntos() + jugador2.getPuntos())>=12){
                juegoTerminado = true;
                break; 
              }
            }
          }



          System.out.println(green + "El juego ha terminado!" + reset);
          System.out.println(
            yellow +
            jugador1.getNombre() +
            " tiene " +
            jugador1.getPuntos() +
            " puntos." +
            reset
          );
          System.out.println(
            yellow +
            jugador2.getNombre() +
            " tiene " +
            jugador2.getPuntos() +
            " puntos." +
            reset
          );

          if (jugador1.getPuntos() > jugador2.getPuntos()) {
            System.out.println(green + jugador1.getNombre() + " gana!" + reset);
          } else if (jugador2.getPuntos() > jugador1.getPuntos()) {
            System.out.println(green + jugador2.getNombre() + " gana!" + reset);
          } else {
            System.out.println(red + "Es un empate!" + reset);
          }

          break;
        case 2:
          if (tablero == null || !tableroCreado || !usandoTablero) {
            System.out.println(
              red + "Primero debes crear o cargar un tablero." + reset
            );
            break;
          }

          break;
        case 3:
          System.out.println("Digita el nombre del tablero debe ser .txt");
          nombreArchivo = kb.next();
          try (
            BufferedReader reader = new BufferedReader(
              new FileReader(nombreArchivo)
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
                  new FileReader(nombreArchivo)
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

                // Llenar el tablero visual después de cargar el tablero de letras
                tablero.llenarTableroVisual();
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

          tablero.imprimirNumeros();

          System.out.println("Digita el nombre del tablero debe ser .txt");
          nombreArchivo = kb.next();
          tablero.guardarTableroEnArchivo(nombreArchivo);

          System.out.println(green + "Tablero creado" + reset);
          tableroCreado = true;
          usandoTablero = true;
          break;
        case 5:
          salir = true;
          System.out.println(
            yellow + "Gracias por jugar. ¡Hasta luego!" + reset
          );

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
