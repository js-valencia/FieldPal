package edu.unl.cc.proyect.logica.view;

import edu.unl.cc.proyect.logica.domain.*;

public class ReserveExecutor {

    public static void main(String[] args) {
        Menu menu = new Menu();
        int user = menu.welcomeMenu();
        if (user== 1){
            menu.playerMenu();
        } else {
            menu.adminMenu();
        }
    }
}