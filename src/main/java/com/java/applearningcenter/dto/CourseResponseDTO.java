package com.java.applearningcenter.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseResponseDTO {
    private UUID id;
    private String name;
    private String mentor;
    private Integer duration;
    private String courseCode;
    private Integer maxStudent;
    private Integer price;
    private LocalDate startTime;
}
