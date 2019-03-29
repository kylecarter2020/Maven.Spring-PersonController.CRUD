package io.zipcoder.crudapp.controllers;

import io.zipcoder.crudapp.models.Person;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    private List<Person> people;

    public PersonController() {
        this.people = new ArrayList<>();
    }

    @PostMapping(value = "/people")
    ResponseEntity<Person> createPerson(@RequestBody Person p){
        this.people.add(p.getId(), p);
        return new ResponseEntity<>(p, HttpStatus.CREATED);
    }

    @GetMapping(value = "/people/{id}")
    ResponseEntity<Person> getPerson(@PathVariable Integer id){
        return new ResponseEntity<>(this.people.get(id), HttpStatus.OK);
    }

    @GetMapping(value = "/people")
    ResponseEntity<List<Person>> getPersonList(){
        return new ResponseEntity<>(this.people, HttpStatus.OK);
    }

    @PutMapping(value = "/people/{id}")
    ResponseEntity<Person> updatePerson(@PathVariable Integer id, @RequestBody Person p){
        this.people.add(id, p);
        return new ResponseEntity<>(p, HttpStatus.OK);
    }

    @DeleteMapping(value = "/people/{id}")
    ResponseEntity<Boolean> deletePerson(@PathVariable Integer id){
        return new ResponseEntity<>(this.people.remove(id),HttpStatus.OK) ;
    }
}
