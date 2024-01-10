package com.stepnicky.springchallenge.person;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao {
    List<Person> getByCriteria(PersonCriteria personCriteria);
}
