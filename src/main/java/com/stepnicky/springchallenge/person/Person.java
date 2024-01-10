package com.stepnicky.springchallenge.person;

public interface Person {
    String getFirstName();
    String getLastName();
    String getPesel();
    int getHeight();
    int getWeight();
    String getEmail();
    String getGender();
    void setFirstName(String firstName);
    void setLastName(String lastName);
    void setPesel(String pesel);
    void setHeight(int height);
    void setWeight(int weight);
    void setEmail(String email);
    void setGender(String gender);
}
