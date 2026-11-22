package modulo2;
/*
 * Variables utilizadas en esta clase:
 * codigo, nombre, especie, edad, tamanoAproximado, presenciaEnfermedades,
 * descripcionEnfermedad, nivelEnergia, nivelSociabilidad, estado, motivoRetiro,
 * salud, energiaTexto, sociabilidadTexto, resumen
 */
public class Mascota {
    private String codigo;
    private String nombre;
    private String especie;
    private int edad;
    private double tamanoAproximado;
    private boolean presenciaEnfermedades;
    private String descripcionEnfermedad;
    private int nivelEnergia;
    private int nivelSociabilidad;
    private String estado;
    private String motivoRetiro;

    public Mascota(String codigo, String nombre, String especie, int edad, double tamanoAproximado,
                   boolean presenciaEnfermedades, String descripcionEnfermedad,
                   int nivelEnergia, int nivelSociabilidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.especie = especie;
        this.edad = edad;
        this.tamanoAproximado = tamanoAproximado;
        this.presenciaEnfermedades = presenciaEnfermedades;
        this.descripcionEnfermedad = descripcionEnfermedad;
        this.nivelEnergia = nivelEnergia;
        this.nivelSociabilidad = nivelSociabilidad;
        this.estado = "Disponible";
        this.motivoRetiro = "";
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public double getTamanoAproximado() {
        return tamanoAproximado;
    }

    public void setTamanoAproximado(double tamanoAproximado) {
        this.tamanoAproximado = tamanoAproximado;
    }

    public boolean isPresenciaEnfermedades() {
        return presenciaEnfermedades;
    }

    public void setPresenciaEnfermedades(boolean presenciaEnfermedades) {
        this.presenciaEnfermedades = presenciaEnfermedades;
    }

    public String getDescripcionEnfermedad() {
        return descripcionEnfermedad;
    }

    public void setDescripcionEnfermedad(String descripcionEnfermedad) {
        this.descripcionEnfermedad = descripcionEnfermedad;
    }

    public int getNivelEnergia() {
        return nivelEnergia;
    }

    public void setNivelEnergia(int nivelEnergia) {
        this.nivelEnergia = nivelEnergia;
    }

    public int getNivelSociabilidad() {
        return nivelSociabilidad;
    }

    public void setNivelSociabilidad(int nivelSociabilidad) {
        this.nivelSociabilidad = nivelSociabilidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getMotivoRetiro() {
        return motivoRetiro;
    }

    public void setMotivoRetiro(String motivoRetiro) {
        this.motivoRetiro = motivoRetiro;
    }

    public String obtenerResumen() {
        String salud = presenciaEnfermedades ? "Sí (" + descripcionEnfermedad + ")" : "No";
        String energiaTexto;
        if (nivelEnergia == 1) {
            energiaTexto = "1 - Tranquilo";
        } else if (nivelEnergia == 2) {
            energiaTexto = "2 - Actividad moderada";
        } else {
            energiaTexto = "3 - Muy activo";
        }

        String sociabilidadTexto;
        if (nivelSociabilidad == 1) {
            sociabilidadTexto = "1 - Baja sociabilidad";
        } else if (nivelSociabilidad == 2) {
            sociabilidadTexto = "2 - Sociabilidad moderada";
        } else {
            sociabilidadTexto = "3 - Alta sociabilidad";
        }

        String resumen = "Código: " + codigo + "\n";
        resumen += "Nombre: " + nombre + "\n";
        resumen += "Especie: " + especie + "\n";
        resumen += "Edad: " + edad + " años\n";
        if (especie.equalsIgnoreCase("perro")) {
            resumen += "Tamaño aproximado: " + tamanoAproximado + " cm\n";
        }
        resumen += "Enfermedades: " + salud + "\n";
        resumen += "Nivel de energía: " + energiaTexto + "\n";
        resumen += "Nivel de sociabilidad: " + sociabilidadTexto + "\n";
        resumen += "Estado: " + estado;
        return resumen;
    }
}
