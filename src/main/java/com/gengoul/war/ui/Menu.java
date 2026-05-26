package com.gengoul.war.ui;

import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);

    public MainMenuAction mainMenu() {
        displayMainMenu();
        return MainMenuAction.fromInt(getUserEntry(1,3));
    }

    public GameMenuAction gameMenu() {
        displayGameMenu();
        return GameMenuAction.fromInt(getUserEntry(1,3));
    }

    // TODO : ces deux méthodes privées displayXxxMenu sont utiles ?

    private void displayMainMenu() {
        System.out.println("************* Menu principal *************");
        System.out.println("1- Afficher les cartes");
        System.out.println("2- Mélanger les cartes");
        System.out.println("3- Commencer la partie");
        System.out.println("******************************************");
    }

    private void displayGameMenu() {
        System.out.println("**************** Menu jeu ****************");
        System.out.println("1- Main suivante");
        System.out.println("2- Afficher la distribution des cartes par joueur");
        System.out.println("3- Automatiser les mains jusqu’à la fin de la partie");
        System.out.println("******************************************");
    }

    private int getUserEntry(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int entry = scanner.nextInt();
                if (entry >= min && entry <= max) {
                    return entry;
                }
            } else {
                scanner.next();
            }
            System.out.println("Entrée invalide.");
        }
    }
}
