package br.com.pablo.api.dtos;

import br.com.pablo.api.entities.Person;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;
import org.springframework.hateoas.RepresentationModel;

import java.util.Objects;

@JsonPropertyOrder({"id", "firstName", "lastName", "gender", "address"})
public class PersonDTO extends RepresentationModel<PersonDTO> {

    @Mapping("id")
    private Long key;
    private String firstName;
    private String lastName;
    private String address;

    //@JsonIgnore
    private String gender;

    public PersonDTO(){}

    public PersonDTO(Long key, String firstName, String lastName, String address, String gender) {
        this.key = key;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.gender = gender;
    }

    public PersonDTO(Person person) {
        key = person.getId();
        firstName = person.getFirstName();
        lastName = person.getLastName();
        address = person.getAddress();
        gender = person.getGender();
    }

    public Long getKey() {
        return key;
    }

    public void setKey(Long key) {
        this.key = key;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return Objects.equals(key, personDTO.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), key);
    }
}
