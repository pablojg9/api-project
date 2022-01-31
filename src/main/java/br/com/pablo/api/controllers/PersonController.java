package br.com.pablo.api.controllers;

import br.com.pablo.api.dtos.PersonDTO;
import br.com.pablo.api.entities.Person;
import br.com.pablo.api.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
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

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<List<PersonDTO>> findAll() {
        List<PersonDTO> listDTO = personService.findAll();

        listDTO.stream()
                .forEach(personDTO -> personDTO.add(
                        linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel()
                        )
                );

        return ResponseEntity.ok(listDTO);
    }

    @GetMapping(value = "/id/{id}", produces = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<PersonDTO> findById(@PathVariable("id") Long id) {
        PersonDTO personDTO = personService.findById(id);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(id)).withSelfRel());

        return ResponseEntity.ok(personDTO);
    }

    @PostMapping(value = "/create", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<PersonDTO> save(@RequestBody PersonDTO personDTO) {
        personService.save(personDTO);
        personDTO.add(linkTo(methodOn(PersonController.class).findById(personDTO.getKey())).withSelfRel());

        return ResponseEntity.ok().body(personDTO);
    }
    
    @PutMapping(value = "/update/{id}", produces = { "application/json", "application/xml", "application/x-yaml" },
            consumes = { "application/json", "application/xml", "application/x-yaml" })
    public ResponseEntity<PersonDTO> update(@PathVariable("id") Long id, @RequestBody Person person) {
        PersonDTO personDTO = personService.updateById(id, person);
        personDTO.add(linkTo(methodOn(PersonController.class).update(id, person)).withSelfRel());

        return ResponseEntity.ok(personDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        personService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public void deleteAll() {
        personService.deleteAll();
    }
}
