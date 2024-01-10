package com.stepnicky.springchallenge.person;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PersonCriteria {

    @NotBlank
    private String type;
    private String firstName;
    private String lastName;
    private String pesel;
    private String gender;
    private Integer heightFrom;
    private Integer heightTo;
    private Integer weightFrom;
    private Integer weightTo;
    private String email;
    private Float salaryFrom;
    private Float salaryTo;
    private String position;
    private Float grantFrom;
    private Float grantTo;
    private String school;
    private Float pensionFrom;
    private Float pensionTo;
    private int offset;
    private int limit;
}
