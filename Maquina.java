// Maquina.java
import java.util.Random;

public class Maquina extends Jugador {

  public Maquina(String nombre) {
    super(nombre, 0);
  }

  public int[] elegirParejaAleatoria(int tamañoTablero) {
    Random rand = new Random();
    int[] pareja = new int[2];
    pareja[0] = rand.nextInt(tamañoTablero);
    pareja[1] = rand.nextInt(tamañoTablero);
    return pareja;
  }
}
