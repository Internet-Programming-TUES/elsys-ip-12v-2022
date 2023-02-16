package org.elsys.ip.springdatajpa2.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Contact {
    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String lastName;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "CONTACT_ID")
    private List<ContactEntry> entries;

    public Contact(String firstName, String lastName, List<ContactEntry> entries) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.entries = entries;
    }

    public Contact() {
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

    public List<ContactEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<ContactEntry> entries) {
        this.entries = entries;
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
