package com.stepnicky.springchallenge.person;

import com.stepnicky.springchallenge.pensioner.Pensioner;
import com.stepnicky.springchallenge.pensioner.PensionerService;
import com.stepnicky.springchallenge.student.Student;
import com.stepnicky.springchallenge.student.StudentService;
import com.stepnicky.springchallenge.worker.Worker;
import com.stepnicky.springchallenge.worker.WorkerService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<Person>> getPeople(PersonCriteria personCriteria) {
        List<Person> filteredPeople =  personService.getPeopleByCriteria(personCriteria);
        return new ResponseEntity<List<Person>>(filteredPeople, HttpStatusCode.valueOf(200));
    }
}
