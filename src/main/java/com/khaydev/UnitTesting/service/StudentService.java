package com.khaydev.UnitTesting.service;

import com.khaydev.UnitTesting.model.Student;

public interface StudentService {

    Student createStudent(Student student);

    Student findStudentById(int id);

    Iterable<Student> findAllStudents();

    void deleteStudentById(int id);

    Student updateStudent(int id, Student student);
}
