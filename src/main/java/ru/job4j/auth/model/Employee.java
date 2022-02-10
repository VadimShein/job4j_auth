package ru.job4j.auth.model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.*;

@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String surname;
    private int inn;
    @Column(name = "hire_date")
    private Timestamp hireDate;
    @Transient
    private HashMap<Integer, Person> accounts = new HashMap<>();

    public Employee() {
    }

    public Employee(String name, String surname, int inn) {
        this.name = name;
        this.surname = surname;
        this.inn = inn;
        this.hireDate = new Timestamp(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getInn() {
        return inn;
    }

    public void setInn(int inn) {
        this.inn = inn;
    }

    public Timestamp getHireDate() {
        return hireDate;
    }

    public void setHireDate(Timestamp hireDate) {
        this.hireDate = hireDate;
    }

    public HashMap<Integer, Person> getAccounts() {
        return accounts;
    }

    public void setAccounts(HashMap<Integer, Person> accounts) {
        this.accounts = accounts;
    }

    public void addAccount(Person person) {
        accounts.put(person.getId(), person);
    }

    public void deleteAccount(int id) {
        accounts.remove(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Employee employee = (Employee) o;
        return id == employee.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
