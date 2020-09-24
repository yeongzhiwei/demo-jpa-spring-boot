package com.yeongzhiwei.demojpa.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(value = TestController.class)
class TestControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @Test
    void test() throws Exception {
        this.mockMvc.perform(get("/test"))
            .andExpect(status().isOk());
    }

}
