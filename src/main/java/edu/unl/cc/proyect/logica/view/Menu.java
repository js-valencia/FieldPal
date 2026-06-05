package edu.unl.cc.proyect.logica.view;

import java.util.Scanner;

public class Menu {

    public Scanner getScanner(){
        return new Scanner(System.in);
    }
    
    public int welcomeMenu() {
        System.out.println("Bienvenido a FieldPal: Sistema de reserva de canchas");
        System.out.println("Ingresar como: \n1. Jugador\n2.Administrador:");

        Scanner scanner = new Scanner(System.in);
        int user = scanner.nextInt();

        while (true) {
            if (user == 1) {
                System.out.println("Ha ingresado como Jugador");
                break;
            } else if (user == 2) {
                System.out.println("Ha ingresado como Administrador");
                break;
            } else {
                System.out.println("Vuelva a ingresar una opción válida: 1 para Jugador y 2 para Administrador");
                user = scanner.nextInt();
            }
        }
        return user;
    }

    public void adminMenu() {
        Scanner scanner = getScanner();
        System.out.println("Ingrese su usuario");
        String username = scanner.nextLine();
        System.out.println("Ingrese su contraseña");
        String password = scanner.nextLine();
        System.out.println("Bienvenido " + username);
    }

    public int playerMenu() {
        while (true) {
            Scanner scanner = getScanner();
            System.out.println("1. Reservar Cancha");
            System.out.println("2. Cancelar Reserva");
            System.out.println("3. Ver Reservas");
            System.out.println("4. Salir");
            System.out.print("Ingrese una opcion valida: ");
            int option = scanner.nextInt();
            switch (option){
                case 1,2,3:
                    break;
                case 4:
                    welcomeMenu();
                    break;
                default:
                    throw new IllegalArgumentException("Opcion invalida");
            }
        }
    }
}