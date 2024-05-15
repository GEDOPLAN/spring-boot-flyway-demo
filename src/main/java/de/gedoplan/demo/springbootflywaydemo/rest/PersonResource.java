package de.gedoplan.demo.springbootflywaydemo.rest;

import de.gedoplan.demo.springbootflywaydemo.domain.Person;
import de.gedoplan.demo.springbootflywaydemo.repository.PersonRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/person", produces = MediaType.APPLICATION_JSON_VALUE)
public class PersonResource {

    private final PersonRepository personRepository;

    public PersonResource(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping()
    public List<Person> getAll() {
        return this.personRepository.findAll();
    }

    @GetMapping("/{id}")
    public Person get(@PathParam("id") Integer id) {
        return this.personRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person insert(@RequestBody Person person) {
        person.setId(null);
        return this.personRepository.save(person);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Person update(@PathParam("id") Integer id, @RequestBody Person person) {
        if (!Objects.equals(id, person.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (!this.personRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        return this.personRepository.save(person);
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathParam("id") Integer id) {
        if (!this.personRepository.existsById(id)) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        this.personRepository.deleteById(id);
    }

}
