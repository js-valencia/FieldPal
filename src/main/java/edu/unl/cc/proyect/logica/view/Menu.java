package edu.unl.cc.proyect.logica.view;

import java.util.Scanner;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import edu.unl.cc.proyect.logica.domain.Player;
import edu.unl.cc.proyect.logica.domain.Admin;
import edu.unl.cc.proyect.logica.domain.User;
import edu.unl.cc.proyect.logica.domain.Field;

public class Menu {

    private final Scanner scanner = new Scanner(System.in);
    private final ArrayList<Player> playerList = new ArrayList<>();
    private final ArrayList<Admin> adminList = new ArrayList<>();
    private final ArrayList<Field> fieldList = new ArrayList<>();

    public Menu() {
        User playerAccount = new User();
        Player mockPlayer = new Player("Kiara Condoy", "0999999999", "kiara@fieldpal.com", playerAccount);
        mockPlayer.register("Kiara17", "liderneocore");
        playerList.add(mockPlayer);

        User adminAccount = new User();
        Admin mockAdmin = new Admin("Javier Guarnizo", "0888888888", "javier@fieldpal.com", adminAccount);
        mockAdmin.register("admin", "admin123");
        adminList.add(mockAdmin);

        fieldList.add(new Field("Cancha Central", "SOCCER_5", 25.0));
        fieldList.add(new Field("Cancha Pucara", "SOCCER_7", 35.0));
        fieldList.add(new Field("Cancha NeoCore", "SOCCER_11", 50.0));
    }

    public int welcomeMenu() {
        System.out.println("\n=== Bienvenido a FieldPal: Sistema de reserva de canchas ===");
        System.out.println("Ingresar como:");
        System.out.println("1. Jugador");
        System.out.println("2. Administrador");
        System.out.println("3. Salir del programa");
        System.out.print("Seleccione una opción: ");

        int selectedOption = scanner.nextInt();
        scanner.nextLine();

        while (selectedOption != 1 && selectedOption != 2 && selectedOption != 3) {
            System.out.print("Opción inválida. Ingrese (1: Jugador, 2: Admin, 3: Salir): ");
            selectedOption = scanner.nextInt();
            scanner.nextLine();
        }
        return selectedOption;
    }

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
            System.out.print("Ingrese su Nombre Completo: "); String fullName = scanner.nextLine();
            System.out.print("Ingrese su Teléfono: "); String phoneNumber = scanner.nextLine();
            System.out.print("Ingrese su Correo: "); String email = scanner.nextLine();
            System.out.print("Ingrese su Nombre de Usuario: "); String username = scanner.nextLine();
            System.out.print("Ingrese su Contraseña: "); String password = scanner.nextLine();

            User temporaryUserContainer = new User();
            Player newPlayer = new Player(fullName, phoneNumber, email, temporaryUserContainer);
            newPlayer.register(username, password);

            playerList.add(newPlayer);

            System.out.println("\n¡Jugador registrado con éxito en el sistema!");
            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();

        } else if (playerOption == 2) {
            System.out.println("\n--- INICIAR SESIÓN JUGADOR ---");
            System.out.print("Usuario: "); String username = scanner.nextLine();
            System.out.print("Contraseña: "); String password = scanner.nextLine();

            Player loggedPlayer = null;
            for (Player p : playerList) {
                if (p.login(username, password)) {
                    loggedPlayer = p;
                    break;
                }
            }

            if (loggedPlayer != null) {
                System.out.println("\n¡Login Exitoso! Bienvenido, " + loggedPlayer.getFullName());

                System.out.println("\n--- SELECCIÓN DE CANCHA ---");
                for (int i = 0; i < fieldList.size(); i++) {
                    Field f = fieldList.get(i);
                    String fieldName = (i == 0) ? "Cancha Central" : (i == 1) ? "Cancha Pucara" : "Cancha NeoCore";
                    String fieldType = (i == 0) ? "SOCCER_5" : (i == 1) ? "SOCCER_7" : "SOCCER_11";
                    String fieldPrice = (i == 0) ? "25.0" : (i == 1) ? "35.0" : "50.0";

                    System.out.println((i + 1) + ". " + fieldName + " (" + fieldType + ") - $" + fieldPrice);
                }
                System.out.print("Seleccione una cancha: ");
                int fieldChoice = scanner.nextInt() - 1;
                scanner.nextLine();

                if (fieldChoice >= 0 && fieldChoice < fieldList.size()) {
                    Field selectedField = fieldList.get(fieldChoice);

                    System.out.print("Ingrese la fecha y hora (Formato: DD/MM/AAAA HH:MM): ");
                    String dateTimeInput = scanner.nextLine();

                    try {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
                        LocalDateTime reservationDate = LocalDateTime.parse(dateTimeInput, formatter);

                        System.out.print("Ingrese la cantidad de horas a reservar: ");
                        int durationHours = scanner.nextInt();
                        scanner.nextLine();

                        System.out.println("\nProcesando reserva en el Dominio...");
                        loggedPlayer.makeReservation(reservationDate, durationHours, selectedField);
                        System.out.println("¡Reserva creada y asociada al jugador con éxito!");

                    } catch (java.time.format.DateTimeParseException e) {
                        System.out.println("\n[ERROR] Formato de fecha inválido.");
                    } catch (IllegalStateException exception) {
                        System.out.println("\n[AVISO DEL DOMINIO] " + exception.getMessage());
                    }
                } else {
                    System.out.println("\n[ERROR] Selección de cancha inválida.");
                }

            } else {
                System.out.println("\n[ERROR] Usuario o contraseña incorrectos.");
            }

            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }

    public void adminMenu() {
        System.out.println("\n--- SUBMENÚ ADMINISTRADOR ---");
        System.out.println("1. Iniciar Sesión como Admin");
        System.out.println("2. Volver al menú principal");
        System.out.print("Seleccione una opción: ");

        int adminOption = scanner.nextInt();
        scanner.nextLine();

        if (adminOption == 1) {
            System.out.println("\n--- LOGIN ADMINISTRADOR ---");
            System.out.print("Usuario Admin: "); String adminUsername = scanner.nextLine();
            System.out.print("Contraseña Admin: "); String adminPassword = scanner.nextLine();

            Admin loggedAdmin = null;
            for (Admin a : adminList) {
                if (a.login(adminUsername, adminPassword)) {
                    loggedAdmin = a;
                    break;
                }
            }

            if (loggedAdmin != null) {
                System.out.println("\n¡Login de Administrador Exitoso! Bienvenido, " + loggedAdmin.getFullName());
                System.out.println("Acceso concedido al panel de control de FieldPal (Simulado).");
            } else {
                System.out.println("\n[ERROR] Credenciales de administrador inválidas.");
            }

            System.out.println("Presione ENTER para continuar...");
            scanner.nextLine();
        }
    }
}