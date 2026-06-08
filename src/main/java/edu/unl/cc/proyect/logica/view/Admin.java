package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.FieldType;
import edu.unl.cc.proyect.logica.domain.Organization;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Admin {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Inicializamos la organización con horarios por defecto según el diagrama (08:00 a 22:00)
        Organization miOrg = new Organization(
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

            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    try {
                        System.out.print("Ingrese hora de apertura (HH:mm): ");
                        miOrg.setOpeningHour(LocalTime.parse(sc.nextLine()));
                        System.out.print("Ingrese hora de cierre (HH:mm): ");
                        miOrg.setClosingHour(LocalTime.parse(sc.nextLine()));
                        System.out.println(">> ¡Horarios operativos actualizados con éxito!");
                    } catch (DateTimeParseException e) {
                        System.out.println(">> ERROR: Formato de hora inválido. Utilice el formato de 24 horas (HH:mm).");
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
                    FieldType[] tipos = FieldType.values();
                    for (int i = 0; i < tipos.length; i++) {
                        System.out.println("  [" + i + "] " + tipos[i]);
                    }
                    System.out.print("Seleccione el índice del tipo: ");
                    int tipoIdx = sc.nextInt();
                    sc.nextLine();

                    if (tipoIdx >= 0 && tipoIdx < tipos.length) {
                        java.time.LocalDate fechaHoy = java.time.LocalDate.now();

                        Field nuevaCancha = new Field(
                                name,
                                tipos[tipoIdx],
                                price,
                                fechaHoy,
                                miOrg.getOpeningHour(),
                                miOrg.getClosingHour()
                        );

                        miOrg.addField(nuevaCancha);
                        System.out.println(">> ¡Cancha '" + name + "' añadida con éxito al inventario empresarial con sus horarios generados!");
                    } else {
                        System.out.println(">> ERROR: Índice de tipo inválido. Operación cancelada.");
                    }
                    break;

                case 3:
                    try {
                        System.out.println("\n--- ELIMINAR CANCHA DE LA ORGANIZACIÓN ---");
                        if (miOrg.getFields().isEmpty()) {
                            System.out.println("No existen canchas registradas en este momento.");
                            break;
                        }

                        for (int i = 0; i < miOrg.getFields().size(); i++) {
                            Field f = miOrg.getFields().get(i);
                            System.out.println("  [" + i + "] " + f.getName() + " (" + f.getFieldType() + ")");
                        }
                        System.out.print("Elija el índice de la cancha que desea remover: ");
                        int delIdx = sc.nextInt();
                        sc.nextLine();

                        if (delIdx >= 0 && delIdx < miOrg.getFields().size()) {
                            Field canchaARemover = miOrg.getFields().get(delIdx);
                            miOrg.removeField(canchaARemover);
                            System.out.println(">> ¡Cancha eliminada del catálogo satisfactoriamente!");
                        } else {
                            System.out.println(">> ERROR: Índice fuera de rango.");
                        }
                    } catch (IllegalStateException e) {
                        // Captura la restricción de multiplicidad 1..* del diagrama (no puede quedar con 0 canchas)
                        System.out.println(">> ALERTA DE REGLA DE NEGOCIO: " + e.getMessage());
                    } catch (Exception e) {
                        System.out.println(">> ERROR: Ocurrió un problema al procesar la eliminación.");
                    }
                    break;

                case 4:
                    System.out.println("\n=============================================");
                    System.out.println("        PERFIL EMPRESARIAL - " + miOrg.getName().toUpperCase());
                    System.out.println("=============================================");
                    System.out.println("Dirección física: " + miOrg.getAddress());
                    System.out.println("Horario comercial: " + miOrg.getOpeningHour() + " hrs a " + miOrg.getClosingHour() + " hrs.");

                    if (miOrg.getOpeningHour() != null && miOrg.getClosingHour() != null) {
                        System.out.println("Duración de la Jornada: " + miOrg.calculateBussinessHours().toHours() + " horas continuas.");
                    }

                    System.out.println("Total de canchas activas en inventario: " + miOrg.getFields().size());
                    System.out.println("---------------------------------------------");
                    if (!miOrg.getFields().isEmpty()) {
                        System.out.println("Listado Detallado de Canchas:");
                        miOrg.getFields().forEach(c ->
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