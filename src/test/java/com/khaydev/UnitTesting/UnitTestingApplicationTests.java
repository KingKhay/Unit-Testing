package com.khaydev.UnitTesting;

import com.khaydev.UnitTesting.model.Student;
import com.khaydev.UnitTesting.repository.StudentRepository;
import com.khaydev.UnitTesting.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class UnitTestingApplicationTests {

	@Autowired
	StudentService studentService;

	@MockBean
	StudentRepository studentRepository;

	@Test
	void contextLoads() {
	}

	@Test
	@DisplayName("Save Method Must Return the Student Saved")
	public void testSaveStudentServiceMethod(){
		Student student = new Student(1,"Khay","Takoradi",null,null,null);

		when(studentRepository.save(student)).thenReturn(student);

		Student theSavedStudent = studentService.createStudent(student);

		assertEquals(student.getName(), theSavedStudent.getName());
		assertEquals(student.getAddress(), theSavedStudent.getAddress());

		//Verify that the studentRepository.save method run only once
		verify(studentRepository, times(1)).save(student);
	}

}
