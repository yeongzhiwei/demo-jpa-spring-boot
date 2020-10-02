package com.yeongzhiwei.demojpa.controller;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yeongzhiwei.demojpa.TestUtil;
import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.dto.PersonRequest;
import com.yeongzhiwei.demojpa.dto.PersonResponse;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class PersonControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonRepository repository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void seedData() {
        repository.saveAll(TestUtil.createPersonList());
    }

    @AfterEach
    void removeData() {
        repository.deleteAll();
    }

    @Test
    void getAll() throws Exception {
        List<Person> expected = TestUtil.createPersonList();

        this.mockMvc.perform(get("/persons"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(expected.size()));
    }

    @Test
    void getExistingPerson() throws Exception {
        Person person = TestUtil.createPerson();
        person = repository.save(person);

        this.mockMvc.perform(get("/persons/" + person.getId()))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(person.getId()), Long.class))
            .andExpect(jsonPath("$.name", is(person.getName())));
    }

    @Test
    void getNonExistingPerson() throws Exception {
        this.mockMvc.perform(get("/persons/999"))
            .andExpect(status().isNotFound());
    }

    @Test
    void createPerson() throws Exception {
        PersonRequest person = new PersonRequest();
        person.setName("Zach");

        String response = this.mockMvc.perform(post("/persons")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.id").exists())
            .andExpect(jsonPath("$.name", is(person.getName())))
            .andReturn()
            .getResponse()
            .getContentAsString();
        PersonResponse actual = objectMapper.readValue(response, PersonResponse.class);

        assertTrue(repository.findById(actual.getId()).isPresent());
    }

    @Test
    void updateExistingPerson() throws Exception {
        Person person = TestUtil.createPerson();
        person = repository.save(person);
        PersonRequest personRequest = new PersonRequest();
        personRequest.setName("Zach");

        this.mockMvc.perform(put("/persons/" + person.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(personRequest)))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.id", is(person.getId()), Long.class))
            .andExpect(jsonPath("$.name", is(personRequest.getName())));

        assertEquals("Zach", repository.findById(person.getId()).get().getName());
    }

    @Test
    void updateNonExistingPerson() throws Exception {
        PersonRequest person = new PersonRequest();
        person.setName("Zach");

        this.mockMvc.perform(put("/persons/999")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(person)))
            .andExpect(status().isNotFound());
    }

    @Test
    void deleteAll() throws Exception {
        this.mockMvc.perform(delete("/persons"))
            .andExpect(status().isNoContent());

        assertEquals(0, repository.count());
    }

    @Test
    void deletePerson() throws Exception {
        this.mockMvc.perform(delete("/persons/1"))
            .andExpect(status().isNoContent());

        assertFalse(repository.findById(Long.valueOf(1)).isPresent());
    }
}
