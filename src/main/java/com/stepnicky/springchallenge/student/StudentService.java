package com.stepnicky.springchallenge.student;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentDao studentDao;

    public StudentService(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Person> getStudentsByCriteria(PersonCriteria personCriteria) {
        return studentDao.getByCriteria(personCriteria);
    }
}
