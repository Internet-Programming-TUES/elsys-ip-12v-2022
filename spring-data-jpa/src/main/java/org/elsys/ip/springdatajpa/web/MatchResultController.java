package org.elsys.ip.springdatajpa.web;

import jakarta.annotation.PostConstruct;
import org.elsys.ip.springdatajpa.entity.MatchResult;
import org.elsys.ip.springdatajpa.entity.MatchResultRepository;
import org.elsys.ip.springdatajpa.entity.Team;
import org.elsys.ip.springdatajpa.entity.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class MatchResultController {
    @Autowired
    private MatchResultRepository repository;

    @Autowired
    private TeamRepository teams;

    @GetMapping(path = "results")
    public String list(@RequestParam(required = false) String home,
                       @RequestParam(required = false) Integer homeScore,
                       @RequestParam(required = false) String away,
                       @RequestParam(required = false) Integer awayScore,
                       Model model) {
        if (home != null && homeScore != null && away != null && awayScore != null) {
            MatchResult entity = new MatchResult();

            Team homeTeam;
            Optional<Team> homeTeamOptional = teams.findByName(home);
            if (homeTeamOptional.isPresent()) {
                homeTeam = homeTeamOptional.get();
            } else {
                homeTeam = new Team();
                homeTeam.setName(home);
            }

            entity.setHomeTeam(homeTeam);
            Team awayTeam;
            Optional<Team> awayTeamOptional = teams.findByName(away);
            if (awayTeamOptional.isPresent()) {
                awayTeam = awayTeamOptional.get();
            } else {
                awayTeam = new Team();
                awayTeam.setName(away);
            }

            entity.setAwayTeam(awayTeam);
            entity.setHomeScore(homeScore);
            entity.setAwayScore(awayScore);

            repository.save(entity);
        }
        model.addAttribute("matchResults", repository.findAll());

        return "results";
    }

}
