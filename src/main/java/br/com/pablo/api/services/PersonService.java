package br.com.pablo.api.services;

import br.com.pablo.api.entities.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();

    public Person findById(Long id) {

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("pablo");
        person.setLastName("junior");
        person.setAddress("barueri");
        person.setGender("masculino");

        return person;
    }

    public List<Person> findAll() {
        List<Person> list = new ArrayList<>();

        for (int x = 0; x < 8; x++) {
            Person person = mockPerson(x);
            list.add(person);
        }
        return list;
    }

    private Person mockPerson(int x) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("pablo name" + x);
        person.setLastName("junior" + x);
        person.setAddress("barueri" + x);
        person.setGender("masculino" + x);

        return person;

    }

}
