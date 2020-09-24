package com.yeongzhiwei.demojpa.repository;

import java.util.List;

import com.yeongzhiwei.demojpa.domain.Person;

public interface PersonRepository extends AbstractRepository<Person> {

    List<Person> findAll();

}
