package com.gengoul.war.ui;

import java.util.Scanner;

public class InputHandler {

    private final Scanner scanner = new Scanner(System.in);

    public int askInt(int min, int max) {
        while (true) {
            if (scanner.hasNextInt()) {
                int entry = scanner.nextInt();
                if (entry >= min && entry <= max) {
                    return entry;
                }
            } else {
                scanner.next();
            }
            System.out.println("Entrée invalide");
        }
    }
}
