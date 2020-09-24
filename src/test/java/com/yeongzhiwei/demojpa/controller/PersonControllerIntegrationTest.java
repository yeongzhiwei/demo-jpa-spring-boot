package com.yeongzhiwei.demojpa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.yeongzhiwei.demojpa.TestUtil;
import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
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

        this.mockMvc.perform(get("/person"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(expected.size()));
    }

}
