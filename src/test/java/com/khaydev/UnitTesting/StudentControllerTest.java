package com.khaydev.UnitTesting;

import com.khaydev.UnitTesting.model.Student;
import com.khaydev.UnitTesting.repository.StudentRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private StudentRepository studentRepository;
    private final String ENDPOINT = "/api/students";


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
        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    @DisplayName("Get Student Should Return 200")
    @Order(2)
    void testGetStudentEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get(ENDPOINT + "/{id}", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value("Khay"));

    }

    @Test
    @DisplayName("Save Student Should Return 201")
    @Order(3)
    void testSaveStudentEndpoint() throws Exception {

        String contentBody = """
                {
                    "id": 3,
                    "name": "Maggie",
                    "address": "Paris"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post(ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON).content(contentBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Maggie"));
    }

    @Test
    @DisplayName("Update Student Should Return 201")
    @Order(4)
    void testUpdateStudentEndpoint() throws Exception {

        String contentBody = """
                {
                    "name": "Ronnie",
                    "address": "London"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put(ENDPOINT + "/{id}", 2)
                        .contentType(MediaType.APPLICATION_JSON).content(contentBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ronnie"));
    }

    @Test
    @DisplayName("Delete Student Should Return 204")
    @Order(5)
    void testDeleteStudentEndpoint() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.delete(ENDPOINT + "/{id}", 2))
                .andExpect(status().isNoContent());
    }
}
