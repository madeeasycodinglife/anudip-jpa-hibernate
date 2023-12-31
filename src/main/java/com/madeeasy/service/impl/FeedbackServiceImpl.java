package com.madeeasy.service.impl;

import com.madeeasy.dao.impl.FeedbackDaoImpl;
import com.madeeasy.entity.Feedback;
import com.madeeasy.entity.Student;
import com.madeeasy.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;

@Slf4j
public class FeedbackServiceImpl implements FeedbackService {
    private Scanner scanner = new Scanner(System.in);
    private FeedbackDaoImpl feedbackDaoImpl = new FeedbackDaoImpl();

    @Override
    public void saveFeedback() {
        String instructorName = null;
        do {
            System.out.print("Enter instructor name : ");
            instructorName = scanner.nextLine().trim();
        } while (instructorName.isBlank());

        String feedback = null;
        do {
            System.out.print("Enter feedback : ");
            feedback = scanner.nextLine().trim();
        } while (feedback.isBlank());

        Feedback feedbackBuilder = Feedback.builder()
                .id(generateUniqueFeedbackId())
                .date(LocalDate.now())
                .instructorName(instructorName)
                .feedback(feedback)
                .build();
        this.feedbackDaoImpl.saveFeedback(feedbackBuilder);
    }

    private int generateUniqueFeedbackId() {
        return Math.abs(UUID.randomUUID().hashCode());
    }


//    private String generateUniqueFeedbackId() {
//        return "F" + UUID.randomUUID().toString()
//                .replaceAll("-", "")
//                .substring(1, 11);
//    }

    @Override
    public void getAllFeedbacks() {
        List<Feedback> allFeedbacks = this.feedbackDaoImpl.getAllFeedbacks();
        if (!allFeedbacks.isEmpty()) {
            // Print column headers
            System.out.printf("| %-10s | %-12s | %-30s | %-100s |\n",
                    "Feedback ID", "Date", "Instructor Name", "Feedback");

            System.out.println("|-------------|--------------|--------------------------------|------------------------------------------------------------------------------------------------------|");

            allFeedbacks.forEach(feedback -> {
                Optional.ofNullable(feedback).ifPresent(f -> {
                    // Print feedback details
                    System.out.printf("| %-11s | %-12s | %-30s | %-100s |\n",
                            f.getId(), f.getDate(), f.getInstructorName(), f.getFeedback());
                });
            });
        } else {
            System.out.println("Sorry!! No feedbacks were found!!");
        }
    }


    @Override
    public void getFeedbackByInstructorName(String instructorName) {

    }

    @Override
    public void findInstructorsByStudentFeedback(Student studentId) {

    }

    @Override
    public void findFeedbackForTeacherByStudent(String studentId, String instructorName) {

    }
}
