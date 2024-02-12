package com.example.automapping_lab.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "first_name", nullable = false)
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(nullable = false)
    private BigDecimal salary;
    @Column(nullable = false)
    private LocalDate birthday;
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    private Address address;
    @ManyToOne(cascade = CascadeType.ALL)
    private Employee manager;


    public Employee() {
    }

    public Employee(String firstName, String lastName, BigDecimal salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, Address address) {
        this(firstName, lastName, salary);
        this.birthday = birthday;
        this.address = address;
    }

    public Employee(String firstName, String lastName, BigDecimal salary, LocalDate birthday, Address address, Employee manager) {
        this(firstName, lastName, salary, birthday, address);
        this.manager = manager;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


    @Override
    public String toString() {
        return String.format("FirstName: %s, LastName %s\n Salary: %.2f, Birthdate: %s\n Address: (%s) Manager: [%s]",
                this.firstName,
                this.lastName,
                this.salary,
                this.birthday,
                this.address,
                this.manager == null ? "no manager" : this.manager.getLastName());
    }
}
