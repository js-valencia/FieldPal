package edu.unl.cc.proyect.logica.view;
import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.FieldType;
import edu.unl.cc.proyect.logica.domain.Schedule;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class FieldView {

    private static Field[] fieldsCatalog;

    static {
        // Configuramos la simulación para el día de hoy
        LocalDate hoy = LocalDate.now();
        LocalTime apertura = LocalTime.of(8, 0);   // 08:00 AM
        LocalTime cierre = LocalTime.of(12, 0);   // 12:00 PM (Genera 4 bloques horarios)

        fieldsCatalog = new Field[2];
        fieldsCatalog[0] = new Field("Cancha Central", FieldType.SOCCER, new BigDecimal("25.00"), hoy, apertura, cierre);
        fieldsCatalog[1] = new Field("Cancha Alterna", FieldType.VOLEYBALL, new BigDecimal("15.00"), hoy, apertura, cierre);
    }

    // Método de acceso para las vistas de Player y Admin
    public static Field[] getFieldsCatalog() {
        return fieldsCatalog;
    }

    // Método para imprimir limpiamente la disponibilidad en consola
    public static void displayCatalogConsola() {
        System.out.println("\n=======================================================");
        System.out.println("          CATÁLOGO DE CANCHAS DISPONIBLES (HOY)        ");
        System.out.println("=======================================================");

        for (int i = 0; i < fieldsCatalog.length; i++) {
            Field cancha = fieldsCatalog[i];
            System.out.println("\nCancha ID [" + (i + 1) + "]: " + cancha.getName() + " (" + cancha.getFieldType() + ")");
            System.out.println("Precio por hora: $" + cancha.getPricePerHour());
            System.out.println("Bloques Horarios:");

            int slotIdx = 1;
            for (Schedule horario : cancha.getSchedules()) {
                System.out.println("   Slot [" + slotIdx + "] -> " + horario);
                slotIdx++;
            }
        }
        System.out.println("\n=======================================================");
    }
}