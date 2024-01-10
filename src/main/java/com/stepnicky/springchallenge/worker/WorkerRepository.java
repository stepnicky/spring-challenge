package com.stepnicky.springchallenge.worker;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {

    @Query("SELECT " +
            "w FROM Worker w " +
            "WHERE (:#{#criteria.firstName} IS NULL OR LOWER(w.firstName) LIKE LOWER(CONCAT('%', :#{#criteria.firstName}, '%'))) " +
            "AND (:#{#criteria.lastName} IS NULL OR LOWER(w.lastName) LIKE LOWER(CONCAT('%', :#{#criteria.lastName}, '%'))) " +
            "AND (:#{#criteria.pesel} IS NULL OR w.pesel = :#{#criteria.pesel}) " +
            "AND (:#{#criteria.gender} IS NULL OR w.gender = :#{#criteria.gender}) " +
            "AND (:#{#criteria.heightFrom} IS NULL OR w.height >= :#{#criteria.heightFrom}) " +
            "AND (:#{#criteria.heightTo} IS NULL OR w.height <= :#{#criteria.heightTo}) " +
            "AND (:#{#criteria.weightFrom} IS NULL OR w.weight >= :#{#criteria.weightFrom}) " +
            "AND (:#{#criteria.weightTo} IS NULL OR w.weight <= :#{#criteria.weightTo}) " +
            "AND (:#{#criteria.email} IS NULL OR LOWER(w.email) LIKE LOWER(CONCAT('%', :#{#criteria.email}, '%'))) " +
            "AND (:#{#criteria.position} IS NULL OR LOWER(w.position) LIKE LOWER(CONCAT('%', :#{#criteria.position}, '%'))) " +
            "AND (:#{#criteria.salaryFrom} IS NULL OR w.salary >= :#{#criteria.salaryFrom}) " +
            "AND (:#{#criteria.salaryTo} IS NULL OR w.salary <= :#{#criteria.salaryTo}) ")
    public List<Person> getByCriteria(@Param("criteria") PersonCriteria personCriteria);
}
