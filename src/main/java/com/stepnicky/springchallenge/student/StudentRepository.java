package com.stepnicky.springchallenge.student;

import com.stepnicky.springchallenge.person.Person;
import com.stepnicky.springchallenge.person.PersonCriteria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT " +
            "s FROM Student s " +
            "WHERE (:#{#criteria.firstName} IS NULL OR LOWER(s.firstName) LIKE LOWER(CONCAT('%', :#{#criteria.firstName}, '%'))) " +
            "AND (:#{#criteria.lastName} IS NULL OR LOWER(s.lastName) LIKE LOWER(CONCAT('%', :#{#criteria.lastName}, '%'))) " +
            "AND (:#{#criteria.pesel} IS NULL OR s.pesel = :#{#criteria.pesel}) " +
            "AND (:#{#criteria.gender} IS NULL OR s.gender = :#{#criteria.gender}) " +
            "AND (:#{#criteria.heightFrom} IS NULL OR s.height >= :#{#criteria.heightFrom}) " +
            "AND (:#{#criteria.heightTo} IS NULL OR s.height <= :#{#criteria.heightTo}) " +
            "AND (:#{#criteria.weightFrom} IS NULL OR s.weight >= :#{#criteria.weightFrom}) " +
            "AND (:#{#criteria.weightTo} IS NULL OR s.weight <= :#{#criteria.weightTo}) " +
            "AND (:#{#criteria.email} IS NULL OR LOWER(s.email) LIKE LOWER(CONCAT('%', :#{#criteria.email}, '%'))) " +
            "AND (:#{#criteria.school} IS NULL OR LOWER(s.school) LIKE LOWER(CONCAT('%', :#{#criteria.school}, '%'))) " +
            "AND (:#{#criteria.grantFrom} IS NULL OR s.grantAmount >= :#{#criteria.grantFrom}) " +
            "AND (:#{#criteria.grantTo} IS NULL OR s.grantAmount <= :#{#criteria.grantTo}) ")
    public List<Person> getByCriteria(@Param("criteria") PersonCriteria personCriteria);
}
