package com.stepnicky.springchallenge.worker;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkerService {

    private final WorkerDao workerDao;

    public WorkerService(WorkerDao workerDao) {
        this.workerDao = workerDao;
    }

    public List<Person> getWorkersByCriteria(PersonCriteria personCriteria) {
        return workerDao.getByCriteria(personCriteria);
    }
}
