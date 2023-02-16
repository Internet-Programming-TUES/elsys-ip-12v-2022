package org.elsys.ip.springdatajpa2;

import org.elsys.ip.springdatajpa2.data.Contact;
import org.elsys.ip.springdatajpa2.data.ContactEntry;
import org.elsys.ip.springdatajpa2.data.ContactEntryType;
import org.elsys.ip.springdatajpa2.data.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private ContactRepository repository;

    /*
    add firstName lastName [type data]*
    find string
     */

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 2) {
            throw new IllegalArgumentException("Arguments must be more than 2");
        }

        if (args[0].equals("add")) {
            if (args.length < 3) {
                throw new IllegalArgumentException("Arguments must be more than 3");
            }

            String firstName = args[1];
            String lastName = args[2];
            List<ContactEntry> entries = new ArrayList<>();

            for (int i = 3; i < args.length; i+=2) {
                entries.add(new ContactEntry(args[i+1], ContactEntryType.valueOf(args[i])));
            }
            Contact contact = new Contact(firstName, lastName, entries);
            repository.save(contact);

            System.out.println(contact);
            System.out.println("ADDED");
        }

        if (args[0].equals("find")) {
            String searchString = args[1];
            List<Contact> searchResult = repository.findAllByFirstNameContainsIgnoreCase(searchString);
            for (Contact contact : searchResult) {
                System.out.println(contact);
            }
        }
    }
}