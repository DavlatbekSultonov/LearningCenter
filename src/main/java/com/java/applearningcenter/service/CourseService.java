package com.java.applearningcenter.service;


import com.java.applearningcenter.dto.CourseCreateDTO;
import com.java.applearningcenter.dto.CourseResponseDTO;
import com.java.applearningcenter.entity.course.Course;
import com.java.applearningcenter.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseService {
    ModelMapper modelMapper = new ModelMapper();
    private final CourseRepository courseRepository;
    public CourseResponseDTO create(CourseCreateDTO requestDTO) {
        Course course = modelMapper.map(requestDTO, Course.class);
        courseRepository.save(course);
        return modelMapper.map(course,CourseResponseDTO.class);
    }

    public List<CourseResponseDTO> getAll() {
        List<Course> courseList = courseRepository.findAll();
        return courseList.stream().map(course -> modelMapper.map(course,CourseResponseDTO.class)).toList();
    }

    public Course findById(UUID id) {
        Course course = courseRepository.findById(id).get();
        return course;
    }

    public Course update(Course course) {
        return courseRepository.save(course);
    }

    public void delete(UUID courseId) {

        courseRepository.deleteById(courseId);
    }


}
