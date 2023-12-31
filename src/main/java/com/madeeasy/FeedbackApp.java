package com.madeeasy;

import com.madeeasy.service.impl.FeedbackServiceImpl;

import java.util.Scanner;

public class FeedbackApp {
    private static int choice = 0;
    private static FeedbackServiceImpl feedbackServiceImpl = new FeedbackServiceImpl();

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("==== The Student Management ====");
            System.out.println("1) Save new Feedback\n"
                    + "2) Display all Feedbacks\n"
                    + "9) Exit");

            System.out.print("Enter your choice : ");

            try {
                choice = Integer.parseInt(scanner.nextLine());
                menu(choice);
            } catch (NumberFormatException e) {
                System.out.println("Sorry, you have to enter a number !!");
            }

        } while (choice != 9);

    }

    public static void menu(int choice) {
        switch (choice) {
            case 1:
                feedbackServiceImpl.saveFeedback();
                System.out.println("this is case 1");
                break;

            case 2:
                feedbackServiceImpl.getAllFeedbacks();
                break;
            case 9:
                System.out.println("Thanks for visiting!!");
                System.exit(0);
                break;
            default:
                System.out.println("Wrong Input!!");
                break;
        }
    }
}
