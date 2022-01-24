package br.com.pablo.api.controllers;

import br.com.pablo.api.dtos.PersonDTO;
import br.com.pablo.api.entities.Person;
import br.com.pablo.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PersonDTO>> findAll() {

        List<Person> list = personService.findAll();
        List<PersonDTO> listDTO = list.stream().map(PersonDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @GetMapping(value = "/id/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {

        Person find = personService.findById(id);
        PersonDTO findDTO = new PersonDTO(find);

        return ResponseEntity.ok().body(findDTO);
    }

    @PostMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> createPerson(@RequestBody Person person) {

        Person personOBJ = personService.save(person);

        PersonDTO personDTO = new PersonDTO(personOBJ);

        return ResponseEntity.ok().body(personDTO);
    }

    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable Long id, @RequestBody Person person) {

        Person personOBJ = personService.updatePerson(person);

        PersonDTO personDTO = new PersonDTO(personOBJ);

        return ResponseEntity.ok().body(personDTO);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable Long id) {
        personService.deleteById(id);
    }

    @DeleteMapping(value = "/delete")
    public void deleteAll() {
        personService.deleteAll();
    }

}
