package edu.unl.cc.proyect.logica.view;
import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.Organization;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class Admin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Organization miOrg = new Organization("FieldPal Center", "Av. Universitaria", null, null);

        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- GESTIÓN DE ORGANIZACIÓN (ADMIN) ---");
            System.out.println("1. Configurar Horarios (Entrada y Cierre)");
            System.out.println("2. Agregar Cancha del Catálogo");
            System.out.println("3. Eliminar Cancha de la Organización");
            System.out.println("4. Ver Perfil y Jornada");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = sc.nextInt();
            sc.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese hora de apertura (HH:mm): ");
                        miOrg.setOppeningHour(LocalTime.parse(sc.nextLine()));
                        System.out.print("Ingrese hora de cierre (HH:mm): ");
                        miOrg.setClosingHour(LocalTime.parse(sc.nextLine()));
                        System.out.println("Horarios actualizados con éxito.");
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: Formato de hora inválido. Use HH:mm.");
                    }
                    break;

                case 2:
                    System.out.println("Canchas disponibles en catálogo:");
                    List<Field> catalogo = Field.getStaticFields();
                    for (int i = 0; i < catalogo.size(); i++) {
                        System.out.println(i + ". " + catalogo.get(i).getName());
                    }
                    System.out.print("Elija el índice de la cancha a agregar: ");
                    int idx = sc.nextInt();
                    miOrg.addField(catalogo.get(idx));
                    System.out.println("Cancha añadida al inventario.");
                    break;

                case 3:
                    try {
                        System.out.println("Canchas actuales en " + miOrg.getName() + ":");
                        for (int i = 0; i < miOrg.getFields().size(); i++) {
                            System.out.println(i + ". " + miOrg.getFields().get(i).getName());
                        }
                        System.out.print("Elija el índice para eliminar: ");
                        int delIdx = sc.nextInt();
                        miOrg.removeField(miOrg.getFields().get(delIdx));
                        System.out.println("Cancha eliminada con éxito.");
                    } catch (IllegalStateException e) {
                        // Captura la excepción de multiplicidad 1..* definida en tu clase [4, 5]
                        System.out.println("ALERTA: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error al procesar la eliminación.");
                    }
                    break;

                case 4:
                    System.out.println("Organización: " + miOrg.getName());
                    System.out.println("Horario: " + miOrg.getOppeningHour() + " a " + miOrg.getClosingHour());
                    System.out.println("Jornada Total: " + miOrg.calculateBussinessHours().toHours() + " horas.");
                    System.out.println("Canchas en inventario: " + miOrg.getFields().size());
                    break;

                case 5:
                    salir = true;
                    break;
            }
        }
    }

}
