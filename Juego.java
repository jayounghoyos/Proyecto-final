import java.util.Scanner;

public class Juego {
    public static void main(String[] args) {
        int turno = 0;
        boolean salir = false;
        Boolean tableroCreado = false;
        boolean usandoTablero = false;

        String reset="\u001B[0m";
        String red="\033[31m"; 
        String green="\033[32m"; 
        String yellow="\033[33m"; 
        /*String blue="\033[34m"; 
        String purple="\033[35m"; 
        String cyan="\033[36m"; 
        String white="\033[37m";
        String black="\033[30m"; 
        */
        Scanner kb = new Scanner(System.in);

        while (!salir) {
            System.out.println("Bienvenido al juego Concéntrate");
            System.out.println("1. Jugar entre dos jugadores");
            System.out.println("2. Jugar contra la máquina");
            System.out.println("3. Cargar tablero desde un archivo");
            System.out.println("4. Leer tablero por teclado");
            System.out.println("5. Salir");
            System.out.print("Ingrese la opción que desea ejecutar: ");

            int opcion = kb.nextInt();
            switch (opcion) {
                case 1:
                    if (tableroCreado == false || usandoTablero == false) {
                        System.out.println(red + "Primero tienes que crear o cargar un archivo para jugar" + reset);
                        break;
                    }
                    // Lógica para jugar entre dos jugadores
                    break;
                case 2:
                    if (tableroCreado == false || usandoTablero == false) {
                        System.out.println(red + "Primero tienes que crear o cargar un archivo para jugar" + reset);
                        break;
                    }
                    System.out.println("Esta es una línea.");
                    System.out.println("Esta es otra línea.");
                    System.out.println("Esta es una tercera línea.");

                    // Secuencia de escape ANSI para "borrar" parte de la consola
                    System.out.print("\033[3;1H\033[2K"); // Ubicarse en la tercera línea y borrarla

                    // Imprimir después de la parte "borrada"
                    System.out.println("Esta línea está después de la tercera línea.");

                    // Lógica para jugar contra la máquina
                    break;
                case 3:
                    // Lógica para cargar tablero desde un archivo
                    // tablero.cargarTableroLetras();
                    // tablero.llenarTableroNumeros();
                    break;
                case 4:
                    // Lógica para leer tablero por teclado
                    System.out.println("Digita el tamaño del tablero");
                    int dimension = kb.nextInt();
                    if(dimension != 5 && dimension != 7 && dimension != 9){
                        System.out.println(red + "La matriz debe ser de 5x5, 7x7  ó 9x9"+ reset);
                        break;
                    }

                    Tablero tablero = new Tablero(dimension);

                    tablero.llenarTableroLetras();
                    tablero.llenarTableroVisual();

                    tablero.imprimirLetras();
                    tablero.imprimirNumeros();
                    
                    System.out.println(green + "Tablero creado" + reset);
                    tableroCreado = true;
                    usandoTablero = true;
                    break;
                case 5:
                    salir = true;
                    System.out.println(yellow + "Gracias por jugar. ¡Hasta luego!" + reset);

                    //System.out.print("\033[H\033[2J");
                    //System.out.flush();
                    break;
                default:
                    System.out.println(red + "Opción inválida. Por favor, ingrese una opción válida." + reset);
            }
        }
        kb.close();
        
    }
}