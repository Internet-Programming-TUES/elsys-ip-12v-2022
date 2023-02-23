package org.elsys.ip.springdatajpa2.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ContactRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        Contact contact1 = new Contact("First name", "Last name", new ContactEntry("0987654321", ContactEntryType.PHONE));
        Contact contact2 = new Contact("Contact 2", "0987654321", new ContactEntry("email", ContactEntryType.EMAIL));
        Contact contact3 = new Contact("Contact 3", "Tri");
        Contact contact4 = new Contact("Contact 4", "Chetiri", new ContactEntry("0881234", ContactEntryType.PHONE), new ContactEntry("discord#123", ContactEntryType.DISCORD));

        contactRepository.save(contact1);
        contactRepository.save(contact2);
        contactRepository.save(contact3);
        contactRepository.save(contact4);
    }

    @Test
    void findByAny() {
        List<Contact> onlyFirst = contactRepository.findByAny("First");
        assertThat(onlyFirst).hasSize(1);
        assertThat(onlyFirst.get(0).getFirstName()).isEqualTo("First name");

        onlyFirst = contactRepository.findByAny("Last");
        assertThat(onlyFirst).hasSize(1);
        assertThat(onlyFirst.get(0).getFirstName()).isEqualTo("First name");

        onlyFirst = contactRepository.findByAny("last");
        assertThat(onlyFirst).hasSize(1);
        assertThat(onlyFirst.get(0).getFirstName()).isEqualTo("First name");

        onlyFirst = contactRepository.findByAny("st n");
        assertThat(onlyFirst).hasSize(1);
        assertThat(onlyFirst.get(0).getFirstName()).isEqualTo("First name");

        List<Contact> onlySecond = contactRepository.findByAny("email");
        assertThat(onlySecond).hasSize(1);
        assertThat(onlySecond.get(0).getFirstName()).isEqualTo("Contact 2");

        List<Contact> all = contactRepository.findByAny("0987");
        assertThat(all).hasSize(2);

        List<Contact> none = contactRepository.findByAny("non-existing-string");
        assertThat(none).hasSize(0);

        List<Contact> only3 = contactRepository.findByAny("tri");
        assertThat(only3).hasSize(1);
        assertThat(only3.get(0).getFirstName()).isEqualTo("Contact 3");

        List<Contact> only4 = contactRepository.findByAny("discord#123");
        assertThat(only4).hasSize(1);
        assertThat(only4.get(0).getFirstName()).isEqualTo("Contact 4");

        only4 = contactRepository.findByAny("123");
        assertThat(only4).hasSize(1);
        assertThat(only4.get(0).getFirstName()).isEqualTo("Contact 4");
    }
}