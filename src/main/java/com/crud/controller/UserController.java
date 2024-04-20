package com.crud.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crud.entity.Student;
import com.crud.service.StudentService;

@Controller
public class UserController {
    @Autowired
    private StudentService studentService;


    @GetMapping("/register")
    public String showRegistrationForm() {
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationForm(
            @RequestParam("rollno") String rollNo,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("address") String address) {

        Student student = new Student();
        student.setRollNo(rollNo);
        student.setName(name);
        student.setEmail(email);
        student.setAddress(address);

        studentService.saveStudent(student);

        return "redirect:/students";
    }
    @GetMapping("/students")
    public String showStudentList(Model model) {
        // Retrieve the list of students from the service layer
        List<Student> studentList = studentService.getAllStudents();
        
        // Add the list of students to the model
        model.addAttribute("students", studentList);
        
        // Return the view for displaying the list of students
        return "studentList";
    }
    @GetMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable("id") Long id) {
        // Delete the student by id
        studentService.deleteStudent(id);
        
        // Redirect to the list of students
        return "redirect:/students";
    }
    
    
    

    @GetMapping("/students/{id}/update")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        // Retrieve the student by id
        Optional<Student> studentOptional = studentService.getStudentById(id);

        // Check if the student exists
        if (studentOptional.isPresent()) {
            // If the student exists, add it to the model and return the update form
            model.addAttribute("student", studentOptional.get());
            return "update";
        } else {
            // If the student doesn't exist, redirect to an error page or handle the error accordingly
            return "error";
        }
    }

    @PostMapping("/students/{id}/update")
    public String processUpdateForm(
            @PathVariable("id") Long id,
            @RequestParam("rollno") String rollNo,
            @RequestParam("name") String name,
            @RequestParam("email") String email,
            @RequestParam("address") String address) {

        // Retrieve the student by id
        Optional<Student> studentOptional = studentService.getStudentById(id);

        // Check if the student exists
        if (studentOptional.isPresent()) {
            // If the student exists, update its details and save it
            Student student = studentOptional.get();
            student.setRollNo(rollNo);
            student.setName(name);
            student.setEmail(email);
            student.setAddress(address);
            studentService.saveStudent(student);
            return "redirect:/students";
        } else {
            // If the student doesn't exist, redirect to an error page or handle the error accordingly
            return "error";
        }
    }

    
    
    
}
