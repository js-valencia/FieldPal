package edu.unl.cc.proyect.logica.view;

import java.util.Scanner;
import java.time.LocalDateTime;
import edu.unl.cc.proyect.logica.domain.Player;
import edu.unl.cc.proyect.logica.domain.User;

public class Menu {

    // Single Scanner instance for the whole class
    private final Scanner scanner = new Scanner(System.in);

    // Temporary reference to keep a single Player object in memory for domain testing
    private Player samplePlayer = null;

    /**
     * Main welcome menu.
     * Returns the chosen role or exit signal (1 = Player, 2 = Admin, 3 = Exit).
     */
    public int welcomeMenu() {
        System.out.println("\n=== Bienvenido a FieldPal: Sistema de reserva de canchas ===");
        System.out.println("Ingresar como:");
        System.out.println("1. Jugador");
        System.out.println("2. Administrador");
        System.out.println("3. Salir del programa");
        System.out.print("Seleccione una opción: ");

        int selectedOption = scanner.nextInt();
        scanner.nextLine(); // Clear scanner buffer after reading an integer

        // Validation loop
        while (selectedOption != 1 && selectedOption != 2 && selectedOption != 3) {
            System.out.print("Opción inválida. Ingrese (1: Jugador, 2: Admin, 3: Salir): ");
            selectedOption = scanner.nextInt();
            scanner.nextLine(); // Clear buffer again
        }

        return selectedOption;
    }

    /**
     * Specific submenu for the Player role.
     */
    public void playerMenu() {
        System.out.println("\n--- SUBMENÚ JUGADOR ---");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int playerOption = scanner.nextInt();
        scanner.nextLine();

        if (playerOption == 1) {
            System.out.println("\n--- REGISTRO DE NUEVO JUGADOR ---");
            System.out.print("Ingrese su Nombre Completo: ");
            String fullName = scanner.nextLine();

            System.out.print("Ingrese su Teléfono: ");
            String phoneNumber = scanner.nextLine();

            System.out.print("Ingrese su Correo: ");
            String email = scanner.nextLine();

            System.out.print("Ingrese su Nombre de Usuario: ");
            String username = scanner.nextLine();

            System.out.print("Ingrese su Contraseña: ");
            String password = scanner.nextLine();

            User temporaryUserContainer = new User() {};

            samplePlayer = new Player(fullName, phoneNumber, email, temporaryUserContainer);
            samplePlayer.register(username, password);

            System.out.println("\n¡Objeto Player creado y registrado con éxito en el dominio!");
            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();

        } else if (playerOption == 2) {
            System.out.println("\n--- INICIAR SESIÓN JUGADOR ---");
            System.out.print("Usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            if (samplePlayer != null && samplePlayer.login(username, password)) {
                System.out.println("\n¡Login Exitoso! Bienvenido, " + samplePlayer.getFullName());

                try {
                    System.out.println("Simulando creación de reserva desde el objeto Player...");

                    samplePlayer.makeReservation(LocalDateTime.now(), 5);
                    System.out.println("¡Reserva asociada al jugador con éxito!");

                } catch (IllegalStateException exception) {
                    System.out.println("\n[AVISO DEL DOMINIO] No se pudo concretar la reserva: " + exception.getMessage());
                }

            } else {
                System.out.println("\n[ERROR] No hay usuarios registrados o las credenciales no coinciden.");
            }

            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    /**
     * Specific submenu for the Administrator role.
     */
    public void adminMenu() {
        System.out.println("\n--- SUBMENÚ ADMINISTRADOR ---");
        System.out.println("1. Iniciar Sesión como Admin");
        System.out.println("2. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int adminOption = scanner.nextInt();
        scanner.nextLine();

        if (adminOption == 1) {
            System.out.println("\n--- LOGIN ADMINISTRADOR ---");
            System.out.print("Usuario Admin: ");
            String adminUsername = scanner.nextLine();
            System.out.print("Contraseña Admin: ");
            String adminPassword = scanner.nextLine();

            System.out.println("\n[INFO] Lógica de administración lista en el Dominio. (Simulación estática)");
            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }
}