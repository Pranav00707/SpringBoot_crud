package com.crud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crud.Repo.StudentRepository;
import com.crud.entity.Student;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }
    public void deleteStudent(Long id) {
        // Retrieve the student by ID
        Optional<Student> studentOptional = studentRepository.findById(id);
        
        // Check if the student exists
        if (studentOptional.isPresent()) {
            // If the student exists, delete it
            studentRepository.deleteById(id);
        } else {
            // If the student doesn't exist, throw an exception or handle the error accordingly
            throw new RuntimeException("Student not found with id: " + id);
        }
    }
}