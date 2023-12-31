package com.madeeasy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_details")
@Builder
public class Student {

    @Id
    @Column(length = 20)
    private String id;

    @Column(length = 30, nullable = false)
    private String firstName;

    @Column(length = 30, nullable = false)
    private String lastName;

    @Column(length = 30, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String gender;

    @Column(length = 10, nullable = false, unique = true)
    private String phone;

    @Column
    private LocalDate dateOfBirth;
}
