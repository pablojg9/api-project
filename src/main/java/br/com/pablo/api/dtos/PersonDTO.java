package br.com.pablo.api.dtos;

import br.com.pablo.api.entities.Person;

public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;

    public PersonDTO(){}

    public PersonDTO(Person person) {
        id = person.getId();
        firstName = person.getFirstName();
        lastName = person.getLastName();
        address = person.getAddress();
        gender = person.getGender();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
