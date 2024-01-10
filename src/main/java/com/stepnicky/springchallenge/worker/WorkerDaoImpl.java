package com.stepnicky.springchallenge.worker;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WorkerDaoImpl implements WorkerDao {

    private final EntityManager entityManager;

    public WorkerDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Person> getByCriteria(PersonCriteria personCriteria) {
        TypedQuery<Person> query = entityManager.createQuery(
                "SELECT " +
                        "w FROM Worker w " +
                        "WHERE (:firstName IS NULL OR LOWER(w.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
                        "AND (:lastName IS NULL OR LOWER(w.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
                        "AND (:pesel IS NULL OR w.pesel = :pesel) " +
                        "AND (:gender IS NULL OR w.gender = :gender) " +
                        "AND (:heightFrom IS NULL OR w.height >= :heightFrom) " +
                        "AND (:heightTo IS NULL OR w.height <= :heightTo) " +
                        "AND (:weightFrom IS NULL OR w.weight >= :weightFrom) " +
                        "AND (:weightTo IS NULL OR w.weight <= :weightTo) " +
                        "AND (:email IS NULL OR LOWER(w.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
                        "AND (:salaryFrom IS NULL OR w.salary >= :salaryFrom) " +
                        "AND (:salaryTo IS NULL OR w.salary <= :salaryTo) " +
                        "AND (:position IS NULL OR LOWER(w.position) LIKE LOWER(CONCAT('%', :position, '%')))", Person.class);
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
                .setParameter("position", personCriteria.getPosition())
                .setParameter("salaryFrom", personCriteria.getSalaryFrom())
                .setParameter("salaryTo", personCriteria.getSalaryTo())
                .setFirstResult(personCriteria.getOffset());
        int limit = personCriteria.getLimit();
        if (limit != 0) {
            query = query.setMaxResults(limit);
        }
        return query.getResultList();
    }
}
