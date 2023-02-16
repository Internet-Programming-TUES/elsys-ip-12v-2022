package org.elsys.ip.springdatajpa2.data;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer> {
    List<Contact> findAllByFirstNameContainsIgnoreCase(String firstName);
}
