package org.elsys.ip.springdatajpa2;

import org.elsys.ip.springdatajpa2.data.Contact;
import org.elsys.ip.springdatajpa2.data.ContactEntry;
import org.elsys.ip.springdatajpa2.data.ContactEntryType;
import org.elsys.ip.springdatajpa2.data.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommandLineAppStartupRunner implements CommandLineRunner {
    @Autowired
    private ContactRepository repository;

    @Override
    public void run(String... args) throws Exception {
        if (args.length < 2) {
            System.out.println("Arguments must be more than 2");
            return;
        }

        String firstName = args[0];
        String lastName = args[1];
        List<ContactEntry> entries = Arrays.stream(args).skip(2)
                .map(x -> new ContactEntry(x, ContactEntryType.PHONE))
                .collect(Collectors.toList());
        Contact contact = new Contact(firstName, lastName, entries);
        repository.save(contact);
    }
}