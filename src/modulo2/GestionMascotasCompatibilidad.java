/*
 * Variables utilizadas en esta clase:
 * mascotas, adoptantes, contador, sc, codigo, m, cedula, a, txt, e, actual,
 * nombre, especie, edadTxt, tamTxt, enfResp, descEnf, enerTxt, socTxt,
 * estadoTxt, edad, limiteEdad, tamano, tieneEnf, energia, sociabilidad,
 * nueva, nuevoEstado, op, esp, hay, resp, motivo, pts, seleccionada,
 * lista, porc, p, i, j, auxP, auxM
 */
package modulo2;

import java.util.ArrayList;
import java.util.Scanner;

public class GestionMascotasCompatibilidad {
    private ArrayList<Mascota> mascotas = new ArrayList<>();
    private ArrayList<Adoptante> adoptantes = new ArrayList<>();
    private int contador = 1;
    private Scanner sc = new Scanner(System.in);

    public GestionMascotasCompatibilidad() {
        adoptantes.add(new Adoptante("1711111111", "Carlos Perez", "Activo/Habilitado", "Grande", 3, false));
        adoptantes.add(new Adoptante("1722222222", "Maria Gomez", "Activo/Habilitado", "Pequeño", 6, true));
        adoptantes.add(new Adoptante("1733333333", "Luis Torres", "Inhabilitado", "Mediano", 4, false));
    }

    private Mascota buscarMascota(String codigo) {
        for (Mascota m : mascotas) {
            if (m.getCodigo().equalsIgnoreCase(codigo)) {
                return m;
            }
        }
        return null;
    }

    private Adoptante buscarAdoptante(String cedula) {
        for (Adoptante a : adoptantes) {
            if (a.getCedula().equals(cedula)) {
                return a;
            }
        }
        return null;
    }

    private int aEntero(String txt) {
        try {
            return Integer.parseInt(txt);
        } catch (Exception e) {
            return -1;
        }
    }

    private double aDouble(String txt) {
        try {
            return Double.parseDouble(txt);
        } catch (Exception e) {
            return -1;
        }
    }

    private void guardarDatosMascota(Mascota actual) {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Especie (perro/gato): ");
        String especie = sc.nextLine().trim().toLowerCase();
        System.out.print("Edad (años): ");
        String edadTxt = sc.nextLine().trim();

        String tamTxt = "";
        if (especie.equals("perro")) {
            System.out.print("Tamaño aproximado (cm): ");
            tamTxt = sc.nextLine().trim();
        }

        System.out.print("¿Tiene enfermedades? (Si/No): ");
        String enfResp = sc.nextLine().trim().toLowerCase();
        String descEnf = "Ninguna";
        if (enfResp.equals("si") || enfResp.equals("sí")) {
            System.out.print("Descripción de la enfermedad: ");
            descEnf = sc.nextLine().trim();
        }

        System.out.print("Nivel de energía (1-3): ");
        String enerTxt = sc.nextLine().trim();
        System.out.print("Nivel de sociabilidad (1-3): ");
        String socTxt = sc.nextLine().trim();

        String estadoTxt = "Disponible";
        if (actual != null) {
            System.out.print("Estado (Disponible / En proceso de adopción / Adoptada): ");
            estadoTxt = sc.nextLine().trim();
        }

        if (nombre.isEmpty() && (especie.isEmpty() || edadTxt.isEmpty() || enfResp.isEmpty() || enerTxt.isEmpty() || socTxt.isEmpty())) {
            System.out.println("Error: Los campos obligatorios deben estar completos.");
            return;
        }
        if (nombre.isEmpty()) {
            System.out.println("Error: Debe ingresar el nombre de la mascota.");
            return;
        }
        if (especie.isEmpty() || edadTxt.isEmpty() || enfResp.isEmpty() || enerTxt.isEmpty() || socTxt.isEmpty()
                || (especie.equals("perro") && tamTxt.isEmpty()) || (actual != null && estadoTxt.isEmpty())) {
            System.out.println("Error: Los campos obligatorios deben estar completos.");
            return;
        }
        if (!especie.equals("perro") && !especie.equals("gato")) {
            System.out.println("Error: La especie debe ser perro o gato.");
            return;
        }

        int edad = aEntero(edadTxt);
        if (actual == null) {
            if (edad <= 0) {
                System.out.println("Error: La edad de la mascota debe ser mayor a cero.");
                return;
            }
        } else {
            int limiteEdad = especie.equals("perro") ? 25 : 30;
            if (edad <= 0 || edad > limiteEdad) {
                System.out.println("Error: La edad de la mascota no es válida.");
                return;
            }
        }

        double tamano = 0;
        if (especie.equals("perro")) {
            tamano = aDouble(tamTxt);
            if (tamano <= 0 || tamano > 150) {
                System.out.println("Error: El tamaño del perro debe estar entre 0 y 150 centímetros.");
                return;
            }
        }

        boolean tieneEnf;
        if (enfResp.equals("si") || enfResp.equals("sí")) {
            tieneEnf = true;
            if (descEnf.isEmpty()) {
                System.out.println("Error: Debe especificar la enfermedad de la mascota.");
                return;
            }
        } else if (enfResp.equals("no")) {
            tieneEnf = false;
        } else {
            System.out.println("Error: Debe responder Sí o No en presencia de enfermedades.");
            return;
        }

        int energia = aEntero(enerTxt);
        if (energia < 1 || energia > 3) {
            System.out.println("Error: El nivel de energía debe estar entre 1 y 3.");
            return;
        }

        int sociabilidad = aEntero(socTxt);
        if (sociabilidad < 1 || sociabilidad > 3) {
            System.out.println("Error: El nivel de sociabilidad debe estar entre 1 y 3.");
            return;
        }

        if (actual == null) {
            String codigo = "MASC-" + contador++;
            while (buscarMascota(codigo) != null) {
                codigo = "MASC-" + contador++;
            }
            Mascota nueva = new Mascota(codigo, nombre, especie, edad, tamano, tieneEnf, descEnf, energia, sociabilidad);
            mascotas.add(nueva);
            System.out.println("Registro exitoso. La mascota ha sido registrada correctamente.");
            System.out.println("Código generado: " + codigo);
        } else {
            String nuevoEstado = "";
            if (estadoTxt.equalsIgnoreCase("Disponible")) {
                nuevoEstado = "Disponible";
            } else if (estadoTxt.equalsIgnoreCase("En proceso de adopción") || estadoTxt.equalsIgnoreCase("En proceso de adopcion")) {
                nuevoEstado = "En proceso de adopción";
            } else if (estadoTxt.equalsIgnoreCase("Adoptada")) {
                nuevoEstado = "Adoptada";
            } else {
                System.out.println("Error: Estado no válido.");
                return;
            }

            if (actual.getEstado().equalsIgnoreCase("Adoptada") && !nuevoEstado.equalsIgnoreCase("Adoptada")) {
                System.out.println("Error: No se puede modificar la disponibilidad de una mascota que ya ha sido adoptada.");
                return;
            }

            actual.setNombre(nombre);
            actual.setEspecie(especie);
            actual.setEdad(edad);
            actual.setTamanoAproximado(tamano);
            actual.setPresenciaEnfermedades(tieneEnf);
            actual.setDescripcionEnfermedad(descEnf);
            actual.setNivelEnergia(energia);
            actual.setNivelSociabilidad(sociabilidad);
            actual.setEstado(nuevoEstado);
            System.out.println("Operación exitosa. La información de la mascota se ha consultado o actualizado correctamente.");
        }
    }

    public void registrarMascota() {
        System.out.println("\n--- M1: REGISTRAR MASCOTA ---");
        guardarDatosMascota(null);
    }

    public void consultarYActualizarMascota() {
        System.out.println("\n--- M2: CONSULTAR Y ACTUALIZAR ---");
        System.out.print("Buscar por (1) Código o (2) Especie: ");
        String op = sc.nextLine().trim();
        String codigo = "";

        if (op.equals("1")) {
            System.out.print("Código de mascota: ");
            codigo = sc.nextLine().trim();
        } else if (op.equals("2")) {
            System.out.print("Especie (perro/gato): ");
            String esp = sc.nextLine().trim().toLowerCase();
            if (!esp.equals("perro") && !esp.equals("gato")) {
                System.out.println("Error: La especie debe ser perro o gato.");
                return;
            }
            boolean hay = false;
            for (Mascota m : mascotas) {
                if (m.getEspecie().equalsIgnoreCase(esp)) {
                    System.out.println("-----\n" + m.obtenerResumen(true));
                    hay = true;
                }
            }
            if (!hay) {
                System.out.println("Error: La mascota no se encuentra registrada en el sistema.");
                return;
            }
            System.out.print("Ingrese el código de la mascota a seleccionar: ");
            codigo = sc.nextLine().trim();
        } else {
            System.out.println("Opción no válida.");
            return;
        }

        Mascota m = buscarMascota(codigo);
        if (m == null) {
            System.out.println("Error: La mascota no se encuentra registrada en el sistema.");
            return;
        }

        System.out.println("\n" + m.obtenerResumen(true));
        System.out.print("¿Desea actualizar esta mascota? (Si/No): ");
        String resp = sc.nextLine().trim().toLowerCase();
        if (resp.equals("si") || resp.equals("sí")) {
            guardarDatosMascota(m);
        } else {
            System.out.println("Operación exitosa. La información de la mascota se ha consultado o actualizado correctamente.");
        }
    }

    public void eliminarPerfilMascota() {
        System.out.println("\n--- M3: ELIMINAR PERFIL ---");
        System.out.print("Código de la mascota: ");
        Mascota m = buscarMascota(sc.nextLine().trim());

        if (m == null) {
            System.out.println("Error: La mascota ingresada no está registrada");
            return;
        }
        if (m.getEstado().equalsIgnoreCase("Adoptada")) {
            System.out.println("Error: No se puede retirar una mascota que ya fue adoptada");
            return;
        }

        System.out.print("Motivo de retiro (máx 150 caracteres): ");
        String motivo = sc.nextLine().trim();
        if (motivo.isEmpty()) {
            System.out.println("Error: Debe especificar el motivo del retiro");
            return;
        }
        if (motivo.length() > 150) {
            System.out.println("Error: El motivo supera la longitud máxima permitida");
            return;
        }

        m.setEstado("Inactivo");
        m.setMotivoRetiro(motivo);
        System.out.println("La mascota ha sido retirada correctamente del catálogo");
    }

    public double calcularCompatibilidad(Adoptante a, Mascota m) {
        if (a.isTieneNinos() && m.getNivelSociabilidad() == 1) {
            return 0;
        }

        double pts = 0;

        if (m.getNivelSociabilidad() == 3) {
            pts += 35;
        } else if (m.getNivelSociabilidad() == 2) {
            pts += 25;
        } else {
            pts += 20;
        }

        if (m.getEspecie().equalsIgnoreCase("gato") || m.getTamanoAproximado() <= 40) {
            pts += 35;
        } else if (a.getEspacioDisponible().equalsIgnoreCase("Grande")) {
            pts += 35;
        } else if (a.getEspacioDisponible().equalsIgnoreCase("Mediano")) {
            pts += 25;
        } else {
            pts += 10;
        }

        if (m.getNivelEnergia() == 1 || a.getHorasFueraCasa() <= 4) {
            pts += 30;
        } else if (a.getHorasFueraCasa() <= 6) {
            pts += 20;
        } else {
            pts += 10;
        }

        return pts;
    }

    private Adoptante validarAdoptante() {
        System.out.print("Cédula del adoptante: ");
        Adoptante a = buscarAdoptante(sc.nextLine().trim());
        if (a == null) {
            System.out.println("Error: El adoptante ingresado no está registrado");
            return null;
        }
        if (!a.getEstado().equalsIgnoreCase("Activo/Habilitado")) {
            System.out.println("Error: El adoptante está inhabilitado para realizar una adopción");
            return null;
        }
        return a;
    }

    public void evaluarPorcentajeCompatibilidad() {
        System.out.println("\n--- C1: EVALUAR COMPATIBILIDAD ---");
        Adoptante a = validarAdoptante();
        if (a == null) {
            return;
        }

        System.out.print("Código de la mascota: ");
        Mascota seleccionada = buscarMascota(sc.nextLine().trim());
        if (seleccionada == null || !seleccionada.getEstado().equalsIgnoreCase("Disponible")) {
            System.out.println("Error: La mascota ingresada no se encuentra disponible");
            return;
        }

        System.out.println("Compatibilidad con " + seleccionada.getNombre() + ": " + calcularCompatibilidad(a, seleccionada) + "%");
        System.out.println("\nTodas las mascotas disponibles:");
        for (Mascota m : mascotas) {
            if (m.getEstado().equalsIgnoreCase("Disponible")) {
                System.out.println("- " + m.getNombre() + ": " + calcularCompatibilidad(a, m) + "%");
            }
        }
    }

    public void generarReporteCompatibles() {
        System.out.println("\n--- C2: REPORTE DE COMPATIBLES ---");
        Adoptante a = validarAdoptante();
        if (a == null) {
            return;
        }

        ArrayList<Mascota> lista = new ArrayList<>();
        ArrayList<Double> porc = new ArrayList<>();

        for (Mascota m : mascotas) {
            if (m.getEstado().equalsIgnoreCase("Disponible")) {
                double p = calcularCompatibilidad(a, m);
                if (p > 60) {
                    lista.add(m);
                    porc.add(p);
                }
            }
        }

        if (lista.isEmpty()) {
            System.out.println("No se encontraron mascotas con el nivel mínimo de compatibilidad requerido");
            return;
        }

        for (int i = 0; i < porc.size() - 1; i++) {
            for (int j = 0; j < porc.size() - 1 - i; j++) {
                if (porc.get(j) < porc.get(j + 1)) {
                    double auxP = porc.get(j);
                    porc.set(j, porc.get(j + 1));
                    porc.set(j + 1, auxP);

                    Mascota auxM = lista.get(j);
                    lista.set(j, lista.get(j + 1));
                    lista.set(j + 1, auxM);
                }
            }
        }

        for (int i = 0; i < lista.size(); i++) {
            System.out.println("-----");
            System.out.println(lista.get(i).obtenerResumen(false));
            System.out.println("Compatibilidad: " + porc.get(i) + "%");
        }
    }
}