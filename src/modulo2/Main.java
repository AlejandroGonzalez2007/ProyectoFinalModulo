package modulo2;
/*
 * Variables utilizadas en esta clase:
 * args, sc, gestion, opcion
 */
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionMascotas gestion = new GestionMascotas();
        String opcion = "";

        while (!opcion.equals("0")) {
            System.out.println("\n=== MÓDULO 2: MASCOTAS ===");
            System.out.println("1. M1 - Registrar mascota");
            System.out.println("2. M2 - Consultar y actualizar perfil de mascota");
            System.out.println("3. M3 - Eliminar perfil de mascota");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextLine().trim();

            switch (opcion) {
                case "1":
                    gestion.registrarMascota();
                    break;
                case "2":
                    gestion.consultarYActualizarMascota();
                    break;
                case "3":
                    gestion.eliminarPerfilMascota();
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
