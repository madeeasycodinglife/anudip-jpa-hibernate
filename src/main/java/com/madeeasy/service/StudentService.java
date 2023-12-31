package com.madeeasy.service;

import com.madeeasy.entity.Student;

import java.util.List;

public interface StudentService {
    public Student saveStudent();

    public void findAllStudents();

    public void findStudentById(String id);

    public void findByEmail(String email);

    public void updateStudentById(String id);

    public void removeStudentById(String id);

    public void removeAllStudents();
}
