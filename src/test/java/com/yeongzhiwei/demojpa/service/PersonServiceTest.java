package com.yeongzhiwei.demojpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.yeongzhiwei.demojpa.TestUtil;
import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PersonServiceTest {
    
    private PersonService service;

    @Mock
    private PersonRepository repository;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PersonService(repository);

        when(repository.findAll()).thenReturn(TestUtil.createPersonList());
    }

    @Test
    void testFindAll() {
        List<Person> expected = TestUtil.createPersonList();
        List<Person> actual = service.findAll();
        assertEquals(expected, actual);
    }

}
