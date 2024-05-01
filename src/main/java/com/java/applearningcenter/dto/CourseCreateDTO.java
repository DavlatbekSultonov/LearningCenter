package com.java.applearningcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseCreateDTO {
    private String name;
    private String mentor;
    private Integer duration;
    private String  courseCode;
    private Integer maxStudent;
    private Integer price;
    private LocalDate startTime;
}
