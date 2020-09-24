package com.yeongzhiwei.demojpa;

import java.util.ArrayList;
import java.util.List;

import com.yeongzhiwei.demojpa.domain.Person;

public class TestUtil {

    public static Person createPerson() {
        Person person = new Person();
        person.setName("Adam");
        return person;
    }

    public static Person createPerson2() {
        Person person = new Person();
        person.setName("Ben");
        return person;
    }

    public static List<Person> createPersonList() {
        List<Person> persons = new ArrayList<>();
        persons.add(createPerson());
        persons.add(createPerson2());
        return persons;
    }
    
}
