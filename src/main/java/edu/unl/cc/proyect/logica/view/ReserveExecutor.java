package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReserveExecutor {

    public static Scanner getScanner(){
        return new Scanner(System.in);
    }

    public static void welcomeMenu(){
        while(true){
            System.out.println("1. Iniciar Sesion");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            System.out.print("Ingrese una opcion valida: ");
            Scanner scanner = getScanner();
            int option = scanner.nextInt();
            switch(option){
                case 1:
                    loginMenu();
                    break;
                case 2:
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                    break;
                default:
                    throw new IllegalArgumentException("Opcion invalida");
            }
        }
    }

    public static void loginMenu(){
        System.out.println("Ingrese su usuario");
        Scanner scanner = getScanner();
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña");
        String password = scanner.nextLine();
        System.out.println("Bienvenido " + username);
        mainMenu();
    }

    public static void mainMenu() {
        while (true) {
            System.out.println("1. Reservar Cancha");
            System.out.println("2. Cancelar Reserva");
            System.out.println("3. Ver Reservas");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion valida: ");
            Scanner scanner = getScanner();
            int option = scanner.nextInt();

            switch (option){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    welcomeMenu();
                    break;
                default:
                    throw new IllegalArgumentException("Opcion invalida");
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Sistema de Reserva de Canchas 'FieldPal'");
        welcomeMenu();
    }
}
