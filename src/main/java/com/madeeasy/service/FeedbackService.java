package com.madeeasy.service;

import com.madeeasy.entity.Feedback;
import com.madeeasy.entity.Student;

import java.util.List;

public interface FeedbackService {
    public void saveFeedback();

    public void getAllFeedbacks();

    public void getFeedbackByInstructorName(String instructorName);

    public void findInstructorsByStudentFeedback(Student studentId);

    public void findFeedbackForTeacherByStudent(String studentId, String instructorName);

}
