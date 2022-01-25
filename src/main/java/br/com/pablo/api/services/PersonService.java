package br.com.pablo.api.services;

import br.com.pablo.api.converter.DozerConverter;
import br.com.pablo.api.dtos.PersonDTO;
import br.com.pablo.api.entities.Person;
import br.com.pablo.api.exceptions.ResourceNotFoundException;
import br.com.pablo.api.repositories.PersonRepository;
import br.com.pablo.api.utils.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    // FindAll
    public List<PersonDTO> findAll() {
        return DozerConverter.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    // DELETE ALL
    public void deleteAll() {
        personRepository.deleteAll();
    }

    public PersonDTO findById(Long id) {

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.MESSAGE_NOT_FOUND_ID));

        return DozerConverter.parseObject(entity, PersonDTO.class);
    }

    public void save(PersonDTO personDTO) {
        Person entity = DozerConverter.parseObject(personDTO, Person.class);
        DozerConverter.parseObject(personRepository.save(entity), PersonDTO.class);
    }

    public void deleteById(Long id) {

        personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.MESSAGE_NOT_FOUND_ID));
        personRepository.deleteById(id);
    }

    public PersonDTO updateById(Long id, Person person) {

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(MessageUtil.MESSAGE_NOT_FOUND_ID));

        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setAddress(person.getAddress());
        entity.setGender(person.getGender());

        return DozerConverter.parseObject(personRepository.save(entity), PersonDTO.class);
    }
}
