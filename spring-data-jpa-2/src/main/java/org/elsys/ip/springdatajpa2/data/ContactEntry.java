package org.elsys.ip.springdatajpa2.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ContactEntry {
    @Id
    @GeneratedValue
    private int id;

    private String data;

    private ContactEntryType type;

    public ContactEntry(String data, ContactEntryType type) {
        this.data = data;
        this.type = type;
    }

    public ContactEntry() {
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
}

