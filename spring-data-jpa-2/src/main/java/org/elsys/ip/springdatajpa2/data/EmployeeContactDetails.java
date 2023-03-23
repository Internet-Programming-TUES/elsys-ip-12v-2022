package org.elsys.ip.springdatajpa2.data;

import jakarta.persistence.*;

@Entity
public class EmployeeContactDetails {
    @Id
    @GeneratedValue
    private int id;

    private String data;

    private ContactEntryType type;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "EMPLOYEE_ID")
//    private Employee employee;

    public EmployeeContactDetails(String data, ContactEntryType type) {
        this.data = data;
        this.type = type;
    }

    public EmployeeContactDetails() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public ContactEntryType getType() {
        return type;
    }

    public void setType(ContactEntryType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "ContactEntry{" +
                "data='" + data + '\'' +
                ", type=" + type +
                '}';
    }

//    public Employee getContact() {
//        return employee;
//    }
//
//    public void setContact(Employee employee) {
//        this.employee = employee;
//    }
}

