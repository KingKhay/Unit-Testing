package com.khaydev.UnitTesting;

import com.khaydev.UnitTesting.model.Student;
import com.khaydev.UnitTesting.repository.StudentRepository;
import com.khaydev.UnitTesting.service.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Transactional
public class IntegrationTestingTests {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @BeforeEach
    @Transactional
    void setup(){
        Student student1 = new Student(1, "King", "Takoradi", null, null, null);
        Student student2 = new Student(2, "Khay", "Cape Coast", null, null, null);
        Student student3 = new Student(3, "Ebenezer", "Accra", null, null, null);

        studentRepository.save(student1);
        studentRepository.save(student2);
        studentRepository.save(student3);
    }

    @AfterEach
    @Transactional
    void cleanup(){
        studentRepository.deleteAll();
    }

    @Test
    @DisplayName("Save Student Integration Test")
    @Order(1)
    @Transactional
    void testSaveStudentMethod(){

        Student student4 = new Student(4, "Roxie", "London", null, null, null);
        Student student = studentService.createStudent(student4);

        assertEquals(4, student.getId());
        assertEquals("Roxie", student.getName());
    }

    @Test
    @DisplayName("Get All Students Integration Test")
    @Order(2)
    @Transactional
    void testGetAllStudents(){

        List<Student> retrievedStudents = studentService.findAllStudents();

        assertEquals(3, retrievedStudents.size());
    }

    @Test
    @DisplayName("Get Student By Id Integration Test")
    @Order(3)
    @Transactional
    void testGetStudentById(){
        Student retrievedStudent = studentService.findStudentById(2);

        assertNotNull(retrievedStudent);
        assertEquals("Khay", retrievedStudent.getName());
    }

    @Test
    @DisplayName("Update Student By Id Integration Test")
    @Order(4)
    @Transactional
    void testUpdateStudent(){

        Student student = new Student(2, "Julie", "Tokyo", null, null, null);

        Student updatedStudent = studentService.updateStudent(2, student);

        assertEquals(student.getName(), updatedStudent.getName());
        assertEquals(student.getAddress(), updatedStudent.getAddress());
    }

    @Test
    @DisplayName("Delete Student By Id Integration Test")
    @Order(5)
    @Transactional
    void deleteStudent(){

        studentService.deleteStudentById(3);

        Optional<Student> student = studentRepository.findById(3);
        assertFalse(student.isPresent());
    }


}
