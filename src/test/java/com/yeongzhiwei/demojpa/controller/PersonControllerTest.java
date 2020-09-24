package com.yeongzhiwei.demojpa.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import com.yeongzhiwei.demojpa.TestUtil;
import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.service.PersonService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

    @MockBean
    private PersonService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        List<Person> persons = TestUtil.createPersonList();
        when(service.findAll()).thenReturn(persons);
        
        this.mockMvc.perform(get("/persons"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.length()").value(persons.size()));
    }

}
