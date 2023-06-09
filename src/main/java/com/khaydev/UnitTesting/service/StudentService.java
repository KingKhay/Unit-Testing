package com.khaydev.UnitTesting.service;

import com.khaydev.UnitTesting.model.Student;

import java.util.List;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudentById(int id);

    List<Student> findAllStudents();

    void deleteStudentById(int id);

    Student updateStudent(int id, Student student);
}
