package com.computacao.nuvem.peoplemicroservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Ratings {
    @Id
    @Column(name = "tconst")
    private String id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private Title title;
    private float averageRating;
    private int numVotes;
}
