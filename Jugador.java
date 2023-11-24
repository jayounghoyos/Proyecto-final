public class Jugador {
    protected String nombre;
    protected int edad;
    protected int puntuacionPorAcierto;
  
    public Jugador(String nombre, int edad) {
      this.nombre = nombre;
      this.edad = edad;
    }
  
    // setters
    public void setNombre(String nombre) {
      this.nombre = nombre;
    }
  
    public void setEdad(int edad) {
      this.edad = edad;
    }
  
    public void setPuntuacionPorAcierto(int puntuacionPorAcierto) {
      this.puntuacionPorAcierto = puntuacionPorAcierto;
    }
  
    // getters
    public String getNombre() {
      return nombre;
    }
  
    public int getEdad() {
      return edad;
    }
  
    public int getPuntuacionPorAcierto() {
      return puntuacionPorAcierto;
    }
  
    // métodos
    public int elegirPareja(int pareja) {
      return pareja;
    }
  }