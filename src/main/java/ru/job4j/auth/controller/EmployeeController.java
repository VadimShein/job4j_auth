package ru.job4j.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import ru.job4j.auth.model.Employee;
import ru.job4j.auth.model.Person;
import ru.job4j.auth.repository.EmployeeRepository;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private static final String API = "http://localhost:8080/person/";
    private static final String API_ID = "http://localhost:8080/person/{id}";
    private final EmployeeRepository employees;

    public EmployeeController(final EmployeeRepository employees) {
        this.employees = employees;
    }

    @Autowired
    private RestTemplate rest;

    @GetMapping("/")
    public Collection<Employee> findAll() {
        HashMap<Integer, Employee> rsl = new HashMap();
        List<Person> persons = rest.exchange(
                API, HttpMethod.GET, null, new ParameterizedTypeReference<List<Person>>() { }
        ).getBody();
        for (Person person : persons) {
            if (rsl.containsKey(person.getEmployeeId())) {
                rsl.get(person.getEmployeeId()).addAccount(person);
            } else {
                Employee emp = employees.findEmployeeById(person.getEmployeeId());
                emp.addAccount(person);
                rsl.put(emp.getId(), emp);
            }
        }
        return rsl.values();
    }

    @PostMapping("/")
    public ResponseEntity<Person> create(@RequestBody Person person) {
        Person rsl = rest.postForObject(API, person, Person.class);
        employees.findEmployeeById(person.getEmployeeId()).addAccount(person);
        return new ResponseEntity<>(rsl, HttpStatus.CREATED);
    }

    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Person person) {
        rest.put(API, person);
        employees.findEmployeeById(person.getEmployeeId()).addAccount(person);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        rest.delete(API_ID, id);
        return ResponseEntity.ok().build();
    }
}
