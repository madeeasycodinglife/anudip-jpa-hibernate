package com.madeeasy.dao.impl;

import com.madeeasy.dao.FeedbackDao;
import com.madeeasy.entity.Feedback;
import com.madeeasy.entity.Student;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class FeedbackDaoImpl implements FeedbackDao {
    private SessionFactory sessionFactory = null;

    public FeedbackDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

//    public FeedbackDaoImpl(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }

    @Override
    public void saveFeedback(Feedback feedback) {
        Transaction transaction = null;
        try (Session session = this.sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                session.persist(feedback);
                transaction.commit();
                System.out.println("Feedback saved successfully!!");
            } catch (ConstraintViolationException exception) {
                log.error("Constraint violation: {}", exception.getMessage());
                // Rollback the transaction in case of a constraint violation
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
            }
        } catch (Exception e) {
            log.error("Error saving student: {}", e.getMessage());
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
        }
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            try {
                String hql = "FROM Feedback";
                Query<Feedback> query = session.createQuery(hql, Feedback.class);
                List<Feedback> feedbacks = query.list();

                transaction.commit();
                return feedbacks;
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace(); // Log or handle the exception according to your application's error-handling strategy
            }
        }

        return null;
    }

    @Override
    public List<Feedback> getFeedbackByInstructorName(String instructorName) {
        return null;
    }

    @Override
    public void findInstructorsByStudentFeedback(Student studentId) {

    }

    @Override
    public void findFeedbackForTeacherByStudent(String studentId, String instructorName) {

    }
}
