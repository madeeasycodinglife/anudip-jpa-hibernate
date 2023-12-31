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
@Builder
public class Feedback {
    @Id
    private Integer id;
    @Column
    private LocalDate date;
    @Column(length = 30, nullable = false)
    private String instructorName;
    @Column(length = 100, nullable = false)
    private String feedback;
}
