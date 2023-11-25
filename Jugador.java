public class Jugador {

  protected String nombre;
  protected int puntos;
  protected int puntuacionPorAcierto;

  public Jugador(String nombre, int puntos) {
    this.nombre = nombre;
    this.puntos = puntos;
  }

  // setters
  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public void setPuntos(int puntos) {
    this.puntos = puntos;
  }

  public void setPuntuacionPorAcierto(int puntuacionPorAcierto) {
    this.puntuacionPorAcierto = puntuacionPorAcierto;
  }

  // getters
  public String getNombre() {
    return nombre;
  }

  public int getPuntos() {
    return puntos;
  }

  public int getPuntuacionPorAcierto() {
    return puntuacionPorAcierto;
  }

  // métodos
  public int elegirPareja(int pareja) {
    return pareja;
  }
}
