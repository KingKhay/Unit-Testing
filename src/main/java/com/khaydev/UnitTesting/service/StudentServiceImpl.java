package com.khaydev.UnitTesting.service;

import com.khaydev.UnitTesting.exception.StudentNotFoundException;
import com.khaydev.UnitTesting.model.Student;
import com.khaydev.UnitTesting.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student findStudentById(int id) {
        return studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
    }

    @Override
    public Iterable<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public void deleteStudentById(int id) {

        studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);

        studentRepository.deleteById(id);
    }

    @Override
    public Student updateStudent(int id, Student student) {
        Student existingStudent =
                studentRepository.findById(id).orElseThrow(StudentNotFoundException::new);
        existingStudent.setName(student.getName());
        existingStudent.setAddress(student.getAddress());

        return studentRepository.save(existingStudent);
    }
}
