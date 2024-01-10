package com.stepnicky.springchallenge.person;

import com.stepnicky.springchallenge.pensioner.Pensioner;
import com.stepnicky.springchallenge.pensioner.PensionerService;
import com.stepnicky.springchallenge.student.Student;
import com.stepnicky.springchallenge.student.StudentService;
import com.stepnicky.springchallenge.worker.Worker;
import com.stepnicky.springchallenge.worker.WorkerService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PersonService {

    private final PensionerService pensionerService;
    private final StudentService studentService;
    private final WorkerService workerService;

    public PersonService(PensionerService pensionerService,
                         StudentService studentService,
                         WorkerService workerService) {
        this.pensionerService = pensionerService;
        this.studentService = studentService;
        this.workerService = workerService;
    }

    public List<Person> getPeopleByCriteria(PersonCriteria personCriteria) {
        String type = personCriteria.getType();
        return switch (type) {
            case "worker" -> workerService.getWorkersByCriteria(personCriteria);
            case "student" -> studentService.getStudentsByCriteria(personCriteria);
            case "pensioner" -> pensionerService.getPensionersByCriteria(personCriteria);
            default -> throw new RuntimeException("Proper type not specified");
        };
    }
}
