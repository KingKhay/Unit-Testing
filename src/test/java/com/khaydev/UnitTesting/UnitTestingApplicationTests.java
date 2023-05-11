package com.khaydev.UnitTesting;

import com.khaydev.UnitTesting.exception.StudentNotFoundException;
import com.khaydev.UnitTesting.model.Student;
import com.khaydev.UnitTesting.repository.StudentRepository;
import com.khaydev.UnitTesting.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
class UnitTestingApplicationTests {

	@Autowired
	StudentService studentService;

	@MockBean
	StudentRepository studentRepository;

	private Student student = null;
	private final int nonExistentId = 5;

	@Test
	void contextLoads() {
	}

	@BeforeEach
	void setup(){
		student = new Student(1,"Khay","Takoradi",null,null,null);
	}

	@Test
	@DisplayName("Save Student Test")
	void testSaveStudentServiceMethod(){

		when(studentRepository.save(student)).thenReturn(student);

		Student theSavedStudent = studentService.createStudent(student);

		assertEquals(student.getName(), theSavedStudent.getName());
		assertEquals(student.getAddress(), theSavedStudent.getAddress());

		//Verify that the studentRepository.save method run only once
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	@DisplayName("Update Student Test")
	void testUpdateStudentByIdServiceMethod(){

		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		when(studentRepository.save(student)).thenReturn(student);

		Student updatedStudent
				= studentService.updateStudent(1,new Student(1,"Ebenezer","Accra",null,null,null));

		assertEquals(updatedStudent.getName(), student.getName());
		assertEquals(updatedStudent.getAddress(), student.getAddress());

		verify(studentRepository, times(1)).findById(student.getId());
		verify(studentRepository, times(1)).save(student);
	}

	@Test
	@DisplayName("Update Student Test (Throws exception)")
	void testUpdateStudentByIdServiceMethodThrowsException(){

		when(studentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		assertThrows(StudentNotFoundException.class,
				() -> {
			studentService.updateStudent(nonExistentId, new Student());
				});

		verify(studentRepository, times(1)).findById(nonExistentId);
	}

	@Test
	@DisplayName("Delete Student Test")
	void testDeleteStudentByIdServiceMethod(){

		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

		studentService.deleteStudentById(student.getId());

		verify(studentRepository, times(1)).findById(student.getId());
		verify(studentRepository, times(1)).deleteById(student.getId());
	}

	@Test
	@DisplayName("Delete Student Test (Throws Exception)")
	void testDeleteStudentByIdServiceMethodStudentNotFound(){

		when(studentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		assertThrows(StudentNotFoundException.class,
				() -> {
					studentService.deleteStudentById(nonExistentId);
				});

		verify(studentRepository, times(1)).findById(nonExistentId);
	}

	@Test
	@DisplayName("Get Student Test")
	void getStudentByIdServiceMethod(){

		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));

		Student retrievedStudent = studentService.findStudentById(student.getId());

		assertEquals(student.getName(), retrievedStudent.getName());
		assertEquals(student.getAddress(), retrievedStudent.getAddress());

		verify(studentRepository, times(1)).findById(student.getId());
	}

	@Test
	@DisplayName("Get Student Test (Throws exception)")
	void getStudentByIdServiceMethodThrowsStudentNotFound(){

		when(studentRepository.findById(nonExistentId)).thenReturn(Optional.empty());

		assertThrows(StudentNotFoundException.class,
				() -> {
			studentService.findStudentById(nonExistentId);
				});

		verify(studentRepository, times(1)).findById(nonExistentId);
	}

}
