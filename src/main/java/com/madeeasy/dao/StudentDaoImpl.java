package com.madeeasy.dao;

import com.madeeasy.entity.Student;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import java.util.List;

@Slf4j
public class StudentDaoImpl implements StudentDao {
    private SessionFactory sessionFactory = null;

    public StudentDaoImpl() {
        this.sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public Student saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            try {
                session.persist(student);
                transaction.commit();
                System.out.println("Student saved successfully!!");
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
        return student;
    }


    @Override
    public List<Student> findAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            // Use HQL (Hibernate Query Language) to retrieve all students
            String hql = "FROM Student";
            Query<Student> query = session.createQuery(hql, Student.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception according to your application's error-handling strategy
            return null;
        }
    }

    @Override
    public Student findStudentById(String id) {
        try (Session session = sessionFactory.openSession()) {
            // Use HQL to retrieve a student by ID
            String hql = "FROM Student s WHERE s.id = :studentId";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentId", id);

            return query.uniqueResult();
        } catch (Exception e) {
            // Log or handle the exception according to your application's error-handling strategy
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student findByEmail(String email) {
        try (Session session = sessionFactory.openSession()) {
            // Use HQL to retrieve a student by email
            String hql = "FROM Student s WHERE s.email = :studentEmail";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("studentEmail", email);

            return query.uniqueResult();
        } catch (Exception e) {
            // Log or handle the exception according to your application's error-handling strategy
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void updateStudentById(String id, Student updatedStudent) {
        if (id == null || id.isBlank() || updatedStudent == null) {
            System.out.println("Invalid input. ID and student information are required.");
            return;
        }

        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // Check if the student with the given ID exists
                Student existingStudent = session.get(Student.class, id);
                if (existingStudent == null) {
                    System.out.println("Student with ID " + id + " not found.");
                    transaction.rollback();
                    return;
                }

                // Update the student information if provided
                if (updatedStudent.getFirstName() != null && !updatedStudent.getFirstName().isBlank()) {
                    existingStudent.setFirstName(updatedStudent.getFirstName());
                }

                if (updatedStudent.getLastName() != null && !updatedStudent.getLastName().isBlank()) {
                    existingStudent.setLastName(updatedStudent.getLastName());
                }

                if (updatedStudent.getEmail() != null && !updatedStudent.getEmail().isBlank()) {
                    existingStudent.setEmail(updatedStudent.getEmail());
                }

                if (updatedStudent.getGender() != null && !updatedStudent.getGender().isBlank()) {
                    existingStudent.setGender(updatedStudent.getGender());
                }

                if (updatedStudent.getPhone() != null && !updatedStudent.getPhone().isBlank()) {
                    existingStudent.setPhone(updatedStudent.getPhone());
                }

                if (updatedStudent.getDateOfBirth() != null) {
                    existingStudent.setDateOfBirth(updatedStudent.getDateOfBirth());
                }

                // Save the changes
                session.update(existingStudent);

                transaction.commit();
                System.out.println("Student with ID " + id + " updated successfully.");
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                // Log or handle the exception according to your application's error-handling strategy
                e.printStackTrace();
            }
        }
    }


    @Override
    public void removeStudentById(String id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // Use HQL to delete a student by ID
                String hql = "DELETE FROM Student s WHERE s.id = :studentId";
                Query<?> query = session.createQuery(hql);
                query.setParameter("studentId", id);

                int result = query.executeUpdate();

                if (result > 0) {
                    System.out.println("Student with ID " + id + " has been removed successfully.");
                } else {
                    System.out.println("No student found with ID " + id + ". No removal performed.");
                }

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                // Log or handle the exception according to your application's error-handling strategy
                e.printStackTrace();
            }
        }
    }


    @Override
    public void removeAllStudents() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            try {
                // Use HQL to delete all students
                String hql = "DELETE FROM Student";
                Query<?> query = session.createQuery(hql);

                int result = query.executeUpdate();

                System.out.println(result + " student(s) removed successfully.");

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                // Log or handle the exception according to your application's error-handling strategy
                e.printStackTrace();
            }
        }
    }


    @Override
    public void getStudentsWithFeedback() {

    }
}
