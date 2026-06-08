package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.FieldType;
import edu.unl.cc.proyect.logica.domain.Organization;
import edu.unl.cc.proyect.logica.domain.Payment;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AdminView {
    
    // El método ahora recibe el estado global para mantener los cambios en memoria
    public void adminMenu(Organization organization, List<Payment> paymentsDay) {
        Scanner sc = new Scanner(System.in);
        boolean goOut = false;

        while (!goOut) {
            System.out.println("\n=============================================");
            System.out.println("   PANEL DE ADMINISTRACIÓN CENTRAL (FieldPal) ");
            System.out.println("=============================================");
            System.out.println("1. Configurar Horarios");
            System.out.println("2. Registrar Cancha");
            System.out.println("3. Eliminar Cancha");
            System.out.println("4. Ver Perfil del Complejo");
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
                        System.out.println(">> ¡Horarios actualizados con éxito!");
                    } catch (DateTimeParseException e) {
                        System.out.println(">> ERROR: Formato inválido.");
                    }
                    break;

                case 2:
                    System.out.println("\n--- REGISTRAR CANCHA ---");
                    System.out.print("Nombre de la cancha: ");
                    String name = sc.nextLine();
                    System.out.print("Precio por hora: $");
                    java.math.BigDecimal price = sc.nextBigDecimal();
                    sc.nextLine();
                    
                    // Registramos la cancha directamente en el inventario de la organización compartida
                    Field nueva = new Field(name, FieldType.SOCCER, price, java.time.LocalDate.now(), organization.getOpeningHour(), organization.getClosingHour());
                    organization.addField(nueva);
                    System.out.println(">> Cancha registrada en la organización.");
                    break;

                case 3:
                    try {
                        if (organization.getFields().isEmpty()) {
                            System.out.println(">> No hay canchas registradas.");
                            break;
                        }
                        // Lista las canchas existentes para poder eliminar por índice
                        for (int i = 0; i < organization.getFields().size(); i++) {
                            System.out.println("  [" + i + "] " + organization.getFields().get(i).getName());
                        }
                        System.out.print("Índice de la cancha a eliminar: ");
                        int idx = sc.nextInt();
                        sc.nextLine();
                        
                        if (idx >= 0 && idx < organization.getFields().size()) {
                            Field f = organization.getFields().get(idx);
                            organization.removeField(f);
                            System.out.println(">> Eliminada con éxito.");
                        } else {
                            System.out.println(">> Índice inválido.");
                        }
                    } catch (IllegalStateException e) {
                        System.out.println(">> ALERTA: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\n--- PERFIL DE " + organization.getName().toUpperCase() + " ---");
                    System.out.println("Dirección: " + organization.getAddress());
                    System.out.println("Horario: " + organization.getOpeningHour() + " a " + organization.getClosingHour());
                    System.out.println("Canchas totales: " + organization.getFields().size());
                    break;

                case 5:
                    System.out.println("Regresando al menú de roles...");
                    goOut = true;
                    break;

                default:
                    System.out.println(">> Opción no válida.");
            }
        }
    }
}
