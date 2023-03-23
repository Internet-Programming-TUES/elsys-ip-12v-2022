package org.elsys.ip.springdatajpa2.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends CrudRepository<Employee, Integer> {
    @Query("SELECT c FROM Employee c LEFT JOIN c.entries e WHERE " +
            "lower(c.firstName) like concat('%', lower(:search), '%') or " +
            "lower(c.lastName) like concat('%', lower(:search), '%') or " +
            "lower(e.data) like concat('%', lower(:search), '%')")
    List<Employee> findByAny(String search);
}
