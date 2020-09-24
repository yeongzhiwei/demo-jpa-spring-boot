package com.yeongzhiwei.demojpa;

import java.util.ArrayList;
import java.util.List;

import com.yeongzhiwei.demojpa.domain.Person;

public class TestUtil {

    public static Person createPerson() {
        return new Person("Adam");
    }

    public static Person createPerson2() {
        return new Person("Ben");
    }

    public static List<Person> createPersonList() {
        List<Person> persons = new ArrayList<>();
        persons.add(createPerson());
        persons.add(createPerson2());
        return persons;
    }
    
}
