package com.stepnicky.springchallenge.pensioner;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PensionerService {

    private final PensionerDao pensionerDao;

    public PensionerService(PensionerDao pensionerDao) {
        this.pensionerDao = pensionerDao;
    }

    public List<Person> getPensionersByCriteria(PersonCriteria personCriteria) {
        return pensionerDao.getByCriteria(personCriteria);
    }
}
