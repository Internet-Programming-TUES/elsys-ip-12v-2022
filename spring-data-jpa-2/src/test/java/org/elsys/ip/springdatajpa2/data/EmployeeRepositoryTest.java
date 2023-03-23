package org.elsys.ip.springdatajpa2.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeRepositoryTest {
    @Autowired
    private ContactRepository contactRepository;

    @BeforeEach
    void setUp() {
        Employee employee1 = new Employee("First name", "Last name", new EmployeeContactDetails("0987654321", ContactEntryType.PHONE));
        Employee employee2 = new Employee("Contact 2", "0987654321", new EmployeeContactDetails("email", ContactEntryType.EMAIL));
        Employee employee3 = new Employee("Contact 3", "Tri");
        Employee employee4 = new Employee("Contact 4", "Chetiri", new EmployeeContactDetails("0881234", ContactEntryType.PHONE), new EmployeeContactDetails("discord#123", ContactEntryType.DISCORD));

        contactRepository.save(employee1);
        contactRepository.save(employee2);
        contactRepository.save(employee3);
        contactRepository.save(employee4);
    }

    @Test
    void findByAny() {
        List<Employee> onlyFirst = contactRepository.findByAny("First");
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

        List<Employee> onlySecond = contactRepository.findByAny("email");
        assertThat(onlySecond).hasSize(1);
        assertThat(onlySecond.get(0).getFirstName()).isEqualTo("Contact 2");

        List<Employee> all = contactRepository.findByAny("0987");
        assertThat(all).hasSize(2);

        List<Employee> none = contactRepository.findByAny("non-existing-string");
        assertThat(none).hasSize(0);

        List<Employee> only3 = contactRepository.findByAny("tri");
        assertThat(only3).hasSize(1);
        assertThat(only3.get(0).getFirstName()).isEqualTo("Contact 3");

        List<Employee> only4 = contactRepository.findByAny("discord#123");
        assertThat(only4).hasSize(1);
        assertThat(only4.get(0).getFirstName()).isEqualTo("Contact 4");

        only4 = contactRepository.findByAny("123");
        assertThat(only4).hasSize(1);
        assertThat(only4.get(0).getFirstName()).isEqualTo("Contact 4");
    }
}