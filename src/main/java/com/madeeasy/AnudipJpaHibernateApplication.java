package com.madeeasy;

import java.util.Scanner;

public class AnudipJpaHibernateApplication {
    private static int choice = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("1) add");
            System.out.println("2) show");
            System.out.print("Enter your choice : ");
            try {
                choice = Integer.parseInt(scanner.nextLine());
                menu(choice);
            } catch (NumberFormatException e) {
                System.out.println("you must enter a number");
            }
        } while (choice != 0);
    }

    private static void menu(int choice) {
        switch (choice) {
            case 1:
                System.out.println("this is case 1");
                break;
            case 2:
                System.out.println("this is case 2");
                break;
            case 0:
                System.out.println("Thanks for visiting!!");
                System.exit(0);
                break;
            default:
                System.out.println("Wrong Input!!");
                break;
        }
    }
}
