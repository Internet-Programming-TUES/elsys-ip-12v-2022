package org.elsys.ip.springdatajpa2;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.transaction.Transactional;
import org.elsys.ip.springdatajpa2.data.ContactEntryType;
import org.elsys.ip.springdatajpa2.data.ContactRepository;
import org.elsys.ip.springdatajpa2.data.Employee;
import org.elsys.ip.springdatajpa2.data.EmployeeContactDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

@Component
@Profile("!test")
@Transactional
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
            List<EmployeeContactDetails> entries = new ArrayList<>();

            for (int i = 3; i < args.length; i += 2) {
                entries.add(new EmployeeContactDetails(args[i + 1], ContactEntryType.valueOf(args[i])));
            }
            Employee employee = new Employee(firstName, lastName, entries.toArray(new EmployeeContactDetails[0]));
            employee.getReports().addAll(
                    StreamSupport.stream(repository.findAll().spliterator(), false)
                            .filter(x -> x.getManager() == null)
                            .toList());

            employee.getFriends().addAll(
                    StreamSupport.stream(repository.findAll().spliterator(), false)
                            .filter(x -> x.getReports() == null || x.getReports().isEmpty())
                            .toList());

            repository.save(employee);

            System.out.println(employee);
            System.out.println("ADDED");
        }

        if (args[0].equals("find")) {
            String searchString = args[1];
            List<Employee> searchResult = repository.findByAny(searchString);
            for (Employee employee : searchResult) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                System.out.println(mapper.writeValueAsString(employee));
            }
        }
    }
}