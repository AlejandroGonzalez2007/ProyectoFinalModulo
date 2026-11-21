package modulo2;
/*
 * Variables utilizadas en esta clase:
 * cedula, nombre, estado, espacioDisponible, horasFueraCasa, tieneNinos
 */
public class Adoptante {
    private String cedula;
    private String nombre;
    private String estado;
    private String espacioDisponible;
    private int horasFueraCasa;
    private boolean tieneNinos;

    public Adoptante(String cedula, String nombre, String estado,
                     String espacioDisponible, int horasFueraCasa, boolean tieneNinos) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.estado = estado;
        this.espacioDisponible = espacioDisponible;
        this.horasFueraCasa = horasFueraCasa;
        this.tieneNinos = tieneNinos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEstado() {
        return estado;
    }

    public String getEspacioDisponible() {
        return espacioDisponible;
    }

    public int getHorasFueraCasa() {
        return horasFueraCasa;
    }

    public boolean isTieneNinos() {
        return tieneNinos;
    }
}
