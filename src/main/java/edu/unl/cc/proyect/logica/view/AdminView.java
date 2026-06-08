package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.FieldType;
import edu.unl.cc.proyect.logica.domain.Organization;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class AdminView {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Organization organization = new Organization(
                "FieldPal Center",
                "Av. Universitaria",
                LocalTime.of(8, 0),
                LocalTime.of(22, 0)
        );

        boolean salir = false;
        while (!salir) {
            System.out.println("\n=============================================");
            System.out.println("   PANEL DE ADMINISTRACIÓN CENTRAL (FieldPal) ");
            System.out.println("=============================================");
            System.out.println("1. Configurar Horarios del Complejo (Apertura/Cierre)");
            System.out.println("2. Registrar Nueva Cancha en el Inventario");
            System.out.println("3. Eliminar Cancha de la Organización");
            System.out.println("4. Visualizar Perfil, Canchas y Jornada");
            System.out.println("5. Salir del Panel");
            System.out.print("Seleccione una opción: ");

            int option = sc.nextInt();
            sc.nextLine(); 

            switch (option) {
                case 1:
                    try {
                        System.out.print("Ingrese hora de apertura (HH:mm): ");
                        organization.setOpeningHour(LocalTime.parse(sc.nextLine()));
                        System.out.print("Ingrese hora de cierre (HH:mm): ");
                        organization.setClosingHour(LocalTime.parse(sc.nextLine()));
                        System.out.println(">> ¡Horarios operativos actualizados con éxito!");
                    } catch (DateTimeParseException e) {
                        throw new IllegalArgumentException(">> ERROR: Formato de hora inválido. Utilice el formato de 24 horas (HH:mm).");
                    }
                    break;

                case 2:
                    System.out.println("\n--- REGISTRAR NUEVA CANCHA ---");
                    System.out.print("Ingrese el nombre/identificador de la cancha: ");
                    String name = sc.nextLine();

                    System.out.print("Ingrese el precio de alquiler por hora: $");
                    java.math.BigDecimal price = sc.nextBigDecimal();
                    sc.nextLine();

                    System.out.println("Seleccione el Tipo de Cancha:");
                    FieldType[] type = FieldType.values();
                    for (int i = 0; i < type.length; i++) {
                        System.out.println("  [" + i + "] " + type[i]);
                    }
                    System.out.print("Seleccione el índice del tipo: ");
                    int typeIdx = sc.nextInt();
                    sc.nextLine();

                    if (typeIdx >= 0 && typeIdx < type.length) {
                        java.time.LocalDate today = java.time.LocalDate.now();

                        Field nuevaCancha = new Field(
                                name,
                                type[typeIdx],
                                price,
                                today,
                                organization.getOpeningHour(),
                                organization.getClosingHour()
                        );

                        organization.addField(nuevaCancha);
                        System.out.println(">> ¡Cancha '" + name + "' añadida con éxito al inventario empresarial con sus horarios generados!");
                    } else {
                        throw new ArrayIndexOutOfBoundsException(">> ERROR: Índice de tipo inválido. Operación cancelada.");
                    }
                    break;

                case 3:
                    try {
                        System.out.println("\n--- ELIMINAR CANCHA DE LA ORGANIZACIÓN ---");
                        if (organization.getFields().isEmpty()) {
                            throw new IllegalStateException("No existen canchas registradas en este momento.");
                        }

                        for (int i = 0; i < organization.getFields().size(); i++) {
                            Field f = organization.getFields().get(i);
                            System.out.println("  [" + i + "] " + f.getName() + " (" + f.getFieldType() + ")");
                        }
                        System.out.print("Elija el índice de la cancha que desea remover: ");
                        int delIdx = sc.nextInt();
                        sc.nextLine();

                        if (delIdx >= 0 && delIdx < organization.getFields().size()) {
                            Field fieldToRemove = organization.getFields().get(delIdx);
                            organization.removeField(fieldToRemove);
                            System.out.println(">> ¡Cancha eliminada del catálogo satisfactoriamente!");
                        } else {
                            throw new IndexOutOfBoundsException(">> ERROR: Índice fuera de rango.");
                        }
                    } catch (IllegalStateException e) {

                        throw new IllegalStateException(">> ALERTA DE REGLA DE NEGOCIO: " + e.getMessage());
                    } catch (Exception e) {
                        throw new RuntimeException(">> ERROR: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("\n=============================================");
                    System.out.println("        PERFIL EMPRESARIAL - " + organization.getName().toUpperCase());
                    System.out.println("=============================================");
                    System.out.println("Dirección física: " + organization.getAddress());
                    System.out.println("Horario comercial: " + organization.getOpeningHour() + " hrs a " + organization.getClosingHour() + " hrs.");

                    if (organization.getOpeningHour() != null && organization.getClosingHour() != null) {
                        System.out.println("Duración de la Jornada: " + organization.calculateBussinessHours().toHours() + " horas continuas.");
                    }

                    System.out.println("Total de canchas activas en inventario: " + organization.getFields().size());
                    System.out.println("---------------------------------------------");
                    if (!organization.getFields().isEmpty()) {
                        System.out.println("Listado Detallado de Canchas:");
                        organization.getFields().forEach(c ->
                                System.out.println("  • " + c.getName() + " | Tipo: " + c.getFieldType() + " | Precio/Hora: $" + c.getPricePerHour())
                        );
                    }
                    System.out.println("=============================================");
                    break;

                case 5:
                    System.out.println(">> Saliendo del sistema administrativo de FieldPal...");
                    salir = true;
                    break;

                default:
                    System.out.println(">> Opción inválida. Intente nuevamente.");
                    break;
            }
        }
    }
}