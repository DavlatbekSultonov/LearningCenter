package com.java.applearningcenter.entity.course;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "course")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String mentor;
    private Integer duration;
    @Column(name = "course_code")
    private String courseCode;
    @Column(name = "max_student")
    private Integer maxStudent;
    private Integer price;
    @Column(name = "start_time")
    private LocalDate startTime;
}