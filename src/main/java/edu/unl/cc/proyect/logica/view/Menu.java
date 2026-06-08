package edu.unl.cc.proyect.logica.view;

import java.util.Scanner;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);

    public int welcomeMenu() {
        System.out.println("\n=== Bienvenido a FieldPal: Sistema de reserva de canchas ===");
        System.out.println("Ingresar como:");
        System.out.println("1. Jugador");
        System.out.println("2. Administrador");
        System.out.println("3. Salir del programa");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        while (opcion != 1 && opcion != 2 && opcion != 3) {
            System.out.print("Opción inválida. Ingrese (1: Jugador, 2: Admin, 3: Salir): ");
            opcion = scanner.nextInt();
            scanner.nextLine();
        }

        return opcion;
    }

    public void playerMenu() {
        System.out.println("\n--- SUBMENÚ JUGADOR ---");
        System.out.println("1. Registrarse");
        System.out.println("2. Iniciar Sesión");
        System.out.println("3. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.println("\n--- REGISTRO DE NUEVO JUGADOR ---");
            System.out.print("Ingrese su Nombre Completo: ");
            String name = scanner.nextLine();

            System.out.print("Ingrese su Nombre de Usuario: ");
            String username = scanner.nextLine();

            // --- LECTURA DISCRETA DE CONTRASEÑA ---
            System.out.print("Ingrese su Contraseña: ");
            java.io.Console console = System.console();
            String password;

            if (console != null) {
                char[] passwordChars = console.readPassword();
                password = new String(passwordChars);
            } else {
                password = scanner.nextLine();
                for (int i = 0; i < 30; i++) {
                    System.out.println();
                }
            }

            /*
               Player nuevoJugador = new Player(name, ..., ...);
               nuevoJugador.register(username, password);
            */

            System.out.println("¡Jugador registrado con éxito en el sistema!");
            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();

        } else if (opcion == 2) {
            System.out.println("\n--- INICIAR SESIÓN JUGADOR ---");
            System.out.print("Usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            System.out.println("Funcionalidad de Login en desarrollo.");
            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    public void adminMenu() {
        System.out.println("\n--- SUBMENÚ ADMINISTRADOR ---");
        System.out.println("1. Iniciar Sesión como Admin");
        System.out.println("2. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        if (opcion == 1) {
            System.out.println("\n--- LOGIN ADMINISTRADOR ---");
            System.out.print("Usuario Admin: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña Admin: ");
            String password = scanner.nextLine();

            System.out.println("Sección de administración en desarrollo.");
            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }
}