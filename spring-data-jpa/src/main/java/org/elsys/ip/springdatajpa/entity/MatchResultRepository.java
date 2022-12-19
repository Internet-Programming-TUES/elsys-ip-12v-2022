package org.elsys.ip.springdatajpa.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchResultRepository extends JpaRepository<MatchResult, Integer> {

    List<MatchResult> findAllByHomeTeam(Team homeTeam);
}
