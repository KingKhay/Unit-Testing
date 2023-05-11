package com.khaydev.UnitTesting.controller;

import com.khaydev.UnitTesting.model.Student;
import com.khaydev.UnitTesting.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Student saveStudent(@RequestBody Student student) {
        return studentService.createStudent(student);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    Student findStudentById(@PathVariable int id){
        return studentService.findStudentById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Student> findAllStudents(){
        return studentService.findAllStudents();
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    Student updateStudent(int id, Student student){
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteStudent(@PathVariable int id){
        studentService.deleteStudentById(id);
    }
}
