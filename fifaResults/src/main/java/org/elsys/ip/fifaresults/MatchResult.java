package org.elsys.ip.fifaresults;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MatchResult {
    //MatchNumber	:	46
    //RoundNumber	:	3
    //DateUtc	:	2022-12-02 15:00:00Z
    //Location	:	Education City Stadium
    //HomeTeam	:	Korea Republic
    //AwayTeam	:	Portugal
    //Group	:	Group H
    //HomeTeamScore	:	2
    //AwayTeamScore	:	1
    @JsonProperty("DateUtc")
    private String date;

    @JsonProperty("HomeTeam")
    private String homeTeam;

    @JsonProperty("AwayTeam")
    private String awayTeam;

    @JsonProperty("Group")
    private String group;

    @JsonProperty("HomeTeamScore")
    private int homeTeamScore;

    @JsonProperty("AwayTeamScore")
    private int awayTeamScore;

    public String getDate() {
        return date;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public String getGroup() {
        return group;
    }

    public int getHomeTeamScore() {
        return homeTeamScore;
    }

    public int getAwayTeamScore() {
        return awayTeamScore;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public void setHomeTeamScore(int homeTeamScore) {
        this.homeTeamScore = homeTeamScore;
    }

    public void setAwayTeamScore(int awayTeamScore) {
        this.awayTeamScore = awayTeamScore;
    }
}
