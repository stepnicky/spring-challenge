package com.stepnicky.springchallenge.pensioner;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PensionerDaoImpl implements PensionerDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Person> getByCriteria(PersonCriteria personCriteria) {
        TypedQuery<Person> query = entityManager.createQuery(
                "SELECT " +
                "p FROM Pensioner p " +
                "WHERE (:firstName IS NULL OR LOWER(p.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) " +
                "AND (:lastName IS NULL OR LOWER(p.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) " +
                "AND (:pesel IS NULL OR p.pesel = :pesel) " +
                "AND (:gender IS NULL OR p.gender = :gender) " +
                "AND (:heightFrom IS NULL OR p.height >= :heightFrom) " +
                "AND (:heightTo IS NULL OR p.height <= :heightTo) " +
                "AND (:weightFrom IS NULL OR p.weight >= :weightFrom) " +
                "AND (:weightTo IS NULL OR p.weight <= :weightTo) " +
                "AND (:email IS NULL OR LOWER(p.email) LIKE LOWER(CONCAT('%', :email, '%'))) " +
                "AND (:pensionFrom IS NULL OR p.pensionAmount >= :pensionFrom) " +
                "AND (:pensionTo IS NULL OR p.pensionAmount <= :pensionTo)", Person.class);
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
                    .setParameter("pensionFrom", personCriteria.getPensionFrom())
                    .setParameter("pensionTo", personCriteria.getPensionTo())
                    .setFirstResult(personCriteria.getOffset());
        int limit = personCriteria.getLimit();
        if (limit != 0) {
            query = query.setMaxResults(limit);
        }
        return query.getResultList();
    }
}
