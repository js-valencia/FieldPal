package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.Organization;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AdminView {
    public void adminMenu() {
        Scanner sc = new Scanner(System.in);

        Organization organization = new Organization(
                "FieldPal Center",
                "Av. Universitaria",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0)
        );

        boolean goOut = false;
        while (!goOut) {
            System.out.println("\n=============================================");
            System.out.println("   PANEL DE ADMINISTRACIÓN CENTRAL (FieldPal) ");
            System.out.println("=============================================");
            System.out.println("1. Configurar Horarios");
            System.out.println("2. Registrar Cancha");
            System.out.println("3. Eliminar Cancha");
            System.out.println("4. Ver Perfil");
            System.out.println("5. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 1:
                    try {
                        System.out.print("Apertura (HH:mm): ");
                        organization.setOpeningHour(LocalTime.parse(sc.nextLine()));
                        System.out.print("Cierre (HH:mm): ");
                        organization.setClosingHour(LocalTime.parse(sc.nextLine()));
                        System.out.println(">> Éxito.");
                    } catch (DateTimeParseException e) {
                        System.out.println(">> ERROR: Formato inválido.");
                    }
                    break;

                case 3:
                    try {
                        if (organization.getFields().isEmpty()) {
                            System.out.println(">> No hay canchas.");
                            break;
                        }
                        Field f = organization.getFields().get(0); // Ejemplo
                        organization.removeField(f);
                        System.out.println(">> Eliminada.");
                    } catch (IllegalStateException e) {

                        System.out.println(">> ALERTA: " + e.getMessage());
                    }
                    break;

                case 5:
                    goOut = true;
                    break;

                default:
                    System.out.println(">> Opción no válida.");
            }
        }
    }
}