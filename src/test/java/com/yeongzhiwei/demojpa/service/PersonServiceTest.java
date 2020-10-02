package com.yeongzhiwei.demojpa.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import com.yeongzhiwei.demojpa.TestUtil;
import com.yeongzhiwei.demojpa.domain.Person;
import com.yeongzhiwei.demojpa.repository.EmailRepository;
import com.yeongzhiwei.demojpa.repository.PersonRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class PersonServiceTest {
    
    private PersonService service;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private EmailRepository emailRepository;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new PersonService(personRepository, emailRepository);

        when(personRepository.findAll()).thenReturn(TestUtil.createPersonList());
    }

    @Test
    void testFindAll() {
        List<Person> expected = TestUtil.createPersonList();
        List<Person> actual = service.findAllPersons();
        assertEquals(expected, actual);
    }

    // This class is not updated beyond testing findAll() since the class being tested contains almost no logic.

}
