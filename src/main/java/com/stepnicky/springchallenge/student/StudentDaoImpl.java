package com.stepnicky.springchallenge.student;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    private final EntityManager entityManager;

    public StudentDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Person> getByCriteria(PersonCriteria personCriteria) {
        TypedQuery<Person> query = entityManager.createQuery(
                "SELECT " +
                        "s FROM Student s " +
                        "WHERE (:firstName IS NULL OR LOWER(s.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
                        "AND (:lastName IS NULL OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
                        "AND (:pesel IS NULL OR s.pesel = :pesel) " +
                        "AND (:gender IS NULL OR s.gender = :gender) " +
                        "AND (:heightFrom IS NULL OR s.height >= :heightFrom) " +
                        "AND (:heightTo IS NULL OR s.height <= :heightTo) " +
                        "AND (:weightFrom IS NULL OR s.weight >= :weightFrom) " +
                        "AND (:weightTo IS NULL OR s.weight <= :weightTo) " +
                        "AND (:email IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
                        "AND (:grantFrom IS NULL OR s.grantAmount >= :grantFrom) " +
                        "AND (:grantTo IS NULL OR s.grantAmount <= :grantTo) " +
                        "AND (:school IS NULL OR LOWER(s.school) LIKE LOWER(CONCAT('%', :school, '%')))", Person.class);
        query = query
                .setParameter("firstName", personCriteria.getFirstName())
                .setParameter("lastName", personCriteria.getLastName())
                .setParameter("pesel", personCriteria.getPesel())
                .setParameter("gender", personCriteria.getGender())
                .setParameter("heightFrom", personCriteria.getHeightFrom())
                .setParameter("heightTo", personCriteria.getHeightTo())
                .setParameter("weightFrom", personCriteria.getWeightFrom())
                .setParameter("weightTo", personCriteria.getWeightTo())
                .setParameter("email", personCriteria.getEmail())
                .setParameter("school", personCriteria.getSchool())
                .setParameter("grantFrom", personCriteria.getGrantFrom())
                .setParameter("grantTo", personCriteria.getGrantTo())
                .setFirstResult(personCriteria.getOffset());
        int limit = personCriteria.getLimit();
        if (limit != 0) {
            query = query.setMaxResults(limit);
        }
        return query.getResultList();
    }
}
