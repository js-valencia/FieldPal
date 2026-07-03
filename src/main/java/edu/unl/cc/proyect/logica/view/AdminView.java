package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.Organization;
import edu.unl.cc.proyect.logica.domain.Payment;
import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.FieldType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class AdminView {

    private final Scanner scanner = new Scanner(System.in);
    private Organization organization;
    private List<Payment> paymentsDay;

    public void adminMenu(Organization organization, List<Payment> paymentsDay) {
        this.organization = organization;
        this.paymentsDay = paymentsDay;
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- PANEL DE CONTROL: ADMINISTRADOR ---");
            System.out.println("1. Configurar horarios de atención del Complejo");
            System.out.println("2. Agregar nueva cancha al catálogo");
            System.out.println("3. Eliminar una cancha del catálogo");
            System.out.println("4. Ver resumen de ingresos y pagos del día");
            System.out.println("5. Salir al menú principal");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    configurarHorariosComplejo();
                    break;
                case 2:
                    agregarCancha();
                    break;
                case 3:
                    eliminarCancha();
                    break;
                case 4:
                    verResumenIngresosDia();
                    break;
                case 5:
                    System.out.println("Cerrando panel de administración...");
                    salir = true;
                    break;
                default:
                    System.out.println(">> Opción inválida.");
                    break;
            }
        }
    }

    private void configurarHorariosComplejo() {
        try {
            System.out.print("Ingrese nueva hora de APERTURA (HH:mm, ej. 07:00): ");
            LocalTime apertura = LocalTime.parse(scanner.nextLine());
            System.out.print("Ingrese nueva hora de CIERRE (HH:mm, ej. 23:00): ");
            LocalTime cierre = LocalTime.parse(scanner.nextLine());

            System.out.println("\n[ÉXITO] Horarios actualizados en la Organización.");
            System.out.println("Nuevo horario: " + apertura + " a " + cierre);
        } catch (DateTimeParseException e) {
            System.out.println("[ERROR] Formato de hora inválido. Use HH:mm.");
        }
    }

    private void agregarCancha() {
        try {
            System.out.print("Nombre de la cancha: ");
            String name = scanner.nextLine();
            System.out.print("Precio por hora (ej. 20.00): ");
            BigDecimal precio = new BigDecimal(scanner.nextLine());

            Field nueva = new Field(name, FieldType.SOCCER, precio, LocalDate.now(), LocalTime.of(8,0), LocalTime.of(22,0));
            organization.addField(nueva);
            System.out.println("[ÉXITO] Cancha agregada correctamente a la organización.");
        } catch (Exception e) {
            System.out.println("[ERROR] No se pudo agregar la cancha. Verifique los datos.");
        }
    }

    private void eliminarCancha() {
        List<Field> canchas = organization.getFields();
        if (canchas == null || canchas.isEmpty()) {
            System.out.println("[AVISO] No hay canchas registradas en la organización.");
            return;
        }

        System.out.println("\n--- LISTA DE CANCHAS ---");
        for (int i = 0; i < canchas.size(); i++) {
            System.out.println((i + 1) + ". " + canchas.get(i).getName());
        }
        System.out.print("Seleccione el número de la cancha a eliminar: ");
        int idx = scanner.nextInt() - 1;
        scanner.nextLine();

        if (idx >= 0 && idx < canchas.size()) {
            organization.removeField(canchas.get(idx));
            System.out.println("[ÉXITO] Cancha eliminada correctamente.");
        } else {
            System.out.println("[ERROR] Selección inválida.");
        }
    }

    private void verResumenIngresosDia() {
        System.out.println("\n=============================================");
        System.out.println("     REPORTE DE INGRESOS Y PAGOS DEL DÍA     ");
        System.out.println("=============================================");
        System.out.println("Complejo: " + organization.getName());
        System.out.println("---------------------------------------------");

        // Detalle de cada pago utilizando el método 'calculateAmountToBePaid()'
        for (int i = 0; i < paymentsDay.size(); i++) {
            Payment pago = paymentsDay.get(i);
            System.out.println("Pago #" + (i + 1) + " -> Cliente: " + pago.getReservation().getPlayer().getFullName() +
                    " | Cancha: " + pago.getReservation().getField().getName() +
                    " | Monto: $" + pago.calculateAmountToBePaid() +
                    " | Estado: " + pago.getStatus());
        }

        System.out.println("---------------------------------------------");

        // Usamos una instancia auxiliar de pago para llamar a tu método generateDailyReport
        Payment reportHelper = new Payment();
        System.out.println(reportHelper.generateDailyReport(paymentsDay));
        System.out.println("=============================================");
    }
}