package com.madeeasy;

import com.madeeasy.service.impl.StudentServiceImpl;

import java.util.Scanner;

public class StudentApp {

    private static int choice = 0;
    private static StudentServiceImpl studentServiceImpl = new StudentServiceImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        do {
            System.out.println("==== The Student Management ====");
            System.out.println("1) Save new Student details\n"
                    + "2) Display all Students\n"
                    + "3) Display Student using id\n"
                    + "4) Display Student using email\n"
                    + "5) Update Student using id\n"
                    + "6) Delete Student using id\n"
                    + "7) Delete All Students\n"
                    + "8) Get Students with Feedback\n"
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
                studentServiceImpl.saveStudent();
                break;

            case 2:
                studentServiceImpl.findAllStudents();
                break;

            case 3:
                System.out.print("Enter Student id : ");
                String id = scanner.nextLine();
                studentServiceImpl.findStudentById(id);
                break;
            case 4:
                System.out.print("Enter Student id : ");
                String email = scanner.nextLine();
                studentServiceImpl.findByEmail(email);
                break;
            case 5:
                System.out.print("Enter Student id to update : ");
                String studentId = scanner.nextLine();
                studentServiceImpl.updateStudentById(studentId);
                break;
            case 6:
                System.out.print("Enter Student id to delete : ");
                String studentIdToUpdate = scanner.nextLine();
                studentServiceImpl.removeStudentById(studentIdToUpdate);
                break;
            case 7:
                studentServiceImpl.removeAllStudents();
                break;
            case 8:
                System.out.println("Not implemented yet !!");
                break;
            case 9:
                System.out.println("Thank you for visiting!!");
                System.exit(0);
                break;

            default:
                System.out.println("Wrong Input!!");
                break;
        }
    }
}
