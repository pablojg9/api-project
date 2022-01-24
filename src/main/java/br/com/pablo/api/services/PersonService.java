package br.com.pablo.api.services;

import br.com.pablo.api.entities.Person;
import br.com.pablo.api.exceptions.ResourceNotFoundException;
import br.com.pablo.api.repositories.PersonRepository;
import br.com.pablo.api.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // FindALl
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    // DELETE ALL
    public void deleteAll() {
        personRepository.deleteAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.MESSAGE_NOT_FOUND_ID));
    }

    public Person save(Person person) {
        return personRepository.save(person);
    }

    public void deleteById(Long id) {

        personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.MESSAGE_NOT_FOUND_ID));
        personRepository.deleteById(id);
    }

    public Person updatePerson(Person person) {

        Person entity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.MESSAGE_NOT_FOUND_ID));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return personRepository.save(entity);
    }
}
