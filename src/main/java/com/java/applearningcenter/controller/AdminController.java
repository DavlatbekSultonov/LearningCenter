package com.java.applearningcenter.controller;

import com.java.applearningcenter.dto.AuthUserDto;
import com.java.applearningcenter.dto.CourseCreateDTO;
import com.java.applearningcenter.dto.CourseResponseDTO;
import com.java.applearningcenter.dto.StudentDto;
import com.java.applearningcenter.service.AuthUserService;
import com.java.applearningcenter.service.CourseService;
import com.java.applearningcenter.service.EduStackService;
import com.java.applearningcenter.service.StudentService;
import com.java.applearningcenter.dto.*;
import com.java.applearningcenter.entity.authuser.AuthUser;
import com.java.applearningcenter.entity.course.Course;
import com.java.applearningcenter.entity.stack.EduStack;
import com.java.applearningcenter.entity.student.Student;
import com.java.applearningcenter.enums.Role;
import com.java.applearningcenter.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final StudentService studentService;
    private final EduStackService stackService;
    private final AuthUserService userService;
    private final CourseService courseService;
    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/create/student")
    public String createStudentPage(Model model) {

        List<EduStack> stackList = stackService.getAll();
        model.addAttribute("stackList",stackList);

        List<Role> roles = Arrays.stream(Role.values()).toList();
        model.addAttribute("roles", roles);

        return "/admin/student/student-add";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/create/mentor")
    public String createMentorPage() {

        return "/admin/mentor/mentor-add";
    }
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create/student")
    public String createStudent(@ModelAttribute StudentDto studentDto) {
        System.out.println("student = " + studentDto);
        studentService.create(studentDto);
        return "redirect:/admin/get/students";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/create/mentor")
    public String createMentor(@ModelAttribute AuthUserDto authUserDto) {
        System.out.println(authUserDto);
        userService.create(authUserDto);
        return "redirect:/admin/get/mentors";
    }


    @GetMapping("/get/students")
    public String getAll(Model model) {
        List<Student> students = studentService.getAll();
        model.addAttribute("students", students);
        return "/admin/student/student-list";
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @GetMapping("/get/mentors")
    public String getAllMentors(Model model) {
        List<AuthUser> mentors =  userService.getAllMentors();

        model.addAttribute("mentors", mentors);

        return "/admin/mentor/mentor-list";
    }

    @GetMapping("/delete/mentor/{id}")
    public String deleteMentor(@PathVariable Integer id) {
        userService.mentorDeleteById(id);
        return "redirect:/admin/get/mentors";
    }

    @GetMapping("/update/mentor/{id}")
    public String updateMentor(@PathVariable Integer id, Model model) {
        AuthUser updatingMentor = userService.getById(id);
        System.out.println("updatingMentor = " + updatingMentor);
        model.addAttribute("updatingMentor",updatingMentor);

        return "/admin/mentor/mentor-update";
    }
    @PostMapping("/update/mentor/{id}")
    public String updateMentor(@PathVariable Integer id, @ModelAttribute AuthUserDto authUserDto) {
        System.out.println("MentorDto = " + authUserDto);
        userService.update(id, authUserDto);

        return "redirect:/admin/get/mentors";
    }

    @GetMapping("/delete/student/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        studentService.deleteById(id);
        return "redirect:/admin/get/students";
    }
    @GetMapping("/update/student/{id}")
    public String updateStudent(@PathVariable Integer id, Model model) {
        Student updatingStudent = studentService.getById(id);
        System.out.println("updatingStudent = " + updatingStudent);
        model.addAttribute("updatingStudent",updatingStudent);

        return "/admin/student/student-update";
    }
    @PostMapping("/update/student/{id}")
    public String updateStudent(@PathVariable Integer id, @ModelAttribute StudentDto studentDto) {
        System.out.println("studentDto = " + studentDto);
        studentService.update(id, studentDto);

        return "redirect:/admin/get/students";
    }

   //Course
   @GetMapping("/saveCourse")
   public String addCourse(){
       return "/admin/course/create-course";
   }
    @PostMapping("/AddCourse")
    public String create(@ModelAttribute CourseCreateDTO requestDTO, Model model){
        CourseResponseDTO responseDTO = courseService.create(requestDTO);
        model.addAttribute("Course", responseDTO);
        return "redirect:/admin/getAllCourse";
    }

    @GetMapping(value = "/getAllCourse")
    public String getAllCourse(Model model){
        List<CourseResponseDTO>courses=courseService.getAll();
        model.addAttribute("courseList", courses);
        return "/admin/course/course-all";
    }

    @GetMapping("updateCourse/{id}")
    public String update(@PathVariable("id") UUID id, Model model){
        Course course = courseService.findById(id);
        model.addAttribute("courseUpdate",course);
        return "/admin/course/update-course";
    }
    @PostMapping("/updateCourseId/{id}")
    public String updateCourse (@ModelAttribute Course course){
        courseService.update(course);
        return "redirect:/admin/getAllCourse";
    }

    @GetMapping("/deleteCourse/{id}")
    public String delete(@PathVariable("id") UUID courseId){
        this.courseService.delete(courseId);
        return "redirect:/admin/getAllCourse";
    }

}