package com.madeeasy.service;

import com.madeeasy.dao.StudentDaoImpl;
import com.madeeasy.entity.Student;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

public class StudentServiceImpl implements StudentService {
    private Scanner scanner = new Scanner(System.in);
    private final StudentDaoImpl studentDaoImpl = new StudentDaoImpl();

    @Override
    public Student saveStudent() {

        String firstName = null;
        do {
            System.out.print("Enter student first name : ");
            firstName = scanner.nextLine().trim();
        } while (firstName.isBlank());

        String lastName = null;
        do {
            System.out.print("Enter student last name : ");
            lastName = scanner.nextLine().trim();
        } while (lastName.isBlank());

        String email = null;
        do {
            System.out.print("Enter student email : ");
            email = scanner.nextLine().trim();
        } while (email.isBlank());


        String gender = null;
        do {
            System.out.print("Enter student gender : ");
            gender = scanner.nextLine().trim();
        } while (gender.isBlank());

        String phone = null;
        do {
            System.out.print("Enter student phone number : ");
            phone = scanner.nextLine().trim();
        } while (phone.isBlank());

        LocalDate dateOfBirth = null;
        boolean validDate = false;

        while (!validDate) {
            try {
                System.out.print("Date of Birth (YYYY-MM-DD): ");
                String dobString = scanner.nextLine();
                dateOfBirth = LocalDate.parse(dobString);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Please enter the date in the correct format (YYYY-MM-DD).");
            }
        }

        Student student = Student.builder()
                .id(generateUniqueStudentId())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .phone(phone)
                .dateOfBirth(dateOfBirth)
                .build();
        return this.studentDaoImpl.saveStudent(student);
    }

    // Method to generate a unique student ID using UUID
    private String generateUniqueStudentId() {
        return "S" + UUID.randomUUID().toString()
                .replaceAll("-", "")
                .substring(1, 11);
    }

    @Override
    public void findAllStudents() {
        List<Student> allStudents = this.studentDaoImpl.findAllStudents();
        allStudents.forEach(student -> {
            Optional.ofNullable(student)
                    .ifPresent(s -> {
                        // Print column headers
                        System.out.printf("| %-20s | %-20s | %-20s | %-30s | %-8s | %-12s | %-13s |\n",
                                "Student ID", "First Name", "Last Name", "Email", "Gender", "Phone", "Date of Birth");

                        System.out.println("|----------------------|----------------------|----------------------|"
                                + "--------------------------------|----------|--------------|---------------|");

                        System.out.printf("| %-20s | %-20s | %-20s | %-30s | %-8s | %-12s | %-13s |\n",
                                s.getId(), s.getFirstName(), s.getLastName(), s.getEmail(),
                                s.getGender(), s.getPhone(), s.getDateOfBirth());
                    });
        });
        if (allStudents.isEmpty()) {
            System.out.println("No students found!!");
        }
    }

    @Override
    public void findStudentById(String id) {
        Student student = this.studentDaoImpl.findStudentById(id);
        if (student != null) {
            System.out.printf("| %-20s | %-20s | %-20s | %-30s | %-8s | %-12s | %-13s |\n",
                    "Student ID", "First Name", "Last Name", "Email", "Gender", "Phone", "Date of Birth");

            System.out.println("|----------------------|----------------------|----------------------|"
                    + "--------------------------------|----------|--------------|---------------|");

            System.out.printf("| %-20s | %-20s | %-20s | %-30s | %-8s | %-12s | %-13s |\n",
                    student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(),
                    student.getGender(), student.getPhone(), student.getDateOfBirth());
        } else {
            System.out.println("Student not found!!");
        }
    }

    @Override
    public void findByEmail(String email) {
        Student student = this.studentDaoImpl.findByEmail(email);
        if (student != null) {
            System.out.printf("| %-20s | %-20s | %-20s | %-30s | %-8s | %-12s | %-13s |\n",
                    "Student ID", "First Name", "Last Name", "Email", "Gender", "Phone", "Date of Birth");

            System.out.println("|----------------------|----------------------|----------------------|"
                    + "--------------------------------|----------|--------------|---------------|");

            System.out.printf("| %-20s | %-20s | %-20s | %-30s | %-8s | %-12s | %-13s |\n",
                    student.getId(), student.getFirstName(), student.getLastName(), student.getEmail(),
                    student.getGender(), student.getPhone(), student.getDateOfBirth());
        } else {
            System.out.println("Student not found!!");
        }
    }

    @Override
    public void updateStudentById(String id) {

        System.out.print("Enter student first name : ");
        String firstName = scanner.nextLine().trim();

        System.out.print("Enter student last name : ");
        String lastName = scanner.nextLine().trim();


        System.out.print("Enter student email : ");
        String email = scanner.nextLine().trim();


        System.out.print("Enter student gender : ");
        String gender = scanner.nextLine().trim();

        System.out.print("Enter student phone number : ");
        String phone = scanner.nextLine().trim();

        LocalDate dateOfBirth = null;
        boolean validDate = false;

        while (!validDate) {
            try {
                System.out.print("Date of Birth (YYYY-MM-DD, press Enter to skip): ");
                String dobString = scanner.nextLine().trim();

                // Check if the input is empty
                if (dobString.isEmpty()) {
                    System.out.println("Date of Birth skipped.");
                    validDate = true;
                    break;  // Exit the loop
                }

                dateOfBirth = LocalDate.parse(dobString);
                validDate = true;
            } catch (DateTimeParseException e) {
                System.out.println("Error: Please enter the date in the correct format (YYYY-MM-DD).");
            }
        }


        Student student = Student.builder()
                .id(generateUniqueStudentId())
                .firstName(firstName)
                .lastName(lastName)
                .email(email)
                .gender(gender)
                .phone(phone)
                .dateOfBirth(dateOfBirth)
                .build();
        this.studentDaoImpl.updateStudentById(id, student);
    }

    @Override
    public void removeStudentById(String id) {
        this.studentDaoImpl.removeStudentById(id);
    }

    @Override
    public void removeAllStudents() {
        this.studentDaoImpl.removeAllStudents();
    }
}
