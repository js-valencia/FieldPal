package edu.unl.cc.proyect.logica.view;
import edu.unl.cc.proyect.logica.domain.Field;
import edu.unl.cc.proyect.logica.domain.Schedule;
import java.math.BigDecimal;
import java.time.LocalTime;

public class FieldView {

    // Arreglo estático compartido de 2 canchas (Soccer y Voley)
    private static Field[] fieldsCatalog;

    // Bloque estático para inicializar el catálogo de prueba
    static {
        LocalTime apertura = LocalTime.of(8, 0);  // 08:00 AM
        LocalTime cierre = LocalTime.of(12, 0);  // 12:00 PM (Generará 4 bloques de horarios)

        fieldsCatalog = new Field[2];
        fieldsCatalog[0] = new Field("Cancha Central", "SOCCER", new BigDecimal("25.00"), apertura, cierre);
        fieldsCatalog[1] = new Field("Cancha Alterna", "VOLEYBALL", new BigDecimal("15.00"), apertura, cierre);
    }

    // Método para que la clase Player o Admin obtengan el catálogo actual
    public static Field[] getFieldsCatalog() {
        return fieldsCatalog;
    }

    // Método de utilidad para mostrar el inventario completo en consola
    public static void displayCatalogConsola() {
        System.out.println("\n=== CATÁLOGO DE CANCHAS DISPONIBLES ===");
        for (int i = 0; i < fieldsCatalog.length; i++) {
            Field cancha = fieldsCatalog[i];
            System.out.println("------------------------------------------------");
            System.out.println((i + 1) + ". " + cancha.getName() + " [" + cancha.getFieldType() + "]");
            System.out.println("   Precio por hora: $" + cancha.getPricePerHour());
            System.out.println("   Horarios de la jornada:");

            int slotIdx = 1;
            for (Schedule horario : cancha.getSchedules()) {
                System.out.println("     [" + slotIdx + "] " + horario);
                slotIdx++;
            }
        }
        System.out.println("------------------------------------------------");
    }
}
