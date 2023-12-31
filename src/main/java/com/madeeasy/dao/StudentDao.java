package com.madeeasy.dao;

import com.madeeasy.entity.Student;

import java.util.List;

public interface StudentDao {
    public Student saveStudent(Student student);

    public List<Student> findAllStudents();

    public Student findStudentById(String id);

    public Student findByEmail(String email);

    public void updateStudentById(String id, Student student);

    public void removeStudentById(String id);

    public void removeAllStudents();

    public void getStudentsWithFeedback();
}
