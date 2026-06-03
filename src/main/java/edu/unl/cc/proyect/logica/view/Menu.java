package edu.unl.cc.proyect.logica.view;

import java.util.Scanner;

public class Menu {

    public void showMenu(){
        System.out.println("Bienvenido al FieldPal: Sistema de reserva de canchas");
        System.out.println("Ingresar como: \n1. Jugador\n2.Administrador:");

        Scanner scanner = new Scanner(System.in);
        int user = scanner.nextInt();

        do {
            if (user == 1){
                System.out.println("Ha ingresado como Jugador");
            } else if (user == 2) {
                System.out.println("Ha ingresado como Administrador");
            } else {
                System.out.println("Vuelva a ingresar: 1 para Jugador y 2 para Administrador");
                user = scanner.nextInt();
            }
        } while (user != 1 && user!=2);
    }

}
