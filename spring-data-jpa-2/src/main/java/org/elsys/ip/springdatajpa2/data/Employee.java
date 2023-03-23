package org.elsys.ip.springdatajpa2.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID")
    private List<EmployeeContactDetails> entries;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    @JsonSerialize(using = EmployeeIdSerializer.class)
    private List<Employee> reports = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID")
    @JsonIgnore
    private Employee manager;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonSerialize(using = EmployeeIdSerializer.class)
    private List<Employee> friends = new ArrayList<>();

    public Employee(String firstName, String lastName, EmployeeContactDetails... entries) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.entries = Arrays.stream(entries).toList();
    }

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public List<EmployeeContactDetails> getEntries() {
        return entries;
    }

    public void setEntries(List<EmployeeContactDetails> entries) {
        this.entries = entries;
    }

    public List<Employee> getReports() {
        return reports;
    }

    public void setReports(List<Employee> reports) {
        this.reports = reports;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public List<Employee> getFriends() {
        return friends;
    }

    public void setFriends(List<Employee> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", entries=" + entries +
                '}';
    }
}
