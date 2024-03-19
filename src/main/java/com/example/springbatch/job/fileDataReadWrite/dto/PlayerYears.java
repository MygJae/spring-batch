package com.example.springbatch.job.fileDataReadWrite.dto;

import lombok.Data;

import java.time.Year;

@Data
public class PlayerYears {
    private String id;
    private String lastName;
    private String firstName;
    private String position;
    private int birthYear;
    private int debutYear;
    private int yearsExperience;

    public PlayerYears(Player player) {
        this.id = player.getId();
        this.lastName = player.getLastName();
        this.firstName = player.getFirstName();
        this.position = player.getPosition();
        this.birthYear = player.getBirthYear();
        this.debutYear = player.getDebutYear();
        this.yearsExperience = Year.now().getValue() - player.getDebutYear();
    }


}

