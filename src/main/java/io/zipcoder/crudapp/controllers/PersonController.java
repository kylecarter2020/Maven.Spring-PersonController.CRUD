package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import io.zipcoder.crudapp.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping(value = "/people/")
    public ResponseEntity<Person> createPerson(@RequestBody Person p){
        personRepository.save(p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping(value = "/people/{id}")
    public ResponseEntity<Person> getPerson(@PathVariable Integer id){
        return new ResponseEntity<>(personRepository.findOne(id), HttpStatus.OK);
    }

    @GetMapping(value = "/people/")
    public ResponseEntity<List<Person>> getPersonList(){
        return new ResponseEntity<>((List<Person>) personRepository.findAll(), HttpStatus.OK);
    }

    @PutMapping(value = "/people/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person p){
        Person originalPerson = personRepository.findOne(id);
        originalPerson.setFirstName(p.getFirstName());
        originalPerson.setLastName(p.getLastName());
        personRepository.save(originalPerson);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping(value = "/people/{id}")
    public ResponseEntity<Boolean> deletePerson(@PathVariable Integer id){
        personRepository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK) ;
    }
}
