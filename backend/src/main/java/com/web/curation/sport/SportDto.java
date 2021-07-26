package com.web.curation.sport;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Sport")
public class SportDto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sportId;

    @Column(name = "sport_name")
    private String name;
}
