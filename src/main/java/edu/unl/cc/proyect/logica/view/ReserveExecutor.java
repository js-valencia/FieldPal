package edu.unl.cc.proyect.logica.view;

public class ReserveExecutor {

    public static void main(String[] args) {
        Menu menu = new Menu();
        boolean continuar = true;

        while (continuar) {
            int userType = menu.welcomeMenu();
            
            if (userType == 1) {
                menu.playerMenu();
            } else if (userType == 2) {
                menu.adminMenu();
            } else if (userType == 3) {
                System.out.println("¡Gracias por usar FieldPal! Cerrando sistema...");
                continuar = false;
            }
        }
    }
}