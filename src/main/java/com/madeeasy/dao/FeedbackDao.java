package com.madeeasy.dao;

import com.madeeasy.entity.Feedback;
import com.madeeasy.entity.Student;

import java.util.List;

public interface FeedbackDao {
    public void saveFeedback(Feedback feedback);

    public List<Feedback> getAllFeedbacks();

    public List<Feedback> getFeedbackByInstructorName(String instructorName);

    public void findInstructorsByStudentFeedback(Student studentId);

    public void findFeedbackForTeacherByStudent(String studentId, String instructorName);
}
