package com.khaydev.UnitTesting;

import com.khaydev.UnitTesting.model.Student;
import com.khaydev.UnitTesting.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;



    @BeforeEach
    void setup(){
        Student student1 = new Student(1, "King", "Takoradi", null, null, null);
        Student student2 = new Student(2, "Khay", "Cape Coast", null, null, null);

        studentRepository.save(student1);
        studentRepository.save(student2);
    }

    @Test
    @DisplayName("Get All Students Endpoint")
    @Order(1)
    void testGetAllStudentEndpoint() throws Exception {
        //Verify HTTP Response => Status Code, Content-Type, Json Response Body
        mockMvc.perform(MockMvcRequestBuilders.get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }
}
